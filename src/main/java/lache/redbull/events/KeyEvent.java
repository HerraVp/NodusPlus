package lache.redbull.events;

import lache.redbull.utils.event.Event;

public class KeyEvent extends Event<KeyEvent>{
	
	public int code;
	
	public KeyEvent(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	

}
