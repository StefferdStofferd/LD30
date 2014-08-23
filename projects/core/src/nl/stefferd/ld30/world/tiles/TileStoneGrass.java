package nl.stefferd.ld30.world.tiles;

import nl.stefferd.ld30.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileStoneGrass extends Tile {

	public TileStoneGrass() {
		super();
	}
	
	public TileStoneGrass(int x, int y) {
		super(x, y);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.tileStoneGrass, getAbsoluteX(), getAbsoluteY());
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
