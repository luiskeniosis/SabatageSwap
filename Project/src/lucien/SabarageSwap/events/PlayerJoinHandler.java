package lucien.SabarageSwap.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import lucien.SabatageSwap.game.Main;
import lucien.SabatageSwap.game.PreGameManager;

public class PlayerJoinHandler implements Listener {
    @EventHandler
    public void handle(PlayerJoinEvent event) {
	Player player = event.getPlayer();
	if(!player.hasPlayedBefore()) {
	    if(Main.preGame == false)
		player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &cA game has already begun! Join back later to catch the next one."));
	    else
		player.getInventory().addItem(PreGameManager.notReadyWool);
	}
    }
}
