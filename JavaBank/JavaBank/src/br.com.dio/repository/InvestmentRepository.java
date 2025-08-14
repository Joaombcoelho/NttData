
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.management.InstanceNotFoundException;
import br.com.dio.model.AccountWallet;
import br.com.dio.model.Investment;
import br.com.dio.model.InvestmentWallet;
import br.com.dio.model.Wallet;

public class InvestmentRepository {

    private static long nextId;
    private final static List<Investment> investments = new ArrayList<>();
    private final List<InvestmentWallet> wallet = new ArrayList<>();

    public static Investment create(final List<String> tax, final long initialFunds) {
        nextId++;
        var investment = new Investment();
        investments.add(investment);
        return investment;
    }

    public void initInvestments(final AccountWallet account, final long id) throws InstanceNotFoundException {
        var accountsInUse = wallet.stream()
                .map(InvestmentWallet::getAccount)
                .toList();
        if (accountsInUse.contains(account)) {
            throw new AccountwithInvestmentsException("A conta já possui uma carteira de investimento");
        }

    }

    // void checkFundsForTransaction(account, long investment.initialFunds()){;
    // var wallet = new InvestmentWallet(investment, account,
    // investment.initialFunds());
    // wallets.add(wallet);
    // return wallet;

    // }

    public InvestmentWallet deposit(final String pix, final long funds) {
        var wallet = findWalletByAccountPix(pix);
        ((Wallet) wallet).addMoney(((InvestmentWallet) wallet).getAccount().reduceMoney(funds),
                wallet.getService(), "Investimento");
        return (InvestmentWallet) wallet;
    }

    public InvestmentWallet withdraw(final String pix, final long funds) {
        var wallet = findWalletByAccountPix(pix);
        checkFundsForTransaction();
        ((InvestmentWallet) wallet).getAccount().addMoney(((Wallet) wallet).reduceMoney(funds),
                wallet.getService(), "Retirada de Investimento");
        if (((Wallet) wallet).getFounds() == 0) {
            Wallet.remove(wallet);
        }
        return (InvestmentWallet) wallet;
    }

    private void checkFundsForTransaction() {

        throw new UnsupportedOperationException("Unimplemented method 'checkFundsForTransaction'");
    }

    public void updateAmount(final long percent) {
    }

    public Investment findById(final Long id) throws InstanceNotFoundException {
        return investments.stream().filter(a -> a.id() == id)
                .findFirst().orElseThrow(() -> new InstanceNotFoundException("O Investimento não foi encontrada"));
    }

    public InvestmentWallet findWalletByAccountPix(final String pix) {
        return wallet.stream()
                .filter(wallet -> wallet.getAccount().getPix().contains(pix))
                .findFirst()
                .orElseThrow(() -> new WalletNotFoundsException("A carteira não foi encontrada"));
    }

    public List<InvestmentWallet> listWallets() {
        return this.wallet;
    }

    public List<Investment> list() {
        return this.investments;
    }

    public void updateAmount() {

        throw new InvestmentNotFoundsException("Unimplemented method 'updateAmount'");
    }

    public void createInvestment(Scanner scanner) {

        throw new InvestmentNotFoundsException("Unimplemented method 'createInvestment'");
    }

    public void createWalletInvestment() {

        throw new UnsupportedOperationException("Unimplemented method 'createwalletInvestment'");
    }

    public void makeInvestment() {

        throw new UnsupportedOperationException("Unimplemented method 'makeInvestment'");
    }

    public void invest() {

        throw new UnsupportedOperationException("Unimplemented method 'invest'");
    }

    public void withdrawInvestment() {

        throw new UnsupportedOperationException("Unimplemented method 'withdrawInvestment'");
    }

    public static Investment create(double tax, long initialFunds) {

        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    // public static Investment create(List<String> tax, long initialFunds) {

    // throw new UnsupportedOperationException("Unimplemented method 'create'");
    // }
}