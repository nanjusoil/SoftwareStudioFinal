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
	private ItemBox itemBox;
	private Item microwave;
	private JFrame jframe;
	private LoginApplet loginapplet;
	private Item safe;
		
	public MainApplet(JFrame jframe){
		this.jframe = jframe;
	}

	
	public void setup() {
		size(windowWidth, windowHeight);
		smooth();
		imgBackground = loadImage(path+"LivingRoom.jpg");
		imgBackground.resize(windowWidth-itemboxWidth, windowHeight);
		loginapplet = new LoginApplet(jframe);
		
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
