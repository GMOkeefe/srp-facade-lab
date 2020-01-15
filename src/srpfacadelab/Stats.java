package srpfacadelab;

private class Stats
{
    private int health;

    private int maxHealth;

    private int armour;

    protected void calculateStats(Inventory inventory)
    {
        armour = inventory.calculateArmor();
    }

    protected boolean willParry(int damage)
    {
        if (damage < armour)
            return true;
        return false;
    }

    protected int takeDamage(Inventory inventory, int damage)
    {
        int damageToDeal = damage - armour;

        if (inventory.getInventoryWeight() < 0.5 * inventory.getCarryingCapacity())
            damageToDeal *= 0.75;
        health -= damageToDeal;
    }

    protected int getHealth() { return health; }

    protected void setHealth(int health) { this.health = health; }

    protected int getMaxHealth() { return maxHealth; }

    protected void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }

    protected int getArmour() { return armour; }

    protected void setArmour(int armour) { this.armour = armour; }
}