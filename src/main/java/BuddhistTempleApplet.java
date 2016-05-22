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
public class BuddhistTempleApplet extends PApplet{
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
	private JFrame jframe;
	private LoginApplet loginapplet;
	
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

	public BuddhistTempleApplet(JFrame jframe){
		this.jframe = jframe;
	}

	
	public void setup() {
		size(windowWidth, windowHeight);
		smooth();
		imgBackground = loadImage(path+"BuddhistTempleWithoutTable.png");
		imgBackground.resize(windowWidth-itemboxWidth, windowHeight);
		loginapplet = new LoginApplet(jframe);
		
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
		
		buttonRight = new Item(this , 128, 128, 900 , 275 , "arrowRight.png" , "arrowRight.png" , "arrowRightPressed.png", Type.CONTROL){
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
		
		
//		key = new Item(this , 56, 56, 800 , 600 , "key.png" , "key.png" , "key.png", 60, 60, 900, 600, "cabinet.png", Type.TOOL){
//			@Override
//			public void controlEvent(CallbackEvent theEvent) {
//				if(theEvent.getController().getName().equals("key")){
//					if (theEvent.getAction() == 100) {
//						if(!isInBox){
//							itemBox.putinItem(this);
//						}else{
//							itemBox.checkItem(this);
//						}
//					}
//				}else if(theEvent.getController().getName().equals("solkey")){
//					if ((theEvent.getAction() == 100) && isInBox && isHolded){
//						itemBox.useItem(this);
//					}
//				}
//		    }
//		};
		
		
		
		//sizex:sizey = 1.14402:1
//		table = new Item(this , 343 , 300 , 430 , 330 , "table.png" , "table.png" , "table.png", Type.FURNITURE){
		table = new Item(this , 343 , 300 , 430 , 330 , "TableWithStatue.png" , "TableWithStatue.png" , "TableWithStatue.png", Type.FURNITURE){
			
		@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("TableWithStatue")){
					if (theEvent.getAction() == 100) {
						if(true){ //later change
							//deactivate the original picture
							this.controlP5.setVisible(false);
							imgBackground = loadImage(path+"TableBackground.png");
							imgBackground.resize(windowWidth-itemboxWidth, windowHeight);
							
							//activate the statues
							baijuyi.solControlP5.setVisible(true);
							dufu.solControlP5.setVisible(true);
							wangwei.solControlP5.setVisible(true);
							libai.solControlP5.setVisible(true);
						}
					}
				}
		    }
		};
		
//		lucifer = new Item(this , 186 , 240 , 330 , 50 , "lucifer.png" , "lucifer.png" , "lucifer.png", Type.FURNITURE){
//				
//			@Override
//				public void controlEvent(CallbackEvent theEvent) {
//					if(theEvent.getController().getName().equals("lucifer")){
//						if (theEvent.getAction() == 100) {
//							if(true){ //later change: if player is holding a token
//								//when player hold a token and click statue,
//								// the statue will hold that token
////								lucifer.imgItem = loadImage(path+"luciferwithtoken.png");
////								lucifer.imgItemHover = loadImage(path+"luciferwithtoken.png");
////								lucifer.imgItemOnclick = loadImage(path+"luciferwithtoken.png");
//							}
//						}
//					}
//			   	}
//		};
//		
//		confucius = new Item(this , 80 , 186 , 520 , 100 , "confucius.png" , "confucius.png" , "confucius.png", Type.FURNITURE){
//			
//			@Override
//				public void controlEvent(CallbackEvent theEvent) {
//					if(theEvent.getController().getName().equals("confucius")){
//						if (theEvent.getAction() == 100) {
//							
//						}
//					}
//			   	}
//		};
//		
//		buddha = new Item(this , 110 , 186 , 630 , 105 , "buddha.png" , "buddha.png" , "buddha.png", Type.FURNITURE){
//			
//			@Override
//				public void controlEvent(CallbackEvent theEvent) {
//					if(theEvent.getController().getName().equals("buddha")){
//						if (theEvent.getAction() == 100) {
//							
//						}
//					}
//			   	}
//		};
		
//		ludongbin = new Item(this , 118 , 186 , 750 , 105 , "ludongbin.png" , "ludongbin.png" , "ludongbin.png", Type.FURNITURE){
//			
//			@Override
//				public void controlEvent(CallbackEvent theEvent) {
//					if(theEvent.getController().getName().equals("ludongbin")){
//						if (theEvent.getAction() == 100) {
//							
//						}
//					}
//			   	}
//		};
		
		//later change the picture to tokens
		baijuyi = new Item(this , 100 , 100 , 100 , 200 , "monopoly.png" , "monopoly.png" , "monopoly.png", 186 , 240 , 330 , 50, "lucifer.png", Type.TOOL){
		
		@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("monopoly")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
							itemBox.putinItem(this);
						}else{
							itemBox.checkItem(this);
						}
					}
				}else if(theEvent.getController().getName().equals("solmonopoly")){
					if ((theEvent.getAction() == 100) && isInBox && isHolded){ //later change
						itemBox.useItem(this);
					}
				}
		   	}
		};
	
		dufu = new Item(this , 100 , 100 , 320 , 20 , "musician.png" , "musician.png" , "musician.png", 80 , 186 , 520 , 100, "confucius.png", Type.TOOL){
		
		@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("musician")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
							itemBox.putinItem(this);
						}else{
							itemBox.checkItem(this);
						}
					}
				}else if(theEvent.getController().getName().equals("solmusician")){
					if ((theEvent.getAction() == 100) && isInBox && isHolded){ //later change
						itemBox.useItem(this);
					}
				}
		   	}
		};
		
		wangwei = new Item(this , 100 , 100 , 20 , 210 , "Napoleon.png" , "Napoleon.png" , "Napoleon.png", 110 , 186 , 630 , 105, "buddha.png", Type.TOOL){
		
		@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("Napoleon")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
							itemBox.putinItem(this);
						}else{
							itemBox.checkItem(this);
						}
					}
				}else if(theEvent.getController().getName().equals("solNapoleon")){
					if ((theEvent.getAction() == 100) && isInBox && isHolded){ //later change
						itemBox.useItem(this);
					}
				}
		   	}
		};
		
		
		libai = new Item(this , 100 , 100 , 20 , 20 , "carpet.jpg" , "carpet.jpg" , "carpet.jpg", 118 , 186 , 750 , 105, "ludongbin.png", Type.TOOL){
			
			@Override
				public void controlEvent(CallbackEvent theEvent) {
					if(theEvent.getController().getName().equals("carpet")){
						if (theEvent.getAction() == 100) {
							if(!isInBox){
								itemBox.putinItem(this);
							}else{
								itemBox.checkItem(this);
							}
						}
					}else if(theEvent.getController().getName().equals("solcarpet")){
						if ((theEvent.getAction() == 100) && isInBox && isHolded){ //later change
							itemBox.useItem(this);
						}
					}
			   	}
		};
		
		
		
		//they are deactivated until changing to the table background
		baijuyi.solControlP5.setVisible(false);
		dufu.solControlP5.setVisible(false);
		wangwei.solControlP5.setVisible(false);
		libai.solControlP5.setVisible(false);
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

