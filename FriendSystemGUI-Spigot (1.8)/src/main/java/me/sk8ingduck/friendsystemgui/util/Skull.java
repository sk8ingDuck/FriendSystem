package me.sk8ingduck.friendsystemgui.util;


import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Skull {

    BACK("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzhjZjQ0NGJjMGUwZGRjNmE2YTlkNzU5NTE3NDZlMDk1NWJkNjc2YzRhNjc3MWMxYTVlZDkwZTc4MjdmIn19fQ=="),
    LEFT("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTJmMDQyNWQ2NGZkYzg5OTI5MjhkNjA4MTA5ODEwYzEyNTFmZTI0M2Q2MGQxNzViZWQ0MjdjNjUxY2JlIn19fQ=="),
    RIGHT("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ4NjVhYWUyNzQ2YTliOGU5YTRmZTYyOWZiMDhkMThkMGE5MjUxZTVjY2JlNWZhNzA1MWY1M2VhYjliOTQifX19");

    private final ItemStack skull;

    Skull(String base64) {
        skull = SkullCreator.itemFromBase64(base64);
    }

    public ItemStack getSkull(String displayName) {
        ItemMeta itemMeta = skull.getItemMeta();
        if (itemMeta != null)
            itemMeta.setDisplayName(displayName);
        skull.setItemMeta(itemMeta);

        return skull;
    }
}