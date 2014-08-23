package nl.stefferd.ld30.world.entities;

import nl.stefferd.ld30.Assets;
import nl.stefferd.ld30.world.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Entity {
	
	private static final float SPEED = 350;

	public Player(World world, float x, float y) {
		super(world, new Sprite(Assets.playerPlayer), x, y);
	}

	@Override
	public void update() {
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
		
		// Call the super version of the update function, to deal with physics
		super.update();
	}
	
	protected Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

	@Override
	protected float getAcceleration() {
		return 800;
	}
	
	@Override
	protected float getDecceleration() {
		return 1000;
	}

	@Override
	protected float getMaxSpeed() {
		return SPEED;
	}

}
