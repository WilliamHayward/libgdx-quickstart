package com.williamhayward.libgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Sprites are Animations that will automatically change
 * their current image as expected over the course of their lifetime
 */
public class Sprite extends Animation {
	private float lifetime = 0; // This holds how many milliseconds have passed since creation of instance
	private float speed = 1f;
	private Vector2 origin = new Vector2(0, 0);
	
	public Sprite(float frameDuration, Array<? extends TextureRegion> keyFrames) {
		super(frameDuration, keyFrames);
		this.setPlayMode(PlayMode.LOOP);
	}
	
	public Sprite(Sprite sprite) {
		super(sprite.getFrameDuration(), sprite.getKeyFrames());
		this.setPlayMode(PlayMode.LOOP);
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getLifetime() {
		return lifetime;
	}
	
	public TextureRegion getFrame() {
		lifetime += Gdx.graphics.getDeltaTime() * speed;
		return this.getKeyFrame(lifetime, true);
	}
	
	public float getHeight() {
		return this.getKeyFrame(lifetime).getRegionHeight();
	}
	
	public float getWidth() {
		return this.getKeyFrame(lifetime).getRegionWidth();
	}
	
	public void setXOrigin(float xOrigin) {
		origin.x = xOrigin;
	}
	
	public void setYOrigin(float yOrigin) {
		origin.y = yOrigin;
	}
	
	public void setOrigin(Vector2 origin) {
		this.origin.set(origin);
	}
	
	public void reset() {
		lifetime = 0;
	}
}
