package com.williamhayward.libgdx.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.williamhayward.libgdx.entities.Entity;

/**
 * A world is an extended stage, featuring layers and more complex actor filtering
 */
public class World extends Stage {
	private Map<String, Group> layers = new HashMap<String, Group>();

	public World() {
		addLayer("default");
	}

	public <E extends Enum<E>> World(Enum<E>[] layerList) {
		createLayers(layerList);
	}

	public World(Viewport viewport) {
		super(viewport);
		addLayer("default");
	}
	
	public <E extends Enum<E>> World(Viewport viewport, Enum<E>[] layerList) {
		super(viewport);
		createLayers(layerList);
	}
	
	public World(Viewport viewport, Batch batch) {
		super(viewport, batch);
		addLayer("default");
	}

	public <E extends Enum<E>> World(Viewport viewport, Batch batch, Enum<E>[] layerList) {
		super(viewport, batch);
		createLayers(layerList);
	}

	/**
	 * Using given Enum values, creates layers for world.
	 * 
	 * If "default" is not in layers then it will be added last.
	 */
	private <E extends Enum<E>> void createLayers(Enum<E>[] layerList) {
		for (Enum<E> layer: layerList) {
			addLayer(layer.toString());
		}
		
		if (!layers.containsKey("default")) {
			addLayer("default");
		}
	}
	
	/**
	 * Add a labelled group that will remain in a specific position, depth-wise
	 */
	private void addLayer(String name) {
		Group layer = new Group();
		layers.put(name, layer);
		super.addActor(layer);
	}
	
	/**
	 * Inserts actor into appropriate layer in world
	 */
	@Override
	public void addActor(Actor actor) {
		String layer = "default";
		
		if (actor instanceof Entity) {
			String entityLayer = ((Entity) actor).getLayer();
			if (layers.containsKey(entityLayer)) {
				layer = entityLayer;
			}
		}
		layers.get(layer).addActor(actor);
	}
	
	/**
	 * Returns all actors in world (across all layers)
	 */
	@Override
	public Array<Actor> getActors () {
		Array<Actor> actors = new Array<Actor>();
		for (Group layer: layers.values()) {
			for (Actor actor: layer.getChildren()) {
				actors.add(actor);
			}
		}
		return actors;
	}
	
	/**
	 * This filters down a list of all actors to just those
	 * which are a given instance.
	 */
	public <T> List<T> getActors(Class<T> filter) {
		List<T> actors = new ArrayList<T>();
		for (Actor actor: this.getActors()) {
			if (filter.isInstance(actor)) { // Check that it is correct class
				actors.add(filter.cast(actor)); // Cast to that class and add to list
			}
		}
		return actors;
	}
	
	/**
	 * Returns all Entities in world
	 */
	@SuppressWarnings("unchecked")
	public List<Entity> getEntities() {
		return (List<Entity>)(Object) getActors(Entity.class);
	}
}
