package lucien.SabarageSwap.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import lucien.SabatageSwap.game.Core;
import lucien.SabatageSwap.game.Main;

public class PlayerMoveHandler implements Listener {
    @EventHandler
    public void handle(PlayerMoveEvent event) {
	if(!Core.gameStartFallingPlayers.isEmpty() && Main.preGame == false){
	    Player player = event.getPlayer();
	    if(Core.gameStartFallingPlayers.contains(player.getUniqueId()) && player.isOnGround()) {
		player.removePotionEffect(PotionEffectType.SLOW_FALLING);
		Core.gameStartFallingPlayers.remove(player.getUniqueId());
	    }
	}
    }
}
