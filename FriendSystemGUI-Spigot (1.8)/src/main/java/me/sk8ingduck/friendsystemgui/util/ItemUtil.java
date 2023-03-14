package me.sk8ingduck.friendsystemgui.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemUtil {


    public static ItemStack getPlayerHead(String displayName, String player) {
        ItemStack playerItem = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta playerMeta = (SkullMeta) playerItem.getItemMeta();
        assert playerMeta != null;
        playerMeta.setOwner(player);
        playerMeta.setDisplayName(displayName);
        playerItem.setItemMeta(playerMeta);

        return playerItem;
    }

    public static ItemStack createItem(Material material, String itemName) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(itemName);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createItem(Material material, byte data, String itemName) {
        ItemStack itemStack = new ItemStack(material, 1, data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(itemName);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
