package lache.redbull.modules.movement;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.utils.Invoker;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;

public class FastLadder extends Module {

	public FastLadder() {
		super(Nodusplus.beforeNameObj +"FastLadder", Keyboard.KEY_NONE, Category.PLAYER);

	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				if(Invoker.isOnLadder() && Keyboard.isKeyDown(Invoker.getForwardCode())) {
					Invoker.setMotionY(1);
				}else if(Invoker.isOnLadder() && !Keyboard.isKeyDown(Invoker.getForwardCode())) {
					Invoker.setMotionY(-1);
				}

				}
			}
		}
	}


