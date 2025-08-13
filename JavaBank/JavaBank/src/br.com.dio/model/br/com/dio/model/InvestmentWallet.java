package br.com.dio.model;

import static br.com.dio.model.BankService.INVESTMENT;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.stream.Stream;

@ToString
@Getter

public abstract class InvestmentWallet extends Wallet {

    private final Investment investment;
    private final AccountWallet account;

    public abstract BankService getService();

    public InvestmentWallet(Object service, Investment investment, AccountWallet account, final long amount) {
        super(INVESTMENT);
        this.investment = investment;
        this.account = account;
        addMoney(money, ((InvestmentWallet) account.reduceMoney(amount)).getService(), "Investimento");
    }

    public void updateAmount(final long percent) {
        var amount = getFounds() * percent / 100;
        var history = new MoneyAudit(UUID.randomUUID(), getService(), "Rendimentos", OffsetDateTime.now());
        var money = Stream.generate(() -> new Money(history)).limit(amount).toList();
        this.money.addAll(money);
    }

    public abstract Wallet getAccount();

}
