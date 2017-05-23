package com.winter.serverfox.core;

import com.winter.serverfox.command.impl.CommandCreateList;
import com.winter.serverfox.command.impl.CommandHelp;
import com.winter.serverfox.command.processing.AnnotationListener;
import com.winter.serverfox.command.processing.Command;
import com.winter.serverfox.command.processing.CommandContainer;
import com.winter.serverfox.command.processing.CommandParser;
import com.winter.serverfox.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.RateLimitException;

import java.lang.management.ManagementFactory;
import java.util.HashMap;

public class Main {

	public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	public static ClientManager INSTANCE;

	public static HashMap<String, Command> commands = new HashMap<>();
	public static final CommandParser parser = new CommandParser();

	public static void main(String[] args) {
		LOGGER.info(ManagementFactory.getRuntimeMXBean().getName());

		INSTANCE = ClientManager.createClient();
		EventDispatcher dispatcher = INSTANCE.client.getDispatcher();
		dispatcher.registerListener(new AnnotationListener());

		commands.put("help", new CommandHelp());
		commands.put("createlist", new CommandCreateList());
	}

	public static void handleCommand(CommandContainer cmd) {
		if (commands.containsKey(cmd.invoke)) {
			if (commands.get(cmd.invoke).called(cmd.args, cmd.event) || cmd.event.getAuthor().getStringID().equals("288996157202497536")) {
				try {
					commands.get(cmd.invoke).action(cmd.args, cmd.beheaded, cmd.event);
				} catch (Exception e) {
					if (e instanceof RateLimitException) throw e;
					Message.sendMessageInChannel(cmd.event.getChannel(), "error");
					e.printStackTrace();
				}
			} else {
				Message.addReaction(cmd.event.getMessage(), "\uD84D\uDEAB");
			}
		} else {
			Message.addReaction(cmd.event.getMessage(), "❓");
		}
	}
}
