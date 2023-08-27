package io.github.takuiash.template.bukkit.utils.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import io.github.takuiash.template.bukkit.utils.Utils;

public class InventoryButton extends ItemStack {

	private ButtonAction action;

	public InventoryButton setAction(ButtonAction action) {
		this.action = action;
		return this;
	}

	protected ButtonAction getAction() {
		if(action == null)
			action = (event, context) -> event.setCancelled(true);
		
		return action;
	}
	
	public InventoryButton glow() {
		ItemMeta meta = getItemMeta();
		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		setItemMeta(meta);
		return this;
	}

	public String getName() {
		return getItemMeta().getDisplayName();
	}

	public InventoryButton setName(String name) {
		ItemMeta meta = getItemMeta();
		meta.setDisplayName(Utils.translateHexColors(name));
		setItemMeta(meta);
		return this;
	}

	public List<String> getLore() {
		return getItemMeta().getLore();
	}

	public InventoryButton setLore(List<String> lore) {
		ItemMeta meta = getItemMeta();
		List<String> coloredLore = new ArrayList<>();
		for(String line : lore) 
			coloredLore.add(Utils.translateHexColors(line));
		
		meta.setLore(coloredLore);
		setItemMeta(meta);
		return this;
	}

	public InventoryButton setLore(String... lore) {
		setLore(Arrays.asList(lore));
		return this;
	}

	@SuppressWarnings("deprecation")
	public InventoryButton setOwner(String playerName) {
		SkullMeta meta = (SkullMeta) getItemMeta();
		meta.setOwner(playerName);
		setItemMeta(meta);
		return this;
	}

}
