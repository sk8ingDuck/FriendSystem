package me.sk8ingduck.friendsystemvelocity.config;

import me.sk8ingduck.friendsystemvelocity.FriendSystem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public abstract class MessagesConfig extends Config {

	protected final LinkedHashMap<String, String> messages;
	private final MiniMessage mm;

	public MessagesConfig(String path) {
		super(path, false);

		mm = MiniMessage.miniMessage();
		this.messages = new LinkedHashMap<>();
		loadMessages();


		messages.forEach((messagePath, defaultMessage) -> {
			String[] messagePaths = messagePath.split("\\.");
			messages.put(messagePath, rootNode.node((Object[]) messagePaths).getString(defaultMessage));
		});

		save();
	}

	public abstract void loadMessages();

	private String replacePlaceholders(String text, String... replacements) {
		for (int i = 0; i < replacements.length; i += 2) {
			String toReplace = replacements[i];
			String replacement = replacements[i + 1];

			text = text.replaceAll(toReplace, replacement);
		}
		return text;
	}

	public Component get(String path, boolean prefix, String... replacements) {
		String message = prefix ? messages.get("prefix") : "";
		message += replacePlaceholders(messages.get(path), replacements);
		return mm.deserialize(message);
	}

	public Component get(String path, String... replacements) {
		return get(path, true, replacements);
	}

	public String formatDifferenceRequest(LocalDateTime requestDate) {
		Duration duration = Duration.between(LocalDateTime.now(),
				requestDate.plusMinutes(FriendSystem.getInstance().getSettingsConfig().getRequestDuration()));

		return formatTimeFormat(duration);
	}

	public String formatDifference(LocalDateTime start) {
		Duration duration = Duration.between(start, LocalDateTime.now());
		return formatTimeFormat(duration);
	}
	private String formatTimeFormat(Duration duration) {
		long totalSeconds = duration.getSeconds();
		long seconds = totalSeconds % 60;
		long totalMinutes = totalSeconds / 60;
		long minutes = totalMinutes % 60;
		long totalHours = totalMinutes / 60;
		long hours = totalHours % 24;
		long totalDays = totalHours / 24;
		long days = totalDays % 365;
		long years = totalDays / 365;

		if (years > 0) {
			return years + " " + (messages.get("friend.timeformat.year" + (years > 1 ? "s" : "")))
					+ (days > 0 ? " " + days + " " + (messages.get("friend.timeformat.day" + (days > 1 ? "s" : ""))) : "");
		}
		if (days > 0) {
			return days + " " + (messages.get("friend.timeformat.day" + (days > 1 ? "s" : "")))
					+ (hours > 0 ? " " + hours + " " + (messages.get("friend.timeformat.hour" + (hours > 1 ? "s" : ""))) : "");
		}
		if (hours > 0) {
			return hours + " " + (messages.get("friend.timeformat.hour" + (hours > 1 ? "s" : "")))
					+ (minutes > 0 ? " " + minutes + " " + (messages.get("friend.timeformat.minute" + (minutes > 1 ? "s" : ""))) : "");
		}
		if (minutes > 0) {
			return minutes + " " + (messages.get("friend.timeformat.minute" + (minutes > 1 ? "s" : "")))
					+ (seconds > 0 ? " " + seconds + " " + (messages.get("friend.timeformat.second" + (seconds > 1 ? "s" : ""))) : "");
		}
		if (seconds > 0) {
			return seconds + " " + (messages.get("friend.timeformat.second" + (seconds > 1 ? "s" : "")));
		}
		return "invalid";
	}

}
