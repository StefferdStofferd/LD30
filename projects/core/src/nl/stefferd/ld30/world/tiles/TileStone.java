package nl.stefferd.ld30.world.tiles;

import nl.stefferd.ld30.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileStone extends Tile {

	public TileStone() {
		super();
	}
	
	public TileStone(int x, int y) {
		super(x, y);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.tileStone, getAbsoluteX(), getAbsoluteY());
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
