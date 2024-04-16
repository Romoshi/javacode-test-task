package test.task.romoshi.javacode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequest {

    @NotBlank(message = "ID кошелька не должен быть пустым")
    private String walletId;

    @NotNull(message = "Тип операции должен быть задан")
    private OperationType operationType;

    @Positive(message = "Сумма должна быть положительной")
    private double amount;
}
