package lache.redbull.modules.movement;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class Nofall extends Module {

	public Nofall() {
		super(Nodusplus.beforeNameObj + "NoFall", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onDisable() {
	}

	public void onEnable() {
	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				if(mc.thePlayer.fallDistance > 2) {
					   mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
					   mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(false));
					   mc.thePlayer.sendQueue.addToSendQueue((new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING)));
				}
			}
		}
	}

}
