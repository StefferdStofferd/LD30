package nl.stefferd.ld30.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParalaxingBackground {
	
	private Texture[] textures;
	private double[] positionsX;
	private double[] positionsY;
	
	public ParalaxingBackground(Texture[] textures) {
		// set the textures and update the texture filters
		this.textures = textures;
		//for (int i = 0; i < textures.length; i++)
			//textures[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		// set all the positions to a default 0,0
		positionsX = new double[textures.length];
		for (int i = 0; i < positionsX.length; i++)
			positionsX[i] = 0;
		positionsY = new double[textures.length];
		for (int i = 0; i < positionsY.length; i++)
			positionsY[i] = 0;
	}
	
	/**
	 * Updates the position of the backgrounds based upon the camera coordinates
	 * and world size given.
	 * @param x camera x position
	 * @param y camera y position
	 * @param width world width
	 * @param width world height
	 */
	public void update(float x, float y, float width, float height) {
		// TODO: stop the jittering
		
		// calculate the offset of the camera from the center
		double xOffs = width / 2 - x;
		double yOffs = height / 2 - y;
		
		// go through all the background textures
		for (int i = 0; i < textures.length; i++) {
			// calculate the default position for the background
			double defX = textures[i].getWidth() / 2 - Gdx.graphics.getWidth() / 2;
			double defY = textures[i].getHeight() / 2 - Gdx.graphics.getHeight() / 2;
			
			// calculate the amount it needs to move
			double dx = (textures[i].getWidth() - Gdx.graphics.getWidth()) / 2 / width * xOffs;
			double dy = (textures[i].getHeight() - Gdx.graphics.getHeight()) / 2 / height * yOffs;
			
			// set the position of the background
			positionsX[i] = -defX + dx + x - Gdx.graphics.getWidth() / 2;
			positionsY[i] = -defY + dy + y - Gdx.graphics.getHeight() / 2;
		}
	}
	
	public void render(SpriteBatch batch) {
		batch.begin();
		for (int i = 0; i < textures.length; i++)
			batch.draw(textures[i], (float)positionsX[i], (float)positionsY[i]);
		batch.end();
	}

}
