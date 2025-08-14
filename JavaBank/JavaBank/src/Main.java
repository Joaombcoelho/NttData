import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.AccountNotFoundException;

private static final Scanner scanner = new Scanner(System.in);
private static final AccountRepository accountRepository = new AccountRepository();
private static final InvestmentRepository investmentRepository = new InvestmentRepository();

// private static void withdrawInvestment() throws AccountNotFoundException {
// };

// private static void transferToAccount() throws NoFundsEnougthException {
// };

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int valor = scanner.nextInt();
    final String BANK_NAME = "DIO Bank";
    final String BANK_VERSION = "1.0.0";
    final AccountRepository accountRepository = new AccountRepository();
    final InvestmentRepository investmentRepository = new InvestmentRepository();

    System.out.println("Ola seja bem vindo ao DIO Bank!");
    while (true) {
        System.out.println("Selecione a operação desejada");
        System.out.println("1- Criar uma Conta");
        System.out.println("2- Criar um Investimento");
        System.out.println("3- Fazer um Investimento");
        System.out.println("4- Depositar na Conta");
        System.out.println("5- Sacar da Conta");
        System.out.println("6- Transferencia entre Conta");
        System.out.println("7- Investir");
        System.out.println("8- Sacar Investimento");
        System.out.println("9- Listar Investimentos");
        System.out.println("10- Listar Carteira de Investimentos");
        System.out.println("11- Listar Contas");
        System.out.println("12- Listar Conta de Investimento");
        System.out.println("13- Atualizar Investimentos");
        System.out.println("14- Historico de Conta");
        System.out.println("15- Sair");
        var option = scanner.nextInt();

        switch (option) {
            case 1:
                accountRepository.createAccount();
                break;
            case 2:
                investmentRepository.createWalletInvestment();
                break;
            case 3:
                investmentRepository.makeInvestment();
                break;
            case 4:
                accountRepository.deposit();
                break;
            case 5:
                accountRepository.withdraw();
                break;
            case 6:
                accountRepository.transferToAccount();
                break;
            case 7:
                investmentRepository.invest();
                break;
            case 8:
                investmentRepository.withdrawInvestment();
                break;
            case 9:
                investmentRepository.listWallets().forEach(System.out::println);
                break;
            case 10:
                investmentRepository.listWallets().forEach(System.out::println);
                break;
            case 11:
                accountRepository.list().forEach(System.out::println);
                break;
            case 12:
                investmentRepository.list().forEach(System.out::println);
                break;
            case 13: {
                investmentRepository.updateAmount();
                System.out.println("Investimentos atualizados com sucesso!");
                break;
            }
            case 14:
                accountRepository.checkHistory();
                break;
            case 15:
                System.out.println("Obrigado por usar o " + BANK_NAME + " v" + BANK_VERSION + "!");
                return; // Exit the program
            default:
                System.out.println("Opção inválida, tente novamente.");
        }
    }
}

private static void createAccount() {
    System.out.println("Informe a chave pix para a conta:");
    String pixInput = scanner.nextLine();
    List<String> pix = Arrays.asList(pixInput.split(";"));
    System.out.println("Informe o valor inicial de Depósito");
    var amouth = scanner.nextLong();

    var wallet = accountRepository.create(pix, amouth);
    System.out.println("Conta criada com sucesso! " + wallet);
}

private static void createInvestment() {
    System.out.println("Informe a taxa do Investimento:");
    String taxInput = scanner.nextLine();
    List<String> tax = Arrays.asList(taxInput.split(";"));
    var amouth = scanner.nextInt();
    System.out.println("Informe o valor inicial de Depósito");
    var initialFunds = scanner.nextLong();
    var investment = InvestmentRepository.create(tax, initialFunds);
    System.out.println("Investimento criado com sucesso! " + investment);
}

private static void deposit() throws AccountNotFoundException {
    System.out.println("Informe a chave pix da conta:");
    var pix = scanner.next();
    System.out.println("Informe o valor do depósito:");
    var amount = scanner.nextLong();
    var account = AccountRepository.findByPix(pix);
    if (account != null) {
        account.deposit(amount);
        System.out.println("Depósito realizado com sucesso! Saldo atual: " + account.getBalance());
    } else {
        System.out.println("Conta não encontrada.");
    }
}

private static void withdraw() {
    System.out.println("Informe a chave pix da conta:");
    var pix = scanner.next();
    System.out.println("Informe o valor do saque:");
    var amount = scanner.nextLong();
    var account = AccountRepository.findByPix(pix);
    if (account != null) {
        if (account.withdraw(amount)) {
            System.out.println("Saque realizado com sucesso! Saldo atual: " + account.getBalance());
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    } else {
        System.out.println("Conta não encontrada.");
    }
}

private static void transferToAccount() throws NoFundsEnougthException {
    System.out.println("Informe a chave pix da conta de origem:");
    var sourcePix = scanner.next();
    System.out.println("Informe a chave pix da conta de destino:");
    var targetPix = scanner.next();
    System.out.println("Informe o valor da transferência:");
    var amount = scanner.nextLong();

    var sourceAccount = AccountRepository.findByPix(sourcePix);
    var targetAccount = AccountRepository.findByPix(targetPix);

    if (sourceAccount != null && targetAccount != null) {
        if (sourceAccount.transfer(targetAccount, amount)) {
            System.out.println("Transferência realizada com sucesso! Saldo atual: " + sourceAccount.getBalance());
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    } else {
        System.out.println("Conta(s) não encontrada(s).");
    }
}

private static void createWalletInvestment() throws WalletInvestmentException {
    System.out.println("Informe a taxa do Investimento:");
    var tax = scanner.nextDouble();
    System.out.println("Informe o valor inicial de Depósito");
    var initialFunds = scanner.nextLong();
    var investment = InvestmentRepository.create(tax, initialFunds);
    System.out.println("Investimento criado com sucesso! " + investment);
}

private static void makeInvestment() throws WalletInvestmentException {
    System.out.println("Informe a chave pix da conta:");
    var pix = scanner.next();
    System.out.println("Informe o valor do investimento:");
    var amount = scanner.nextLong();
    var account = AccountRepository.findByPix(pix);
    if (account != null) {
        var investment = InvestmentRepository.create(amount, account.getBalance());
        System.out.println("Investimento realizado com sucesso! Investimento: " + investment);
    } else {
        System.out.println("Conta não encontrada.");
    }
}

private static void invest() throws WalletInvestmentException {
    System.out.println("Informe a chave pix da conta:");
    var pix = scanner.next();
    System.out.println("Informe o valor do investimento:");
    var amount = scanner.nextLong();
    var account = AccountRepository.findByPix(pix);
    if (account != null) {
        var investment = InvestmentRepository.create(amount, account.getBalance());
        System.out.println("Investimento realizado com sucesso! Investimento: " + investment);
    } else {
        System.out.println("Conta não encontrada.");
    }
}

private static void withdrawInvestment() // throws WalletInvestmentException
{
    System.out.println("Informe a chave pix da conta:");
    String pix = scanner.nextLine();

    System.out.println("Informe o valor do saque do investimento:");
    long amount = scanner.nextLong();
    scanner.nextLine();

    var account = accountRepository.findByPix(pix);
    if (account != null) {
        var investment = investmentRepository.withdraw(pix, amount);
        System.out.println("Saque de investimento realizado com sucesso! Investimento: " + investment);
    } else {
        System.out.println("Conta não encontrada.");
    }
}

private static void checkHistory() throws AccountNotFoundException {
    Scanner scanner = new Scanner(System.in); // cria scanner

    System.out.println("Informe a chave pix da conta:");
    String pix = scanner.next(); // lê como texto

    var account = AccountRepository.findByPix(pix);
    if (account != null) {
        System.out.println("Histórico de transações da conta " + pix + ":");
        account.getHistory().forEach(System.out::println);
    } else {
        System.out.println("Conta não encontrada.");
    }
}
