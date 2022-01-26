package lache.redbull.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.utils.Timer;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;
import lache.redbull.modules.settings.BooleanSetting;
import lache.redbull.modules.settings.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0APacketAnimation;

public class KillAura extends Module {
    public Timer timer = new Timer();

    public NumberSetting range = new NumberSetting("Range", 4, 1, 6, 0.1);
    public NumberSetting aps = new NumberSetting("APS", 10, 1, 20, 1);
    public BooleanSetting noSwing = new BooleanSetting("No Swing", false);
    public BooleanSetting rotate = new BooleanSetting("Rotations", false);

    public KillAura() {
        super(Nodusplus.beforeNameObj + "KillAura", 0, Category.COMBAT);
        this.addSettings(range, aps, noSwing, rotate);
    }

    public void onDisable() {
    }

    public void onEnable() {
    }

    public void onEvent(Event e) {
        if (e instanceof UpdateEvent) {
            List<EntityLivingBase> targets = (List<EntityLivingBase>) this.mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());


            targets = targets.stream().filter(entity -> (entity.getDistanceToEntity(this.mc.thePlayer) < range.getValue() && entity != this.mc.thePlayer && !entity.isDead && entity.getHealth() > 0.0F)).collect(Collectors.toList());


            targets.sort(Comparator.comparingDouble(entity -> entity.getDistanceToEntity(this.mc.thePlayer)));


            if (!targets.isEmpty()) {
                EntityLivingBase target = targets.get(0);

                if (rotate.isEnabled()) {
                    mc.thePlayer.rotationYaw = (getRotations(target)[0]);
                    mc.thePlayer.rotationPitch = (getRotations(target)[0]);
                } else {

                }


                if (this.timer.hasTimeElapsed((long) (1000 / aps.getValue()), true)) {
                    if (noSwing.isEnabled()) {
                        mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
                    } else {
                        this.mc.thePlayer.swingItem();
                    }
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, C02PacketUseEntity.Action.ATTACK));
                }
            }
        }
    }

    public float[] getRotations(Entity e) {
        double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX,
                deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
                deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
                distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));

        float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
                pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));

        if (deltaX < 0 && deltaZ < 0) {
            yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        } else if (deltaX > 0 && deltaZ < 0) {
            yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        }

        return new float[]{yaw, pitch};
    }
}
