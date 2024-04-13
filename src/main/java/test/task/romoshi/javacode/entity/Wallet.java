package test.task.romoshi.javacode.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private double c_amount;
}
