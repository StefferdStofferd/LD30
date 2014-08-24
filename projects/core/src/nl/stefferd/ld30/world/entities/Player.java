package nl.stefferd.ld30.world.entities;

import nl.stefferd.ld30.Assets;
import nl.stefferd.ld30.world.World;
import nl.stefferd.ld30.world.tiles.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {
	
	private static final float SPEED = 350;
	
	private Vector2 momentum;

	public Player(World world, float x, float y) {
		super(world, new Sprite(Assets.playerPlayer), x, y);
		momentum = new Vector2();
	}
	
	boolean walking = false;
	float direction = RIGHT;

	@Override
	public void update() {
		if (Gdx.input.justTouched()) {
			shootDemat();
		}
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			sprite.setScale(-1, 1);
			walk(LEFT);
		}
		if (Gdx.input.isKeyPressed(Keys.D)){
			sprite.setScale(1, 1);
			walk(RIGHT);
		}
		
		// listen for jump input
		if (Gdx.input.isKeyJustPressed(Keys.W) && isGrounded()) jump(750);
		
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
	
	public void walk(float direction) {
		this.direction = direction;
		walking = true;
		momentum.x = Math.min(momentum.x + getAcceleration() *
				Gdx.graphics.getDeltaTime(), getMaxSpeed());
	}
	
	/**
	 * Makes the entity jump with a given force.
	 * @param force force to jump with
	 */
	public void jump(float force) {
		momentum.y += force;
	}
	
	final Vector2 center = new Vector2(1280/2, 720/2+100-32);
	private void shootDemat() {
		Vector2 mouse = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		world.addEntity(new DematBullet(world, x + 32, y + 32,
				mouse.sub(center).scl(1, -1)));
	}
	
	protected Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

	protected float getAcceleration() {
		return 800;
	}
	
	protected float getDecceleration() {
		return 1000;
	}

	protected float getMaxSpeed() {
		return SPEED;
	}

}
