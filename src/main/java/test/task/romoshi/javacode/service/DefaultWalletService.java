package test.task.romoshi.javacode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.romoshi.javacode.model.OperationType;
import test.task.romoshi.javacode.entity.Wallet;
import test.task.romoshi.javacode.model.WalletRequest;
import test.task.romoshi.javacode.repository.WalletRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DefaultWalletService implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Wallet getWalletById(String walletId) {
        return walletRepository.findById(walletId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void updateWalletAmount(WalletRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId()).orElseThrow(NoSuchElementException::new);
        double newAmount;

        if(request.getOperationType() == OperationType.DEPOSIT) {
            newAmount = wallet.getAmount() + request.getAmount();
            wallet.setAmount(newAmount);
        }
        if(request.getOperationType() == OperationType.WITHDRAW) {
            newAmount = wallet.getAmount() - request.getAmount();
            if(newAmount <= 0) {
                throw new IllegalArgumentException();
            } else {
                wallet.setAmount(newAmount);
            }
        }
        walletRepository.save(wallet);
    }
}
