package nl.stefferd.ld30.world;

import nl.stefferd.ld30.Renderable;

public class Chunk implements Renderable {
	
	public static final int SIZE = 32;
	
	private int x;
	private int y;
	private Tile[] tiles;
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		tiles = new Tile[SIZE * SIZE];
	}
	
	@Override
	public void update() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].update();
		}
	}
	
	@Override
	public void render() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].render();
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
