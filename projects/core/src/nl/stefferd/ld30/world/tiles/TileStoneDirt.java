package nl.stefferd.ld30.world.tiles;

import nl.stefferd.ld30.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileStoneDirt extends Tile {

	public TileStoneDirt() {
		super();
	}
	
	public TileStoneDirt(int x, int y) {
		super(x, y);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.tileStoneDirt, getAbsoluteX(), getAbsoluteY());
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
