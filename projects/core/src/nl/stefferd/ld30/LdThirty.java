package nl.stefferd.ld30;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LdThirty extends Game {
	
	SpriteBatch batch;
	
	@Override
	public void create () {
		Assets.load();
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(Assets.tileDemo, 0, 0);
		batch.end();
	}
}
