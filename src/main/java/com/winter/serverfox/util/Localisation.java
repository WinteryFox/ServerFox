package com.winter.serverfox.util;

import sx.blah.discord.handle.obj.IGuild;

import java.util.ResourceBundle;

public class Localisation {

	private static ResourceBundle enLang = ResourceBundle.getBundle("locale.en");

	private static String checkLanguage(IGuild guild) {
		return "en";
	}

	public static boolean changeLanguage (String guildID, String language) {
		switch (language) {
			case "en":
				updateGuildLanguage(guildID, "en");
				return true;
		}
		return false;
	}

	private static void updateGuildLanguage (String guildID, String language) {
		// TODO
	}

	public static String getMessage(IGuild guild, String str) {
		String lang = checkLanguage(guild);
		switch (lang) {
			case "en":
				if (enLang.containsKey(str))
					return enLang.getString(str);
		}
		return "Localisation error please report this error in the Discord server so it can be fixed as quickly as possible; https://discord.gg/MCUTSZz";
	}

	public static String getPMMessage(String str) {
		if(enLang.containsKey(str))
			return enLang.getString(str);
		return "Localisation error please report this error in the Discord server so it can be fixed as quickly as possible; https://discord.gg/MCUTSZz";
	}
}