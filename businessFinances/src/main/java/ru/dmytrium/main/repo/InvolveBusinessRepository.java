package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.InvolveBusiness;
import ru.dmytrium.main.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvolveBusinessRepository extends JpaRepository<InvolveBusiness, Long> {
    List<InvolveBusiness> findAllByBusiness(Business business);
    Optional<InvolveBusiness> findByBusinessAndUser(Business business, User user);
}
