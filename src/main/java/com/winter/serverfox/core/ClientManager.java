package com.winter.serverfox.core;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class ClientManager {

	public IDiscordClient client;

	public ClientManager(IDiscordClient reference) {
		client = reference;
	}

	public static ClientManager createClient() {
		String token;
		if (Config.debug) {
			token = Config.debugToken;
		} else {
			token = Config.botToken;
		}
		ClientManager bot = null;

		ClientBuilder clientBuilder = new ClientBuilder();
		clientBuilder.withToken(token);
		clientBuilder.withRecommendedShardCount();

		try {
			IDiscordClient client = clientBuilder.login();
			bot = new ClientManager(client);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		return bot;
	}
}