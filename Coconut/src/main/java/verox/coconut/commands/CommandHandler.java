package verox.coconut.commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import verox.coconut.init.BotUtilities;
import verox.coconut.init.CocoMessage;
import verox.coconut.init.EventHandler;

public class CommandHandler {
	public static void Handler(MessageReceivedEvent event, CocoMessage msg) {
		MessageChannel channel = event.getChannel();

		// Main Command Handling
		switch (msg.words[0]) {

// >> PRIMARY COMMANDS <<
			default:
				BotUtilities.Messager(channel, ".\n"
						+ "Do you really think I know that command? :unamused:\n"
						+ "Maybe you misspelled it... :thinking:\n"
						+ "Or maybe you can type \"~help\" to see what I actually *do* know. :blush:");
				break;
			
		// Main Test Command
			case "test":
				if (EventHandler.DEBUG_MODE) {
					if (msg.words.length == 1) {
						BotUtilities.Messager(channel, "Test Recieved!");
					} else {
						try {
							String outputArguments = "";
							for (short i = 1; i < msg.words.length; i++) outputArguments = outputArguments + msg.words[i] + "\n";
							String output = ((msg.words.length - 1) + " Test Arguments Recieved!\n"
										+ "Arguments Seen:\n"
										+ outputArguments
									);
							BotUtilities.Messager(channel, output);
						} catch(Exception e) {
							BotUtilities.Messager(channel, "Augmented Test Failed! :sob:\nThis is why: " + e);
						}
					}
				} else {
					BotUtilities.Messager(channel, "Message read loud and clear!  I am up and running! :smile:");
				}
				break;
				
		// Help Command
			case "help":
				BotUtilities.Messager(channel, "I'm sorry, I don't really know anything yet. :cold_sweat:");
				break;
				
// >> SECONDARY COMMANDS <<
		}
	}
}
