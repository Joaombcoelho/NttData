# 🎮 Jogo da Forca em Java

Este projeto implementa um clássico **Jogo da Forca (Hangman)** em Java, usando princípios de orientação a objetos, controle de exceções e uma interface de texto simples no terminal.

---

## 📋 Funcionalidades

- ✅ Interface no terminal com menu interativo  
- ✅ Validação de letras repetidas  
- ✅ Mensagens personalizadas de vitória e derrota  
- ✅ Desenho dinâmico do boneco da forca com até 6 erros  
- ✅ Controle completo do estado do jogo (`PENDING`, `WIN`, `LOSE`)  

---

## 📁 Estrutura do Projeto

src/
├── model/
│ ├── HangmanGame.java
│ ├── HangmanChar.java
│ └── HangmanGameStatus.java
├── exception/
│ ├── GameIsFinishedException.java
│ └── LetterAlreadyInputException.java
└── Main.java

yaml
Copiar código

---

## 🚀 Como Executar

### 1. Compilar o projeto

Abra o terminal no diretório `src` e compile tudo com:

```bash
javac Main.java model/*.java exception/*.java
2. Executar o jogo
Execute o jogo passando a palavra secreta como argumento (letra por letra).
Por exemplo, para a palavra JAVA:

bash
Copiar código
java Main J A V A
🧩 Exemplo de Execução
markdown
Copiar código
Bem vindo ao jogo da forca! Tente adivinhar a palavra!

Selecione uma das opções:
1. Informar uma letra
2. Verificar Status do Jogo
3. Sair do Jogo
Ao escolher 1, o jogador digita uma letra. O jogo atualiza o estado com:

A letra revelada (se correta)

O boneco da forca sendo montado (se errada)

Mensagens de vitória ou derrota ao final

🎯 Status do jogo
O status pode ser:

PENDING: Jogo em andamento

WIN: Vitória

LOSE: Derrota após 6 erros

⚠️ Tratamento de Erros
LetterAlreadyInputException: Letra já foi usada.

GameIsFinishedException: O jogo já terminou.

✅ Requisitos
Java 11 ou superior

Terminal/console para entrada de dados

📌 TODO
 Ignorar acentos e diferenciar maiúsculas/minúsculas

 Palavras aleatórias de um dicionário

 Interface gráfica com Swing ou JavaFX

 Suporte a multiplayer local

👨‍💻 Autor
Desenvolvido por João Manoel

