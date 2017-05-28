package com.winter.serverfox.util;

import com.winter.serverfox.core.Main;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utility {

	public static IChannel getChannel(IGuild guild, String channel) {
		IChannel temp = null;
		try {
			temp = guild.getChannelByID(Long.parseUnsignedLong(channel));
		} catch (NumberFormatException ignored) { }
		if (temp == null) {
			List<IChannel> results = guild.getChannelsByName(channel);
			if (results.size() > 0)
				temp = results.get(0);
		}
		return temp;
	}

	public static String listenFor(IChannel channel, IUser author, long timeout, TimeUnit timeUnit) {
		String obj = null;
		try {
			obj = Main.INSTANCE.client.getDispatcher().waitFor((MessageReceivedEvent e) -> (e.getChannel().equals(channel) && e.getAuthor().equals(author)), timeout, timeUnit).getMessage().getContent();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			Message.sendMessageInChannel(channel, "timed-out");
		}
		return obj;
	}
}