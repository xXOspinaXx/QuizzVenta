package org.example.inventory;

import java.util.Map;

public class RealInventoryService implements InventoryService {
    private final Map<String, Integer> inventory = Map.of(
            "BALON-01", 10,
            "RAQUETA-01", 5
    );

    @Override
    public int getAvailableUnits(String productId){
        System.out.println("Consultando inventario real para " + productId);
        return inventory.getOrDefault(productId, 0);
    }
}
