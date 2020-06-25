package lucien.SabarageSwap.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import lucien.SabatageSwap.game.Main;
import lucien.SabatageSwap.game.PreGameManager;

public class PlayerInteractHandler implements Listener {
    @EventHandler
    public void handle(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	if(Main.preGame == true) {
	    event.setCancelled(true);
	    if(event.hasItem()) {
		ItemStack item = event.getItem();
		if(item.getType() == Material.RED_WOOL && player.getCooldown(Material.RED_WOOL) == 0) {
		    player.getInventory().remove(item);
		    player.getInventory().addItem(PreGameManager.readyWool);
		    player.setCooldown(Material.GREEN_WOOL, 40);
		    PreGameManager.playersReady.add(player.getUniqueId());
		    PreGameManager.update();
		}
		if(item.getType() == Material.GREEN_WOOL && player.getCooldown(Material.GREEN_WOOL) == 0) {
		    player.getInventory().remove(item);
		    player.getInventory().addItem(PreGameManager.notReadyWool);
		    player.setCooldown(Material.RED_WOOL, 100);
		    PreGameManager.playersReady.remove(player.getUniqueId());
		    PreGameManager.update();
		}
	    }
	}
    }
}
