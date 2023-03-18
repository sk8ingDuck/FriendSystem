package me.sk8ingduck.friendsystemgui.config;

import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;

public abstract class GuiConfig extends Config {
	protected String mainGuiTitle;
	protected String requestsGuiTitle;
	protected String settingsGuiTitle;
	protected String selectedPlayerGuiTitle;
	protected HashMap<String, ItemStack> items;


	public GuiConfig(String name, File path) {
		super(name, path);
		items = new HashMap<>();
		loadValues();
		items.forEach((itemPath, item) -> items.put(itemPath, (ItemStack) getPathOrSet(itemPath, item)));
	}

	public abstract void loadValues();

	public String getMainGuiTitle() {
		return mainGuiTitle;
	}

	public String getRequestsGuiTitle() {
		return requestsGuiTitle;
	}

	public String getSettingsGuiTitle() {
		return settingsGuiTitle;
	}

	public String getSelectedPlayerGuiTitle() {
		return selectedPlayerGuiTitle;
	}

	public ItemStack get(String path) {
		return items.get(path);
	}
}