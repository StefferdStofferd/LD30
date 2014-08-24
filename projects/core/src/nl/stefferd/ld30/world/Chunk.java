package nl.stefferd.ld30.world;

import nl.stefferd.ld30.Renderable;
import nl.stefferd.ld30.world.tiles.Tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Chunk implements Renderable {
	
	public static final int SIZE = 32;
	
	private int x;
	private int y;
	private Rectangle rect;
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
		rect = new Rectangle(x * SIZE * Tile.SIZE, y * SIZE * Tile.SIZE,
				SIZE * Tile.SIZE, SIZE * Tile.SIZE);
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
	
	/**
	 * Removes the tile at the given position by settings it to null
	 * @param x
	 * @param y
	 */
	public void removeTile(int x, int y) {
		// Set tile to null at given index
		tiles[x + y * SIZE] = null;
	}
	
	/**
	 * Returns the tile at the position given
	 * @param x x position to get the tile
	 * @param y y position to get the tile
	 * @return Tile object
	 */
	public Tile getTile(int x, int y) {
		return tiles[x + y * SIZE];
	}
	
	public Rectangle getAbsoluteRectangle() {
		return rect;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
