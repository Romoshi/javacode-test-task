package test.task.romoshi.javacode.service;

import com.google.common.util.concurrent.AtomicDouble;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.romoshi.javacode.entity.OperationType;
import test.task.romoshi.javacode.entity.Wallet;
import test.task.romoshi.javacode.entity.WalletRequest;
import test.task.romoshi.javacode.repository.WalletRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet getWalletById(String walletId) {
        return walletRepository.findById(walletId).orElseThrow(NoSuchElementException::new);
    }

    public void updateWalletAmount(WalletRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId()).orElseThrow(NoSuchElementException::new);
        AtomicDouble atomicAmount = new AtomicDouble(wallet.getC_amount());
        double currentAmount;
        double newAmount;

        if(request.getOperationType() == OperationType.DEPOSIT) {
            do {
                currentAmount = atomicAmount.get();
                newAmount = currentAmount + request.getAmount();
            } while (!atomicAmount.compareAndSet(currentAmount, newAmount));
        }

        if(request.getOperationType() == OperationType.WITHDRAW) {
            do {
                currentAmount = atomicAmount.get();
                newAmount = currentAmount - request.getAmount();
                if(newAmount <= 0) {
                    throw new IllegalArgumentException();
                } else {
                    wallet.setC_amount(newAmount);
                }
            } while (!atomicAmount.compareAndSet(currentAmount, newAmount));
        }
        walletRepository.save(wallet);
    }
}
