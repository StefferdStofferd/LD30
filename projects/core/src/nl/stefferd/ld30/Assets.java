package nl.stefferd.ld30;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	public static TextureRegion tileDemo;
	
	public static void load() {
		TextureAtlas tiles = new TextureAtlas("sprites/tiles.pack");
		tileDemo = tiles.findRegion("demo");
	}

}
