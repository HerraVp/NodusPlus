package lache.redbull.utils;

import lache.redbull.Nodusplus;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class XeroxPrinter {
	
	public static void print(String message) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.thePlayer.addChatMessage(new ChatComponentText("[§a" + Nodusplus.chatName + "§r] " + message));
    }

}
