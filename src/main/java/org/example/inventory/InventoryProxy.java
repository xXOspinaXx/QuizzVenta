package org.example.inventory;

public class InventoryProxy implements InventoryService {
    private final InventoryService realInventory;
    private final boolean accessAllowed;

    public InventoryProxy(InventoryService realInventory, boolean accessAllowed){
        this.realInventory = realInventory;
        this.accessAllowed = accessAllowed;
    }

    @Override
    public int getAvailableUnits(String productId) {
        if (!accessAllowed){
            throw new SecurityException("No tienes permiso para consultar el inventario");
        }
        System.out.println("Acceso autorizado");
        return realInventory.getAvailableUnits(productId);
    }
}
