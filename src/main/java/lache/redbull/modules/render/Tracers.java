package lache.redbull.modules.render;

import lache.redbull.Nodusplus;
import lache.redbull.events.RenderEvent;
import lache.redbull.utils.event.Event;
import lache.redbull.modules.Module;
import lache.redbull.modules.settings.BooleanSetting;
import lache.redbull.utils.render.RenderUtils;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityChest;
import org.lwjgl.input.Keyboard;

public class Tracers extends Module {

    public BooleanSetting player = new BooleanSetting("Players", true);
    public BooleanSetting hostile = new BooleanSetting("Hostile", true);
    public BooleanSetting passive = new BooleanSetting("Passive", true);
    //TODO Storage
    // public BooleanSetting storage = new BooleanSetting("Storage", true);

    public Tracers() {
        super(Nodusplus.beforeNameObj + "Tracers", Keyboard.KEY_NONE, Category.RENDER);
        this.addSettings(player, hostile, passive);
    }

    public void onEvent(Event e) {
        if (!isEnabled())
            return;

        for(Object o : mc.theWorld.loadedEntityList) {
            if (!(o instanceof EntityLivingBase)) {
                continue;
            }

            EntityLivingBase entity = (EntityLivingBase)o;
            float red;
            float green;
            float blue;

            if (mc.thePlayer.getDistanceToEntity(entity) > 25) {
                red = 0f;
                green = 1f;
                blue = 0f;
            } else {
                red = (float) (1 - mc.thePlayer.getDistanceToEntity(entity) * 0.04);
                green = (float) (mc.thePlayer.getDistanceToEntity(entity) * 0.04);
                blue = 0f;
            }

            if (entity.isInvisible()) {
                continue;
            }

            double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
            double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
            double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;

            if (entity instanceof EntityPlayer && player.isEnabled()) {
                    RenderUtils.drawTracerLine(x, y, z, red, green, blue, 0.5F, 1.5F);
            }
            else if (entity instanceof EntityAnimal && passive.isEnabled()) {
                RenderUtils.drawTracerLine(x, y, z, red, green, blue, 0.5F, 1.5F);
            }
            else if (entity instanceof EntityMob && hostile.isEnabled()) {
                RenderUtils.drawTracerLine(x, y, z, red, green, blue, 0.5F, 1.5F);
            }
        }
    }
}
