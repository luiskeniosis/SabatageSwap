package lucien.SabatageSwap.game;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SwapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
	if(sender instanceof Player && sender.hasPermission("ss.swap")) {
	    Bukkit.broadcastMessage("Manually swapping.");
	    Core.swap();
	}
	return true;
    }
}