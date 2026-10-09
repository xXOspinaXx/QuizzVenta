package org.example.payment;

public class PaymentAdapter implements PaymentProcessor {
    private final ExternalPaymentService externalService;

    public PaymentAdapter(ExternalPaymentService externalService) {
        this.externalService = externalService;
    }

    @Override
    public boolean pay(String cardNumber, double amount) {
        long amountInCents = Math.round(amount * 100);
        return externalService.chargeInCents(cardNumber, amountInCents);
    }
}
