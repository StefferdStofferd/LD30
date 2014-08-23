package nl.stefferd.ld30.world.entities;

import nl.stefferd.ld30.Renderable;
import nl.stefferd.ld30.world.World;
import nl.stefferd.ld30.world.tiles.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity implements Renderable {
	
	protected static final float LEFT = -1;
	protected static final float RIGHT = 1;

	public float x;
	public float y;
	protected World world;
	protected Sprite sprite;
	
	private Vector2 momentum;
	
	public Entity(World world, Sprite sprite, float x, float y) {
		this.world = world;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		momentum = new Vector2();
	}
	
	boolean walking = false;
	float direction = RIGHT;
	
	/**
	 * Method that should be called by all the children of this class.
	 */
	@Override
	public void update() {
		momentum.y += -9.8f * Gdx.graphics.getDeltaTime() * Tile.SIZE * 2;
		if (isGrounded() && momentum.y < 0)
			momentum.y = 0;
		// TODO: make landing nicer
		
		if (!walking) {
			momentum.x -= getDecceleration() * Gdx.graphics.getDeltaTime();
			if (momentum.x < 0)
				momentum.x = 0;
		}
		walking = false;
		
		move(momentum.x * Gdx.graphics.getDeltaTime() * direction,
				momentum.y * Gdx.graphics.getDeltaTime());
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
	 * Makes the entity jump with a given force.
	 * @param force force to jump with
	 */
	public void jump(float force) {
		momentum.y += force;
	}
	
	public void walk(float direction) {
		this.direction = direction;
		walking = true;
		momentum.x = Math.min(momentum.x + getAcceleration() *
				Gdx.graphics.getDeltaTime(), getMaxSpeed());
	}
	
	/**
	 * Moves the entity by an amount
	 * @param x the amount to move on the x-axis
	 * @param y the amount to move on the y-axis
	 */
	private void move(float x, float y) {
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
	 * Returns the offset of the point where it has to check for floor
	 * collision.
	 * @return
	 */
	protected abstract Rectangle getBounds();
	
	protected abstract float getAcceleration();
	protected abstract float getDecceleration();
	protected abstract float getMaxSpeed();
	
}
