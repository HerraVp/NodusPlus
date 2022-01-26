package lache.redbull.modules.render;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.modules.Module;
import lache.redbull.modules.Module.Category;

public class ClickGUI extends Module {

	public ClickGUI() {
		super(Nodusplus.beforeNameObj + "ClickGUI", Keyboard.KEY_NONE, Category.RENDER);
		toggled = true;
	}

}
