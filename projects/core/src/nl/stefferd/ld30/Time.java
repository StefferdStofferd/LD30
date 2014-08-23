package nl.stefferd.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class Time {
	
	private static final Vector3Animation.KeyFrame[] COLOR_KEYS =
			new Vector3Animation.KeyFrame[] {
		new Vector3Animation.KeyFrame(new Vector3(1, 0, 0), 1),
		new Vector3Animation.KeyFrame(new Vector3(0, 1, 0), 1),
		new Vector3Animation.KeyFrame(new Vector3(0, 0, 1), 1),
	};
	
	private Vector3Animation animation;
	private float time = 0;
	
	public Time() {
		animation = new Vector3Animation(COLOR_KEYS);
	}
	
	public void update() {
		time += Gdx.graphics.getDeltaTime();
	}
	
	/**
	 * Returns the current color of the ambient lighting.
	 * @return
	 */
	public Vector3 getCurrentColor() {
		return animation.keyValueAt(time);
	}

}
