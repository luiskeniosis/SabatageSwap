package lucien.SabatageSwap.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PreGameManager {
    public static List<UUID> playersReady = new ArrayList<UUID>();
    public static ItemStack notReadyWool = new ItemStack(Material.RED_WOOL);
    public static ItemStack readyWool = new ItemStack(Material.GREEN_WOOL);

    public static void update() {
	Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes ('&', "&8[&5Lucien&l&dAI&r&8] &7Players ready: &e(" + playersReady.size() + "/" + Bukkit.getOnlinePlayers().size() + ")"));
	if(playersReady.size() == Bukkit.getOnlinePlayers().size()) {
	    if(playersReady.size() > 1) {
		for(Player player : Bukkit.getOnlinePlayers())
		    player.getInventory().clear();
		Core.startGame();
	    }
	    else
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes ('&', "&8[&5Lucien&l&dAI&r&8] &cThere must be at least &e2 &cplayers to start the game."));
	}
    }

    public static void generatePreGameItems() {
	ItemMeta notReadyWoolMeta = notReadyWool.getItemMeta();
	notReadyWoolMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "NOT READY");
	List<String> notReadyWoolLore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&8&l-&r&7Right click to be ready."));
	notReadyWoolMeta.setLore(notReadyWoolLore);
	notReadyWool.setItemMeta(notReadyWoolMeta);

	ItemMeta readyWoolMeta = readyWool.getItemMeta();
	readyWoolMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "READY");
	readyWoolMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
	readyWoolMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	List<String> readyWoolLore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&8&l-&r&7Right click to be not ready."));
	readyWoolMeta.setLore(readyWoolLore);
	readyWool.setItemMeta(readyWoolMeta);
    }
}
