package org.example.payment;

/** Simula la biblioteca externa, cuya interfaz no se modifica. */
public class ExternalPaymentService {
    public boolean chargeInCents(String cardNumber, long amountInCents) {
        System.out.printf(
                "Servicio externo: cobrando %d centavos a la tarjeta %s%n",
                amountInCents,
                maskCardNumber(cardNumber)
        );
        return true;
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}
