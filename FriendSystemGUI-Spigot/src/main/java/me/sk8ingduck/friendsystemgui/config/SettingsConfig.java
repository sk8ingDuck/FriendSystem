package me.sk8ingduck.friendsystemgui.config;

import java.io.File;

public class SettingsConfig extends Config {

	private final String language;
	private final boolean guiEnabled;
	private final int guiSlot;
	private final boolean rightClickPlayerToOpenMenuEnabled;
	private final boolean canDropItem;
	private final boolean canMoveItemInInventory;

	public SettingsConfig(String name, File path) {
		super(name, path);

		this.language = (String) getPathOrSet("language", "german", false);

		guiEnabled = (boolean) getPathOrSet("guiEnabled", true);
		guiSlot = (int) getPathOrSet("guiSlot", 8);
		rightClickPlayerToOpenMenuEnabled = (Boolean) getPathOrSet("rightClickPlayerToOpenMenuEnabled", true);
		canDropItem = (boolean) getPathOrSet("canDropItem", false);
		canMoveItemInInventory = (boolean) getPathOrSet("canMoveItemInInventory", false);
	}

	public String getLanguage() {
		return language;
	}

	public boolean isGuiEnabled() {
		return guiEnabled;
	}

	public boolean isCanDropItem() {
		return canDropItem;
	}

	public boolean isCanMoveItemInInventory() {
		return canMoveItemInInventory;
	}
	public int getGuiSlot() {
		return guiSlot;
	}

	public boolean isRightClickPlayerToOpenMenuEnabled() {
		return rightClickPlayerToOpenMenuEnabled;
	}
}

