package lache.redbull.modules.movement;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Step extends Module {

	public Step() {
		super(Nodusplus.beforeNameObj + "Step", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onDisable() {
		mc.thePlayer.stepHeight = 0.5f;
	}

	public void onEnable() {
	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				if((mc.thePlayer.isCollidedHorizontally) && (mc.thePlayer.onGround)) {
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY+0.42, mc.thePlayer.posZ, mc.thePlayer.onGround));
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY+0.72, mc.thePlayer.posZ, mc.thePlayer.onGround));
					mc.thePlayer.stepHeight = 1.0f;
				}
			}
		}
	}

}
