package client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "studentClient", url = "${student.service.url}")
public interface StudentClient {

    @GetMapping("/students/{studentId}")
    StudentDTO getStudent(@PathVariable("studentId") String studentId);
}