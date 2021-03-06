package cinematics;

import java.util.ArrayList;

import math3d.Vector3f;
import render.Camera;

public class Camera_Rail {
	
	ArrayList<KeyPlacement> rail;
	Camera cam;
	private int movesleft;
	private float rx,ry,rz;
	private Vector3f move;
	private boolean moving=false;
	
	
	
	/**
	 * @param cam the camera
	 */
	public Camera_Rail(Camera cam) {
		super();
		this.cam = cam;
		rail=new ArrayList<KeyPlacement>();
		move=new Vector3f();
	}

	public void tick() {
		if(!rail.isEmpty()){
			if(!moving){
				movesleft=rail.get(0).getTime();
				rx=(rail.get(0).getRx()-cam.getRx())/rail.get(0).getTime();
				ry=(rail.get(0).getRy()-cam.getRy())/rail.get(0).getTime();
				rz=(rail.get(0).getRz()-cam.getRz())/rail.get(0).getTime();
				rail.get(0).getPos().sub(cam.getPosition(), move);
				move.div(rail.get(0).getTime());
				moving=true;
				cam.setCin(true);
			}else{
				cam.getPosition().add(move);
				cam.setRx(cam.getRx()+rx);
				cam.setRy(cam.getRy()+ry);
				cam.setRz(cam.getRz()+rz);
				
				movesleft--;
				
				if(movesleft<=0){
					moving=false;
					rail.remove(0);
				}
			}
		}else {
			cam.setCin(false);
		}
		
	}
	
	public void addPlacement(KeyPlacement place) {
		rail.add(place);
	}

}
