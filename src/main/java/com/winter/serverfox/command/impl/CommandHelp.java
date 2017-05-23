package com.winter.serverfox.command.impl;

import com.winter.serverfox.command.processing.Command;
import com.winter.serverfox.command.processing.CommandType;
import com.winter.serverfox.core.Main;
import com.winter.serverfox.util.Localisation;
import com.winter.serverfox.util.Message;
import org.apache.commons.lang3.text.WordUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.stream.Collectors;

public class CommandHelp implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, String raw, MessageReceivedEvent event) {
		if (args.length == 0) {
			for(int i = 0; i < CommandType.getTypeCount(); i++) {
				CommandType c = CommandType.getTypes()[i];

				String help;
				if(!event.getChannel().isPrivate()) {
					help = c.getCommands(c)
							.stream()
							.map(command -> "**.horo" + command + "** - " + Localisation.getMessage(event.getGuild(), Main.commands.get(command).help()) + "\n")
							.collect(Collectors.joining(""));
				} else {
					help = c.getCommands(c)
							.stream()
							.map(command -> "**.horo" + command + "** - " + Localisation.getPMMessage(Main.commands.get(command).help()) + "\n")
							.collect(Collectors.joining(""));
				}

				int arrayLength = (int) Math.ceil(((help.length() / (double) 1024)));
				String result[] = new String[arrayLength];

				int j = 0;
				int lastIndex = result.length - 1;
				for (int x = 0; x < lastIndex; x++) {
					result[x] = help.substring(j, j + 1024);
					j += 1024;
				}
				result[lastIndex] = help.substring(j);

				EmbedBuilder builder = new EmbedBuilder();
				builder.withColor(Color.CYAN);
				for (String part : result) {
					builder.appendField(WordUtils.capitalizeFully(c.toString()), part, false);
				}
				Message.sendEmbedInChannel(event.getChannel(), "", builder.build());
			}

			Message.sendMessageInChannel(event.getAuthor().getOrCreatePMChannel(), "Hey there!\n\n" +
					"I know everyone hates these kinds of messages and so do I so I'll keep it short,\n" +
					"ServerFox and HoroBot are projects which I've been working on for the past few month for fun but I am in desperate need of funds to keep them running.\n" +
					"So should you even have 1 dollar to spare, please head over to https://www.patreon.com/HoroBot and help me out, or for a one-time donation go here; https://paypal.me/HoroBot \n\n" +
					"Thanks a lot for reading and helping me out!");
			if(!event.getMessage().getChannel().isPrivate()) event.getMessage().delete();
		} else if(args.length == 1) {
			if(!event.getMessage().getChannel().isPrivate()) {
				if (Main.commands.containsKey(args[0])) {
					Message.sendMessageInChannel(event.getChannel(), Main.commands.get(args[0]).help());
				} else {
					Message.sendMessageInChannel(event.getChannel(), "no-command", args[0]);
				}
			} else {
				Message.sendMessageInChannel(event.getChannel(), "private-channel");
			}
		} else {
			Message.sendMessageInChannel(event.getChannel(), help());
		}
	}

	@Override
	public String help() {
		return "help-help";
	}

	@Override
	public CommandType getType() { return CommandType.UTILITY; }
}
