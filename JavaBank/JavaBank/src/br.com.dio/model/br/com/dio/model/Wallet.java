package br.com.dio.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@ToString
public abstract class Wallet {

    @Getter
    protected final BankService serviceType;

    protected final List<Money> money;

    public Wallet(final BankService serviceType) {
        this.serviceType = serviceType;
        this.money = new ArrayList<>();
    }

    protected List<Money> generateMoney(final long amount, final String description) {
        var history = new MoneyAudit(UUID.randomUUID(), serviceType, description, OffsetDateTime.now());
        return Stream.generate(() -> new Money(history))
                .limit(amount)
                .toList();
    }

    // Método abstrato para criar uma instância de M com base no histórico
    protected abstract Money createMoney(MoneyAudit history);

    public long getFounds() {
        return money.size();
    }

    public void addMoney(final List<Money> Money, final BankService service, final String description) {
        var history = new MoneyAudit(UUID.randomUUID(), service, description, OffsetDateTime.now());
        money.forEach(m -> addHistory(history));
        this.money.addAll(money);
    }

    // Método abstrato para adicionar histórico ao dinheiro
    // protected abstract void addHistoryToMoney(M moneyItem, MoneyAudit history);

    protected abstract Object addHistory(MoneyAudit history);

    public List<Money> reduceMoney(final long amount) {
        List<Money> toRemove = new ArrayList<>();
        for (int i = 0; i < amount && !money.isEmpty(); i++) {
            toRemove.add(money.remove(0));
        }
        return toRemove;
    }

    public List<Money> getFinancialTransactions() {
        return money.stream().flatMap(m -> getHistory().stream()).toList();
    }

    protected abstract Collection<Money> getHistory();

    protected abstract BankService getService();

    protected void addHistoryToMoney(Object moneyItem, MoneyAudit history) {

        throw new UnsupportedOperationException("Unimplemented method 'addHistoryToMoney'");
    }

    protected List getHistoryFromMoney(Object moneyItem) {

        throw new UnsupportedOperationException("Unimplemented method 'getHistoryFromMoney'");
    }
}
