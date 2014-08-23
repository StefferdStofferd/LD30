package nl.stefferd.ld30.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nl.stefferd.ld30.Renderable;

public class World implements Renderable {
	
	private Chunk[] chunks;
	
	public World(int width, int height) {
		chunks = new Chunk[width * height];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				chunks[x + y * width] = new Chunk(x, y);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		for (int i = 0; i < chunks.length; i++) {
			chunks[i].render(batch);
		}
	}
	
}
