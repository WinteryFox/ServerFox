package com.winter.serverfox.util;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;

import java.util.List;

public class Utility {

	public static IChannel getChannel(MessageReceivedEvent event, String channel) {
		IChannel temp = null;
		try {
			temp = event.getGuild().getChannelByID(Long.parseUnsignedLong(channel));
		} catch (NumberFormatException ignored) { }
		if (temp == null) {
			List<IChannel> results = event.getGuild().getChannelsByName(channel);
			if (results.size() > 0)
				temp = results.get(0);
		}
		if (temp == null && event.getMessage().getChannelMentions().size() > 0)
			temp = event.getMessage().getChannelMentions().get(0);
		return temp;
	}
}