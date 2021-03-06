package io.github.aemogie.timble.components;

import io.github.aemogie.timble.renderer.Color;
import io.github.aemogie.timble.renderer.Sprite;
import io.github.aemogie.timble.renderer.Texture;
import io.github.aemogie.timble.timble.Transform;
import io.github.aemogie.timble.timble.Window;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;

/**
 * @author <a href="mailto:theaemogie@gmail.com"> Aemogie. </a>
 */
public class SpriteRenderer extends Component {
	
	private Color color = new Color(1, true);
	private Sprite sprite = new Sprite();
	
	private transient Transform lastTransform;
	private transient boolean isDirty = true;

//    public SpriteRenderer(Vector4f color) {
//        this.color = color;
//        this.sprite = new Sprite(null);
//    }
//
//    public SpriteRenderer(Sprite sprite) {
//        this.sprite = sprite;
//        this.color = new Vector4f(1,1,1,1);
//    }
	
	@Override
	public void start() {
		this.lastTransform = gameObject.transform.copy();
	}
	
	@Override
	public void update(Window window, double deltaTime) {
		if (!this.lastTransform.equals(gameObject.transform)) {
			gameObject.transform.copy(this.lastTransform);
			this.isDirty = true;
		}
	}
	
	@Override
	public void imGui() {
		if (ImGui.collapsingHeader("Color Picker")) {
			Vector4f color = this.color.toNormVec4();
			float[] imColor = {color.x, color.y, color.z, color.w};
			if (ImGui.colorPicker4("", imColor)) {
				this.setColor(new Color(imColor[0], imColor[1], imColor[2], imColor[3], true));
			}
		}
	}
	
	public Color getColor() {
		return color;
	}
	
	public SpriteRenderer setColor(Color color) {
		if (!this.color.equals(color)) {
			this.color.setColor(color);
			this.isDirty = true;
		}
		return this;
	}
	
	public Texture getTexture() {
		return sprite.getTexture();
	}
	
	public SpriteRenderer setTexture(Texture texture) {
		this.sprite.setTexture(texture);
		return this;
	}
	
	public Vector2f[] getTextureCoords() {
		return sprite.getTextureCoords();
	}
	
	public boolean isDirty() {
		return this.isDirty;
	}
	
	public void setClean() {
		isDirty = false;
	}
	
	public SpriteRenderer setSprite(Sprite sprite) {
		this.sprite = sprite;
		this.isDirty = true;
		return this;
	}
	
	@Override
	public String toString() {
		return "Class: " + this.getClass().getCanonicalName() + "\n" + "Sprite: \n" + this.sprite.toString().replaceAll("(?m)^", "\t") + "\n";
	}
}