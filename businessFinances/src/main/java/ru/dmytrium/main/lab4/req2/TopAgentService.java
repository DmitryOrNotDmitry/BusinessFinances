package ru.dmytrium.main.lab4.req2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopAgentService {
    @Autowired
    private TopAgentRepository topAgentRepository;

    public List<TopAgent> getTopAgents(Long businessId) {
        List<Object[]> results = topAgentRepository.getTopAgentsByBusiness(businessId);
        return results.stream()
                .map(row -> new TopAgent(
                        (Long) row[0],
                        (String) row[1],
                        (BigDecimal) row[2],
                        (Long) row[3]
                ))
                .collect(Collectors.toList());
    }
}

