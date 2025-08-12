package br.com.dio.model;

import java.util.Collection;
import java.util.List;

import static br.com.dio.model.BankService.ACCOUNT;

public class AccountWallet extends Wallet {

    private static BankService serviceType;
    private final List<String> pix;

    public AccountWallet(Object service) {
        super(serviceType);
        this.pix = List.of();
    }

    public AccountWallet(final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
    }

    public AccountWallet(final long amount, final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
        addMoney(amount, "Valor de criação da conta");
    }

    public void addMoney(final long amount, final String description) {
        var money = generateMoney(amount, description);
        this.money.addAll(money);
    }

    @Override
    protected Money createMoney(MoneyAudit history) {

        throw new UnsupportedOperationException("Unimplemented method 'createMoney'");
    }

    @Override
    protected void addHistoryToMoney(Object moneyItem, MoneyAudit history) {

        throw new UnsupportedOperationException("Unimplemented method 'addHistoryToMoney'");
    }

    @Override
    protected List getHistoryFromMoney(Object moneyItem) {

        throw new UnsupportedOperationException("Unimplemented method 'getHistoryFromMoney'");
    }

    @Override
    protected Object addHistory(MoneyAudit history) {

        throw new UnsupportedOperationException("Unimplemented method 'addHistory'");
    }

    @Override
    protected Collection<Money> getHistory() {

        throw new UnsupportedOperationException("Unimplemented method 'getHistory'");
    }

    @Override
    protected BankService getService() {

        throw new UnsupportedOperationException("Unimplemented method 'getService'");
    }

    public Collection<AccountWallet> getPix() {

        throw new UnsupportedOperationException("Unimplemented method 'getPix'");
    }

    public long getBalance() {

        throw new UnsupportedOperationException("Unimplemented method 'getBalance'");
    }
}