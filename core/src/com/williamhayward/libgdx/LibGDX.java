package com.williamhayward.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.williamhayward.libgdx.graphics.Sprite;
import com.williamhayward.libgdx.graphics.SpriteCache;

public class LibGDX extends ApplicationAdapter {
	Sprite image;
	Batch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		SpriteCache.registerSprites(TestEnum.values());
		image = new Sprite(SpriteCache.loadSprite(TestEnum.FLY));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(image.getFrame(), 10, 10);
		batch.end();
	}
	
	@Override
	public void dispose () {
	}
}
