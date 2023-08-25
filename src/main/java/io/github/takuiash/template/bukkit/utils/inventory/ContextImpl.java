package io.github.takuiash.template.bukkit.utils.inventory;

import org.bukkit.entity.Player;

public class ContextImpl implements ViewContext {

	private Player player;
	
	private InventoryView currentView;
	private InventoryView previousView;
	
	public ContextImpl(Player player, InventoryView currentView) {
		this.player = player;
		
		this.currentView = currentView;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void open(InventoryView view) {
		this.previousView = currentView;
		view.open(player, this);
	}
	
	public void close() {
		currentView.close();
	}
	
	public void previousPage() {
		currentView.previousPage();
	}

	public void nextPage() {
		currentView.nextPage();
	}

	public boolean hasPreviousView() {
		return previousView != null;
	}

	public void openPreviousView() {
		if(hasPreviousView())		
			open(previousView);
	}

	public InventoryView getPreviousView() {
		return previousView;
	}

}
