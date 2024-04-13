package test.task.romoshi.javacode.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.task.romoshi.javacode.entity.Wallet;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, String> {
}
