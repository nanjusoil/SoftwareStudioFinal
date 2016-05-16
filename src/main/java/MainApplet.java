package main.java;

import javax.swing.JFrame;

import controlP5.CallbackEvent;
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
	private final int windowWidth = 1286, windowHeight = 700;
	private final int itemboxWidth = 86;
	private ControlP5 controlP5;
	private int currentRoom=1;
	private String[] filenameRooms = {"BedRoom.jpg" , "LivingRoom.jpg" , "Kitchen.jpg"};
	private Item buttonRight;
	private Item buttonLeft;
	private Item key;
	private Item paperball;
	private ItemBox itemBox;
	private Item microwave;
	private Item safe;
	private JFrame jframe;
	private LoginApplet loginapplet;
	
	// parameter::  sizex, sizey , x , y
	private Item musician;//200, 200, 0, 0
	private Item cabinet;//1200, 700, 0, 0
	private Item monopoly;//190, 150, 385, 60 
	private Item Napoleon;//190, 150, 600, 60
	private Item pentatonix;//190, 150, 385, 250
	private Item rural;//190, 150, 600, 250
		
	public MainApplet(JFrame jframe){
		this.jframe = jframe;
	}

	
	public void setup() {
		size(windowWidth, windowHeight);
		smooth();
		imgBackground = loadImage(path+"LivingRoom.jpg");
		imgBackground.resize(windowWidth-itemboxWidth, windowHeight);
		loginapplet = new LoginApplet(jframe);
		
		
		musician = new Item(this , 200 , 200 , 0 , 0 , "musician.png" , "musician.png" , "musician.png", Type.CONTROL);
		cabinet = new Item(this , 1200 , 700 , 0 , 0 , "cabinet.png" , "cabinet.png" , "cabinet.png", Type.CONTROL);
		monopoly = new Item(this , 190 , 150 , 385 , 60, "monopoly.png" , "monopoly.png" , "monopoly.png", Type.CONTROL);
		Napoleon = new Item(this , 190 , 150 , 600 , 60 , "Napoleon.png" , "Napoleon.png" , "Napoleon.png", Type.CONTROL);
		pentatonix = new Item(this , 190 , 150 , 385 , 250 , "pentatonix.png" , "pentatonix.png" , "pentatonix.png", Type.CONTROL);
		rural = new Item(this , 190 , 150 , 600 , 250, "rural.png" , "rural.png" , "rural.png", Type.CONTROL);
		
		
		buttonLeft = new Item(this , 0 , 275 , "arrowLeft.png" , "arrowLeft.png" , "arrowLeftPressed.png", Type.CONTROL){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
		           if (theEvent.getAction() == 100) {
		        	   loginapplet.init();
		        	   loginapplet.start();
		        	   loginapplet.setFocusable(true);
		        	   jframe.setContentPane(loginapplet);
		        	   jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        	   jframe.setSize(windowWidth, windowHeight);
		        	   jframe.setVisible(true);
		       		  if(currentRoom!=0)
		    		  {
		    			 imgBackground = loadImage(path+filenameRooms[--currentRoom]);
		    			 imgBackground.resize(windowWidth-itemboxWidth, windowHeight);			
		    		  }
		           }
		       }
		};
		
		buttonRight = new Item(this , 900 , 275 , "arrowRight.png" , "arrowRight.png" , "arrowRightPressed.png", Type.CONTROL){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if (theEvent.getAction() == 100) {
					if(currentRoom!=2)
					{
					    imgBackground = loadImage(path+filenameRooms[++currentRoom]);
					    imgBackground.resize(windowWidth-itemboxWidth, windowHeight);			
					}
				}
		    }
		};
		
		itemBox = new ItemBox(this, 1200, 0, "propsColumn.png"){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if (theEvent.getAction() == 100) {
					mouseClicked();
				}
		    }
		};
		
		
		key = new Item(this , 500 , 400 , "key.png" , "key.png" , "key.png", Type.TOOL){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("key")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
							itemBox.putinItem(this);
						}else{
							itemBox.checkItem(this);
						}
					}
				}else if(theEvent.getController().getName().equals("solkey")){
					if ((theEvent.getAction() == 100) && isInBox && isHolded){
						itemBox.useItem(this);
					}
				}
		    }
		};
		
		safe = new Item(this , 500 , 400 , "safe_nomove.png" , "safe_nomove.png" , "safe_nomove.png", Type.TOOL){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if (theEvent.getAction() == 100) {
					
					safe.updateImage("safe_open.png", "safe_open.png", "safe_open.png");
				}
		    }
		};
		
		paperball = new Item(this , 800 , 500 , "paperball.png" , "paperball.png" , "paperball.png", Type.MESSAGE){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("paperball")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
							itemBox.putinItem(this);
						}else{
							itemBox.checkItem(this);
						}
					}
				}
		    }
		};
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
