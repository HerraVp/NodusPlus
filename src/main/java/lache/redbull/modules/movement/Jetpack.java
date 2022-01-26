package lache.redbull.modules.movement;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;

public class Jetpack extends Module {
	
	public Jetpack() {
		super(Nodusplus.beforeNameObj + "Jetpack", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				mc.thePlayer.jump();
			}

		}
	}
}