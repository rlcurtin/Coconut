package verox.coconut.init;

import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.core.entities.MessageChannel;

public class BotUtilities {
	// Handles the sent messages while queuing it.
	public static boolean Messager(MessageChannel channel, String message) {
		try {
			channel.sendMessage(message).queue();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean Messager(MessageChannel channel, String message, int time) {
		try {
			channel.sendMessage(message).queueAfter(time, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// Separates each word
	public static CocoMessage SeperateArguments(String message, boolean isCommand) {
		String[] messageArgs = message.split(" ");
		if (messageArgs.length == 0)
			return null;
		
		// Separates the command prefix from the command value.
		if (isCommand) {
			messageArgs[0] = messageArgs[0].substring(1);
			return new CocoMessage(true, messageArgs);
		} else {
			return new CocoMessage(messageArgs);
		}
	}
	
	public static CocoMessage SeperateArguments(String message) {
		String[] messageArgs = message.split(" ");
		if (messageArgs.length == 0) return null;
		return new CocoMessage(messageArgs);
	}
}
