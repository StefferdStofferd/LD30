package nl.stefferd.ld30;

import com.badlogic.gdx.math.Vector3;

public class Vector3Animation {
	
	private KeyFrame[] keys;
	
	public Vector3Animation(KeyFrame[] keys) {
		this.keys = keys;
	}
	
	/**
	 * Returns the animated value at a certain time
	 * @param time systems time
	 * @return vector3 object
	 */
	public Vector3 keyValueAt(float time) {
		// Get the time in the animation
		float animationTime = Math.abs(time) % getDuration();
		
		// Get the gap the time is in
		int index = 0;
		for (int i = 0; i < keys.length; i++) {
			if (animationTime < getBeginTime(i) + keys[i].duration) {
				index = i;
				break;
			}
		}
		
		// get the two indices of the keys
		int i1 = index;
		int i2 = index + 1;
		// loop back to beginning if at end
		if (i2 >= keys.length)
			i2 = 0;
		
		// get 0f-1f progress
		float progress = (animationTime - getBeginTime(i1)) / keys[i1].duration;
		
		// make the transition
		float x = keys[i1].value.x + (keys[i2].value.x - keys[i1].value.x) * progress;
		float y = keys[i1].value.y + (keys[i2].value.y - keys[i1].value.y) * progress;
		float z = keys[i1].value.z + (keys[i2].value.z - keys[i1].value.z) * progress;
		
		// return the new vector
		return new Vector3(x, y, z);
	}
	
	/**
	 * Returns the total duration of the animation
	 * @return
	 */
	public float getDuration() {
		float res = 0;
		// Loop through the key frames and add it duration
		for (int i = 0; i < keys.length; i++)
			res += keys[i].duration;
		return res;
	}
	
	/**
	 * Returns the begin time of the key given
	 * @param key key for getting the begin time
	 * @return time at beginning of the key
	 */
	public float getBeginTime(int key) {
		float res = 0;
		// Loop through the key frames and add it duration
		for (int i = 0; i < key && i < keys.length; i++)
			res += keys[i].duration;
		return res;
	}
	
	public static class KeyFrame {
		
		private Vector3 value;
		private float duration;
		
		public KeyFrame(Vector3 value, float duration) {
			this.value = value;
			this.duration = duration;
		}
		
	}

}
