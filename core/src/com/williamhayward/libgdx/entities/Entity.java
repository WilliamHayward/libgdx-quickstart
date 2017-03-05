package com.williamhayward.libgdx.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A more versatile version of actor, including layers, in-built sprite, 
 * layers, sizing, collisions, and velocity 
 */
public abstract class Entity extends Actor {
	protected String layer = "default";
	
	public Entity() {
		// TODO Auto-generated constructor stub
	}

	public String getLayer() {
		return layer;
	}

	// TODO: in-built sprite
	// TODO: sizing
	// TODO: collisions
	// TODO: velocity
}
