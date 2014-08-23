package nl.stefferd.ld30;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import javax.imageio.ImageIO;

import nl.stefferd.ld30.world.TileDemo;
import nl.stefferd.ld30.world.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	public static TextureRegion tileDemo;
	
	public static TextureRegion playerPlayer;
	
	private static int[][] islandStart;
	
	public static void load() throws IOException {
		TextureAtlas tiles = new TextureAtlas("sprites/tiles.pack");
		tileDemo = tiles.findRegion("demo");

		TextureAtlas players = new TextureAtlas("sprites/player.pack");
		playerPlayer = players.findRegion("player");
		
		islandStart = getPixelArray(ImageIO.read(Gdx.files.internal("islands/start.png").read()));
	}
	
	/**
	 * Generates an island at the position specified. The position will be in
	 * the center of the island.
	 * @param world world to add the island to
	 * @param x center x position of the island, absolute x tile index
	 * @param y center y position of the island, absolute y tile index
	 */
	public static void addIsland(World world, int x, int y) {
		int startX = x - islandStart[0].length / 2;
		int startY = y - islandStart.length / 2;
		
		for (int yp = 0; yp < islandStart.length; yp++) {
			for (int xp = 0; xp < islandStart[0].length; xp++) {
				int color = islandStart[yp][xp];
				if (color == 0xff000000) {
					world.setTile(new TileDemo(), startX + xp, startY + yp);
				} else if (color == 0xff0000ff) {
					// TODO: set player position
					world.setPlayerPosition(startX + xp, startY + yp);
				}
			}
		}
	}
	
	/**
	 * Gets the ARGB integer values from an image
	 * @param image image to extract
	 * @return 3 dimensional integer array, int[row][column]
	 */
	private static int[][] getPixelArray(BufferedImage image) {
		final byte[] PIXELS = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		final int WIDTH = image.getWidth();
		final int HEIGHT = image.getHeight();
		final boolean HAS_ALPHA = image.getAlphaRaster() != null;
		
		int[][] result = new int[HEIGHT][WIDTH];
		if (HAS_ALPHA) {
			final int PIXEL_LENGTH = 4;
			for (int pixel = 0, row = 0, col = 0; pixel < PIXELS.length; pixel += PIXEL_LENGTH) {
				int argb = 0;
				argb += (((int) PIXELS[pixel] & 0xff) << 24); // alpha
				argb += ((int) PIXELS[pixel + 1] & 0xff); // blue
				argb += (((int) PIXELS[pixel + 2] & 0xff) << 8); // green
				argb += (((int) PIXELS[pixel + 3] & 0xff) << 16); // red
				result[HEIGHT - row - 1][col] = argb;
				col++;
				if (col == WIDTH) {
					col = 0;
					row++;
				}
			}
		} else {
			final int PIXEL_LENGTH = 3;
			for (int pixel = 0, row = 0, col = 0; pixel < PIXELS.length; pixel += PIXEL_LENGTH) {
				int argb = 0;
				argb += -16777216; // 255 alpha
				argb += ((int) PIXELS[pixel] & 0xff); // blue
				argb += (((int) PIXELS[pixel + 1] & 0xff) << 8); // green
				argb += (((int) PIXELS[pixel + 2] & 0xff) << 16); // red
				result[HEIGHT - row - 1][col] = argb;
				col++;
				if (col == WIDTH) {
					col = 0;
					row++;
				}
			}
		}
		
		return result;
	}

}
