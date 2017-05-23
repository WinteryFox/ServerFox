package com.winter.serverfox.util;

import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.RequestBuffer;

public class Message {

	public static void sendMessageInChannel(IChannel channel, String message, Object... args) {
		RequestBuffer.request(() -> channel.sendMessage(String.format(Localisation.getMessage(channel.getGuild(), message), args)));
	}

	public static void addReaction(IMessage message, String reaction) {
		RequestBuffer.request(() -> message.addReaction(reaction));
	}

	public static void sendEmbedInChannel(IChannel channel, String message, EmbedObject embed) {
		RequestBuffer.request(() -> channel.sendMessage(message, embed));
	}
}