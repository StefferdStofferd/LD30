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
		/*
		for (int yp = 0; yp < SIZE; yp++) {
			for (int xp = 0; xp < SIZE; xp++) {
				if ((xp + yp * SIZE) % 3 == 0)
					tiles[xp + yp * SIZE] = new TileDemo(this, xp, yp);
			}
		}*/
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
	
	/**
	 * Sets the tile at a position to a new one.
	 * @param tile tile to be set
	 * @param x x-position within this chunk
	 * @param y y-position within this chunk
	 */
	public void setTile(Tile tile, int x, int y) {
		// Set this chunk as the parent
		tile.setChunk(this);
		
		// check whether the tile is in the bounds of this chunk
		if (x < 0 || x >= SIZE || y < 0 || y >= SIZE)
			return;
		
		// Add the tile to the tiles array
		tiles[x + y * SIZE] = tile;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
