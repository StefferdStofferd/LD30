package nl.stefferd.ld30;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class Time {
	
	private static final Vector3Animation.KeyFrame[] COLOR_KEYS =
			new Vector3Animation.KeyFrame[] {
		new Vector3Animation.KeyFrame(new Vector3(0.25f, 0.25f, 0.3f), 120),
		new Vector3Animation.KeyFrame(new Vector3(0.25f, 0.25f, 0.3f), 15),
		new Vector3Animation.KeyFrame(new Vector3(0.8f, 0.8f, 0.3f), 15),
		new Vector3Animation.KeyFrame(new Vector3(1.0f, 1.0f, 1.0f), 270),
		new Vector3Animation.KeyFrame(new Vector3(1.0f, 1.0f, 1.0f), 15),
		new Vector3Animation.KeyFrame(new Vector3(0.8f, 0.3f, 0.3f), 15),
		new Vector3Animation.KeyFrame(new Vector3(0.25f, 0.25f, 0.3f), 30),
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
	
	public String getFormattedTime() {
		// calculate the hours and add leading zeros
		int hours = (int)((time % 480) / 60 * 30);
		String sHours = hours + "";
		if (hours < 10)
			sHours = "0" + hours;
		
		// calculate the minutes and add leading zeros
		int minutes = (int)((time % 20) * 30);
		String sMinutes = minutes + "";
		if (minutes < 10)
			sMinutes = "0" + minutes;
		return sHours + ":" + sMinutes;
	}

}
