package nl.stefferd.ld30.world;

import nl.stefferd.ld30.Assets;
import nl.stefferd.ld30.Renderable;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SkyBackground implements Renderable {
	
	private World world;
	private Sprite nightSky;
	private boolean displayNightSky = true;
	
	public SkyBackground(World world) {
		this.world = world;
		nightSky = new Sprite(Assets.backgroundNightSky);
	}
	
	@Override
	public void update() {
		// Update the sprite position
		Camera cam = world.getCurrentCamera();
		nightSky.setPosition(cam.position.x - cam.viewportWidth / 2, 
				cam.position.y - cam.viewportHeight / 2);
		
		float timeOfDay = world.getTimeOfDay();
		
		// TODO: optimize by checking for day first
		
		if (timeOfDay >= 120 && timeOfDay < 120 + 30) {
			// transition into day
			float progress = (timeOfDay - 120) / 30;
			nightSky.setAlpha(1 - progress);
		}
		else if (timeOfDay >= 150 && timeOfDay < 150 + 270) {
			// during day time
			displayNightSky = false;
		}
		else if (timeOfDay >= 420 && timeOfDay < 420 + 30) {
			// transition into night
			float progress = (timeOfDay - 420) / 30;
			nightSky.setAlpha(1 - progress);
			// show the night sky again
			displayNightSky = true;
		}
		else if (timeOfDay >= 450 || timeOfDay < 120) {
			// during night time
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		if (displayNightSky)
			nightSky.draw(batch);
		batch.end();
	}

}
