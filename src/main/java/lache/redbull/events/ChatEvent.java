package lache.redbull.events;

import lache.redbull.utils.event.Event;

public class ChatEvent extends Event<ChatEvent> {
	
	public String message;

	
	public ChatEvent(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
