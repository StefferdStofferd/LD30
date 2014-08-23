package nl.stefferd.ld30.world;

import java.util.Random;

import nl.stefferd.ld30.Renderable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class World implements Renderable {
	
	private int width, height;
	
	private Chunk[] chunks;
	private OrthographicCamera camera;
	private Random random;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		chunks = new Chunk[width * height];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				chunks[x + y * width] = new Chunk(x, y);
		
		// Init the camera (orthographic) to the dimensions of the screen
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// generate the world based on the random class set
		random = new Random(1);
		generateWorld();
	}
	
	/**
	 * Adds all the (randomly generated) stuff to the world. Adds it in the form
	 * of tiles, items or possibly entities.
	 */
	private void generateWorld() {
		addStarterIsland();
	}
	
	/**
	 * Adds the starter island in the middle of the world
	 */
	private void addStarterIsland() {
		// Get the center chunk
		int centerChunkIndex = width / 2 + height / 2 * width;
		
		// Random center position for the island in the chunk
		int xPos = random.nextInt(Chunk.SIZE);
		int yPos = random.nextInt(Chunk.SIZE);
		
		// adds the tile to the chunk
		// TODO: make easier way of adding tiles to a chunk
		chunks[centerChunkIndex].setTile(new TileDemo(chunks[centerChunkIndex],
				xPos, yPos), xPos, yPos);
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
