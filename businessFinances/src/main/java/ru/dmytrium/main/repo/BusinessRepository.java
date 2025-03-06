package ru.dmytrium.main.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.User;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    Business findByBusinessName(String businessName);
    List<Business> findAllByAuthor(User author);
    List<Business> findAllByAuthor_UserId(Long userId);
}

