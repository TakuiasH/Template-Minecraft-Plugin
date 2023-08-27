package io.github.takuiash.template.api;

import io.github.takuiash.template.bukkit.TemplatePlugin;

public class TemplatePluginProvider {

	private static TemplatePlugin pluginInstance = null;
	  
	public static TemplatePlugin get() {
		if (pluginInstance == null)
			throw new IllegalStateException("The plugin hasn't finished starting up yet, or failed to load!"); 
		return pluginInstance;
	}
  
	public static void register(TemplatePlugin plugin) {
		pluginInstance = plugin;
	}
	
}
