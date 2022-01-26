package lache.redbull.modules.movement;

import lache.redbull.modules.settings.BooleanSetting;
import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;

public class Sprint extends Module {

	public BooleanSetting multiDirect = new BooleanSetting("multiDirect", false);
	public Sprint() {
		super(Nodusplus.beforeNameObj + "Sprint", Keyboard.KEY_NONE, Category.PLAYER);
		this.settings.add(multiDirect);
	}
	
	public void onDisable() {
		
		mc.thePlayer.setSprinting(false);
	}

	public void onEnable() {
	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				if(multiDirect.isEnabled()) {
					mc.thePlayer.setSprinting(true);
				}

				else if (mc.thePlayer.moveForward > 0 && !mc.thePlayer.isBlocking() && !mc.thePlayer.isUsingItem() && !mc.thePlayer.isOnLadder() && !mc.thePlayer.isEating() && !mc.thePlayer.isSneaking()) {
					mc.thePlayer.setSprinting(true);
				}
			}
		}
	}

}
