package lache.redbull.commandshit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lache.redbull.commandshit.impl.Info;
import lache.redbull.events.ChatEvent;

public class CommandManager {
	
	public static List<Command> commands = new ArrayList<Command>();
	public static String prefix = ".";
	public static boolean foundModule = false;
	public static boolean foundCommand = false;
	
	public CommandManager() {
		setup();
	}
	
	public void setup() {
		//commands.add(new Toggle());
		commands.add(new Info());
	}
	public static void handleChat(ChatEvent event) {
		
		String message = event.getMessage();
		
		if(!message.startsWith(prefix))
			return;
		
		message = message.substring(prefix.length());
		
		event.setCancelled(true);
		
		if(message.split(" ").length > 0) {
			String commandName = message.split(" ")[0];
			
			for(Command c : commands) {
				if(c.aliases.contains(commandName) || c.name.equalsIgnoreCase(commandName)) {
					c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
					foundCommand = true;
					break;
				}
			}
			
			//if(!foundModule) {
        		//Nodusplus.addChatMessage(" Client Error: Couldn't find module.");
        	}
		}
	}

//}
