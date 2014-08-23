package nl.stefferd.ld30.world.tiles;

import nl.stefferd.ld30.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileWood extends Tile {

	public TileWood() {
		super();
	}
	
	public TileWood(int x, int y) {
		super(x, y);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.tileWood, getAbsoluteX(), getAbsoluteY());
	}
	
}
