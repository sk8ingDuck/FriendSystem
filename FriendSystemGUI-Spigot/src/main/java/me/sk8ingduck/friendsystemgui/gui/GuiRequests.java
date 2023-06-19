package me.sk8ingduck.friendsystemgui.gui;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.Pagination;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import io.github.rysefoxx.inventory.plugin.pagination.SlotIterator;
import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.Collectors;

public class GuiRequests {

	public void open(Player player) {
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();
		int rows = guiConfig.getMainGuiRows();

		FriendSystemGUI.getInstance().getPluginMessaging().getRequests(player, requests ->
				RyseInventory.builder()
						.title(guiConfig.getRequestsGuiTitle())
						.rows(rows)
						.provider(new InventoryProvider() {
							@Override
							public void init(Player player, InventoryContents contents) {
								int startSlot = guiConfig.getSlot("requestsMenu.requests.startSlot", false);
								int requestsPerPage = guiConfig.getSlot("requestsMenu.requests.requestsPerPage", false);

								for (int i = 0; i < startSlot; i++) {
									contents.set(i, guiConfig.getItem("requestsMenu.backgroundItem", false));
								}
								for (int i = startSlot + requestsPerPage; i < rows * 9; i++) {
									contents.set(i, guiConfig.getItem("requestsMenu.backgroundItem", false));
								}

								Pagination pagination = contents.pagination();
								pagination.setItemsPerPage(requestsPerPage);

								pagination.iterator(SlotIterator.builder()
										.startPosition(startSlot)
										.type(SlotIterator.SlotIteratorType.HORIZONTAL)
										.build());

								requests.forEach(request -> {
									ItemStack item = getHead(guiConfig.getItem("requestsMenu.requests"),
											request.getName(), request.getRequestDate(), request.getExpiracy());
									pagination.addItem(IntelligentItem.of(item, event ->
											GuiManager.guiSelectedPlayer.open(player, request.getUuid(), request.getName())));
								});

								int previousPageSlot = guiConfig.getSlot("requestsMenu.previousPage");
								if (previousPageSlot != -1) {
									contents.set(previousPageSlot, IntelligentItem.of(guiConfig.getItem("requestsMenu.previousPage"),
											clickEvent -> {
												if (!pagination.isFirst()) {
													RyseInventory currentInventory = pagination.inventory();
													currentInventory.open(player, pagination.previous().page());
												}
											}));
								}

								int nextPageSlot = guiConfig.getSlot("requestsMenu.nextPage");
								if (nextPageSlot != -1) {
									contents.set(nextPageSlot, IntelligentItem.of(guiConfig.getItem("requestsMenu.nextPage"),
											clickEvent -> {
												if (!pagination.isLast()) {
													RyseInventory currentInventory = pagination.inventory();
													currentInventory.open(player, pagination.next().page());
												}
											}));
								}

								int backSlot = guiConfig.getSlot("requestsMenu.back");
								if (backSlot != -1) {
									contents.set(backSlot, IntelligentItem.of(guiConfig.getItem("requestsMenu.back"),
											clickEvent -> GuiManager.guiMainMenu.open(player)));
								}
							}
						})
						.build(FriendSystemGUI.getInstance())
						.open(player)
		);
	}


	public ItemStack getHead(ItemStack template, String name, String requestDate, String expiracy) {
		ItemStack itemStack = template.clone();
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (itemMeta == null) return itemStack;

		itemMeta.setDisplayName(itemMeta.getDisplayName().replaceAll("%PLAYER%", name));
		if (itemMeta.getLore() != null)
			itemMeta.setLore(itemMeta.getLore().stream()
					.map(lore -> lore.replaceAll("%REQUEST_DATE%", requestDate)
							.replaceAll("%EXPIRES_IN%", expiracy))
					.collect(Collectors.toList()));

		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}
