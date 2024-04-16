package test.task.romoshi.javacode.service;

import test.task.romoshi.javacode.entity.Wallet;
import test.task.romoshi.javacode.model.WalletRequest;

public interface WalletService {

    Wallet getWalletById(String walletId);

    void updateWalletAmount(WalletRequest request);
}
