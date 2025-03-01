package ru.dmytrium.main.entity;

import java.io.Serializable;
import java.util.Objects;

public class ConsideringId implements Serializable {
    private Long transaction;
    private Long obligation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsideringId that = (ConsideringId) o;
        return transaction.equals(that.transaction) && obligation.equals(that.obligation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaction, obligation);
    }
}

