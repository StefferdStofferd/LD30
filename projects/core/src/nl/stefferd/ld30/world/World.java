package nl.stefferd.ld30.world;

public class World {
	
	private Chunk[] chuncks;
	
	public World(int width, int height) {
		chuncks = new Chunk[width * height];
	}
	
}
