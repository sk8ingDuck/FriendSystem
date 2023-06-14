package me.sk8ingduck.friendsystemgui.config;

import java.io.File;

public class SettingsConfig extends Config {

	private final String language;
	private final boolean guiEnabled;
	private final int guiSlot;
	private final boolean rightClickPlayerToOpenMenuEnabled;
	private final boolean canDropItem;
	private final boolean canMoveItemInInventory;

	private final boolean canPlaceItem;

	public SettingsConfig(String name, File path) {
		super(name, path);

		language = (String) getPathOrSet("language", "english", false);
		guiEnabled = (boolean) getPathOrSet("guiEnabled", true);
		guiSlot = (int) getPathOrSet("guiSlot", 8);
		rightClickPlayerToOpenMenuEnabled = (Boolean) getPathOrSet("rightClickPlayerToOpenMenuEnabled", true);
		canDropItem = (boolean) getPathOrSet("canDropItem", false);
		canMoveItemInInventory = (boolean) getPathOrSet("canMoveItemInInventory", false);
		canPlaceItem = (boolean) getPathOrSet("canPlaceItem", false);
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

	public boolean isCanPlaceItem() {
		return canPlaceItem;
	}

	public int getGuiSlot() {
		return guiSlot;
	}

	public boolean isRightClickPlayerToOpenMenuEnabled() {
		return rightClickPlayerToOpenMenuEnabled;
	}
}

