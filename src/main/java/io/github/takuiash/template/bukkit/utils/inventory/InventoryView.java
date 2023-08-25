package io.github.takuiash.template.bukkit.utils.inventory;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.scheduler.BukkitTask;

import io.github.takuiash.template.api.TemplatePluginProvider;

public abstract class InventoryView implements InventoryHolder {

	private int page = 0;
	private Inventory inventory;
	private BukkitTask updateTask;
	private ViewContext viewContext = null;

	private Map<Integer, InventoryButton> buttonMap = new HashMap<Integer, InventoryButton>();
	
	public InventoryView(int rows) {
		this.inventory = Bukkit.createInventory(this, rows * 9);
	}
	
	public InventoryView(String title, int rows) {
		this.inventory = Bukkit.createInventory(this, rows * 9, title);
	}
	
	public abstract void onRender(ViewContext context);
	
	public void onOpen(ViewContext context) {}
	public void onUpdate(ViewContext context) {}
	public void onClose(ViewContext context) {}
	
	public void put(int x, int y, InventoryButton button) {
		this.put(y * 9 + x, button);
	}
	
	public void remove(int x, int y) {
		this.remove(y * 9 + x);
	}
	
	public void put(int slot, InventoryButton button) {
		buttonMap.put(slot, button);
		inventory.setItem(slot, button);
	}
	
	public void remove(int slot) {
		buttonMap.remove(slot);
		inventory.clear(slot);
	}
	
	public void open(CommandSender target, long updateRate) {
		this.open(target);
		updateTask = Bukkit.getScheduler().runTaskTimer(TemplatePluginProvider.get(), () -> update(), 0, updateRate);
	}
	
	public void open(CommandSender target) {
		this.viewContext = new ContextImpl((Player) target, this);
		onOpen(viewContext);
		onRender(viewContext);
		((HumanEntity) target).openInventory(getInventory());
	}
	
	public void update() {
		onUpdate(viewContext);
		onRender(viewContext);		
		viewContext.getPlayer().updateInventory();
	}
	
	public void close() {
		viewContext.getPlayer().closeInventory();
		onClose(viewContext);
		if(updateTask != null) { updateTask.cancel(); updateTask = null; }
	}

	public void reopen() {
		onUpdate(viewContext);
		onRender(viewContext);
		viewContext.getPlayer().openInventory(getInventory());
	}
	
	public int getPage() {
		return page;
	}
	
	public void nextPage() {
		page++;
		reopen();
	}
	
	public void previousPage() {
		page--;
		reopen();
	}
	
	protected void open(CommandSender target, ViewContext context) {
		this.viewContext = context;
		onOpen(viewContext);
		onRender(viewContext);
		((HumanEntity) target).openInventory(getInventory());
	}
}
