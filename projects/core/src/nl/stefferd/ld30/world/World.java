package nl.stefferd.ld30.world;

import nl.stefferd.ld30.Renderable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class World implements Renderable {
	
	private Chunk[] chunks;
	private OrthographicCamera camera;
	
	public World(int width, int height) {
		chunks = new Chunk[width * height];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				chunks[x + y * width] = new Chunk(x, y);
		
		// Init the camera (orthographic) to the dimensions of the screen
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	float f = 0;
	
	@Override
	public void update() {
		f += 128 * Gdx.graphics.getDeltaTime();
		camera.position.x = f;
		camera.update();
	}

	@Override
	public void render(SpriteBatch batch) {
		// Apply the camera's transformations to the sprite rendering
		batch.setProjectionMatrix(camera.combined);
		
		// Render all the chunks this world contains
		for (int i = 0; i < chunks.length; i++) {
			chunks[i].render(batch);
		}
	}
	
}
