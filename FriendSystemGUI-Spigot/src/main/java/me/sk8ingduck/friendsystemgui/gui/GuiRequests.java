package me.sk8ingduck.friendsystemgui.gui;

import me.sk8ingduck.friendsystemgui.FriendSystemGUI;
import me.sk8ingduck.friendsystemgui.config.GuiConfig;
import me.sk8ingduck.friendsystemgui.util.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.ipvp.canvas.mask.BinaryMask;
import org.ipvp.canvas.mask.Mask;
import org.ipvp.canvas.mask.RecipeMask;
import org.ipvp.canvas.paginate.PaginatedMenuBuilder;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.slot.SlotSettings;
import org.ipvp.canvas.type.ChestMenu;

public class GuiRequests {

	public void open(Player player) {
		GuiConfig guiConfig = FriendSystemGUI.getInstance().getGuiConfig();
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
							.item('-', ItemCreator.createGlassPane(DyeColor.BLACK, " "))
							.row(5).pattern("---------")
							.row(6).pattern("---------")
							.build();
					menuMask.apply(menu);
					Slot back = menu.getSlot(49);
					back.setItem(guiConfig.get("gui.requestsMenu.item.back"));

					back.setClickHandler((player1, clickInformation) -> GuiManager.guiMainMenu.open(player1));
				});

		FriendSystemGUI.getInstance().getPluginMessaging().getRequests(player, requests -> {
			requests.forEach(request -> {
				ItemStack item = getHead(guiConfig.get("gui.requestsMenu.item.request"), request.getName());
				builder.addItem(SlotSettings.builder()
						.itemTemplate(player1 -> item)
						.clickHandler((player1, click) -> GuiManager.guiSelectedPlayer.open(player1, request.getUuid(), request.getName()))
						.build());
			});
			Bukkit.getScheduler().scheduleSyncDelayedTask(FriendSystemGUI.getInstance(), () -> builder.build().get(0).open(player));
		});
	}

	public ItemStack getHead(ItemStack template, String name) {
		ItemStack itemStack = template.clone();
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (itemMeta == null) return itemStack;

		itemMeta.setDisplayName(itemMeta.getDisplayName().replaceAll("%PLAYER%", name));
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}
