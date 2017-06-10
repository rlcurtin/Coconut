package verox.coconut.init;

public class CocoMessage {
	public boolean isCommand;
	public String[] words;
	
	public CocoMessage(boolean isC, String[] message) {
		isCommand = isC;
		words = message;
	}
	
	public CocoMessage(String[] message) {
		isCommand = false;
		words = message;
	}
}
