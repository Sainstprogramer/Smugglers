package entity;

import math3d.Vector3f;
import models.Texturedmodel;

public class Light extends Effect {
	private Vector3f attenuation = new Vector3f(1, 0, 0);

	public Light(Vector3f velocity, Vector3f position,
			Vector3f color) {
		super(velocity, position, 0, 0, 0, 0, color);
	}

	public Light(Vector3f velocity, Vector3f position, float rx, float ry,
			float rz, float scale, Vector3f color, Vector3f attenuation) {
		super(velocity, position, rx, ry, rz, scale, color);

		this.attenuation = attenuation;
	}

	public Light(Vector3f velocity, Vector3f position, float rx, float ry,
			float rz, float scale, Vector3f color, Texturedmodel model) {
		super(velocity, position, rx, ry, rz, scale, model, color);
	}

	public Light(Vector3f velocity, Vector3f position, float rx, float ry,
			float rz, float scale, Vector3f color, Texturedmodel model,
			Vector3f attenuation) {
		super(velocity, position, rx, ry, rz, scale, model, color);

		this.attenuation = attenuation;
	}

	public Vector3f GetAttenuation() {
		return attenuation;
	}

}
