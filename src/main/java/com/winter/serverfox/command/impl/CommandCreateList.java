package com.winter.serverfox.command.impl;

import com.winter.serverfox.command.processing.Command;
import com.winter.serverfox.command.processing.CommandType;
import com.winter.serverfox.util.Message;
import com.winter.serverfox.util.Utility;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CommandCreateList implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return event.getAuthor().getStringID().equals("288996157202497536") ||
				event.getAuthor().getStringID().equals("156179325421486081") ||
				event.getAuthor().getStringID().equals("109178238458134528");
	}

	@Override
	public void action(String[] args, String raw, MessageReceivedEvent event) {
		if (args.length >= 3) {
			IChannel channel = Utility.getChannel(event, args[0]);
			if (channel != null) {
				EmbedBuilder builder = new EmbedBuilder();
				builder.withColor(Color.CYAN);
				URL url;
				try {
					url = new URL(args[3]);
				} catch (MalformedURLException e) {
					Message.sendMessageInChannel(event.getChannel(), "wrong-image");
					e.printStackTrace();
					return;
				}
				try {
					builder.withImage(url.toURI().toASCIIString());
				} catch (URISyntaxException e) {
					Message.sendMessageInChannel(event.getChannel(), "wrong-image");
					e.printStackTrace();
					return;
				}
				builder.appendField("Guild Name", args[1], true);
				builder.appendField("Link", args[2], true);
				if (args.length >= 5) {
					builder.appendField("Description", Arrays.stream(args).skip(4).collect(Collectors.joining(" ")), false);
				}
				Message.sendEmbedInChannel(channel, "", builder.build());
			} else {
				Message.sendMessageInChannel(event.getChannel(), "no-channel");
			}
		} else {
			Message.sendMessageInChannel(event.getChannel(), help());
		}
	}

	@Override
	public String help() {
		return "createlist-help";
	}

	@Override
	public CommandType getType() {
		return null;
	}
}