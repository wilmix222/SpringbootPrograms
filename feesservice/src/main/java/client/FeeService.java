package client;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FeeService {

    private final ReceiptRepository receiptRepository;
    private final StudentClient studentClient;

    // simple in-memory counter for demo; for real systems use DB sequence or Redis
    private static final AtomicLong COUNTER = new AtomicLong(0);

    public FeeService(ReceiptRepository receiptRepository, StudentClient studentClient) {
        this.receiptRepository = receiptRepository;
        this.studentClient = studentClient;
    }

    public Receipt collectFee(CollectFeeRequest req) {
        StudentDTO student = studentClient.getStudent(req.getStudentId());

        Receipt r = new Receipt();
        r.setReceiptNo(generateReceiptNo());
        r.setReceiptDate(LocalDateTime.now());

        // snapshot student details
        r.setStudentId(student.getStudentId());
        r.setStudentName(student.getStudentName());
        r.setGrade(student.getGrade());
        r.setMobileNumber(student.getMobileNumber());
        r.setSchoolName(student.getSchoolName());

        // payment details
        r.setAcademicYear(req.getAcademicYear());
        r.setTerm(req.getTerm());
        r.setFeeType(req.getFeeType());
        r.setAmountPaid(req.getAmountPaid());
        r.setCurrency("INR");
        r.setPaymentMode(req.getPaymentMode());
        r.setTransactionRef(req.getTransactionRef());
        r.setRemarks(req.getRemarks());
        r.setCollectedBy(req.getCollectedBy());

        r.setStatus("SUCCESS");

        return receiptRepository.save(r);
    }

    private String generateReceiptNo() {
        long seq = COUNTER.incrementAndGet();
        // Example: RCP-2026-000001
        int year = LocalDateTime.now().getYear();
        return String.format("RCP-%d-%06d", year, seq);
    }
}