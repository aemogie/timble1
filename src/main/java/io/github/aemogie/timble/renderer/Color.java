package io.github.aemogie.timble.renderer;

import io.github.aemogie.timble.util.TimbleMath;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
 * @author <a href="mailto:theaemogie@gmail.com"> Aemogie. </a>
 */
public class Color {
	
	public float r;
	public float g;
	public float b;
	public float a;
	
	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public Color(float r, float g, float b, float a, boolean normalized) {
		this(r, g, b, a);
		if (normalized) {
			this.r = fromMap(r);
			this.g = fromMap(g);
			this.b = fromMap(b);
			this.a = fromMap(a);
		}
	}
	
	public Color(float r, float g, float b) {
		this(r, g, b, 255);
	}
	
	public Color(float alpha, boolean normalized) {
		this(normalized ? 1 : 255, normalized ? 1 : 255, normalized ? 1 : 255, alpha, normalized);
	}
	
	private static float toMap(float value) {
		return TimbleMath.map(value, 0, 255, 0, 1);
	}
	
	private static float fromMap(float value) {
		return TimbleMath.map(value, 0, 1, 0, 255);
	}
	
	public static Color fromNormVec4(Vector4f color) {
		return new Color(fromMap(color.x), fromMap(color.y), fromMap(color.z), fromMap(color.w));
	}
	
	public Vector4f toNormVec4() {
		return new Vector4f(toMap(r), toMap(g), toMap(b), toMap(a));
	}
	
	public Vector3f toNormVec3() {
		return new Vector3f(toMap(r), toMap(g), toMap(b));
	}
	
	//region setColor method and overloads.
	
	public void setColor(float rIn, float gIn, float bIn, float aIn, boolean normalized) {
		
		float r = rIn;
		float g = gIn;
		float b = bIn;
		float a = aIn;
		
		if (normalized) {
			r = fromMap(rIn);
			g = fromMap(gIn);
			b = fromMap(bIn);
			a = fromMap(aIn);
		}
		
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public void setColor(float rIn, float gIn, float bIn, float aIn) {
		this.setColor(rIn, gIn, bIn, aIn, false);
	}
	
	public void setColor(float rIn, float gIn, float bIn, boolean normalized) {
		this.setColor(rIn, gIn, bIn, 255, normalized);
	}
	
	public void setColor(float rIn, float gIn, float bIn) {
		this.setColor(rIn, gIn, bIn, false);
	}
	
	public void setColor(float in, boolean normalized) {
		this.setColor(in, in, in, normalized);
	}
	
	public void setColor(float in) {
		this.setColor(in, false);
	}
	
	public void setColor(Vector4f in, boolean normalized) {
		this.setColor(in.x, in.y, in.z, in.w, normalized);
	}
	
	public void setColor(Vector4f in) {
		this.setColor(in, false);
	}
	
	public void setColor(Vector3f in, boolean normalized) {
		this.setColor(in.x, in.y, in.z, normalized);
	}
	
	public void setColor(Vector3f in) {
		this.setColor(in, false);
	}
	
	public void setColor(Color color) {
		this.r = color.r;
		this.g = color.g;
		this.b = color.b;
		this.a = color.a;
	}
	//endregion
	
	
	@Override
	public String toString() {
		return "Red: " + r + " Green: " + g + " Blue: " + b + " Alpha: " + a;
	}
}
