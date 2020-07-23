package lucien.SabarageSwap.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import lucien.SabatageSwap.game.Main;

public class CancelledHandler implements Listener {
    @EventHandler
    public void noEntityDamage(EntityDamageEvent event) {
	if(Main.preGame ==  true || Main.postGame == true)
	    event.setCancelled(true);
    }
    
    @EventHandler
    public void noHungerChange(FoodLevelChangeEvent event) {
	if(Main.preGame == true || Main.postGame == true)
	    event.setCancelled(true);
    }
    
    @EventHandler
    public void noItemPickup(EntityPickupItemEvent event) {
	if(Main.preGame == true)
	    event.setCancelled(true);
    }
    
    @EventHandler
    public void noItemDrop(PlayerDropItemEvent event) {
	if(Main.preGame == true)
	    event.setCancelled(true);
    }
    
    @EventHandler
    public void noPvP(EntityDamageByEntityEvent event) {
	if(event.getEntity().getType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.PLAYER) {
	    event.setCancelled(true);
	}
    }
}
