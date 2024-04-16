package test.task.romoshi.javacode.service;

import test.task.romoshi.javacode.dto.entity.Wallet;
import test.task.romoshi.javacode.dto.WalletRequest;

public interface WalletService {

    Wallet getWalletById(String walletId);

    void updateWalletAmount(WalletRequest request);
}
