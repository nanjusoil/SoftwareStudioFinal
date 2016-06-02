package main.java;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;

import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

@SuppressWarnings("serial")
public class AstronomyApplet extends PApplet implements Runnable{
	private String path = "main/resources/";
	private PImage imgBackground;
	private final int windowWidth = 1286, windowHeight = 700;
	private final int itemboxWidth = 86;
	private ControlP5 controlP5;
	private int currentRoom=1;
	private String[] filenameRooms = {"BedRoom.jpg" , "LivingRoom.jpg" , "Kitchen.jpg"};
	private Item buttonRight;
	private Item buttonLeft;
	private Item bigDipper;
	private Item cassiopeia;
	private Item buttonReturn;
	private Item mykey;
	private ItemBox itemBox;

	private JFrame jframe;
	private LoginApplet loginapplet;
	private Keyboard keyboard;
	private Item safe;
	private Item open;
	
	// parameter::  sizex, sizey , x , y
	private Item table;//200, 200, 0, 0
	private Item lucifer;
	private Item confucius;
	private Item buddha;
	private Item ludongbin;
	
	//the token which ...'s phrase is written on
	private Item baijuyi;
	private Item dufu;
	private Item wangwei;
	private Item libai;
	
	private ArrayList<Item> itemArr; 
	//used as second parameter of checkItem(), to make different items' isHolded exclusive
	//(There is at most only one item's isHolded being true in any given time)
	
	private int numSolved;

	public AstronomyApplet(JFrame jframe){
		this.jframe = jframe;
	}
	
	
	
	public void setup() {
		size(windowWidth, windowHeight);
		smooth();
		imgBackground = loadImage(path+"starnight.jpg");
		imgBackground.resize(windowWidth-itemboxWidth, windowHeight);
		
		//safe = loadImage(path+"safe_nomove2.png");
		//open = loadImage(path+"safe_open2.png");
		
		
		loginapplet = new LoginApplet(jframe);
		numSolved = 0;
		
		
		
		
	
		controlP5 = new ControlP5(this);
		controlP5.addKnob(" ").setImage(loadImage(path+"arrowLeft.png"))
		  .setViewStyle(1)
		  .setColorBackground(0)
		  .setPosition(615, 453)
		  .setRadius(52)
		  .setScrollSensitivity((float) 0.001)
		  .setMin(60)
		  .setMax(140)
		  .addCallback(new CallbackListener(){
				@Override
				public void controlEvent(CallbackEvent theEvent) {
					if(theEvent.getController().getValue() >= 99 && theEvent.getController().getValue() <= 102){
						safe.controlP5.setVisible(false);
						open.controlP5.setVisible(true);
						mykey.controlP5.setVisible(true);
						
					}
					
						
					
			    }
		  });
	
		buttonLeft = new Item(this , 128, 128, 0 , 275 , "arrowLeft.png" , "arrowLeft.png" , "arrowLeftPressed.png", Type.CONTROL){
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
		
		buttonRight = new Item(this , 128, 128, 1000 , 275 , "arrowRight.png" , "arrowRight.png" , "arrowRightPressed.png", Type.CONTROL){
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
		
		buttonReturn = new Item(this , 128, 128, 0 , 572 , "arrowReturn.png" , "arrowReturn.png" , "arrowReturnPressed.png", Type.CONTROL){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if (theEvent.getAction() == 100) {
					imgBackground = loadImage(path+"BuddhistTempleWithoutTable.png");
					imgBackground.resize(windowWidth-itemboxWidth, windowHeight);
					baijuyi.solControlP5.setVisible(false);
					dufu.solControlP5.setVisible(false);
					wangwei.solControlP5.setVisible(false);
					libai.solControlP5.setVisible(false);
					table.controlP5.setVisible(true);
				}
		    }
		};
		
		

		open = new Item(this ,140,100,300 ,550 , "safe_open2.png" , "safe_open2.png" , "safe_open2.png", 60, 60, 900, 600,  "cabinet.png", Type.TOOL){
		@Override
		public void controlEvent(CallbackEvent theEvent) {
			if(theEvent.getController().getName().equals("mykey")){
				if (theEvent.getAction() == 100) {
					if(!isInBox){
						itemBox.putinItem(this);
					}else{
						itemBox.checkItem(this, itemArr);
					}
				}
			}else if(theEvent.getController().getName().equals("solmykey")){
				if ((theEvent.getAction() == 100) && isInBox && isHolded){
					itemBox.useItem(this);
				}
			}
	    }
			
		};
		
		
		
		
		mykey = new Item(this , 40, 40, 350 , 580 , "mykey.png" , "mykey.png" , "mykey.png", 60, 60, 900, 600, "cabinet.png", Type.TOOL){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("mykey")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
							itemBox.putinItem(this);
						}else{
							itemBox.checkItem(this, itemArr);
						}
					}
				}else if(theEvent.getController().getName().equals("solmykey")){
					if ((theEvent.getAction() == 100) && isInBox && isHolded){
						itemBox.useItem(this);
					}
				}
		    }
		};
		safe = new Item(this ,160,120,300,550 , "safe_nomove.png" , "safe_nomove.png" , "safe_nomove.png", 118 , 286 , 850 , 205, "safe_nomove.png", Type.FURNITURE){
			
			@Override
			public void controlEvent(CallbackEvent theEvent) {
					
				if(theEvent.getController().getName().equals("carpet")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
							itemBox.putinItem(this);
						}else{
							itemBox.checkItem(this, itemArr);
						}
					}
				}else if(theEvent.getController().getName().equals("solcarpet")){
					if ((theEvent.getAction() == 100) && isInBox && isHolded){ //later change
						itemBox.useItem(this);
						numSolved ++;
					}
				}
				
				
		   	}
		};
		
		
		
		cassiopeia = new Item(this , 140 , 100 , 930 , 92 , "Cassiopeia.png" , "Cassiopeia.png" , "Cassiopeia.png", 118 , 186 , 750 , 105, "Cassiopeia.png", Type.FURNITURE)
		
		{
			
			@Override
				public void controlEvent(CallbackEvent theEvent) {
						
					if(theEvent.getController().getName().equals("carpet")){
						if (theEvent.getAction() == 100) {
							if(!isInBox){
								itemBox.putinItem(this);
							}else{
								itemBox.checkItem(this, itemArr);
							}
						}
					}else if(theEvent.getController().getName().equals("solcarpet")){
						if ((theEvent.getAction() == 100) && isInBox && isHolded){ //later change
							itemBox.useItem(this);
							numSolved ++;
						}
					}
					
					
			   	}
			
		};
		
		bigDipper = new Item(this ,180 ,150  , 150 , 280 , "BigDipper.jpg" , "BigDipper.jpg" , "BigDipper.jpg", 118 , 286 , 850 , 205, "BigDipper.png", Type.FURNITURE){
			
			@Override
				public void controlEvent(CallbackEvent theEvent) {
					if(theEvent.getController().getName().equals("carpet")){
						if (theEvent.getAction() == 100) {
							if(!isInBox){
								itemBox.putinItem(this);
							}else{
								itemBox.checkItem(this, itemArr);
							}
						}
					}else if(theEvent.getController().getName().equals("solcarpet")){
						if ((theEvent.getAction() == 100) && isInBox && isHolded){ //later change
							itemBox.useItem(this);
							numSolved ++;
						}
					}
			   	}
		};
		
		itemBox = new ItemBox(this, 1190, 0, "propsColumn.png"){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				 
					if (theEvent.getAction() == 100) {
						mouseClicked();
					}
			    }
		    
		};
		
		
		
		mykey.controlP5.setVisible(false);
		itemArr = new ArrayList<Item>(Arrays.asList(baijuyi, dufu, wangwei, libai, mykey));
		
		
	}
	
	
	
	
	
	public void draw() {
		image(imgBackground,0,0,1286,700);
		 
		
	
	
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

