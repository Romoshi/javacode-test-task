package test.task.romoshi.javacode.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.romoshi.javacode.entity.OperationType;
import test.task.romoshi.javacode.entity.Wallet;
import test.task.romoshi.javacode.entity.WalletRequest;
import test.task.romoshi.javacode.repository.WalletRepository;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet getWalletById(String walletId) {
        return walletRepository.findById(walletId).orElseThrow();
    }

    public void updateWalletAmount(WalletRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId()).orElseThrow();

        if(request.getOperationType() == OperationType.DEPOSIT) {
            wallet.setC_amount(wallet.getC_amount() + request.getAmount());
        }
        if(request.getOperationType() == OperationType.WITHDRAW) {
            double withdrawDif = wallet.getC_amount() - request.getAmount();
            if(withdrawDif <= 0) {
                throw new IllegalArgumentException();
            } else {
                wallet.setC_amount(withdrawDif);
            }
        }
        walletRepository.save(wallet);
    }
}
