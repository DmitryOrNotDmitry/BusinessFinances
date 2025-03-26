package ru.dmytrium.main.lab4.req2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Agent;

import java.util.List;

@Repository
public interface TopAgentRepository extends JpaRepository<Agent, Long> {
    @Query(value = "SELECT * FROM get_top_agents_by_transaction_volume(:businessId)", nativeQuery = true)
    List<Object[]> getTopAgentsByBusiness(@Param("businessId") Long businessId);
}

