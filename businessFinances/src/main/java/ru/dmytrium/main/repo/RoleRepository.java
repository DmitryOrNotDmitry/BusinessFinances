package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

