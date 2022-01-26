package lache.redbull.modules.player;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.KeyEvent;
import lache.redbull.events.MotionEvent;
import lache.redbull.modules.Module;

public class Derp extends Module {

	private float yaw;
	
	public Derp() {
		super(Nodusplus.beforeNameObj + "Derp", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onDisable() {
	}

	public void onEnable() {
		if(mc.gameSettings.thirdPersonView > 0) {
			mc.gameSettings.thirdPersonView = 0;
		}
	}
	
	public void onEvent(Event e) {
		if(e instanceof MotionEvent) {
			if(e.isPre()) {
                MotionEvent event = (MotionEvent) e;

                this.yaw -= 22.0f;
                if (this.yaw <= -180.0f) {
                    this.yaw = 180.0f;
                }
                this.mc.thePlayer.renderYawOffset = this.yaw;
                this.mc.thePlayer.rotationYawHead = this.yaw;
			}
			
			if (e instanceof KeyEvent) {
	            int code = ((KeyEvent) e).code;

	            if (code == Keyboard.KEY_F5) {
	                toggle();
	            }
		}
	}

	}
}
