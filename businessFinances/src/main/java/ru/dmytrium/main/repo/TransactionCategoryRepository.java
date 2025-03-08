package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Account;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.TransactionCategory;

import java.util.List;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Long> {
    List<TransactionCategory> findAllByForBusiness(Business forBusiness);
}

