package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
