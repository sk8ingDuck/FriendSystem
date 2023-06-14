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
		FriendSystemGUI.getInstance().getPluginMessaging().getFriends(player,
				friends -> FriendSystemGUI.getInstance().getPluginMessaging().getOwnInfo(player,
						(server, onlineTime, status) -> RyseInventory.builder()
								.title(guiConfig.getMainGuiTitle())
								.rows(6)
								.provider(new InventoryProvider() {
									@Override
									public void init(Player player, InventoryContents contents) {
										for (int i = 0; i < 9; i++) {
											contents.set(4, i, ItemCreator.createGlassPane(DyeColor.BLACK, " "));
											contents.set(5, i, ItemCreator.createGlassPane(DyeColor.BLACK, " "));
										}

										Pagination pagination = contents.pagination();
										pagination.setItemsPerPage(36);

										pagination.iterator(SlotIterator.builder()
												.startPosition(0, 0)
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

										contents.set(5, 0,
												IntelligentItem.of(guiConfig.getItem("mainMenu.previousPage"), clickEvent -> {
													if (!pagination.isFirst()) {
														RyseInventory currentInventory = pagination.inventory();
														currentInventory.open(player, pagination.previous().page());
													}
												}));

										contents.set(5, 8,
												IntelligentItem.of(guiConfig.getItem("mainMenu.nextPage"), clickEvent -> {
													if (!pagination.isLast()) {
														RyseInventory currentInventory = pagination.inventory();
														currentInventory.open(player, pagination.next().page());
													}
												}));

										contents.set(5, 3,
												IntelligentItem.of(guiConfig.getItem("mainMenu.settings"),
														clickEvent -> GuiManager.guiSettings.open(player)));
										contents.set(5, 5,
												IntelligentItem.of(guiConfig.getItem("mainMenu.requests"),
														clickEvent -> GuiManager.guiRequests.open(player)));

										contents.set(5, 4, getHead(guiConfig.getItem("mainMenu.ownInfo"),
												player.getName(), true, onlineTime, server, status));
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