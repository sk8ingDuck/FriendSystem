package me.sk8ingduck.friendsystemgui.config;

import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.LinkedHashMap;

public abstract class GuiConfig extends Config {
	protected String mainGuiTitle;
	protected int mainGuiRows;

	protected String requestsGuiTitle;
	protected int requestsGuiRows;

	protected String settingsGuiTitle;
	protected int settingsGuiRows;

	protected String selectedPlayerGuiTitle;
	protected int selectedPlayerGuiRows;

	protected LinkedHashMap<String, Integer> itemSlots;
	protected LinkedHashMap<String, ItemStack> items;

	public GuiConfig(String name, File path) {
		super(name, path);
		items = new LinkedHashMap<>();
		itemSlots = new LinkedHashMap<>();

		reformatConfig();

		loadValues();
		itemSlots.forEach((itemSlotPath, slot) -> itemSlots.put(itemSlotPath, (int) getPathOrSet(itemSlotPath, slot)));
		items.forEach((itemPath, item) -> items.put(itemPath, (ItemStack) getPathOrSet(itemPath, item)));

		super.save();

		comment =
						"########################################\n" +
						"#                                      #\n" +
						"#            Configuration             #\n" +
						"#                                      #\n" +
						"########################################\n"
						+ "\n" +
						"# You can change gui name, gui size, items and slots.\n" +
						"# If you don't want to use a specific item, set the slot to -1\n" +
						"\n" +
						"# You can also use hex colors!\n" +
						"# Make sure that the '#' is in \" \", otherwise it will be counted as a comment!!!\n";
		saveComment();
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

	public ItemStack getItem(String path, boolean suffix) {
		return suffix ? getItem(path) : items.get("gui." + path);
	}

	public ItemStack getItem(String path) {
		return items.get("gui." + path + ".item");
	}

	public ItemStack getGuiItem() {
		return items.get("guiItem");
	}
	public int getSlot(String path) {
		return itemSlots.get("gui." + path + ".slot");
	}

	public void reformatConfig() {
		renameSection("gui.mainMenu.item.ownInfo", "gui.mainMenu.ownInfo.item");
		renameSection("gui.mainMenu.item.previousPage", "gui.mainMenu.previousPage.item");
		renameSection("gui.mainMenu.item.nextPage", "gui.mainMenu.nextPage.item");
		renameSection("gui.mainMenu.item.settings", "gui.mainMenu.settings.item");
		renameSection("gui.mainMenu.item.requests", "gui.mainMenu.requests.item");
		renameSection("gui.mainMenu.item.onlineFriend", "gui.mainMenu.friends.onlineFriend");
		renameSection("gui.mainMenu.item.onlineFriendFavourite", "gui.mainMenu.friends.onlineFriendFavourite");
		renameSection("gui.mainMenu.item.offlineFriend", "gui.mainMenu.friends.offlineFriend");
		renameSection("gui.mainMenu.item.offlineFriendFavourite", "gui.mainMenu.friends.offlineFriendFavourite");
		if (getFileConfiguration().get("gui.mainMenu.item") != null)
			getFileConfiguration().set("gui.mainMenu.item", null);

		renameSection("gui.requestsMenu.item.request", "gui.requestsMenu.requests.item");
		renameSection("gui.requestsMenu.item.previousPage", "gui.requestsMenu.previousPage.item");
		renameSection("gui.requestsMenu.item.nextPage", "gui.requestsMenu.nextPage.item");
		renameSection("gui.requestsMenu.item.back", "gui.requestsMenu.back.item");
		if (getFileConfiguration().get("gui.requestsMenu.item") != null)
			getFileConfiguration().set("gui.requestsMenu.item", null);

		renameSection("gui.settingsMenu.item.back", "gui.settingsMenu.back.item");
		renameSection("gui.settingsMenu.item.toggleInvites", "gui.settingsMenu.toggleInvites.item");
		renameSection("gui.settingsMenu.item.toggleNotifies", "gui.settingsMenu.toggleNotifies.item");
		renameSection("gui.settingsMenu.item.toggleMsgs", "gui.settingsMenu.toggleMsgs.item");
		renameSection("gui.settingsMenu.item.toggleJump", "gui.settingsMenu.toggleJump.item");
		renameSection("gui.settingsMenu.item.changeStatus", "gui.settingsMenu.changeStatus.item");
		if (getFileConfiguration().get("gui.settingsMenu.item") != null)
			getFileConfiguration().set("gui.settingsMenu.item", null);

		renameSection("gui.settingsMenu.item.invitesOn", "gui.settingsMenu.invitesOn.item");
		renameSection("gui.settingsMenu.item.invitesOff", "gui.settingsMenu.invitesOff.item");
		renameSection("gui.settingsMenu.item.notifiesOn", "gui.settingsMenu.notifiesOn.item");
		renameSection("gui.settingsMenu.item.notifiesOff", "gui.settingsMenu.notifiesOff.item");
		renameSection("gui.settingsMenu.item.msgsOn", "gui.settingsMenu.msgsOn.item");
		renameSection("gui.settingsMenu.item.msgsOff", "gui.settingsMenu.msgsOff.item");
		renameSection("gui.settingsMenu.item.jumpOn", "gui.settingsMenu.jumpOn.item");
		renameSection("gui.settingsMenu.item.jumpOff", "gui.settingsMenu.jumpOff.item");

		renameSection("gui.selectedPlayerMenu.item.back", "gui.selectedPlayerMenu.back.item");
		renameSection("gui.selectedPlayerMenu.item.addFavourite", "gui.selectedPlayerMenu.addFavourite.item");
		renameSection("gui.selectedPlayerMenu.item.removeFavourite", "gui.selectedPlayerMenu.removeFavourite.item");
		renameSection("gui.selectedPlayerMenu.item.addFriend", "gui.selectedPlayerMenu.addFriend.item");
		renameSection("gui.selectedPlayerMenu.item.removeFriend", "gui.selectedPlayerMenu.removeFriend.item");
		renameSection("gui.selectedPlayerMenu.item.acceptFriend", "gui.selectedPlayerMenu.acceptFriend.item");
		renameSection("gui.selectedPlayerMenu.item.denyFriend", "gui.selectedPlayerMenu.denyFriend.item");
		renameSection("gui.selectedPlayerMenu.item.alreadyRequested", "gui.selectedPlayerMenu.alreadyRequested.item");
		renameSection("gui.selectedPlayerMenu.item.jump", "gui.selectedPlayerMenu.jump.item");
		if (getFileConfiguration().get("gui.selectedPlayerMenu.item") != null)
			getFileConfiguration().set("gui.selectedPlayerMenu.item", null);
	}

	private void renameSection(String from, String to) {
		Object object = getFileConfiguration().get(from);
		if (object == null) return;

		getFileConfiguration().set(from, null);
		getFileConfiguration().set(to, object);
	}

}