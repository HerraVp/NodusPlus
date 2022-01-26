package lache.redbull.modules.settings;

public class KeybindSetting extends Setting {
	
	public int code;
	
	public KeybindSetting(int code) {
		this.name = "Keybind";
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setKeyCode(int code) {
		this.code = code;
	}

}
