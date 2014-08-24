package nl.stefferd.ld30.world.entities;

import nl.stefferd.ld30.Renderable;
import nl.stefferd.ld30.world.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity implements Renderable {
	
	protected static final float LEFT = -1;
	protected static final float RIGHT = 1;

	public float x;
	public float y;
	protected World world;
	protected Sprite sprite;
	protected float lifetime = 0;
	
	public Entity(World world, Sprite sprite, float x, float y) {
		this.world = world;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Method that should be called by all the children of this class.
	 */
	@Override
	public void update() {
		lifetime += Gdx.graphics.getDeltaTime();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		sprite.setPosition(x, y);
		sprite.setColor(batch.getColor());
		sprite.draw(batch);
		batch.end();
	}
	
	/**
	 * Moves the entity by an amount
	 * @param x the amount to move on the x-axis
	 * @param y the amount to move on the y-axis
	 */
	protected void move(float x, float y) {
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
		return world.touchesCollidableTile(getBounds());
	}
	
	/**
	 * Returns whether this entity is effected by gravity.
	 * @return true of false, is or isn't effected by gravity respectively
	 */
	public boolean isAffectedByGravity() {
		return true;
	}
	
	/**
	 * Removes this entity from the list of entities in the world class
	 */
	public void destroy() {
		world.removeEntity(this);
	}
	
	/**
	 * Returns the offset of the point where it has to check for floor
	 * collision.
	 * @return
	 */
	protected abstract Rectangle getBounds();
	
}
