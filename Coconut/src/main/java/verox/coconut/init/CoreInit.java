package verox.coconut.init;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CoreInit extends ListenerAdapter {
	public static void main(String[] args) {
		boolean DEBUG_MODE;
		try {
			// Assigns the command prefix
			System.out.println("Starting Bot...");
			if (args[1].length() != 1) {
				System.out.println("Unknown Prefix Error.");
				throw new Exception();
			}
			String PREFIX = args[1];
			
			// Sets up debug mode
			if (args[2] == null) {
				DEBUG_MODE = false;
			} else {
				if (!args[2].equals("true") && !args[2].equals("false")) {
					System.out.println("Falty Debug Mode Argument.");
					throw new Exception();
				}
				DEBUG_MODE = Boolean.parseBoolean(args[2]);
			};
			
			JDA client = new JDABuilder(AccountType.BOT)
					.setToken(args[0])
					.addEventListener(new EventHandler(PREFIX, DEBUG_MODE))
					.buildBlocking();
			
			System.out.println("Discord Ping: " + client.getPing());
			
		} catch (Exception e) {
			System.out.println("Failed to initiate the bot.");
			e.printStackTrace();
		}
	}
}
