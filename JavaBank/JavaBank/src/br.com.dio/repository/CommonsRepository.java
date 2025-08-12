package br.com.dio.repository;

import java.util.List;
import java.util.UUID;

import lombok.NoArgsConstructor;

import static br.com.dio.model.BankService.ACCOUNT;
import static lombok.AccessLevel.PRIVATE;

import java.time.OffsetDateTime;

import br.com.dio.model.AccountWallet;

public final class CommonsRepository {
    public static void checkFundsForTransaction(final Wallet source, final long amount) {
        if (source.getFounds() < amount) {
            throw new NoFundsEnougthException("Sua conta não tem saldo suficiente para realizar essa transação");
        }
    }

    public static List<Money> generateMoney(final UUID transactionId, final long founds, final string description) {
        var history = new MoneyAudit(transactionId, ACCOUNT, description, OffsetDateTime.now);
        return Stream.generate(() -> new Money(history))
                .limit(founds)
                .toList();
    }
}
