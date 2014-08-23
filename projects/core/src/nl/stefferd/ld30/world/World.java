package nl.stefferd.ld30.world;

import java.util.Random;

import nl.stefferd.ld30.Assets;
import nl.stefferd.ld30.Renderable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

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
		// set camera to the center of the world
		camera.position.set(width * Chunk.SIZE * Tile.SIZE / 2,
				height * Chunk.SIZE * Tile.SIZE / 2, 0);
		
		// generate the world based on the random class set
		random = new Random(0);
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
		int centerChunkX = width / 2;
		int centerChunkY = height / 2;
		
		// Random center position for the island in the chunk
		int xPos = random.nextInt(Chunk.SIZE);
		int yPos = random.nextInt(Chunk.SIZE);
		xPos = 15;
		yPos = 15;
		
		// adds the tile to the chunk
		Assets.addIsland(this, centerChunkX * Chunk.SIZE + xPos,
				centerChunkY * Chunk.SIZE + yPos);
	}
	
	@Override
	public void update() {
		Vector3 movement = new Vector3();
		if (Gdx.input.isKeyPressed(Keys.W)) movement.y = 1;
		if (Gdx.input.isKeyPressed(Keys.S)) movement.y = -1;
		if (Gdx.input.isKeyPressed(Keys.A)) movement.x = -1;
		if (Gdx.input.isKeyPressed(Keys.D)) movement.x = 1;
		movement.nor().scl(521 * Gdx.graphics.getDeltaTime());
		camera.position.add(movement);
		camera.update();
		System.out.println((int)camera.position.x / (int)Tile.SIZE + ", " +
				(int)camera.position.y / (int)Tile.SIZE);
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
	
	/**
	 * Sets a tile to the chunk the position is in. Overrides a tile if one was
	 * already set.
	 * @param tile tile to be set
	 * @param x absolute x index of the tile
	 * @param y absolute y index of the tile
	 * @return true of false, success or not respectively
	 */
	public boolean setTile(Tile tile, int x, int y) {
		// Get the chunk position where the tile is in
		int chunkX = x / Chunk.SIZE;
		int chunkY = y / Chunk.SIZE;
		
		// Stop if not within world bounds
		if (chunkX < 0 || chunkX >= width ||
				chunkY < 0 || chunkY >= height)
			return false;
		
		// Get the tile position within the chunk
		int tileX = x % Chunk.SIZE;
		int tileY = y % Chunk.SIZE;
		
		// Set the position of the tile
		tile.x = tileX;
		tile.y = tileY;
		
		// If within bounds
		chunks[chunkX + chunkY * width].setTile(tile, tileX, tileY);
		
		// success
		return true;
	}
	
	/**
	 * Sets the position of the player to a tile. Thus snaps to a 64x64 grid.
	 * @param x absolute tile x index
	 * @param y absolute tile y index
	 */
	public void setPlayerPosition(int x, int y) {
		camera.position.set(x * Tile.SIZE + Tile.SIZE / 2,
				y * Tile.SIZE + Tile.SIZE / 2, 0);
	}
	
}
