package com.winter.serverfox.command.processing;

import com.winter.serverfox.core.Main;
import com.winter.serverfox.util.Message;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class AnnotationListener {

	@EventSubscriber
	public void onReadyEvent(ReadyEvent event) {
		event.getClient().changePlayingText(".foxhelp | .foxinvite");
	}

	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent event) {
		if (event.getClient().isReady()) {
			if (event.getMessage().getAuthor() != event.getClient().getOurUser() && !event.getMessage().getAuthor().isBot()) {
				if (event.getMessage().getContent().startsWith(".fox")) {
					if (!event.getChannel().isPrivate()) {
						Main.handleCommand(Main.parser.parse(event.getMessage().getContent(), ".fox", event));
					} else {
						Message.sendMessageInChannel(event.getChannel(), "Unfortunately, I cannot execute commands in PM");
					}
				}
			}
		}
	}
}
