package client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/fees")
public class FeeController {

    private final FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @PostMapping("/collect")
    @ResponseStatus(HttpStatus.CREATED)
    public Receipt collect(@Valid @RequestBody CollectFeeRequest req) {
        return feeService.collectFee(req);
    }
}