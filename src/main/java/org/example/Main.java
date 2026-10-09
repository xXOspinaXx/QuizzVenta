package org.example;

import org.example.purchase.PurchaseFacade;

public class Main {
    public static void main(String[] args) {
        PurchaseFacade store = new PurchaseFacade();
        store.purchase(
                "Ana Pérez",
                "BALON-01",
                2,
                79.90,
                "1234567812345678"
        );
    }
}
