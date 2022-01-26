package lache.redbull.modules.movement;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.utils.Invoker;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;

public class Spider extends Module {

	public Spider() {
		super(Nodusplus.beforeNameObj + "Spider", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				if(Invoker.isCollidedHorizontally()) {
					Invoker.setMotionY(0.2);
					
					float var6 = 0.15F;
					
					if(Invoker.getMotionX() < (double)-var6) {
						Invoker.setMotionX((double)-var6);
					}
					if(Invoker.getMotionX() < (double)-var6) {
						Invoker.setMotionX((double)-var6);
					}
					if(Invoker.getMotionZ() < (double)-var6) {
						Invoker.setMotionZ((double)-var6);
					}
					if(Invoker.getMotionZ() < (double)-var6) {
						Invoker.setMotionZ((double)-var6);
					}
					
					Invoker.setFallDistance(0);
					
					if(Invoker.getMotionY() < 0.15D) {
						Invoker.setMotionY(-0.15D);
					}

				}
			}
		}
	}

}
