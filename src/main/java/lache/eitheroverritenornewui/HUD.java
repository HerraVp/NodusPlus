package lache.eitheroverritenornewui;

import lache.redbull.Nodusplus;
import lache.redbull.events.RenderGUIEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import lache.redbull.modules.Module;
import net.minecraft.client.renderer.GlStateManager;

public class HUD extends GuiScreen {

	public HUD() {
		this.mc = Minecraft.getMinecraft();
	}

	public void draw() {
		/* abbrevations */
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		FontRenderer fr = mc.fontRendererObj;

		/* glstatemanager shit to draw the client logo NSFDJNKKJNDSFKJNDSF */
		fr.drawStringWithShadow(Nodusplus.name + " b" + Nodusplus.version + " ; " + "FPS: " + Minecraft.debugFPS, 1, 1, 0x44DF44);


		/*
		GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(2, 2, 1);
		GlStateManager.translate(-4, -4, 0);
		fr.drawStringWithShadow(Redbull.name + " b" + Redbull.version, 4, 4, RainbowUtil.getRainbow(4, 0.8f, 1));
		GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(0.5, 0.5, 1);
		GlStateManager.translate(-4, -4, 0);
		*/

		int count = 0;
		for (Module m : Nodusplus.modules) {
			if (!m.toggled)
				continue;

			double offset = count * (fr.FONT_HEIGHT + 6);

			HUD.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) - 8, offset, sr.getScaledWidth(),5 + fr.FONT_HEIGHT + offset, 0x00000000);
			fr.drawStringWithShadow(m.name, sr.getScaledWidth() - fr.getStringWidth(m.name) - 3, 3 + offset, 0x44DF44);
			//+ " " + Minecraft.getMinecraft().debugFPS
			//mc.getDebugFPS();
			count++;
		}

		Nodusplus.onEvent(new RenderGUIEvent());
	}


}
