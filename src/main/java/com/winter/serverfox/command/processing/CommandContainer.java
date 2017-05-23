package com.winter.serverfox.command.processing;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class CommandContainer {

	public final String raw;
	public final String beheaded;
	public final String[] splitBeheaded;
	public final String invoke;
	public final String[] args;
	public final MessageReceivedEvent event;

	public CommandContainer(String raw, String beheaded, String[] splitBeheaded, String invoke, String[] args, MessageReceivedEvent event) {
		this.raw = raw;
		this.beheaded = beheaded;
		this.splitBeheaded = splitBeheaded;
		this.invoke = invoke;
		this.args = args;
		this.event = event;
	}
}
