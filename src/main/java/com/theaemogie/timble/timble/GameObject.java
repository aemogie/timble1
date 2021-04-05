package com.theaemogie.timble.timble;

import com.theaemogie.timble.components.Component;
import com.theaemogie.timble.components.SpriteRenderer;
import com.theaemogie.timble.renderer.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:theaemogie@gmail.com"> Aemogie. </a>
 */
public class GameObject implements Cloneable {
	
	private static int ID_COUNTER = 0;
	public Transform transform;
	private int UUID = -1;
	private String name;
	private List<Component> components;
	
	public GameObject(String name, Transform transform) {
		this.name = name;
		this.components = new ArrayList<>();
		this.transform = transform;
		
		this.UUID = ID_COUNTER++;
	}
	
	public static void init(int maxID) {
		ID_COUNTER = maxID;
	}
	
	public <T extends Component> T getComponent(Class<T> componentClass) {
		for (Component c : components) {
			if (componentClass.isAssignableFrom(c.getClass())) {
				try {
					return componentClass.cast(c);
				} catch (ClassCastException e) {
					e.printStackTrace();
					assert false : "Error: Casting component.";
				}
			}
		}
		return null;
	}
	
	public <T extends Component> void removeComponent(Class<T> componentClass) {
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if (componentClass.isAssignableFrom(c.getClass())) {
				components.remove(i);
				return;
			}
		}
	}
	
	public GameObject addComponent(Component c) {
		c.generateID();
		this.components.add(c);
		c.gameObject = this;
		return this;
	}

//    private boolean firstTime = true;
	
	public void update(Window window, double deltaTime) {
		for (Component component : components) {
			component.update(window, deltaTime);
		}
	}
	
	public void start() {
		for (Component component : components) {
			component.start();
		}
	}
	
	public void imGui() {
		for (Component c : components) {
			c.imGui();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder outputString = new StringBuilder("Class: " + this.getClass().getCanonicalName() + "\n");
		for (Component component : components) {
			outputString.append("Texture: \n" + component.toString().replaceAll("(?m)^", "\t") + "\n");
		}
		return outputString.toString();
	}
	
	public int getUUID() {
		return this.UUID;
	}
	
	public List<Component> getAllComponents() {
		return this.components;
	}
	
	public GameObject clone() throws CloneNotSupportedException {
		return (GameObject) super.clone();
	}
	
	public String getName() {
		return name;
	}
	
	public GameObject gameObjectGen(Sprite sprite, Component... components) {
		SpriteRenderer characterSpriteRenderer = new SpriteRenderer();
		characterSpriteRenderer.setSprite(sprite);
		this.addComponent(characterSpriteRenderer);
		for (Component component : components) {
			this.addComponent(component);
		}
		return this;
	}
}