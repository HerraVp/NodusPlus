package lache.redbull.modules.movement;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;

public class Speed extends Module {

	public Speed() {
		super(Nodusplus.beforeNameObj + "Speed", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onDisable() {
	
	}

	public void onEnable() {
	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				if(mc.thePlayer.onGround) {
					mc.thePlayer.motionX *= 1.5f;
					mc.thePlayer.motionZ *= 1.5f;
				}
			}
		}
	}

}
