package ru.dmytrium.main.services;

import org.springframework.stereotype.Service;
import ru.dmytrium.main.entity.InOutType;

@Service
public class InOutService {

    public boolean isIncome(InOutType inOutType) {
        return inOutType.getShortName().equals("+");
    }

    public boolean isExpense(InOutType inOutType) {
        return inOutType.getShortName().equals("-");
    }

}
