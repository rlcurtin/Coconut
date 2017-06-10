package verox.coconut.moderate;

import verox.coconut.init.EventHandler;

public class SpamDetection {
	public static boolean Check(String[] msg) {
		short duplicateWords = 0;
		String previousWord = "";
		
		for (short i = 0; i < msg.length; i++) {
			if (msg[i].equals(previousWord)) {
				duplicateWords += 1;
			} else if (duplicateWords > 0) {
				duplicateWords -= 1;
			}
			
			previousWord = msg[i];
			
			if (duplicateWords >= EventHandler.SPAM_LIMIT)
				return true;
		}
		
		return false;
	}
}
