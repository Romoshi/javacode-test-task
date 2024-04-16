package test.task.romoshi.javacode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.task.romoshi.javacode.dto.entity.Wallet;
import test.task.romoshi.javacode.dto.WalletRequest;
import test.task.romoshi.javacode.service.DefaultWalletService;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class WalletController {

    private final DefaultWalletService walletService;

    @GetMapping("wallets/{WALLET_UUID}")
    public ResponseEntity<Wallet> getWallet(@PathVariable("WALLET_UUID") String walletId) {
        return ResponseEntity.status(HttpStatus.OK).body(walletService.getWalletById(walletId));
    }

    @PostMapping("wallet")
    public ResponseEntity<Wallet> operationWithWallet(@Valid @RequestBody WalletRequest request) {
        walletService.updateWalletAmount(request);
        return ResponseEntity.status(HttpStatus.OK).body(walletService.getWalletById(request.getWalletId()));
    }
}
