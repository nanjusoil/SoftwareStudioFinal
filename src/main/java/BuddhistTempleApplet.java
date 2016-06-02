package main.java;

import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.Clip;
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
	private Item buttonReturn;
	private Item mykey;
	private ItemBox itemBox;
	private JFrame jframe;
	private JFrame cardframe;
	private LoginApplet loginapplet;
	
	// parameter::  sizex, sizey , x , y
	private Item table;//200, 200, 0, 0
	private Item lucifer;
	private Item confucius;
	private Item buddha;
	private Item ludongbin;
	
	//the token which ...'s phrase is written on
	private Item leftBox;
	private Item rightBox;
	
	
	private Item baijuyi;
	private Item dufu;
	private Item wangwei;
	private Item libai;
	
	private ArrayList<Item> itemArr; 
	//used as second parameter of checkItem(), to make different items' isHolded exclusive
	//(There is at most only one item's isHolded being true in any given time)
	
	private int numSolved;
	
	public Clip slash,slash2;
	
	private MusicPuzzleApplet musicPuzzleApplet;
	
	

	public BuddhistTempleApplet(JFrame jframe){
		this.jframe = jframe;
	}

	
	public void setup() {
		size(windowWidth, windowHeight);
		smooth();
		//loadMusic();
		try{
			slash = MusicPlay.getMusic("src/" + path + "Sounds/slash.wav");
			slash2 = MusicPlay.getMusic("src/" + path + "Sounds/slash2.wav");
			System.out.println("OK playing sound.");
		}catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
		imgBackground = loadImage(path+"BuddhistTempleWithoutTable.png");
		imgBackground.resize(windowWidth-itemboxWidth, windowHeight);
		loginapplet = new LoginApplet(jframe);
		numSolved = 0;
		
		musicPuzzleApplet = new MusicPuzzleApplet(jframe);
		
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
					musicPuzzleApplet.init();
					musicPuzzleApplet.start();
					musicPuzzleApplet.setFocusable(true);
		        	   jframe.setContentPane(musicPuzzleApplet);
		        	   jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        	   jframe.setSize(windowWidth, windowHeight);
		        	   jframe.setVisible(true);
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
		
		itemBox = new ItemBox(this, 1200, 0, "propsColumn.png"){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if (theEvent.getAction() == 100) {
					mouseClicked();
				}
		    }
		};
		
		
		mykey = new Item(this , 56, 56, 500 , 600 , "mykey.png" , "mykey.png" , "mykey.png", 60, 60, 900, 600, "cabinet.png", Type.TOOL){
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
		
		
		
		//sizex:sizey = 1.14402:1
//		table = new Item(this , 343 , 300 , 430 , 330 , "table.png" , "table.png" , "table.png", Type.FURNITURE){
		table = new Item(this , 343 , 300 , 430 , 330 , "TableWithStatue.png" , "TableWithStatue.png" , "TableWithStatue.png", Type.CONTROL){
			
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
		
		
		
		
		
		baijuyi = new Item(this , 30 , 30 , 500 , 440 , "card2.png" , "card2.png" , "card2.png", 186 , 240 , 330 , 50, "lucifer.png", Type.TOOL){
		
		@Override
		
			public void controlEvent(CallbackEvent theEvent) {
				baijuyi.solControlP5.setVisible(false);
				if(theEvent.getController().getName().equals("card2")){
					if (theEvent.getAction() == 100) {
						FirstApplet applet = new FirstApplet();
						applet.init();
						applet.start();
						applet.setFocusable(true);
						
						JFrame window = new JFrame("WORD");
						window.setContentPane(applet);
						window.setSize( 800, 800);
						window.setVisible(true);
						if(!isInBox){
							itemBox.putinItem(this);
						}else{
							itemBox.checkItem(this, itemArr);
						}
					}
				}else if(theEvent.getController().getName().equals("solmonopoly")){
					if ((theEvent.getAction() == 100) && isInBox && isHolded){ //later change
//						itemBox.useItem(this);
						//use btnControlP5 rather than ControlP5
//						this.btnControlP5.setPosition((int)this.btnSolControlP5.getPosition()[0]+200,(int)this.btnSolControlP5.getPosition()[1]-20);
//						this.btnControlP5.setPosition(50,50);
						itemBox.useItem(this);
						numSolved ++;
						checkWin();
					}
				}
		   	}
		};
		
		dufu = new Item(this , 30 , 30 , 550 , 440 , "card2.png" , "card2.png" , "card2.png", 80 , 186 , 520 , 100, "confucius.png", Type.TOOL){
		
		@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("card2")){
					
					if (theEvent.getAction() == 100) {
						SecondApplet applet = new SecondApplet();
						applet.init();
						applet.start();
						applet.setFocusable(true);
						
						JFrame window = new JFrame("WORD");
						window.setContentPane(applet);
						window.setSize( 800, 800);
						window.setVisible(true);
						if(!isInBox){
							itemBox.putinItem(this);
						}else{
							itemBox.checkItem(this, itemArr);
						}
					}
				}else if(theEvent.getController().getName().equals("solmusician")){
					if ((theEvent.getAction() == 100) && isInBox && isHolded){ //later change
						itemBox.useItem(this);
						numSolved ++;
						checkWin();
					}
				}
		   	}
		};
		
		wangwei = new Item(this , 30 , 30 , 620 , 440 , "card2.png" , "card2.png" , "card2.png", 110 , 186 , 630 , 105, "buddha.png", Type.TOOL){
		
		@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("card2")){
					if (theEvent.getAction() == 100) {
						ThirdApplet applet = new ThirdApplet();
						applet.init();
						applet.start();
						applet.setFocusable(true);
						
						JFrame window = new JFrame("WORD");
						window.setContentPane(applet);
						window.setSize( 800, 800);
						window.setVisible(true);
						if(!isInBox){
							itemBox.putinItem(this);
						}else{
							itemBox.checkItem(this, itemArr);
						}
					}
				}else if(theEvent.getController().getName().equals("solNapoleon")){
					if ((theEvent.getAction() == 100) && isInBox && isHolded){ //later change
						itemBox.useItem(this);
						numSolved ++;
						checkWin();
					}
				}
		   	}
		};
		
		
		libai = new Item(this , 30 , 30 , 670 , 440 , "card2.png" , "card2.png" , "card2.png", 118 , 186 , 750 , 105, "ludongbin.png", Type.TOOL){
			
			@Override
				public void controlEvent(CallbackEvent theEvent) {
					if(theEvent.getController().getName().equals("card2")){
						if (theEvent.getAction() == 100) {
							FourthApplet applet = new FourthApplet();
							applet.init();
							applet.start();
							applet.setFocusable(true);
							
							JFrame window = new JFrame("WORD");
							window.setContentPane(applet);
							window.setSize( 800, 800);
							window.setVisible(true);
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
							checkWin();
						}
					}
			   	}
		};
		leftBox = new Item(this , 100 , 100 , 300 , 500 , "buddaSafe_nomove.png" , "buddaSafe_nomove.png" , "buddaSafe_nomove.png", Type.FURNITURE){
			
			@Override
				public void controlEvent(CallbackEvent theEvent) {
					if(theEvent.getController().getName().equals("buddaSafe_nomove")){
						if (theEvent.getAction() == 100) {
							slash.start();
							leftBox.updateImage(100 , 100 , 300 , 500 ,"buddaSafe_open.png", "buddaSafe_open.png", "buddaSafe_open.png");
						}
					}
			   	}
			};
		rightBox = new Item(this , 100 , 100 , 800 , 500 , "buddaSafe_nomove.png" , "buddaSafe_nomove.png" , "buddaSafe_nomove.png", Type.FURNITURE){
				
				@Override
					public void controlEvent(CallbackEvent theEvent) {
						if(theEvent.getController().getName().equals("buddaSafe_nomove")){
							if (theEvent.getAction() == 100) {
								slash2.start();
								rightBox.updateImage(100 , 100 , 800 , 500 ,"buddaSafe_open.png", "buddaSafe_open.png", "buddaSafe_open.png");
							}
						}
					   }
			};
		
		
		//they are deactivated until changing to the table background
		baijuyi.solControlP5.setVisible(false);
		dufu.solControlP5.setVisible(false);
		wangwei.solControlP5.setVisible(false);
		libai.solControlP5.setVisible(false);
		mykey.controlP5.setVisible(false);
		itemArr = new ArrayList<Item>(Arrays.asList(baijuyi, dufu, wangwei, libai, mykey));
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
	
	public void checkWin(){
		if(numSolved == 4){
			this.mykey.controlP5.setVisible(true);
		}
	}
	public void loadMusic(){
		try{
			slash = MusicPlay.getMusic(path + "slash.wav");
			System.out.println("OK playing sound.");
		}catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}


}

