package lache.redbull.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import lache.redbull.utils.event.Event;
import lache.redbull.modules.settings.KeybindSetting;
import lache.redbull.modules.settings.Setting;
import net.minecraft.client.Minecraft;

public abstract class Module {
	
	public String name;
	public boolean toggled;
	//public int keyCode;
	public Category category;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public List<Setting> settings = new ArrayList<Setting>();
	public boolean expanded;
	public int index;
	
	public KeybindSetting keyCode = new KeybindSetting(0);
	
	public Module(String name, int key, Category c) {
		this.name = name;
		keyCode.code = key;
		this.category = c;
		this.addSettings(keyCode);
	}
	
	public void addSettings(Setting...settings) {
		this.settings.addAll(Arrays.asList(settings));
		this.settings.sort(Comparator.comparingInt(s -> s != keyCode ? 1 : 0));
	}
	
	public void onEvent(Event e) {
		
	}
	
	public boolean isEnabled() {
		return toggled;
	}
	
	public int getKey() {
		return keyCode.code;
	}
	
	public void toggle() {
		toggled = !toggled;
		if(toggled) {
			onEnable();
		}else {
			onDisable();
		}
		
	}

	public void onDisable() {
		
	}

	public void onEnable() {

	}

    public enum Category {
		COMBAT(">Combat"),
		PLAYER(">Player"),
		RENDER(">Display"),
		WORLD(">World");
		
		public String name;
		public int moduleIndex;
		
		Category(String name){
		this.name = name;
		}
	}
	

}
