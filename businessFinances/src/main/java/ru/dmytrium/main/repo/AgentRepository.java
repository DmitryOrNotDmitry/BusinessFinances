package ru.dmytrium.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmytrium.main.entity.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByAgentName(String agentName);
}
