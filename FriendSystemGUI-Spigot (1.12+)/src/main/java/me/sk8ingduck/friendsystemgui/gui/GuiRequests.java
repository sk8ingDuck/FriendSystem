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

public class GuiRequests {

	public void open(Player player) {
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getSettingsConfig();
		ChestMenu.Builder gui = ChestMenu.builder(6).title(guiConfig.getRequestsGuiTitle());

		Mask requestHeadSlots = BinaryMask.builder(gui.getDimensions())
				.pattern("111111111")
				.pattern("111111111")
				.pattern("111111111")
				.pattern("111111111").build();


		PaginatedMenuBuilder builder = PaginatedMenuBuilder.builder(gui)
				.slots(requestHeadSlots)
				.previousButton(guiConfig.get("gui.requestsMenu.item.previousPage"))
				.previousButtonSlot(45)
				.previousButtonEmpty(guiConfig.get("gui.requestsMenu.item.previousPage"))
				.nextButton(guiConfig.get("gui.requestsMenu.item.nextPage"))
				.nextButtonSlot(53)
				.nextButtonEmpty(guiConfig.get("gui.requestsMenu.item.nextPage"))

				.newMenuModifier(menu -> {
					Mask menuMask = RecipeMask.builder(menu)
							.item('-', ItemUtil.createItem(Material.STAINED_GLASS_PANE, (short) 0, " "))
							.row(5).pattern("---------")
							.row(6).pattern("---------")
							.build();
					menuMask.apply(menu);
					Slot back = menu.getSlot(49);
					back.setItem(guiConfig.get("gui.requestsMenu.item.back"));

					back.setClickHandler((player1, clickInformation) -> GuiManager.guiMainMenu.open(player1));
				});


		me.sk8ingduck.friendsystemgui.FriendSystemGUI.getInstance().getPluginMessaging().getRequests(player, requests -> {
			requests.stream().sorted((o1, o2) -> Boolean.compare(o2.isOnline(), o1.isOnline())).forEach(request -> {
				ItemStack item = request.isOnline() ? ItemUtil.getPlayerHead("§a" + request.getName(), request.getName()) :
						ItemUtil.createItem(Material.SKULL_ITEM, (short) 0, "§c" + request.getName());
				builder.addItem(SlotSettings.builder()
						.itemTemplate(player1 -> item)
						.clickHandler((player1, click) -> GuiManager.guiSelectedPlayer.open(player1, request.getUuid(), request.getName()))
						.build());
			});
			Bukkit.getScheduler().scheduleSyncDelayedTask(me.sk8ingduck.friendsystemgui.FriendSystemGUI.getInstance(), () -> builder.build().get(0).open(player));
		});
	}
}
