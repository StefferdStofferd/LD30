package nl.stefferd.ld30.world.entities;

import nl.stefferd.ld30.Renderable;
import nl.stefferd.ld30.world.Tile;
import nl.stefferd.ld30.world.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity implements Renderable {

	public float x;
	public float y;
	protected World world;
	
	private float yMomentum = 0;
	
	public Entity(World world, float x, float y) {
		this.world = world;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Method that should be called by all the children of this class.
	 */
	@Override
	public void update() {
		yMomentum += -9.8f * Gdx.graphics.getDeltaTime() * Tile.SIZE;
		if (isGrounded() ||
				wouldBeGrounded(x,y + yMomentum * Gdx.graphics.getDeltaTime()))
			yMomentum = 0;
		
		move(0, yMomentum * Gdx.graphics.getDeltaTime());
	}
	
	/**
	 * Makes the entity jump with a given force.
	 * @param force force to jump with
	 */
	public void jump(float force) {
		yMomentum += force;
	}
	
	/**
	 * Moves the entity by an amount
	 * @param x the amount to move on the x-axis
	 * @param y the amount to move on the y-axis
	 */
	public void move(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	/**
	 * Sets the position of the entity to the coordinates given.
	 * @param x new location on the x-axis
	 * @param y new location on the y-axis
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isGrounded() {
		Vector2 offs = getCollisionPointOffset();
		return world.isTileAt(x + offs.x, y + offs.y);
	}
	
	public boolean wouldBeGrounded(float x, float y) {
		return world.isTileAt(x, y);
	}
	
	/**
	 * Returns whether this entity is effected by gravity.
	 * @return true of false, is or isn't effected by gravity respectively
	 */
	public boolean isAffectedByGravity() {
		return true;
	}
	
	/**
	 * Returns the offset of the point where it has to check for floor
	 * collision.
	 * @return
	 */
	protected abstract Vector2 getCollisionPointOffset();
	
}
