package io.github.takuiash.template.bukkit.utils.command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.reflect.ClassPath;

import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;

public class CommandManager {
	
	private final JavaPlugin plugin;
	
	public CommandManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void registerCommands(String packageName) {
		long time = System.currentTimeMillis();
		int registeredCommands = 0;
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[" + plugin.getName() + "] Starting commands registration system...");

		for(Class<?> c : getAnnotatedClasses(packageName)) {
			try {
				JavaCommand command = ((JavaCommand) c.getConstructor().newInstance()).create(c.getAnnotation(Command.class));
				PluginCommand pluginCommand = plugin.getCommand(command.getName());
				
				if(pluginCommand == null)
					throw new NullPointerException("you need to fill the plugin.yml with the command names ");

				pluginCommand.setExecutor(command);
				pluginCommand.setTabCompleter(command);
				
				registeredCommands++;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (ClassCastException e) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[" + plugin.getName() + "] Command " + c.getName() + " is not a instance of JavaCommand.");
			}
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[" + plugin.getName() + "] " + registeredCommands + " Commands registered. (" + (System.currentTimeMillis() - time) + "ms)");
	}
	
	private List<Class<?>> getAnnotatedClasses(String packageName) {
		try {
			return ClassPath.from(JavaPluginBase.class.getClassLoader()).getAllClasses().stream().filter(clazz -> clazz.getPackageName().startsWith(packageName)).map(clazz -> clazz.load()).filter(clazz -> clazz.isAnnotationPresent(Command.class)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}
}
