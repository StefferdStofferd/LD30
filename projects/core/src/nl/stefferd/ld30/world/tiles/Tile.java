package nl.stefferd.ld30.world.tiles;

import nl.stefferd.ld30.Renderable;
import nl.stefferd.ld30.world.Chunk;

import com.badlogic.gdx.math.Rectangle;

public abstract class Tile implements Renderable {
	
	public static final float SIZE = 64;

	protected Chunk chunk;
	private int x;
	private int y;
	private Rectangle rect;
	
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
	
	/**
	 * Removes this tile fromt the worldq
	 */
	public void remove() {
		// Remove this tile from the chunk
		chunk.removeTile(x, y);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public boolean isBreakable() {
		return true;
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
	
	public Rectangle getAbsoluteRectangle() {
		return rect;
	}
	
	/**
	 * Sets the parent chunk for this tile. A chunk has to be set before the
	 * tile can be used.
	 * @param chunk parent chunk to set
	 */
	public void setChunk(Chunk chunk) {
		this.chunk = chunk;
		rect = new Rectangle(getAbsoluteX(), getAbsoluteY(), SIZE, SIZE);
	}
	
	/**
	 * Returns whether this tile can be used, in other words whether it has a
	 * parent chunk.
	 * @return true or false, usable or not respectively
	 */
	public boolean isUsable() {
		return chunk != null;
	}
	
	public void setX(int x) {
		this.x = x;
		if (isUsable())
			rect = new Rectangle(getAbsoluteX(), getAbsoluteY(), SIZE, SIZE);
	}
	
	public void setY(int y) {
		this.y = y;
		if (isUsable())
			rect = new Rectangle(getAbsoluteX(), getAbsoluteY(), SIZE, SIZE);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Chunk getChunk() {
		return chunk;
	}
	
}
