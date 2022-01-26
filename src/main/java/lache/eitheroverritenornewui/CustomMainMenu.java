package lache.eitheroverritenornewui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class CustomMainMenu extends GuiScreen {
	
	
	public void MainMenu() {
		
	}
	
	public void initGui() {
		
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		 mc.getTextureManager().bindTexture(new ResourceLocation("lachetextures/background.jpg"));
	        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);

	        this.drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);

	        String[] buttons = {"Singleplayer", "Multiplayer", "Settings", "Nodus+ on Github", "Quit"};

	        int count = 0;
	        for (String button : buttons) {
	            float x = (width / buttons.length) * count + (width / buttons.length) / 2f + 8 - mc.fontRendererObj.getStringWidth(button) / 2f;
	            float y = height - 20;
	            boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(button) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT;


	            this.drawCenteredString(mc.fontRendererObj, button, (width / buttons.length) * count + (width / buttons.length) / 2f + 8, height - 20, hovered ? 0x44DF44 : -1);
	            count++;
	        }

	        GlStateManager.pushMatrix();
	        GlStateManager.translate(width / 2f,height / 2f, 0);
	        GlStateManager.scale(3, 3, 1);
	        GlStateManager.translate(-(width / 2f), -(height / 2f), 0);
	        //this.drawCenteredString(mc.fontRendererObj, Nodusplus.name + " b" + Nodusplus.version, width / 2f,height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f, 0x44DF44);
	        GlStateManager.popMatrix();
	    }


	    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
	        String[] buttons = {"Singleplayer", "Multiplayer", "Settings", "Nodus+ on Github", "Quit"};

	        int count = 0;
	        for (String button : buttons) {
	            float x = (width / buttons.length) * count + (width / buttons.length) / 2f + 8 - mc.fontRendererObj.getStringWidth(button) / 2f;
	            float y = height - 20;
	            if (mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(button) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT) {
	                switch (button) {
	                    case "Singleplayer":
	                        mc.displayGuiScreen(new GuiSelectWorld(this));
	                        break;
	                    case "Multiplayer":
	                        mc.displayGuiScreen(new GuiMultiplayer(this));
	                        break;
	                    case "Settings":
	                            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
	                            break;
	                    case "Nodus+ on Github":
	                        Desktop pc = Desktop.getDesktop();
	                        try {
	                            pc.browse(new URI("https://github.com/Infamous-Clients/Redbull-Clients"));
	                        } catch (URISyntaxException e) {
	                            e.printStackTrace();
	                        }
	                        break;
	                    case "Quit":
	                        mc.shutdown();
	                        break;
	                }
	            }
	            count++;
	        }
	    }
	
	}
