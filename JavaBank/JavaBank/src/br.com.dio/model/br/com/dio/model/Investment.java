package br.com.dio.model;

public class Investment {
    private long amount;
    private double tax;

    public Investment(long amount, double tax) {
        this.amount = amount;
        this.tax = tax;
    }

    public long getAmount() {
        return amount;
    }

    public double getTax() {
        return tax;
    }

    @Override
    public String toString() {
        return "Investment{amount=" + amount + ", tax=" + tax + "%}";
    }
}
