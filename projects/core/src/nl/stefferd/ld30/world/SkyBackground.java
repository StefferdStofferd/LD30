package nl.stefferd.ld30.world;

import nl.stefferd.ld30.Assets;
import nl.stefferd.ld30.Renderable;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SkyBackground implements Renderable {
	
	private World world;
	private Sprite nightSky;
	
	public SkyBackground(World world) {
		this.world = world;
		nightSky = new Sprite(Assets.backgroundNightSky);
	}
	
	@Override
	public void update() {
		Camera cam = world.getCurrentCamera();
		nightSky.setPosition(cam.position.x - cam.viewportWidth / 2, 
				cam.position.y - cam.viewportHeight / 2);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		nightSky.draw(batch);
		batch.end();
	}

}
