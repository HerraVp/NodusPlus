package lache.redbull.modules.movement;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;

public class BHop extends Module {

	public BHop() {
		super(Nodusplus.beforeNameObj + "BunnyHop", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onDisable() {
		mc.gameSettings.keyBindJump.pressed = false;
	}

	public void onEnable() {
	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				mc.gameSettings.keyBindJump.pressed = true;
			}
		}
	}

}
