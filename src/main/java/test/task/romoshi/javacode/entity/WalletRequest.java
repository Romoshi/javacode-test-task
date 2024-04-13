package test.task.romoshi.javacode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequest {

    private String walletId;

    private OperationType operationType;

    private double amount;
}
