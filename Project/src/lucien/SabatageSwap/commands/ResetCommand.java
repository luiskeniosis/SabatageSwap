package lucien.SabatageSwap.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import lucien.SabatageSwap.game.Core;

public class ResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
	if(sender.hasPermission("ss.reset")) {
	    Core.reset();
	}
	return true;
    }
}
