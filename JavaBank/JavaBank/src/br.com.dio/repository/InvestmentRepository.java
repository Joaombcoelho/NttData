package br.com.dio.repository;

import br.com.dio.model.AccountWallet;
import br.com.dio.model.Investment;
import br.com.dio.model.InvestmentWallet;

public class InvestmentRepository {
    private long nextId;
    private final List<Investment> investments = new ArrayList<>();
    private final List<InvestmentWallet> Wallet = new ArrayList<>();

    public Investment create(final long tax, final long daysToRescue, final long initialFounds) {
        this.nextId++;
        var investment = new Investment(this.nextId, tax, initialFounds);
        investments.add(investment);
        return investment;
    }

    public InvestmentWallet initInvestments(final AccountWallet account, final long id) {

        var accountInUse = wallets.stream().map(InvestmentWallet::getAccount.toList());
        if (accountInUse.contains(account)) {
            throw new IllegalArgumentException("A conta já possui uma carteira de investimento");
        }
    }

    var Investiments = findById(id);

    checkFundsForTransaction(account, investment.initialFounds());
        var wallet = new InvestmentWallet(account, investment.initialFounds());
        wallet.add(wallets);
        return wallet;

    }

    public InvestmentWallet deposit(final String pix, final long funds) {
        var wallet = findWalletByAccountPix(pix);
        wallet.addMoney(wallet.getAccount().reduceMoney(funds), wallet.getService(), "Investimento");
        return wallet;
    }

    public InvestmentWallet withdraw(final String pix, final long funds) {
        var wallet = findWalletByAccountPix(pix);
        checkFundsForTransaction(wallet, funds);
        wallet.getAccount().addMoney(wallet.reduceMoney(funds), wallet.getService(), "Retirada de Investimento");
        if (wallet.getFunds() == 0) {
            wallets.remove(wallet);
            return wallet;
        }
    }

    public void updateAmount(final Long percent) {
        var amount = getFounds() * percent / 100;
    }

    public Investment findById(final Long id) {
        return Investments.stream().filter(a -> a.id() == id)
                .findFirst().orElseThrow(() -> new InvestmentsNotFoundException("O Investimento não foi encontrada"));
    }

    public InvestmentWallet findWalletByAccountPix(final string pix) {
        return wallets.stream().filter(wallet -> wallet.getAccount().getPix().contains(pix)
                .findFirst().orElseThrow(() -> new walletNotFoundException("A carteira não foi encontrada")));
    }

    public List<InvestmentWallet> listWallets() {
        return this.Wallet;
    }

    public List<Investment> list() {
        return this.investments;
    }
}
