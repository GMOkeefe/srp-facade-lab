package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

private class Inventory
{
    public static final int MAX_CARRYING_CAPACITY = 1000;

    private List<Item> inventory;

    // How much the player can carry in pounds
    private int carryingCapacity;

    private int inventoryWeight;

    public Inventory()
    {
        inventory = new ArrayList<Item>();
        carryingCapacity = MAX_CARRYING_CAPACITY;
    }

    protected void calculateArmor() {
        int armour = 0;
        for (Item i: inventory) {
            armour += i.getArmour();
        }
        return armour;
    }

    protected boolean checkIfItemExistsInInventory(Item item) {
        for (Item i: inventory) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    protected boolean checkIfItemCanBePickedUp(Item item)
    {
        if ((item.isUnique() && checkIfItemExistsInInventory(item)) ||
            inventoryWeight + item.getWeight() > carryingCapacity)
            return false;
        return true;
    }

    protected void add(Item item)
    {
        inventory.add(item);
    }

    private void calculateInventoryWeight() {
        int sum=0;
        for (Item i: inventory) {
            sum += i.getWeight();
        }
        inventoryWeight = sum;
    }

    protected int getInventoryWeight() { return inventoryWeight; }

    public int getCarryingCapacity() { return carryingCapacity; }

    private void setCarryingCapacity(int carryingCapacity) { this.carryingCapacity = carryingCapacity; }
}