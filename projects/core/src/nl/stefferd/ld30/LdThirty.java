package nl.stefferd.ld30;

import nl.stefferd.ld30.world.World;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LdThirty extends Game {
	
	SpriteBatch batch;
	World world;
	
	@Override
	public void create () {
		Assets.load();
		batch = new SpriteBatch();
		world = new World(1, 1);
	}

	@Override
	public void render () {
		world.render(batch);
	}
}
