package io.github.takuiash.template.bukkit;

import io.github.takuiash.template.api.TemplatePluginProvider;
import io.github.takuiash.template.bukkit.utils.message.lang.Language;
import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;

public class TemplatePlugin extends JavaPluginBase {
	
	@Override
	public String getCommandsPackageName() {
		return "io.github.takuiash.template.bukkit.commands";
	}
	
	public void onPluginLoad() {
		TemplatePluginProvider.register(this);
		
		getMessageManager().registerMessageFile(Language.en_us, "lang/messages-en_us.yml");
	}
	
	public void onPluginEnable() {
	}
}
