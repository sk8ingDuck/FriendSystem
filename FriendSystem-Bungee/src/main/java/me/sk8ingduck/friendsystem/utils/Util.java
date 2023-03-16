package me.sk8ingduck.friendsystem.utils;

import me.sk8ingduck.friendsystem.FriendSystem;
import me.sk8ingduck.friendsystem.config.MessagesConfig;

import java.time.Duration;
import java.time.LocalDateTime;

public class Util {


	public static String formatDifference(LocalDateTime start) {
		MessagesConfig c = FriendSystem.getInstance().getConfig();

		Duration duration = Duration.between(start, LocalDateTime.now());
		long years = duration.toDays() / 365;
		long days = duration.toDays() % 365;
		long hours = duration.toHours() % 24;
		long minutes = duration.toMinutes() % 60;
		long seconds = duration.getSeconds() % 60;


		if (years > 0) {
			return years + " " + (c.get("friend.timeformat.year" + (years > 1 ? "s" : ""), false))
					+ (days > 0 ? " " + days + " " + (c.get("friend.timeformat.day" + (days > 1 ? "s" : ""), false)) : "");
		}
		if (days > 0) {
			return days + " " + (c.get("friend.timeformat.day" + (days > 1 ? "s" : ""), false))
					+ (hours > 0 ? " " + hours + " " + (c.get("friend.timeformat.hour" + (hours > 1 ? "s" : ""), false)) : "");
		}
		if (hours > 0) {
			return hours + " " + (c.get("friend.timeformat.hour" + (hours > 1 ? "s" : ""), false))
					+ (minutes > 0 ? " " + minutes + " " + (c.get("friend.timeformat.minute" + (minutes > 1 ? "s" : ""), false)) : "");
		}
		if (minutes > 0) {
			return minutes + " " + (c.get("friend.timeformat.minute" + (minutes > 1 ? "s" : ""), false))
					+ (seconds > 0 ? " " + seconds + " " + (c.get("friend.timeformat.second" + (seconds > 1 ? "s" : ""), false)) : "");
		}
		if (seconds > 0) {
			return seconds + " " + (c.get("friend.timeformat.second" + (seconds > 1 ? "s" : ""), false));
		}
		return "invalid";
	}

}
