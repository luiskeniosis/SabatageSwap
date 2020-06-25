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

public class PreGameCancelledHandler implements Listener {
    @EventHandler
    public void noEntityDamage(EntityDamageEvent event) {
	if(Main.preGame ==  true || Main.postGame == true)
	    //Cancel the event
	    event.setCancelled(true);
    }
    
    @EventHandler
    public void noHungerChange(FoodLevelChangeEvent event) {
	//Player hunger does not change during the pre-game
	//If it is currently the pre-game
	if(Main.preGame == true)
	    //Cancel the event
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
