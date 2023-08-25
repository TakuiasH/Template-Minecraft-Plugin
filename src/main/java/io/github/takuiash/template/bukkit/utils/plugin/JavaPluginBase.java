package io.github.takuiash.template.bukkit.utils.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.takuiash.template.bukkit.utils.command.CommandManager;
import io.github.takuiash.template.core.data.files.ConfigFile;

public class JavaPluginBase extends JavaPlugin {
		
	private final CommandManager cmdManager = new CommandManager(this);
	
	private final ConfigFile config = new ConfigFile(this);
	
	public String getCommandsPackageName() {
		return "io.github.takuiash";
	}
	
	@Override
	public void onLoad() {
		onPluginLoad();
	}
	
	@Override
	public void onEnable() {
		cmdManager.registerCommands(getCommandsPackageName());
		
		onPluginEnable();
	}
	
	@Override
	public void onDisable() {
		onPluginDisable();
	}
	
	public void onPluginLoad() {}
	public void onPluginEnable() {}
	public void onPluginDisable() {}

	public CommandManager getCommandManager() {
		return cmdManager;
	}
	
	public ConfigFile getConfig() {
		return config;
	}
}
