package me.sk8ingduck.friendsystemvelocity.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Util {

	static LuckPerms luckPerms = LuckPermsProvider.get();

	public static String getPrefix(UUID uuid) {
		User user = luckPerms.getUserManager().getUser(uuid);
		String prefix = null;

		if (user == null || (prefix = user.getCachedData().getMetaData().getPrefix()) == null) {
			try {
				user = luckPerms.getUserManager().loadUser(uuid).get();
				prefix = user.getCachedData().getMetaData().getPrefix();
				luckPerms.getUserManager().cleanupUser(user);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		if (prefix == null) {
			return "";
		}

		Component prefixComponent = LegacyComponentSerializer.legacyAmpersand().deserialize(prefix);
		return MiniMessage.miniMessage().serialize(prefixComponent);
	}
}
