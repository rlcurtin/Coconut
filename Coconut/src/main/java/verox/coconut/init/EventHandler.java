package verox.coconut.init;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import verox.coconut.commands.CommandHandler;
import verox.coconut.moderate.ModerationHandler;

public class EventHandler extends ListenerAdapter{
	public static String PREFIX;
	public static boolean DEBUG_MODE;
	
	public static final int SPAM_LIMIT = 3;
	
	public EventHandler(String prefix, boolean debugMode) {
		PREFIX = prefix;
		DEBUG_MODE = debugMode;
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		// Ignores messages from other bots.
		if (event.getAuthor().isBot()) return;
		
		Message EventMsg = event.getMessage();
		String chat = EventMsg.getRawContent();
		
		// Cancels checks if not in #bot-channel while in Debug Mode, or while in #bot-channel when not in Debug Mode.
		if (EventHandler.DEBUG_MODE) {
			if (!event.getChannel().getName().equals("bot-testing")) {
				return;
			}
		} else if (event.getChannel().getName().equals("bot-testing")) {
			return;
		}
		
		// Separates each word into an array.
		CocoMessage msg = (CocoMessage) BotUtilities.SeperateArguments(chat, chat.startsWith(PREFIX));
		
		// Checks the message for moderation.
		ModerationHandler moderate = new ModerationHandler(event, msg);
		moderate.start();
		
		// Throws it to the Command Handler if it's a command.
		if (msg.isCommand) {
			CommandHandler.Handler(event, msg);
		}
	}
}
