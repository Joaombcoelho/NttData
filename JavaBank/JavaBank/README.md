DIO Bank - Sistema Bancário em Java

Este projeto é um sistema bancário simples em Java, permitindo a criação de contas, investimentos, depósitos, saques, transferências e gerenciamento de investimentos. É um projeto didático para aprendizado de programação orientada a objetos e manipulação de dados bancários.

📌 Funcionalidades

O DIO Bank oferece as seguintes funcionalidades:

Criar Conta

Permite criar uma nova conta informando uma chave PIX e um valor inicial de depósito.

Criar Investimento

Permite criar um novo investimento com taxa de rendimento e valor inicial.

Fazer Investimento

Realiza um investimento a partir do saldo de uma conta existente.

Depositar na Conta

Adiciona fundos em uma conta existente usando a chave PIX.

Sacar da Conta

Permite retirar fundos de uma conta existente, respeitando o saldo disponível.

Transferência entre Contas

Permite transferir fundos entre duas contas, usando suas chaves PIX.

Investir

Investe um valor da conta em uma carteira de investimento.

Sacar Investimento

Retira fundos de um investimento existente.

Listar Investimentos

Exibe todos os investimentos criados.

Listar Carteira de Investimentos

Exibe todas as carteiras de investimentos.

Listar Contas

Exibe todas as contas criadas.

Listar Conta de Investimento

Exibe todos os investimentos vinculados a contas.

Atualizar Investimentos

Atualiza os valores dos investimentos com base na taxa de rendimento.

Histórico de Conta

Exibe todas as transações realizadas em uma conta.

Sair do Sistema

Finaliza a execução do programa.

🛠 Tecnologias Utilizadas

Java 17+

Conceitos de POO (Programação Orientada a Objetos): classes, objetos, encapsulamento, métodos.

Manipulação de listas e coleções (List).

Entrada de dados pelo Scanner.

Tratamento básico de exceções.

📂 Estrutura do Projeto

Main.java - Classe principal contendo o menu interativo e métodos auxiliares.

AccountRepository.java - Gerencia contas bancárias.

InvestmentRepository.java - Gerencia investimentos e carteiras de investimento.

Account.java - Representa uma conta bancária com saldo, PIX e histórico de transações.

Investment.java - Representa um investimento com taxa, valor inicial e saldo.

⚙️ Como Executar

Pré-requisitos:

Java 17 ou superior instalado.

IDE como VS Code, IntelliJ ou Eclipse.

Clonar o repositório (se estiver no GitHub):

git clone <URL_DO_REPOSITORIO>
cd dio-bank

Compilar o projeto:

javac Main.java

Executar o programa:

java Main

Interagir com o menu:

Digite o número da operação desejada e siga as instruções.

📝 Exemplo de Uso
Ola seja bem vindo ao DIO Bank!
Selecione a operação desejada
1- Criar uma Conta
...
15- Sair

Criando uma conta:

Informe a chave pix para a conta:
<joao@pix.com>
Informe o valor inicial de Depósito:
1000
Conta criada com sucesso! Wallet{id=1, saldo=1000, pix=[joao@pix.com]}

Depositando na conta:

Informe a chave pix da conta:
<joao@pix.com>
Informe o valor do depósito:
500
Depósito realizado com sucesso! Saldo atual: 1500

Realizando investimento:

Informe a chave pix da conta:
<joao@pix.com>
Informe o valor do investimento:
1000
Investimento realizado com sucesso! Investimento: Investment{id=1, valor=1000, taxa=0.05}

⚠️ Observações

Algumas operações lançam exceções, como AccountNotFoundException e WalletInvestmentException, que devem ser tratadas adequadamente.

O método withdrawInvestment atualmente não lança exceção, mas pode ser ajustado para lançar WalletInvestmentException se necessário.

O projeto utiliza Scanner para entrada de dados; cuidado ao misturar nextInt() e nextLine() para evitar problemas de buffer.

📌 Melhorias Futuras

Implementar persistência de dados em arquivo ou banco de dados.

Adicionar autenticação de usuário.

Implementar juros compostos para investimentos.

Criar interface gráfica com JavaFX ou Swing.

Validar entrada de dados (evitar valores negativos ou inválidos).

📌 Fluxo Principal do Sistema

1. Menu Principal
┌──────────────────────────────┐
│     Menu Principal DIO Bank   │
├───────────────┬──────────────┤
│ 1- Criar Conta │ 2- Criar Investimento │
│ 3- Fazer Investimento │ 4- Depositar │
│ 5- Sacar │ 6- Transferir │
│ 7- Investir │ 8- Sacar Investimento │
│ 9- Listar Investimentos │ 10- Listar Carteira │
│ 11- Listar Contas │ 12- Listar Conta de Investimento │
│ 13- Atualizar Investimentos │ 14- Histórico │
│ 15- Sair │
└───────────────┴──────────────┘

O usuário escolhe uma opção e o programa redireciona para o método correspondente.

2. Fluxo de Criação de Conta
Usuário seleciona "Criar Conta"
          │
          ▼
  Solicita chave PIX e valor inicial
          │
          ▼
   accountRepository.create(pix, valor)
          │
          ▼
     Conta criada com sucesso
          │
          ▼
      Retorna ao Menu

3. Fluxo de Depósito/Saque
Usuário seleciona "Depositar" ou "Sacar"
          │
          ▼
   Solicita chave PIX
          │
          ▼
 AccountRepository.findByPix(pix)
          │
    ┌─────┴─────┐
    │ Conta existe? │
    │     │        │
   Sim   Não
    │      │
  Deposita │ Mostra "Conta não encontrada"
  ou       │
  Retira   │
  Saldo    │
    │
    ▼
 Mostra saldo atualizado
    │
    ▼
  Retorna ao Menu

4. Fluxo de Transferência Entre Contas
Usuário seleciona "Transferir"
          │
          ▼
 Solicita chave PIX origem e destino + valor
          │
          ▼
Encontrar contas com AccountRepository.findByPix()
          │
    ┌─────┴─────┐
    │ Ambas existem? │
    │      │       │
   Sim     Não
    │       │
 Verifica saldo │ Mostra "Conta(s) não encontrada(s)"
    │
  Suficiente?
    │
 ┌──┴──┐
 │Sim  │Não
 │     │
 Transfere │ Mostra "Saldo insuficiente"
 │
 ▼
 Mostra saldo atualizado
 │
 ▼
 Retorna ao Menu

5. Fluxo de Investimento
Usuário seleciona "Investir" ou "Fazer Investimento"
          │
          ▼
 Solicita chave PIX da conta + valor
          │
          ▼
  AccountRepository.findByPix(pix)
          │
    ┌─────┴─────┐
    │ Conta existe? │
    │      │       │
   Sim     Não
    │       │
 Investimento │ Mostra "Conta não encontrada"
  criado
    │
    ▼
Mostra detalhes do investimento
    │
    ▼
Retorna ao Menu

6. Fluxo de Saque de Investimento
Usuário seleciona "Sacar Investimento"
          │
          ▼
 Solicita chave PIX + valor
          │
          ▼
 accountRepository.findByPix(pix)
          │
    ┌─────┴─────┐
    │ Conta existe? │
    │      │       │
   Sim     Não
    │       │
 investmentRepository.withdraw(pix, valor)
    │
 ▼
Mostra saldo atualizado do investimento
    │
 ▼
Retorna ao Menu

🔄 Interações Entre Classes
 ┌───────────────┐
 │   Main.java   │
 └──────┬────────┘
        │ chama métodos
        ▼
 ┌───────────────┐       ┌────────────────┐
 │ AccountRepository│────►│ Account.java   │
 └───────────────┘       └────────────────┘
        │
        ▼
 ┌───────────────┐       ┌────────────────┐
 │InvestmentRepo │────►  │ Investment.java│
 └───────────────┘       └────────────────┘

Main.java gerencia o menu e chama métodos estáticos ou de instâncias.

AccountRepository cria, busca, deposita, saca e lista contas.

InvestmentRepository cria investimentos, atualiza valores e gerencia carteiras.

Account.java representa o saldo, PIX e histórico da conta.

Investment.java representa o investimento, taxa e saldo.
