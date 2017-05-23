package com.winter.serverfox.command.processing;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.ArrayList;

public class CommandParser {

	public CommandContainer parse(String rw, String prefix, MessageReceivedEvent event) {
		ArrayList<String> split = new ArrayList<String>();
		String raw = rw;
		String beheaded = rw.substring(prefix.length(), rw.length());
		String[] splitBeheaded = beheaded.split(" ");

		for(String s : splitBeheaded)
			split.add(s);

		String invoke = split.get(0);
		beheaded = raw.substring(prefix.length() + invoke.length(), raw.length());
		if(beheaded.length() > 0) beheaded = beheaded.substring(1, beheaded.length());
		String[] args = new String[split.size() - 1];
		split.subList(1, split.size()).toArray(args);

		return new CommandContainer(raw, beheaded, splitBeheaded, invoke, args, event);
	}
}
