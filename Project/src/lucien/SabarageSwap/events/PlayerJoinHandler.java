package lucien.SabarageSwap.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import lucien.SabatageSwap.game.PreGameManager;

public class PlayerJoinHandler implements Listener {
    @EventHandler
    public void handle(PlayerJoinEvent event) {
	Player player = event.getPlayer();
	if(!player.hasPlayedBefore()) {
	    player.getInventory().addItem(PreGameManager.notReadyWool);
	}
    }
}
