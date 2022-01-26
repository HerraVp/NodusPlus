package lache.redbull.modules.render;

import lache.redbull.events.RenderEvent;
import lache.redbull.utils.render.RenderUtils;
import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;
import net.minecraft.tileentity.TileEntityChest;

public class ChestESP extends Module {

	public ChestESP() {
		super(Nodusplus.beforeNameObj + "ChestESP", Keyboard.KEY_NONE, Category.RENDER);
	}

	public void onEvent(Event e) {
		if(!this.isEnabled())
			return;

		if(e instanceof RenderEvent) {
			if(e.isPost()) {
				for(Object o: mc.theWorld.loadedTileEntityList) {
					if (o instanceof TileEntityChest) {
						double x = ((TileEntityChest) o).getPos().getX();
						double y = ((TileEntityChest) o).getPos().getY();
						double z = ((TileEntityChest) o).getPos().getZ();

						RenderUtils.drawOutlinedBlockESP(x, y, z, 0f, 1f, 1f, 1f, 1.5f);
					}
				}
			}
		}
	}

}
