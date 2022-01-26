package lache.redbull.utils;

import java.awt.Color;

//Dipshit if it wasnt obvious this draws rainbow effect
public class RainbowUtil {
	public static int getRainbow(float seconds, float saturation, float brightness) {
		float hue = (System.currentTimeMillis() % (int)(seconds * 10000)) / (float) (seconds * 1000);
		int color = Color.HSBtoRGB(hue, saturation, brightness);
		return color;
	}

}
