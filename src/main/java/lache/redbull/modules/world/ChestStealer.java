package lache.redbull.modules.world;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.modules.Module;

public class ChestStealer extends Module {

	public ChestStealer() {
		super(Nodusplus.beforeNameObj +"ChestSteal", Keyboard.KEY_NONE, Category.WORLD);
	}
	

}
