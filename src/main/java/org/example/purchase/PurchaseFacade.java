package org.example.purchase;

import org.example.inventory.InventoryProxy;
import org.example.inventory.InventoryService;
import org.example.inventory.RealInventoryService;
import org.example.notification.BasicMessage;
import org.example.notification.CompressionDecorator;
import org.example.notification.LoggingDecorator;
import org.example.notification.Message;
import org.example.payment.ExternalPaymentService;
import org.example.payment.PaymentAdapter;
import org.example.payment.PaymentProcessor;

public class PurchaseFacade {
    private final InventoryService inventory;
    private final PaymentProcessor payment;
    private final Message notification;

    public PurchaseFacade() {
        // Proxy: valida el acceso antes de consultar el inventario real.
        this.inventory = new InventoryProxy(
                new RealInventoryService(),
                true
        );

        // Adapter: adapta el servicio externo a la interfaz de pagos de la tienda.
        this.payment = new PaymentAdapter(
                new ExternalPaymentService()
        );

        // Decorator: combina logging y compresión sobre el envío base.
        this.notification = new LoggingDecorator(
                new CompressionDecorator(
                        new BasicMessage()
                )
        );
    }

    public boolean purchase(
            String customerName,
            String productId,
            int quantity,
            double unitPrice,
            String cardNumber
    ) {
        if (quantity <= 0 || unitPrice <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad y el precio deben ser mayores que cero."
            );
        }

        System.out.println("Iniciando compra para " + customerName);

        int availableUnits = inventory.getAvailableUnits(productId);

        if (availableUnits < quantity) {
            System.out.println("Compra rechazada: no hay suficiente inventario.");
            return false;
        }

        double total = quantity * unitPrice;
        System.out.printf("Total de la compra: $%.2f%n", total);

        boolean paymentApproved = payment.pay(cardNumber, total);

        if (!paymentApproved) {
            System.out.println("Compra rechazada: el pago no fue aprobado.");
            return false;
        }

        notification.send(
                "Compra confirmada para " + customerName
                        + ". Producto: " + productId
                        + ", cantidad: " + quantity
                        + ", total: $" + String.format("%.2f", total)
        );

        System.out.println("Compra completada.");
        return true;
    }
}