package org.example.payment;

public interface PaymentProcessor {
    boolean pay(String cardNumber, double amount);
}
