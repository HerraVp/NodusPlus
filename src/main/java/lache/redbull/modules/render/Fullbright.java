package lache.redbull.modules.render;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.modules.Module;

public class Fullbright extends Module {

	public Fullbright() {
		super(Nodusplus.beforeNameObj + "Fullbright", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	public void onDisable() {
		mc.gameSettings.gammaSetting = 0;
	}

	public void onEnable() {
		mc.gameSettings.gammaSetting = 69420;
	}
	
	}

