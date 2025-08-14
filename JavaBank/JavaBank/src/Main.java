import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.AccountNotFoundException;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AccountRepository accountRepository = new AccountRepository();
    private static final InvestmentRepository investmentRepository = new InvestmentRepository();

    public static void main(String[] args) throws AccountNotFoundException {
        final String BANK_NAME = "DIO Bank";
        final String BANK_VERSION = "1.0.0";

        System.out.println("Olá! Seja bem-vindo ao " + BANK_NAME + " v" + BANK_VERSION + "!");

        while (true) {
            System.out.println("\nSelecione a operação desejada:");
            System.out.println("1- Criar uma Conta");
            System.out.println("2- Criar um Investimento");
            System.out.println("3- Fazer um Investimento");
            System.out.println("4- Depositar na Conta");
            System.out.println("5- Sacar da Conta");
            System.out.println("6- Transferência entre Conta");
            System.out.println("7- Investir");
            System.out.println("8- Sacar Investimento");
            System.out.println("9- Listar Investimentos");
            System.out.println("10- Listar Carteira de Investimentos");
            System.out.println("11- Listar Contas");
            System.out.println("12- Listar Conta de Investimento");
            System.out.println("13- Atualizar Investimentos");
            System.out.println("14- Histórico de Conta");
            System.out.println("15- Sair");

            int option = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (option) {
                case 1 -> createAccount();
                case 2 -> createInvestment();
                case 3 -> makeInvestment();
                case 4 -> deposit();
                case 5 -> withdraw();
                case 6 -> transferToAccount();
                case 7 -> invest();
                case 8 -> withdrawInvestment();
                case 9 -> investmentRepository.listWallets().forEach(System.out::println);
                case 10 -> investmentRepository.listWallets().forEach(System.out::println);
                case 11 -> accountRepository.list().forEach(System.out::println);
                case 12 -> investmentRepository.list().forEach(System.out::println);
                case 13 -> {
                    investmentRepository.updateAmount();
                    System.out.println("Investimentos atualizados com sucesso!");
                }
                case 14 -> checkHistory();
                case 15 -> {
                    System.out.println("Obrigado por usar o " + BANK_NAME + " v" + BANK_VERSION + "!");
                    return;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void createAccount() {
        System.out.println("Informe a chave pix para a conta:");
        String pixInput = scanner.nextLine();
        List<String> pix = Arrays.asList(pixInput.split(";"));

        System.out.println("Informe o valor inicial de Depósito:");
        long amount = scanner.nextLong();
        scanner.nextLine(); // limpar buffer

        var wallet = accountRepository.create(pix, amount);
        System.out.println("Conta criada com sucesso! " + wallet);
    }

    private static void createInvestment() {
        System.out.println("Informe a taxa do Investimento:");
        double tax = scanner.nextDouble();
        System.out.println("Informe o valor inicial de Depósito:");
        long initialFunds = scanner.nextLong();
        scanner.nextLine();

        var investment = investmentRepository.create(tax, initialFunds);
        System.out.println("Investimento criado com sucesso! " + investment);
    }

    private static void deposit() throws AccountNotFoundException {
        System.out.println("Informe a chave pix da conta:");
        String pix = scanner.nextLine();
        System.out.println("Informe o valor do depósito:");
        long amount = scanner.nextLong();
        scanner.nextLine();

        var account = accountRepository.findByPix(pix);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Depósito realizado com sucesso! Saldo atual: " + account.getBalance());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void withdraw() throws AccountNotFoundException {
        System.out.println("Informe a chave pix da conta:");
        String pix = scanner.nextLine();
        System.out.println("Informe o valor do saque:");
        long amount = scanner.nextLong();
        scanner.nextLine();

        var account = accountRepository.findByPix(pix);
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

    private static void transferToAccount() throws AccountNotFoundException {
        System.out.println("Informe a chave pix da conta de origem:");
        String sourcePix = scanner.nextLine();
        System.out.println("Informe a chave pix da conta de destino:");
        String targetPix = scanner.nextLine();
        System.out.println("Informe o valor da transferência:");
        long amount = scanner.nextLong();
        scanner.nextLine();

        var sourceAccount = accountRepository.findByPix(sourcePix);
        var targetAccount = accountRepository.findByPix(targetPix);

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

    private static void makeInvestment() throws AccountNotFoundException {
        System.out.println("Informe a chave pix da conta:");
        String pix = scanner.nextLine();
        System.out.println("Informe o valor do investimento:");
        long amount = scanner.nextLong();
        scanner.nextLine();

        var account = accountRepository.findByPix(pix);
        if (account != null) {
            var investment = investmentRepository.create(amount, account.getBalance());
            System.out.println("Investimento realizado com sucesso! Investimento: " + investment);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void invest() throws AccountNotFoundException {
        makeInvestment(); // mesma lógica de makeInvestment
    }

    private static void withdrawInvestment() throws AccountNotFoundException {
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
        System.out.println("Informe a chave pix da conta:");
        String pix = scanner.nextLine();

        var account = accountRepository.findByPix(pix);
        if (account != null) {
            System.out.println("Histórico de transações da conta " + pix + ":");
            account.getHistory().forEach(System.out::println);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
}
