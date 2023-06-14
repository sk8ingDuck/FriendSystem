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

		FriendSystemGUI.getInstance().getPluginMessaging().getRequests(player, requests ->
				RyseInventory.builder()
						.title(guiConfig.getRequestsGuiTitle())
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

								requests.forEach(request -> {
									ItemStack item = getHead(guiConfig.getItem("requestsMenu.requests"),
											request.getName(), request.getRequestDate(), request.getExpiracy());
									pagination.addItem(IntelligentItem.of(item, event ->
											GuiManager.guiSelectedPlayer.open(player, request.getUuid(), request.getName())));
								});

								contents.set(5, 0, IntelligentItem.of(guiConfig.getItem("requestsMenu.previousPage"),
										clickEvent -> {
											if (!pagination.isFirst()) {
												RyseInventory currentInventory = pagination.inventory();
												currentInventory.open(player, pagination.previous().page());
											}
										}));

								contents.set(5, 8, IntelligentItem.of(guiConfig.getItem("requestsMenu.nextPage"),
										clickEvent -> {
											if (!pagination.isLast()) {
												RyseInventory currentInventory = pagination.inventory();
												currentInventory.open(player, pagination.next().page());
											}
										}));

								contents.set(5, 4, IntelligentItem.of(guiConfig.getItem("requestsMenu.back"),
										clickEvent -> GuiManager.guiMainMenu.open(player)));
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
