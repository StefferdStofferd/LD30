package nl.stefferd.ld30.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import nl.stefferd.ld30.LdThirty;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.width = 1280;
		config.height = 720;
		config.title = "Ludum Dare #30";
		new LwjglApplication(new LdThirty(), config);
	}
}
