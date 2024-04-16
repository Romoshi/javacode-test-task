package test.task.romoshi.javacode.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import test.task.romoshi.javacode.dto.entity.Wallet;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Wallet> findById(String id);
}
