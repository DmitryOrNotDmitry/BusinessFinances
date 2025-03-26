package ru.dmytrium.main.lab4.req2;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopAgent {
    private Long agentId;
    private String agentName;
    private BigDecimal totalAmount;
    private Long transactionCount;

    public TopAgent(Long agentId, String agentName, BigDecimal totalAmount, Long transactionCount) {
        this.agentId = agentId;
        this.agentName = agentName;
        this.totalAmount = totalAmount;
        this.transactionCount = transactionCount;
    }
}

