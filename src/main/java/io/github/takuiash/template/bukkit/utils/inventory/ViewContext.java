package io.github.takuiash.template.bukkit.utils.inventory;

import org.bukkit.entity.Player;

public interface ViewContext {
	
	Player getPlayer();
	
	void open(InventoryView view);
	void close();
	
	void previousPage();
	void nextPage();
	
	boolean hasPreviousView();
	void openPreviousView();
	InventoryView getPreviousView();
	
}