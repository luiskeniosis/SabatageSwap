package lucien.SabarageSwap.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import lucien.SabatageSwap.game.Core;
import lucien.SabatageSwap.game.Main;

//Thanks to BUNNshmit for suggestion.
public class PlayerDeathHandler implements Listener {
    @EventHandler
    public void handle(PlayerDeathEvent event) {
	Player playerWhoDied = event.getEntity();
	Location deathLocation = playerWhoDied.getLocation();
	Core.playerList.remove(playerWhoDied);
	new BukkitRunnable(){
	    public void run(){
		playerWhoDied.spigot().respawn();
		playerWhoDied.setGameMode(GameMode.SPECTATOR);
		playerWhoDied.teleport(deathLocation);
	    }
	}.runTaskLater(Main.plugin, 3l);
	if(Core.playerList.size() == 1) {
	    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &fStarting new game!"));
	    for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
		onlinePlayer.sendTitle(ChatColor.DARK_PURPLE + Core.playerList.get(0).getName(), ChatColor.WHITE + "Is this game's winner!", 10, 100, 10);
		Main.pregame = true;
		player.getInventory().addItem(PreGameManager.notReadyWool);
		player.teleport(player.getWorld().getSpawnLocation());
		//should give all players red wool, and send to lobby
		player.setGamemode(GameMode.SURVIVAL);
	    }
	}
    }
}
