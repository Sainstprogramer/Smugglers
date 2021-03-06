package cinematics;

import math3d.Vector3f;
import sound.Sound;

public class KeyPlacement {
	private float rx,ry,rz;
	private Vector3f pos;
	private int time;
	private Sound s;
	/**
	 * @param rx rotation x
	 * @param ry rotation y
	 * @param rz rotation z
	 * @param pos position
	 * @param time the time it will take to reach this Placement
	 */
	public KeyPlacement(float rx, float ry, float rz,int time, Vector3f pos,Sound s) {
		super();
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		this.pos = pos;
		this.time=time;
		this.s=s;
	}
	public KeyPlacement(float rx, float ry, float rz,int time, Vector3f pos) {
		super();
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		this.pos = pos;
		this.time=time;
	}
	
	public float getRx() {
		return rx;
	}
	public void setRx(float rx) {
		this.rx = rx;
	}
	public float getRy() {
		return ry;
	}
	public void setRy(float ry) {
		this.ry = ry;
	}
	public float getRz() {
		return rz;
	}
	public void setRz(float rz) {
		this.rz = rz;
	}
	public Vector3f getPos() {
		return pos;
	}
	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Sound getS() {
		return s;
	}

	public void setS(Sound s) {
		this.s = s;
	}
	
	

}
