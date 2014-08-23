package nl.stefferd.ld30.world;

import nl.stefferd.ld30.Renderable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chunk implements Renderable {
	
	public static final int SIZE = 32;
	
	private int x;
	private int y;
	private Tile[] tiles;
	
	/**
	 * Constructor for a chunk. A chunks keeps track of the tiles within its
	 * bounds.
	 * @param x x index of the chunk
	 * @param y y index of the chunk
	 */
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		tiles = new Tile[SIZE * SIZE];
		
		for (int yp = 0; yp < SIZE; yp++) {
			for (int xp = 0; xp < SIZE; xp++) {
				if ((xp + yp * SIZE) % 3 == 0)
					tiles[xp + yp * SIZE] = new TileDemo(this, xp, yp);
			}
		}
	}
	
	@Override
	public void update() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].update();
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] == null)
				continue;
			tiles[i].render(batch);
		}
		batch.end();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
