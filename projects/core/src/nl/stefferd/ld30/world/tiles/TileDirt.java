package nl.stefferd.ld30.world.tiles;

import nl.stefferd.ld30.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileDirt extends Tile {

	public TileDirt() {
		super();
	}
	
	public TileDirt(int x, int y) {
		super(x, y);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.tileDirt, getAbsoluteX(), getAbsoluteY());
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
