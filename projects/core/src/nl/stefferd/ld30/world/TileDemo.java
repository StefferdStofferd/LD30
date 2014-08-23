package nl.stefferd.ld30.world;

import nl.stefferd.ld30.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileDemo extends Tile {

	public TileDemo() {
		super();
	}
	
	public TileDemo(int x, int y) {
		super(x, y);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.tileDemo, getAbsoluteX(), getAbsoluteY());
	}
	
}
