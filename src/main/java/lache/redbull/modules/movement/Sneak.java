package lache.redbull.modules.movement;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;

public class Sneak extends Module {

	public Sneak() {
		super(Nodusplus.beforeNameObj + "Sneak", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onDisable() {
		mc.gameSettings.keyBindSneak.pressed = false;
	}

	public void onEnable() {
	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				mc.gameSettings.keyBindSneak.pressed = true;

			}
		}
	}

}
