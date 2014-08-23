package nl.stefferd.ld30.world;

import nl.stefferd.ld30.Renderable;

public abstract class Tile implements Renderable {
	
	public static final float SIZE = 128;

	protected Chunk chunk;
	public int x;
	public int y;
	
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
	
}
