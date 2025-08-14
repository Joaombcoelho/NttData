
import br.com.dio.model.AccountWallet;
import br.com.dio.model.Getter;

import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.AccountNotFoundException;

@Getter
public class AccountRepository {
    private final static List<AccountWallet> accounts = new ArrayList<>();

    public static AccountWallet create(Object pix2, long amouth) {
        List<AccountWallet> pixInUse = accounts.stream()
                .flatMap(a -> a.getPix().stream())
                .toList();

        Object pix;
        for (var p : pixInUse) {
            if (pixInUse.contains(p)) {
                throw new PixInUseException("A chave PIX " + p + " já está em uso.");
            }
        }

        var newAccount = new AccountWallet(pixInUse);
        accounts.add(newAccount);
        return newAccount;
    }

    public void deposit(final String pix, final long fundsAmount) throws AccountNotFoundException {
        var target = findByPix(pix);
        target.addMoney(fundsAmount, "Depósito na conta " + pix);
    }

    public long withdraw(final String pix, final long amount) throws AccountNotFoundException {
        var source = findByPix(pix);
        checkFundsForTransaction(source, amount);
        source.reduceMoney(amount);
        return amount;
    }

    public void transferMoney(final String sourcePix, final String targetPix, final long amount)
            throws AccountNotFoundException {
        var source = findByPix(sourcePix);
        checkFundsForTransaction(source, amount);
        var target = findByPix(targetPix);

        var message = "Transferência de " + amount + " da conta " + sourcePix + " para a conta " + targetPix;
        source.reduceMoney(amount);
        target.addMoney(amount, message);
    }

    public static AccountWallet findByPix(final String pix) throws AccountNotFoundException {
        return accounts.stream()
                .filter(a -> a.getPix().contains(pix))
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("A conta com a chave PIX " + pix + " não existe."));
    }

    public List<AccountWallet> list() {
        return new ArrayList<>(AccountRepository.accounts);
    }

    private void checkFundsForTransaction(AccountWallet account, long amount) {
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Saldo insuficiente para a transação.");
        }
    }

    public void createAccount() {

        throw new UnsupportedOperationException("Não foi possivel criar a conta");
    }

    public void deposit() {

        throw new UnsupportedOperationException("Não foi possivel realizar o depósito");
    }

    public void withdraw() {

        throw new UnsupportedOperationException("Não foi possivel realizar o saque");
    }

    public void transferToAccount() {

        throw new UnsupportedOperationException("Não foi possivel realizar a transferência");
    }

    public void checkHistory() {

        throw new UnsupportedOperationException("Não foi possivel consultar o histórico");
    }
}
