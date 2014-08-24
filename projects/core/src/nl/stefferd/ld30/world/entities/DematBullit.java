package nl.stefferd.ld30.world.entities;

import nl.stefferd.ld30.Assets;
import nl.stefferd.ld30.world.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DematBullit extends Entity {
	
	private Vector2 direction;

	public DematBullit(World world, float x, float y, Vector2 dir) {
		super(world, new Sprite(Assets.entityDematBullit), x, y);
		direction = dir.nor();
	}
	
	@Override
	public void update() {
		Vector2 mv = direction.cpy().scl(1000 * Gdx.graphics.getDeltaTime());
		x += mv.x;
		y += mv.y;
	}

	@Override
	protected Rectangle getBounds() {
		return null;
	}

}
