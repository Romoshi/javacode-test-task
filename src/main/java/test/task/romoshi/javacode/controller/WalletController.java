package test.task.romoshi.javacode.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.task.romoshi.javacode.entity.Wallet;
import test.task.romoshi.javacode.entity.WalletRequest;
import test.task.romoshi.javacode.service.WalletService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("wallets/{WALLET_UUID}")
    public ResponseEntity<Wallet> getWallet(@PathVariable("WALLET_UUID") String walletId) {
        Wallet wallet = walletService.getWalletById(walletId);
        return ResponseEntity.status(HttpStatus.OK).body(wallet);
    }

    @PostMapping("wallet")
    public ResponseEntity<Wallet> operationWithWallet(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        WalletRequest request = objectMapper.readValue(json, WalletRequest.class);
        walletService.updateWalletAmount(request);
        return ResponseEntity.status(HttpStatus.OK).body(walletService.getWalletById(request.getWalletId()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgumentException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Недостаточно средств на кошельке."));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Такой кошелек не существует."));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ProblemDetail> handleJsonProcessingException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "JSON невалиден."));
    }
}
