package lucien.SabatageSwap.game;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lucien.SabarageSwap.events.PlayerInteractHandler;
import lucien.SabarageSwap.events.PlayerJoinHandler;
import lucien.SabarageSwap.events.PlayerMoveHandler;
import lucien.SabarageSwap.events.PreGameCancelledHandler;

public class Main extends JavaPlugin {
    //Stores "this" plugin for use in other classes
    public static Main plugin;

    //Sets the preGame and graceMode status to true upon server load
    public static boolean preGame = true;
    public static boolean postGame = false;

    //EventListeners for SpigotEvents
    PlayerInteractHandler playerInteractHandler = new PlayerInteractHandler();
    PlayerJoinHandler playerJoinHandler = new PlayerJoinHandler();
    PlayerMoveHandler playerMoveHandler = new PlayerMoveHandler();
    PreGameCancelledHandler preGameHandler = new PreGameCancelledHandler();
    
    @Override
    public void onEnable() {
	//Stores "this" for use in other classes
	plugin = this;

	//Sets vanilla gameRules
	setGamerules();

	//Prepares game functions
	registerHandlers();
	PreGameManager.generatePreGameItems();
	
	getCommand("swap").setExecutor(new SwapCommand());
	getCommand("test").setExecutor(new TestCommand());
    }

    private void setGamerules() {
	World world = getServer().getWorld("world");
	world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
	world.setGameRule(GameRule.SPAWN_RADIUS, 1);
	world.setGameRule(GameRule.MOB_GRIEFING, false);
	world.setSpawnLocation(0, 100, 0);
    }

    private void registerHandlers() {
	PluginManager pluginManager = getServer().getPluginManager();
	pluginManager.registerEvents(playerInteractHandler, this);
	pluginManager.registerEvents(playerJoinHandler, this);
	pluginManager.registerEvents(playerMoveHandler, this);
	pluginManager.registerEvents(preGameHandler, this);
    }
}