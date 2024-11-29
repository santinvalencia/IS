package entities;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import mundo.GameMap;

public enum EntityType {
	
	ECHEVERRI("Echeverri", 32, 74, 70),
	CARLITOS("Carlitos", 40, 56, 70);
	
	private String id;
	private int width, height;
	private float weight;
	
	private EntityType(String id, int width, int height, float weight) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getWeight() {
		return weight;
	}
	
	
	private static HashMap<String, EntityType> entityTypes;
	
	static {
		entityTypes = new HashMap<String, EntityType>();
		for (EntityType type : EntityType.values())
			entityTypes.put(type.id, type);
	}
	
}
