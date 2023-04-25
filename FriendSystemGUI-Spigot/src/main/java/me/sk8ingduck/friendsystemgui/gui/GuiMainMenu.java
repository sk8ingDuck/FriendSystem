package me.sk8ingduck.friendsystemgui.gui;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.pluginmessage.Friend;
import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import me.sk8ingduck.friendsystemgui.util.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.ipvp.canvas.Menu;
import org.ipvp.canvas.mask.BinaryMask;
import org.ipvp.canvas.mask.Mask;
import org.ipvp.canvas.mask.RecipeMask;
import org.ipvp.canvas.paginate.PaginatedMenuBuilder;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.slot.SlotSettings;
import org.ipvp.canvas.type.ChestMenu;

import java.util.Comparator;
import java.util.stream.Collectors;

public class GuiMainMenu {

	public void open(Player player) {
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();
		ChestMenu.Builder gui = ChestMenu.builder(6).title(guiConfig.getMainGuiTitle());

		Mask friendHeadSlots = BinaryMask.builder(gui.getDimensions())
				.pattern("111111111")
				.pattern("111111111")
				.pattern("111111111")
				.pattern("111111111").build();


		PaginatedMenuBuilder builder = PaginatedMenuBuilder.builder(gui)
				.slots(friendHeadSlots)
				.previousButton(guiConfig.get("gui.mainMenu.item.previousPage"))
				.previousButtonSlot(45)
				.previousButtonEmpty(guiConfig.get("gui.mainMenu.item.previousPage"))
				.nextButton(guiConfig.get("gui.mainMenu.item.nextPage"))
				.nextButtonSlot(53)
				.nextButtonEmpty(guiConfig.get("gui.mainMenu.item.nextPage"))

				.newMenuModifier(menu -> {
					Mask menuMask = RecipeMask.builder(menu)
							.item('-', ItemCreator.createGlassPane(DyeColor.BLACK, " "))
							.row(5).pattern("---------")
							.row(6).pattern("---------")
							.build();
					menuMask.apply(menu);
					Slot settings = menu.getSlot(48);
					Slot requests = menu.getSlot(50);

					settings.setItem(guiConfig.get("gui.mainMenu.item.settings"));
					requests.setItem(guiConfig.get("gui.mainMenu.item.requests"));



					settings.setClickHandler((player1, clickInformation) -> GuiManager.guiSettings.open(player1));
					requests.setClickHandler((player1, clickInformation) -> GuiManager.guiRequests.open(player1));
				});


		FriendSystemGUI.getInstance().getPluginMessaging().getFriends(player, friends -> {
			friends.stream().sorted(Comparator.comparing(Friend::isOnline, Comparator.reverseOrder())
							.thenComparing(Friend::isFavourite, Comparator.reverseOrder()))
					.forEach(friend -> {
						ItemStack item = guiConfig.get("gui.mainMenu.item."
								+ (friend.isOnline() ? "online" : "offline") + "Friend" + (friend.isFavourite() ? "Favourite" : ""));

						builder.addItem(SlotSettings.builder()
								.item(getHead(item, friend.getName(), friend.isOnline(), friend.getLastSeen(), friend.getServer(), friend.getStatus()))
								.clickHandler((player1, click) -> GuiManager.guiSelectedPlayer.open(player1, friend.getUuid(), friend.getName()))
								.build());
					});

			Menu menu = builder.build().get(0);

			Slot ownInfo = menu.getSlot(49);
			FriendSystemGUI.getInstance().getPluginMessaging().getOwnInfo(player, (server, onlineTime, status) -> {
				ownInfo.setItem(getHead(guiConfig.get("gui.mainMenu.item.ownInfo"),
						player.getName(), true, onlineTime, server, status));

				Bukkit.getScheduler().scheduleSyncDelayedTask(FriendSystemGUI.getInstance(), () -> menu.open(player));
			});

		});
	}

	public ItemStack getHead(ItemStack template, String name, boolean online, String lastSeen, String server, String status) {
		ItemStack itemStack = template.clone();
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (itemMeta == null) return itemStack;

		itemMeta.setDisplayName(itemMeta.getDisplayName().replaceAll("%PLAYER%", name));
		itemMeta.setLore(itemMeta.getLore().stream()
				.map(lore -> lore.replaceAll("&", "§")
						.replaceAll(online ? "%ONLINE_TIME%" : "%LAST_SEEN%", lastSeen)
						.replaceAll("%SERVER%", server)
						.replaceAll("%STATUS%", status))
				.collect(Collectors.toList()));
		itemStack.setItemMeta(itemMeta);
		if (online) SkullCreator.itemWithName(itemStack, name);

		return itemStack;
	}

}