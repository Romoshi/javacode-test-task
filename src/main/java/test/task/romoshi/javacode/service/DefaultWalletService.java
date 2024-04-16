package test.task.romoshi.javacode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import test.task.romoshi.javacode.dto.OperationType;
import test.task.romoshi.javacode.dto.WalletRequest;
import test.task.romoshi.javacode.dto.entity.Wallet;
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

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void updateWalletAmount(WalletRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId()).orElseThrow(NoSuchElementException::new);
        double currentAmount = wallet.getAmount();
        double addAmount = request.getAmount();

        if(request.getOperationType() == OperationType.DEPOSIT) {
            wallet.setAmount(currentAmount + addAmount);
        }
        if(request.getOperationType() == OperationType.WITHDRAW) {
            currentAmount = currentAmount - addAmount;
            if(currentAmount <= 0) {
                throw new IllegalArgumentException();
            } else {
                wallet.setAmount(currentAmount);
            }
        }
        walletRepository.save(wallet);
    }
}
