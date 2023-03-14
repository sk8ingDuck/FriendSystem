package me.sk8ingduck.friendsystemgui.gui;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.ipvp.canvas.mask.BinaryMask;
import org.ipvp.canvas.mask.Mask;
import org.ipvp.canvas.mask.RecipeMask;
import org.ipvp.canvas.paginate.PaginatedMenuBuilder;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.slot.SlotSettings;
import org.ipvp.canvas.type.ChestMenu;

public class GuiMainMenu {

	public void open(Player player) {
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getSettingsConfig();
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
							.item('-', ItemUtil.createItem(Material.STAINED_GLASS, (short) 0, " "))
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

		me.sk8ingduck.friendsystemgui.FriendSystemGUI.getInstance().getPluginMessaging().getFriends(player, friends -> {
			friends.stream().sorted((o1, o2) -> Boolean.compare(o2.isOnline(), o1.isOnline())).forEach(friend -> {
				ItemStack item = friend.isOnline() ? ItemUtil.getPlayerHead("§a" + friend.getName(), friend.getName()) :
						ItemUtil.createItem(Material.SKULL_ITEM, (short) 0, "§c" + friend.getName());
				builder.addItem(SlotSettings.builder()
						.itemTemplate(player1 -> item)
						.clickHandler((player1, click) -> GuiManager.guiSelectedPlayer.open(player1, friend.getUuid(), friend.getName()))
						.build());

			});
			Bukkit.getScheduler().scheduleSyncDelayedTask(me.sk8ingduck.friendsystemgui.FriendSystemGUI.getInstance(), () -> builder.build().get(0).open(player));
		});

	}
}