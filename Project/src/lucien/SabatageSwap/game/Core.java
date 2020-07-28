package lucien.SabatageSwap.game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Core {
    public static List<UUID> gameStartFallingPlayers = new ArrayList<UUID>();
    @SuppressWarnings("unchecked")
    public static List<Player> playerList = (List<Player>) Bukkit.getOnlinePlayers();
    private static World world = Bukkit.getServer().getWorlds().get(0);
    private static int timeBeforeSwap = Main.plugin.getConfig().getInt("gracePeriodTime");
    private static Random random = new Random();

    public static void startGame() {
	Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
	    int timer = 60;
	    @Override
	    public void run() {
		//If the timer has not finished
		if(timer != -1) {
		    //On the first iteration
		    if(timer == 60) {
			//For every player online
			for(Player players : Bukkit.getOnlinePlayers()) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "3", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
		    }
		    //After 1 second has passed
		    if(timer == 40) {
			//For every player online
			for(Player players : Bukkit.getOnlinePlayers()) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "2", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
		    }
		    //After 2 seconds have passed
		    if(timer == 20) {
			//For every player online
			for(Player players : Bukkit.getOnlinePlayers()) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "1", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
		    }
		    //If the timer is running
		    if(timer != 0) {
			//For every player online
			for(Player players : Bukkit.getOnlinePlayers()) {
			    //Set their game mode to spectator
			    players.setGameMode(GameMode.SPECTATOR);
			    //Boost them upwards
			    players.setVelocity(new Vector(0, 1, 0));
			}
			//Count down from the timer
			timer--;
		    }
		    //If the timer has finished
		    if(timer == 0) {
			//Stores the radius we want to spread players
			int maxX = Main.plugin.getConfig().getInt("spreadRadius") + 1;
			int maxZ = Main.plugin.getConfig().getInt("spreadRadius") + 1;
			//Creates a randomizer object
			Random random = new Random();
			//For every player online
			for(Player player : Bukkit.getOnlinePlayers()) {
			    //Display a title screen
			    player.sendTitle(ChatColor.GREEN + "GO!", "", 0, 40, 10);
			    //Generate a number between 0 and (maxX-1)
			    int x = random.nextInt(maxX);
			    //Generate a number between 0 and (maxZ-1)
			    int z = random.nextInt(maxZ);
			    //If the number generated was in the upper half
			    if(x > ((maxX-1)/2))
				//Divide it by 2 and reverse it
				x = (x/2) * -1;
			    //If the number generated was in the upper half
			    if(z > ((maxX-1)/2))
				//Divide it by 2 and reverse it
				z = (z/2) * -1;
			    //Cancel all player motion
			    player.setVelocity(new Vector(0, 0, 0));
			    player.teleport(new Location(world, x, player.getWorld().getHighestBlockYAt(x, z) + 50, z));
			    gameStartFallingPlayers.add(player.getUniqueId());
			    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 400, 0, false, false));
			    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 0, false, false));
			    //Play a note
			    player.playNote(player.getLocation(), Instrument.PLING, new Note(13));
			    //Play the begin game sound
			    player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 5, 0.8F);
			    //Set the player's game mode to survival
			    player.setGameMode(GameMode.SURVIVAL);
			}
			timer = -1;
			Main.preGame = false;
		    }
		}
	    }
	}, 0L, 1L);
	DecimalFormat formatter = new DecimalFormat("#.##");
	Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &cThe first swap will be in " +
		formatter.format(timeBeforeSwap/60) +" minutes."));
	//Set the world time to 0 (day)
	world.setTime(0);
	//Re-enables mob-greifing
	world.setGameRule(GameRule.MOB_GRIEFING, true);
	playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
	startSwapping();
    }

    private static void startSwapping() {
	boolean tenSecondWarning = Main.plugin.getConfig().getBoolean("tenSecondWarning");
	int minimumSwapTime = Main.plugin.getConfig().getInt("minimumSwapTime");
	int maximumAdditionalTime = Main.plugin.getConfig().getInt("maximumAdditionalTime");
	new BukkitRunnable() {
	    @Override
	    public void run() {
		if(tenSecondWarning == true) {
		    if(timeBeforeSwap == 10) {
			//For every player online
			for(Player players : Bukkit.getOnlinePlayers()) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "10", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
			timeBeforeSwap--;
		    }
		    else if(timeBeforeSwap == 9) {
			//For every player online
			for(Player players : Bukkit.getOnlinePlayers()) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "9", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
			timeBeforeSwap--;
		    }
		    else if(timeBeforeSwap == 8) {
			//For every player online
			for(Player players : Bukkit.getOnlinePlayers()) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "8", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
			timeBeforeSwap--;
		    }
		    else if(timeBeforeSwap == 7) {
			//For every player online
			for(Player players : Bukkit.getOnlinePlayers()) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "7", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
			timeBeforeSwap--;
		    }
		    else if(timeBeforeSwap == 6) {
			//For every player online
			for(Player players : Bukkit.getOnlinePlayers()) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "6", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
			timeBeforeSwap--;
		    }
		}
		if(timeBeforeSwap == 5) {
		    //For every player online
		    for(Player players : Bukkit.getOnlinePlayers()) {
			//Display a title screen
			players.sendTitle(ChatColor.RED + "5", "", 0, 20, 10);
			//Play a note
			players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
		    }
		    timeBeforeSwap--;
		}
		else if(timeBeforeSwap == 4) {
		    //For every player online
		    for(Player players : Bukkit.getOnlinePlayers()) {
			//Display a title screen
			players.sendTitle(ChatColor.RED + "4", "", 0, 20, 10);
			//Play a note
			players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
		    }
		    timeBeforeSwap--;
		}
		else if(timeBeforeSwap == 3) {
		    //For every player online
		    for(Player players : Bukkit.getOnlinePlayers()) {
			//Display a title screen
			players.sendTitle(ChatColor.RED + "3", "", 0, 20, 10);
			//Play a note
			players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
		    }
		    timeBeforeSwap--;
		}
		else if(timeBeforeSwap == 2) {
		    //For every player online
		    for(Player players : Bukkit.getOnlinePlayers()) {
			//Display a title screen
			players.sendTitle(ChatColor.RED + "2", "", 0, 20, 10);
			//Play a note
			players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
		    }
		    timeBeforeSwap--;
		}
		else if(timeBeforeSwap == 1) {
		    //For every player online
		    for(Player players : Bukkit.getOnlinePlayers()) {
			//Display a title screen
			players.sendTitle(ChatColor.RED + "1", "", 0, 20, 10);
			//Play a note
			players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
		    }
		    timeBeforeSwap--;
		}
		else if(timeBeforeSwap == 0) {
		    swap();
		    timeBeforeSwap = random.nextInt(maximumAdditionalTime) + minimumSwapTime;
		}
		else {
		    timeBeforeSwap--;
		}
	    }
	}.runTaskTimer(Main.plugin, 0L, 20L);
    }

    public static void swap() {
	int size = playerList.size();
	if(size == 2) {
	    Player player1 = playerList.get(0);
	    Player player2 = playerList.get(1);
	    Location location1 = player1.getLocation();
	    Location location2 = player2.getLocation();
	    player1.teleport(location2);
	    player2.teleport(location1);
	    player1.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &eSwapped with &f&l" + player2.getName() + "&e."));
	    player2.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &eSwapped with &f&l" + player1.getName() + "&e."));
	}
	else {
	    Collections.shuffle(playerList, random);
	    Player[] playerArray = playerList.toArray(new Player[size]);
	    Location[] locationArray = new Location[size];
	    for (int i = 0; i < size; i++)
		locationArray[i] = playerArray[i].getLocation().clone();
	    for (int i = 0; i < size; i++) {
		Player next = playerArray[(i + 1 == size) ? 0 : (i + 1)];
		Player prev = playerArray[(i == 0) ? (size - 1) : (i - 1)];
		playerArray[i].sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &eYou're at &f&l" + next.getName() +
			"&e's location. &f&l" + prev.getName() + " &eis at yours."));
		playerArray[i].teleport(locationArray[(i + 1 == size) ? 0 : (i + 1)]);
	    }
	}
    }

    public static void reset() {
	Main.preGame = true;
	world.setGameRule(GameRule.MOB_GRIEFING, false);
	Main.plugin.reloadConfig();
	PreGameManager.playersReady.clear();
	timeBeforeSwap = Main.plugin.getConfig().getInt("gracePeriodTime");
	for(Player player : Bukkit.getOnlinePlayers()) {
	    player.setGameMode(GameMode.SURVIVAL);
	    player.setHealth(20);
	    player.setFoodLevel(20);
	    player.setSaturation(5);
	    player.teleport(world	.getSpawnLocation());
	    player.getInventory().clear();
	    player.getInventory().addItem(PreGameManager.notReadyWool);
	    if(!playerList.isEmpty() && !playerList.contains(player))
		playerList.add(player);
	}
	Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &fGame has been reset!"));
    }
}
