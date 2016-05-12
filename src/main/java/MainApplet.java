package main.java;

import controlP5.ControlEvent;
import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private PImage imgBackground;
	private final int windowWidth = 1200, windowHeight = 700;
	private ControlP5 controlP5;
	private int currentRoom=1;
	private String[] filenameRooms = {"BedRoom.jpg" , "LivingRoom.jpg" , "Kitchen.jpg"};
	public void setup() {
		size(windowWidth, windowHeight);
		smooth();
		imgBackground = loadImage(path+"LivingRoom.jpg");
		imgBackground.resize(windowWidth, windowHeight);
		controlP5 = new ControlP5(this);
		controlP5.addButton("buttonLeft")
		     .setPosition(0,275)
		     .setImages(loadImage(path+"arrowLeft.png"), loadImage(path+"arrowLeft.png") , loadImage(path+"arrowLeftPressed.png"))
		     .updateSize();
		controlP5.addButton("buttonRight")
	     .setPosition(900,275)
	     .setImages(loadImage(path+"arrowRight.png"), loadImage(path+"arrowRight.png") , loadImage(path+"arrowRightPressed.png"))
	     .updateSize();
	}
	
	public void controlEvent(ControlEvent theEvent) {

	}


	public void buttonLeft(int theValue) {
		if(currentRoom!=0)
		{
			imgBackground = loadImage(path+filenameRooms[--currentRoom]);
			imgBackground.resize(windowWidth, windowHeight);			
		}

	}
	
	public void buttonRight(int theValue) {
		if(currentRoom!=2)
		{
			imgBackground = loadImage(path+filenameRooms[++currentRoom]);
			imgBackground.resize(windowWidth, windowHeight);			
		}
	}

	public void draw() {
		image(imgBackground, 0, 0);
	}

	public void mousePressed() {
	}
	
	public void mouseDragged() {
	}
	
	public void mouseReleased() {
	}
	
	public void setAllNetwork()
	{
	}
	
	public void keyPressed(){
		
	}

}
