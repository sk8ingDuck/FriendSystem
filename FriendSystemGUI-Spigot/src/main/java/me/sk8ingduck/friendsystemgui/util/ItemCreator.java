package me.sk8ingduck.friendsystemgui.util;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Colorable;

public class ItemCreator {

	public static ItemStack createItem(Material material, String itemName) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (itemMeta == null) return itemStack;
		itemMeta.setDisplayName(itemName);
		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}


    public static ItemStack createGlassPane(DyeColor color, String displayName) {
        ItemStack glassPane;
        ItemMeta meta;
        if (isLegacy()) {
            glassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, color.getWoolData());
            meta = glassPane.getItemMeta();
            meta.setDisplayName(displayName);
        } else {
            glassPane = new ItemStack(Material.valueOf(color.toString() + "_STAINED_GLASS_PANE"));
            meta = glassPane.getItemMeta();
            meta.setDisplayName(displayName);
            ((Colorable) meta).setColor(color);
        }
        glassPane.setItemMeta(meta);
        return glassPane;
    }

    private static boolean isLegacy() {
        try {
            Material.valueOf("STAINED_GLASS_PANE");
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
