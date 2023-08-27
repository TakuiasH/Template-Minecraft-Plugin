package io.github.takuiash.template.bukkit.utils.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.takuiash.template.bukkit.utils.command.CommandManager;
import io.github.takuiash.template.bukkit.utils.message.MessageExecutor;
import io.github.takuiash.template.bukkit.utils.message.api.LanguageManager;
import io.github.takuiash.template.bukkit.utils.message.api.MessageManager;
import io.github.takuiash.template.bukkit.utils.message.lang.LanguageExecutor;
import io.github.takuiash.template.bukkit.utils.storage.StorageConfig;
import io.github.takuiash.template.core.data.files.ConfigFile;

public class JavaPluginBase extends JavaPlugin {
		
	private final CommandManager cmdManager = new CommandManager(this);
	
	private final ConfigFile config = new ConfigFile(this);
	private final StorageConfig storageConfig = new StorageConfig(this);
	
	private final LanguageManager languageManager = new LanguageExecutor(this);
	private final MessageManager messageManager = new MessageExecutor(this);
	
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
	
	public LanguageManager getLanguageManager() {
		return languageManager;
	}
	
	public MessageManager getMessageManager() {
		return messageManager;
	}
	
	public StorageConfig getStorageConfig() {
		return storageConfig;
	}
}
