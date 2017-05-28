package com.winter.serverfox.command.impl;

import com.winter.serverfox.command.processing.Command;
import com.winter.serverfox.command.processing.CommandType;
import com.winter.serverfox.util.Message;
import com.winter.serverfox.util.Utility;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class CommandCreateList implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return event.getAuthor().getStringID().equals("288996157202497536") ||
				event.getAuthor().getStringID().equals("156179325421486081") ||
				event.getAuthor().getStringID().equals("109178238458134528");
	}

	@Override
	public void action(String[] args, String raw, MessageReceivedEvent event) {
		IChannel channel;
		String name;
		String link;
		String image;
		String description;

		Message.sendRawMessageInChannel(event.getChannel(), "Okay, listening now! Channel?");
		String channelListen = Utility.listenFor(event.getChannel(), event.getAuthor(), 60, TimeUnit.SECONDS);
		channel = Utility.getChannel(event.getGuild(), channelListen);
		if (channel == null) {
			Message.sendMessageInChannel(event.getChannel(), "invalid-channel");
			return;
		}

		Message.sendRawMessageInChannel(event.getChannel(), "Name?");
		name = Utility.listenFor(event.getChannel(), event.getAuthor(), 60, TimeUnit.SECONDS);
		if (name == null)
			return;

		Message.sendRawMessageInChannel(event.getChannel(), "Invite link?");
		link = Utility.listenFor(event.getChannel(), event.getAuthor(), 60, TimeUnit.SECONDS);
		if (link == null)
			return;

		Message.sendRawMessageInChannel(event.getChannel(), "Image link?");
		image = Utility.listenFor(event.getChannel(), event.getAuthor(), 60, TimeUnit.SECONDS);
		if (image == null)
			return;

		Message.sendRawMessageInChannel(event.getChannel(), "Description? Type `none` for no description");
		description = Utility.listenFor(event.getChannel(), event.getAuthor(), 60, TimeUnit.SECONDS);
		if (description == null)
			return;

		EmbedBuilder builder = new EmbedBuilder();
		builder.withColor(Color.CYAN);
		builder.withImage(image);
		builder.appendField("Guild Name", name, true);
		builder.appendField("Link", link, true);
		if (!"none".equals(description.toLowerCase())) builder.appendField("Description", description, false);
		Message.sendEmbedInChannel(channel, "", builder.build());
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