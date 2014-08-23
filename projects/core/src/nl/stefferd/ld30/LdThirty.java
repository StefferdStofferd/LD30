package nl.stefferd.ld30;

import nl.stefferd.ld30.world.World;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		world.update();
		world.render(batch);
	}
}
