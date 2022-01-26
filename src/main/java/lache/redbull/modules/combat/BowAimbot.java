package lache.redbull.modules.combat;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import lache.redbull.Nodusplus;
import lache.redbull.utils.event.Event;
import lache.redbull.events.UpdateEvent;
import lache.redbull.modules.Module;
import lache.redbull.modules.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBow;

public class BowAimbot extends Module {

	private float targetPitch;
	private float targetYaw;
	private EntityLivingBase targetEntity;
	
	//Settings
	public BooleanSetting pumpedupkicks = new BooleanSetting("Pumped Up Kicks Mode", false);
	
	public BowAimbot() {
		super(Nodusplus.beforeNameObj + "BowAimbot", Keyboard.KEY_NONE, Category.COMBAT);
		this.addSettings(pumpedupkicks);
	}
	
	public void onDisable() {
	}		

	public void onEnable() {
	}
	
	public void onEvent(Event e) {
		if(e instanceof UpdateEvent) {
			if(e.isPre()) {
				  if(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null)
				    {
				    	if((Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow))
				    	{
				    		this.targetEntity = getCursorEntity();
				
					        if (this.targetEntity == null) 
					        	return;
					        
					        this.targetPitch = Minecraft.getMinecraft().thePlayer.rotationPitch;
					        this.targetYaw = Minecraft.getMinecraft().thePlayer.rotationYaw;
					        this.silentAim(this.targetEntity);
				    	}
				    }
				
			}}
				}
				
				public void postUpdate()
				{
					if((this.targetEntity != null) && (Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null) && ((Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow)))
					{
						Minecraft.getMinecraft().thePlayer.rotationPitch = this.targetPitch;
						Minecraft.getMinecraft().thePlayer.rotationYaw = this.targetYaw;
					}
				}
			 
				public void silentAim(EntityLivingBase targetEntity)
				{
					int bowCurrentCharge = Minecraft.getMinecraft().thePlayer.getItemInUseDuration();
				    float bowVelocity = bowCurrentCharge / 20.0F;
				    bowVelocity = (bowVelocity * bowVelocity + bowVelocity * 2.0F) / 3.0F;
			 
				    if(bowVelocity < 0.1D)
				    {
				    	return;
				    }
			 
				    if(bowVelocity > 1.0F)
				    {
				    	bowVelocity = 1.0F;
				    }
			 
				    double xDistance = targetEntity.posX - Minecraft.getMinecraft().thePlayer.posX;
				    double zDistance = targetEntity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
				    double eyeDistance = targetEntity.posY + targetEntity.getEyeHeight() - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
				    double trajectoryXZ = Math.sqrt(xDistance * xDistance + zDistance * zDistance);
				    double eyeTrajectoryXZ = Math.sqrt(trajectoryXZ * trajectoryXZ + eyeDistance * eyeDistance);
				    float trajectoryTheta90 = (float)(Math.atan2(zDistance, xDistance) * 180.0D / 3.141592653589793D) - 90.0F;
				    
				    float bowTrajectory = -getTrajectoryAngleSolutionLow((float)trajectoryXZ, (float)eyeDistance, bowVelocity);
				    
				    Minecraft.getMinecraft().thePlayer.rotationPitch = bowTrajectory;
				    Minecraft.getMinecraft().thePlayer.rotationYaw = trajectoryTheta90;
				}
			 
				  public EntityLivingBase getCursorEntity()
				  {
					  EntityLivingBase poorEntity = null;
					  double distanceToEntity = 1000.0D;
				    
					  for(Iterator entityIterator = Minecraft.getMinecraft().theWorld.loadedEntityList.iterator(); entityIterator.hasNext();) 
					  { 
						  Object currentObject = entityIterator.next();
			 
						  if((currentObject instanceof Entity)) 
						  {
							  Entity targetEntity = (Entity)currentObject;
			 
							  if((!(targetEntity instanceof EntityLivingBase)) || (targetEntity == Minecraft.getMinecraft().thePlayer))
								  continue;
							  if((targetEntity.getDistanceToEntity(Minecraft.getMinecraft().thePlayer) > 140.0F) || (!Minecraft.getMinecraft().thePlayer.canEntityBeSeen(targetEntity)) || (((EntityLivingBase)targetEntity).deathTime > 0))
							  {
								  continue;
							  }
				    		
							  if (poorEntity == null)
							  {
								  poorEntity = (EntityLivingBase)targetEntity;
							  }
				    		
							  double xDistance = targetEntity.posX - Minecraft.getMinecraft().thePlayer.posX;
							  double zDistance = targetEntity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
							  double eyeDistance = Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight() - (targetEntity.posY + targetEntity.getEyeHeight());
							  double trajectoryXZ = Math.sqrt(xDistance * xDistance + zDistance * zDistance);
							  float trajectoryTheta90 = (float)(Math.atan2(zDistance, xDistance) * 180.0D / 3.141592653589793D) - 90.0F;
							  float trajectoryTheta = (float)(Math.atan2(eyeDistance, trajectoryXZ) * 180.0D / 3.141592653589793D);
			 
							  double xAngleDistance = getDistanceBetweenAngles(trajectoryTheta90, Minecraft.getMinecraft().thePlayer.rotationYaw % 360.0F);
							  double yAngleDistance = getDistanceBetweenAngles(trajectoryTheta, Minecraft.getMinecraft().thePlayer.rotationPitch % 360.0F);
			 
							  double entityDistance = Math.sqrt(xAngleDistance * xAngleDistance + yAngleDistance * yAngleDistance);
				    		
							  if(entityDistance >= distanceToEntity)
								  continue;
							  poorEntity = (EntityLivingBase)targetEntity;
							  distanceToEntity = entityDistance;
						  }
			 
					  }
					  return poorEntity;
				  }
				  
				  private float getTrajectoryAngleSolutionLow(float angleX, float angleY, float bowVelocity)
				  {
					  float velocityIncrement = 0.006F;
					  float squareRootBow = bowVelocity * bowVelocity * bowVelocity * bowVelocity - velocityIncrement * (velocityIncrement * (angleX * angleX) + 2.0F * angleY * (bowVelocity * bowVelocity));
					  return (float)Math.toDegrees(Math.atan((bowVelocity * bowVelocity - Math.sqrt(squareRootBow)) / (velocityIncrement * angleX)));
				  }
			 
				  private float getDistanceBetweenAngles(float angle1, float angle2)
				  {
					  float angleToEntity = Math.abs(angle1 - angle2) % 360.0F;
					  if (angleToEntity > 180.0F)
					  {
						  angleToEntity = 360.0F - angleToEntity;
					  }
					  return angleToEntity;
				  }

}
