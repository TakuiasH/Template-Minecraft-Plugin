package io.github.takuiash.template.bukkit.utils.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface ButtonAction {
	public void run(InventoryClickEvent event, ViewContext context);
}
