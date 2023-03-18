package me.sk8ingduck.friendsystemgui.pluginmessage;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class Request {

	private final UUID uuid;
	private final String name;

	public Request(UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public ItemStack getHead(ItemStack template) {
		ItemStack itemStack = template.clone();
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (itemMeta == null) return itemStack;

		itemMeta.setDisplayName(itemMeta.getDisplayName().replaceAll("%PLAYER%", name));
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}
