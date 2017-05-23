package com.winter.serverfox.command.processing;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public interface Command {

	boolean called (String[] args, MessageReceivedEvent event);
	void action (String[] args, String raw, MessageReceivedEvent event);
	String help();
	CommandType getType();
}
