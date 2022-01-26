package lache.redbull.modules.movement;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;

public class Flight extends Module {

	public Flight() {
		super(Nodusplus.beforeNameObj + "Fly", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onDisable() {
		mc.thePlayer.capabilities.isFlying = false;
		mc.thePlayer.capabilities.allowFlying = false;
	}

	public void onEnable() {
		mc.thePlayer.capabilities.isFlying = true;
		mc.thePlayer.capabilities.allowFlying = true;
	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				
			}
		}
	}

}
