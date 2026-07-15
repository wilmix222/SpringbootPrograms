package client;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptRepository repo;

    public ReceiptController(ReceiptRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{receiptNo}")
    public Receipt getReceipt(@PathVariable String receiptNo) {
        return repo.findByReceiptNo(receiptNo)
                .orElseThrow(() -> new RuntimeException("Receipt not found: " + receiptNo));
    }

    @GetMapping
    public List<Receipt> getReceipts(@RequestParam(value = "studentId", required = false) String studentId) {
        if (studentId != null && !studentId.trim().isEmpty()) {
            return repo.findByStudentId(studentId);
        }
        return repo.findAll();
    }
}