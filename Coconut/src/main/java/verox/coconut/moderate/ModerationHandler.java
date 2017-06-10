package verox.coconut.moderate;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import verox.coconut.init.BotUtilities;
import verox.coconut.init.CocoMessage;
import verox.coconut.init.EventHandler;

public class ModerationHandler extends Thread {
	MessageReceivedEvent event;
	CocoMessage msg;
	
	public ModerationHandler(MessageReceivedEvent Event, CocoMessage message) {
		event = Event;
		msg = message;
	}
	
	public void run() {
		// Echo Test
		if (EventHandler.DEBUG_MODE) {
			String echoMsg = "";
			for (short i = 0; i < msg.words.length; i++) {
				echoMsg = echoMsg + msg.words[i] + " ";
			}
			BotUtilities.Messager(event.getChannel(), ("Echo: " + echoMsg), 2);
		}
		
		// Checks to see if the person is spamming
		boolean spamming = SpamDetection.Check(msg.words);
		if (spamming) {
			try {
				event.getMessage().delete().queue();
			} catch (Exception e) {
				System.out.println("I can't delete this message");
				e.printStackTrace();
			}
			BotUtilities.Messager(event.getChannel(), event.getAuthor().getAsMention() + ", please stop spamming. :sob:", 1);
		}
	}
}
