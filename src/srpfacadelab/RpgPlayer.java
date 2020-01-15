package srpfacadelab;

import java.util.List;
import java.util.ArrayList;


public class RpgPlayer {
    private final IGameEngine gameEngine;

    private Inventory inventory;

    private Stats stats;

    public RpgPlayer(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
        inventory = new Inventory();
    }

    public void useItem(Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(this);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(Item item) {
        if (inventory.checkIfItemCanBePickedUp(item))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            health = stats.getHealth() +  item.getHeal();

            if (health > stats.getMaxHealth())
                health = stats.getMaxHealth();

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }

            stats.setHealth(health);

            return true;
        }

        if (item.isRare() && item.isUnique())
            gameEngine.playSpecialEffect("blue_swirly");
        if (item.isRare())
            gameEngine.playSpecialEffect("cool_swirly_particles");

        inventory.add(item);

        stats.calculateArmour(inventory);

        return true;
    }

    public void takeDamage(int damage) {
        if (stats.willParry(damage)) {
            gameEngine.playSpecialEffect("parry");
        }
        else {
            stats.takeDamage(damage);
            gameEngine.playSpecialEffect("lots_of_gore");
        }
    }

    public int getHealth() { return stats.getHealth(); }

    public void setHealth(int health) { stats.setHealth(health); }

    public int getMaxHealth() { return stats.getMaxHealth(); }

    public void setMaxHealth(int maxHealth) { stats.setMaxHealth(maxHealth); }

    public int getArmour() { return stats.getArmour(); }

    public int getCarryingCapacity() { return inventory.getCarryingCapacity(); }
}
