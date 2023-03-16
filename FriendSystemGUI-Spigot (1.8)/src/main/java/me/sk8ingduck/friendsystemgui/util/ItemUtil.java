package me.sk8ingduck.friendsystemgui.util;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class ItemUtil {

    public static ItemStack getPlayerHead(String player, String displayName, List<String> lore) {
        ItemStack playerItem = new ItemStack(Material.SKULL, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta playerMeta = (SkullMeta) playerItem.getItemMeta();
        assert playerMeta != null;
        playerMeta.setOwner(player);
        playerMeta.setDisplayName(displayName);
        playerMeta.setLore(lore);
        playerItem.setItemMeta(playerMeta);

        return playerItem;
    }

    public static ItemStack getSkeletonHead(String itemName, List<String> lore) {
        ItemStack itemStack = new ItemStack(Material.SKULL, 1, (short) SkullType.SKELETON.ordinal());
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(itemName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createItem(Material material, String itemName) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(itemName);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createItem(Material material, short data, String displayName) {
        ItemStack itemStack = new ItemStack(material, 1, data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
