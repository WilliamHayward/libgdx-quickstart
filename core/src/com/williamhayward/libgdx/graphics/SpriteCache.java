package com.williamhayward.libgdx.graphics;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * SpriteCache is a static map to hold all sprites used in a game.
 * 
 *  Loads sprites using a libGDX sprite atlas and an enum list of sprites
 */
public class SpriteCache {
	private static final float FRAME_LENGTH = 0.5f; //TODO: Make this customizable
	private static Map<String, Sprite> sprites = new HashMap<String, Sprite>();
	
	private SpriteCache() {
	}
	
	/**
	 * Registers all sprites using the EnumName.class as parameter
	 */
	public static <E extends Enum<E>> void registerSprites(Class<? extends Enum<E>> spriteList) {
		registerSprites(spriteList.getEnumConstants());
	}

	
	/**
	 * Registers all sprites using the EnumName.values() as parameter
	 * 
	 * Should be called only once, ideally close to launch
	 */
	public static <E extends Enum<E>> void registerSprites(Enum<E>[] spriteList) {
		TextureAtlas atlas;
		atlas = new TextureAtlas(Gdx.files.internal("packed/sprites.atlas"));
		
		for (Enum<E> sprite: spriteList) {
			String name = sprite.toString().toLowerCase();
			registerSprite(name, atlas);
		}
	}
	
	/**
	 * Registers an individual animation into the animation map 
	 */
	private static void registerSprite(String name, TextureAtlas atlas) {
		Array<TextureAtlas.AtlasRegion> frames = atlas.findRegions(name);
		if (frames.size == 0) {
			Gdx.app.error("URGENT", name + " has no frames");
		}
		
		sprites.put(name, new Sprite(FRAME_LENGTH, frames));
		Gdx.app.log("Loaded", name);

		// Any permanent custom tinkerings can go here easily enough. Frame durations and such.
	}
	
	/**
	 * Retrieves a copy of the Sprite with the given enum value
	 */
	public static <E extends Enum<E>> Sprite loadSprite(Enum<E> id) {
		String name = id.toString().toLowerCase();
		return loadSprite(name);
	}
	
	/**
	 * Retrieves a copy of the Sprite with the given name
	 */
	public static Sprite loadSprite(String name) {
		Gdx.app.log("Specifically", name);
		return new Sprite(sprites.get(name));
	}

}
