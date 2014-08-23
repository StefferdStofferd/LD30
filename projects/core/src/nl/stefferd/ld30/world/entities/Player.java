package nl.stefferd.ld30.world.entities;

import nl.stefferd.ld30.Assets;
import nl.stefferd.ld30.world.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {
	
	private static final float SPEED = 175;

	public Player(World world, float x, float y) {
		super(world, x, y);
	}

	@Override
	public void update() {
		// get the input
		Vector2 movement = new Vector2();
		if (Gdx.input.isKeyPressed(Keys.A)) movement.x = -1;
		if (Gdx.input.isKeyPressed(Keys.D)) movement.x = 1;
		
		// set the speed and correct for delta
		movement.scl(SPEED * Gdx.graphics.getDeltaTime());
		
		// move the player
		move(movement.x, movement.y);
		
		// listen for jump input
		if (Gdx.input.isKeyJustPressed(Keys.W) && isGrounded()) jump(750);
		
		// Call the super version of the update function, to deal with physics
		super.update();
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(Assets.playerPlayer, x, y);
		batch.end();
	}
	
	protected Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

}
