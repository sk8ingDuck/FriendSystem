package me.sk8ingduck.friendsystemgui.pluginmessage;

import me.sk8ingduck.friendsystemgui.util.SkullCreator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;
import java.util.stream.Collectors;

public class Friend {
	private final UUID uuid;
	private final String name;
	private final boolean online;
	private final String server;
	private final String lastSeen;
	private final String status;
	private final boolean isFavourite;

	public Friend(UUID uuid, String name, boolean online, String server, String lastSeen, String status, boolean isFavourite) {
		this.uuid = uuid;
		this.name = name;
		this.online = online;
		this.server = server;
		this.lastSeen = lastSeen;
		this.status = status;
		this.isFavourite = isFavourite;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public boolean isOnline() {
		return online;
	}

	public boolean isFavourite() {
		return isFavourite;
	}

	public ItemStack getHead(ItemStack template) {
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
		if (isOnline()) SkullCreator.itemWithName(itemStack, name);

		return itemStack;
	}
}
