package lache.redbull.modules.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.modules.Module;
import net.minecraft.block.Block;

public class Xray extends Module {

	public static boolean enabled;
	private float oldGamma;
	public ArrayList<Block> xrayBlocks = new ArrayList<Block>();

	public Xray() {
		super(Nodusplus.beforeNameObj + "Xray", Keyboard.KEY_NONE, Category.RENDER);
	}
	

	public void onEnable() {
		Xray.enabled = true;
		this.oldGamma = mc.gameSettings.gammaSetting;
		mc.gameSettings.gammaSetting = 10.0f;
		mc.gameSettings.ambientOcclusion = 0;
		mc.renderGlobal.loadRenderers();
	}
	
	public void onDisable() {
		Xray.enabled = false;
		this.oldGamma = mc.gameSettings.gammaSetting;
		mc.gameSettings.ambientOcclusion = 1;
		mc.renderGlobal.loadRenderers();
	}
	
	public boolean shouldXrayBlock(Block blockid) {
		if(this.xrayBlocks.contains(blockid)) {
			return true;
		}
		return false;
	}
	

}
