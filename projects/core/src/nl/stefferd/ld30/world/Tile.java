package nl.stefferd.ld30.world;

import nl.stefferd.ld30.Renderable;

public abstract class Tile implements Renderable {
	
	public static final float SIZE = 64;

	protected Chunk chunk;
	public int x;
	public int y;
	
	/**
	 * Empty constructor for a tile. The parent chunk needs to be set before
	 * the tile can be used. The position will default to (0, 0).
	 */
	public Tile() {
		this(0, 0);
	}
	
	/**
	 * A little more complex constructor. It sets the position of the tile, but
	 * it leaves the parent chunk at null. The parent chunk does need to be set
	 * before the tile can be used.
	 * @param x
	 * @param y
	 */
	public Tile(int x, int y) {
		this(null, x, y);
	}
	
	/**
	 * Full constructor for a Tile in the game. Requires to be given the chunk
	 * where it it placed in, so the absolute position can be determined.
	 * @param x
	 * @param y
	 */
	public Tile(Chunk chunk, int x, int y) {
		this.chunk = chunk;
		this.x = x;
		this.y = y;
	}
	
	public boolean isSolid() {
		return false;
	}
	
	/**
	 * Returns the absolute x index of this tile from the world origin
	 * @return any positive integer number
	 */
	public int getAbsoluteXIndex() {
		return chunk.getX() * Chunk.SIZE + x;
	}
	
	/**
	 * Returns the absolute y index of this tile from the world origin
	 * @return any positive integer number
	 */
	public int getAbsoluteYIndex() {
		return chunk.getY() * Chunk.SIZE + y;
	}
	
	/**
	 * Returns the absolute x pixel position of the tile in the world.
	 * @return any float, most likely positive
	 */
	public float getAbsoluteX() {
		return getAbsoluteXIndex() * SIZE;
	}

	/**
	 * Returns the absolute y pixel position of the tile in the world.
	 * @return any float, most likely positive
	 */
	public float getAbsoluteY() {
		return getAbsoluteYIndex() * SIZE;
	}
	
	/**
	 * Sets the parent chunk for this tile. A chunk has to be set before the
	 * tile can be used.
	 * @param chunk parent chunk to set
	 */
	public void setChunk(Chunk chunk) {
		this.chunk = chunk;
	}
	
	/**
	 * Returns whether this tile can be used, in other words whether it has a
	 * parent chunk.
	 * @return true or false, usable or not respectively
	 */
	public boolean isUsable() {
		return chunk != null;
	}
	
}
