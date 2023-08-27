package io.github.takuiash.template.bukkit.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import io.github.takuiash.template.bukkit.utils.command.Arguments;
import io.github.takuiash.template.bukkit.utils.command.Command;
import io.github.takuiash.template.bukkit.utils.command.JavaCommand;
import io.github.takuiash.template.bukkit.utils.command.SenderType;
import io.github.takuiash.template.bukkit.utils.message.api.MessageManager;
import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;

@Command(name = "test", senderType = SenderType.ALL, permission = "template.test")
public class TestCommand extends JavaCommand {

	public TestCommand(JavaPluginBase plugin) {
		super(plugin);
	}

	public boolean perform(CommandSender sender, Arguments args) {
		MessageManager.get().sendMessage("test.hello-world", sender);
		return true;
	}

	public List<String> tabComplete(CommandSender sender, Arguments args) {
		return null;
	}
	
}
