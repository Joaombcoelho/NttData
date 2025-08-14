package br.com.dio.model;

public record Investment(
        long id,
        long tax,
        long initialFunds) {

    public void voidInvestment(long nextId, double tax, long initialFunds) {

    }

}
