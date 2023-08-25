package io.github.takuiash.template.bukkit.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import io.github.takuiash.template.bukkit.utils.command.Arguments;
import io.github.takuiash.template.bukkit.utils.command.Command;
import io.github.takuiash.template.bukkit.utils.command.JavaCommand;
import io.github.takuiash.template.bukkit.utils.command.SenderType;

@Command(name = "test", senderType = SenderType.PLAYER, permission = "template.test")
public class TestCommand extends JavaCommand {

	UserRepository userRepo = new UserRepository();
	
	@Override
	public boolean perform(CommandSender sender, Arguments args) {
		UserEntity entity = new UserEntity();
		entity.setUsername("TakuiasH");
		
		userRepo.save(entity);
		System.out.println(userRepo.find(entity.getId()));
		
		entity.setUsername("Vovozona");
		userRepo.save(entity);
		System.out.println(userRepo.find(entity.getId()));

		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, Arguments args) {
		return null;
	}
	
}
