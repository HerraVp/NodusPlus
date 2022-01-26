package lache.redbull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lache.redbull.modules.render.*;
import org.lwjgl.opengl.Display;

import lache.eitheroverritenornewui.HUD;
import lache.redbull.commandshit.CommandManager;
import lache.redbull.utils.event.Event;
import lache.redbull.events.ChatEvent;
import lache.redbull.events.KeyEvent;
import lache.redbull.modules.Module;
import lache.redbull.modules.Module.Category;
import lache.redbull.modules.combat.BowAimbot;
import lache.redbull.modules.combat.KillAura;
import lache.redbull.modules.movement.BHop;
import lache.redbull.modules.movement.FastLadder;
import lache.redbull.modules.movement.Flight;
import lache.redbull.modules.movement.Jetpack;
import lache.redbull.modules.movement.Nofall;
import lache.redbull.modules.movement.Sneak;
import lache.redbull.modules.movement.Speed;
import lache.redbull.modules.movement.Spider;
import lache.redbull.modules.movement.Sprint;
import lache.redbull.modules.movement.Step;
import lache.redbull.modules.world.AutoRespawn;
import lache.redbull.modules.world.ChestStealer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Nodusplus {

	/**Nodus+ Main Class**/
	public static Nodusplus instance = new Nodusplus();
	public static String name = "Nodus+";
	public static String version = "1.6";
	public static String beforeNameObj = ">";
	public static String author = "KayEmEfDem";
	public static String chatName = "N";
	public static String ghost = "PVP Type";
	
	
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static CommandManager commandManager = new CommandManager();
	public static HUD hud = new HUD();

	public static void onEvent(Event e) {
		if(e instanceof ChatEvent) {
			commandManager.handleChat((ChatEvent)e);
		}
		for(Module m : modules) {
			if(!m.toggled)
				continue;
			
			m.onEvent(e);
		}
	}
	
	public static void start() {
		Display.setTitle(Nodusplus.name + " " + Nodusplus.version);
		System.out.println(Nodusplus.name + " " + "has been activated successfully!");


		//PLAYER
		modules.add(new Flight());
		modules.add(new Sprint());
		modules.add(new Step());
		modules.add(new Nofall());
		modules.add(new BHop());
		modules.add(new FastLadder());
		modules.add(new Sneak());
		modules.add(new Speed());
		modules.add(new Spider());
		modules.add(new Jetpack());
		//modules.add(new Derp());
		
		//GUI RELATED
		modules.add(new TabGUI());		
		modules.add(new ClickGUI());
		//COMBAT
		modules.add(new KillAura());
		modules.add(new BowAimbot());
		
		//WORLD
		modules.add(new ChestStealer());
		modules.add(new AutoRespawn());
		
		//RENDER
		modules.add(new Xray());
		modules.add(new Fullbright());
		modules.add(new ChestESP());
		modules.add(new ESP());
		modules.add(new Tracers());
	}
	
	public static List<Module> getModulesByCategory(Category c){
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : Nodusplus.modules) {
			if(m.category == c)
				modules.add(m);
		}
		return modules;
		
	}
	
	public static void addChatMessage(String message) {
		message = "\u00A7a" + name + "\u00A7f:" + message;
		
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
	
	public static void keyPress(int key) {
		Nodusplus.onEvent(new KeyEvent(key));
		for(Module m : modules) {
			if(m.getKey() == key) {
				m.toggle();
			}
		}
	}

}