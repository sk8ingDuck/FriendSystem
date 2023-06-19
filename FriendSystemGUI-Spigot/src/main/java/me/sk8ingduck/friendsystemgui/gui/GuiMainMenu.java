package me.sk8ingduck.friendsystemgui.gui;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.Pagination;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import io.github.rysefoxx.inventory.plugin.pagination.SlotIterator;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.pluginmessage.Friend;
import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import me.sk8ingduck.friendsystemgui.util.SkullCreator;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Comparator;
import java.util.stream.Collectors;

public class GuiMainMenu {

	public void open(Player player) {
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();
		int rows = guiConfig.getMainGuiRows();

		FriendSystemGUI.getInstance().getPluginMessaging().getFriends(player,
				friends -> FriendSystemGUI.getInstance().getPluginMessaging().getOwnInfo(player,
						(server, onlineTime, status) -> RyseInventory.builder()
								.title(guiConfig.getMainGuiTitle())
								.rows(rows)
								.provider(new InventoryProvider() {
									@Override
									public void init(Player player, InventoryContents contents) {
										int startSlot = guiConfig.getSlot("mainMenu.friends.startSlot", false);
										int friendsPerPage = guiConfig.getSlot("mainMenu.friends.friendsPerPage", false);

										for (int i = 0; i < startSlot; i++) {
											contents.set(i, guiConfig.getItem("mainMenu.backgroundItem", false));
										}
										for (int i = startSlot + friendsPerPage; i < rows * 9; i++) {
											contents.set(i, guiConfig.getItem("mainMenu.backgroundItem", false));
										}

										Pagination pagination = contents.pagination();
										pagination.setItemsPerPage(friendsPerPage);

										pagination.iterator(SlotIterator.builder()
												.startPosition(startSlot)
												.type(SlotIterator.SlotIteratorType.HORIZONTAL)
												.build());

										friends.stream().sorted(Comparator.comparing(Friend::isOnline, Comparator.reverseOrder())
														.thenComparing(Friend::isFavourite, Comparator.reverseOrder()))
												.forEach(friend -> {
													ItemStack item = guiConfig.getItem("mainMenu.friends." + (friend.isOnline()
															? "online"
															: "offline")
															+ "Friend" + (friend.isFavourite() ? "Favourite" : ""), false);

													pagination.addItem(IntelligentItem.of(getHead(item, friend.getName(),
																	friend.isOnline(), friend.getLastSeen(),
																	friend.getServer(), friend.getStatus()),
															clickEvent -> GuiManager.guiSelectedPlayer
																	.open(player, friend.getUuid(), friend.getName())));
												});

										int previousPageSlot = guiConfig.getSlot("mainMenu.previousPage");
										if (previousPageSlot != -1) {
											contents.set(previousPageSlot,
													IntelligentItem.of(guiConfig.getItem("mainMenu.previousPage"), clickEvent -> {
														if (!pagination.isFirst()) {
															RyseInventory currentInventory = pagination.inventory();
															currentInventory.open(player, pagination.previous().page());
														}
													}));
										}
										int nextPageSlot = guiConfig.getSlot("mainMenu.nextPage");
										if (nextPageSlot != -1) {
											contents.set(nextPageSlot,
													IntelligentItem.of(guiConfig.getItem("mainMenu.nextPage"), clickEvent -> {
														if (!pagination.isLast()) {
															RyseInventory currentInventory = pagination.inventory();
															currentInventory.open(player, pagination.next().page());
														}
													}));
										}

										int settingsSlot = guiConfig.getSlot("mainMenu.settings");
										if (settingsSlot != -1) {
											contents.set(settingsSlot,
													IntelligentItem.of(guiConfig.getItem("mainMenu.settings"),
															clickEvent -> GuiManager.guiSettings.open(player)));
										}

										int requestsSlot = guiConfig.getSlot("mainMenu.requests");
										if (requestsSlot != -1) {
											contents.set(requestsSlot,
													IntelligentItem.of(guiConfig.getItem("mainMenu.requests"),
															clickEvent -> GuiManager.guiRequests.open(player)));
										}

										int ownInfoSlot = guiConfig.getSlot("mainMenu.ownInfo");
										if (ownInfoSlot != -1) {
											contents.set(ownInfoSlot, getHead(guiConfig.getItem("mainMenu.ownInfo"),
													player.getName(), true, onlineTime, server, status));
										}
									}
								})
								.build(FriendSystemGUI.getInstance()).open(player)));

	}

	public ItemStack getHead(ItemStack template, String name, boolean online, String lastSeen, String server, String status) {
		ItemStack itemStack = template.clone();
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (itemMeta == null) return itemStack;

		itemMeta.setDisplayName(itemMeta.getDisplayName().replaceAll("%PLAYER%", name));
		itemMeta.setLore(itemMeta.getLore().stream()
				.map(lore -> lore.replaceAll(online ? "%ONLINE_TIME%" : "%LAST_SEEN%", lastSeen)
						.replaceAll("%SERVER%", server)
						.replaceAll("%STATUS%", status))
				.collect(Collectors.toList()));
		itemStack.setItemMeta(itemMeta);
		if (online) SkullCreator.itemWithName(itemStack, name);

		return itemStack;
	}

}