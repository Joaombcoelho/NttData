package br.com.dio.model;

import java.util.List;
import java.util.ArrayList;

@EqualsAndHashCode
@Getter
@ToString
public class Money {

    private final List<MoneyAudit> history = new ArrayList<>();

    public Money(final MoneyAudit history) {
        this.history.add(history);
    }

    public void addHistory(final MoneyAudit history) {
        this.history.add(history);
    }

}
