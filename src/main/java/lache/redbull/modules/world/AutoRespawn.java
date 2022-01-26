package lache.redbull.modules.world;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;

public class AutoRespawn extends Module {

	public AutoRespawn() {
		super(Nodusplus.beforeNameObj + "AutoRevive", Keyboard.KEY_NONE, Category.WORLD);
	}
	
	public void onDisable() {
	}

	public void onEnable() {
	}

	@Override
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				if(mc.thePlayer.isDead) {
					mc.thePlayer.respawnPlayer();
				}
			}
		}
	}

}
