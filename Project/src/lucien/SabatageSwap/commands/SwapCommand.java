package lucien.SabatageSwap.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lucien.SabatageSwap.game.Core;

public class SwapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
	if(sender instanceof Player && sender.hasPermission("ss.swap")) {
	    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &fManually Swapping!"));
	    Core.swap();
	}
	return true;
    }
}