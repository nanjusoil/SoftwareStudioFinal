package main.java;




import java.util.ArrayList;
import java.util.Arrays;



import javax.sound.sampled.Clip;

import javax.swing.JFrame;



import controlP5.CallbackEvent;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import io.socket.emitter.Emitter;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

@SuppressWarnings("serial")
public class MusicPuzzleApplet extends PApplet{
	private String path = "main/resources/";
	private PImage imgBackground;
	private final int windowWidth = 1286, windowHeight = 700;
	private final int itemboxWidth = 86;
	private ControlP5 controlP5;
	private int currentRoom=1;
	private String[] filenameRooms = {"BedRoom.jpg" , "LivingRoom.jpg" , "Kitchen.jpg"};
	private Item buttonRight;
	private Item buttonLeft;
	private Item mykey;
	private Item paperball;
	private ItemBox itemBox;
	private Item microwave;
	private Item safe;
	private JFrame jframe;
	private LoginApplet loginapplet;
	private Keyboard keyboard;
	
	// parameter::  sizex, sizey , x , y
	private Item musician;//200, 200, 0, 0
	private Item cabinet;//1200, 700, 0, 0
	private Item monopoly;//190, 150, 385, 60 
	private Item Napoleon;//190, 150, 600, 60
	private Item pentatonix;//190, 150, 385, 250
	private Item rural;//190, 150, 600, 250
	private Item stage;
	private ArrayList<Item> itemArr;
	
	Clip faith, hero, vocal, rurals,toast;   
	
	AstronomyApplet astronomyApplet;
	
	public boolean safe_status = false;//false:safe關著   true:safe 打開
	
	public MusicPuzzleApplet(JFrame jframe){
		this.jframe = jframe;
		
		Main.socket.on("safeopen", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  System.out.println("asc");
				  safe_status = true;
				  server_connection();
				  
			  }

		});
		Main.socket.on("faithPlay", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 	hero.stop();
				 	vocal.stop();
				 	rurals.stop();
					faith.start();
					faith.loop(Clip.LOOP_CONTINUOUSLY);
					
				
			  }
		});
		
		Main.socket.on("heroPlay", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 	faith.stop();
				 	vocal.stop();
				 	rurals.stop();
					hero.start();
					hero.loop(Clip.LOOP_CONTINUOUSLY);
			  }
		});
		
		Main.socket.on("vocalPlay", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 faith.stop();
				 	hero.stop();
				 	rurals.stop();
					vocal.start();
					vocal.loop(Clip.LOOP_CONTINUOUSLY);
			  }
		});
		
		Main.socket.on("ruralsPlay", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 hero.stop();
				 	vocal.stop();
				 	faith.stop();
					rurals.start();
					rurals.loop(Clip.LOOP_CONTINUOUSLY);
			  }
		});
		
		Main.socket.on("faithStop", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 	faith.stop();
			  }
		});
		
		Main.socket.on("heroStop", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 hero.stop();
			  }
		});
		
		Main.socket.on("vocalStop", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 	vocal.stop();
			  }
		});

		
		Main.socket.on("ruralsStop", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
					rurals.stop();
			  }
		});
		
		
		Main.socket.on("putinItemmykey", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 itemBox.putinItem(mykey);
			  }
		});
		

		Main.socket.on("checkItemmykey", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 itemBox.checkItem(mykey, itemArr);
			  }
		});
		
		
		Main.socket.on("useItemmykey", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 	itemBox.useItem(mykey);
				 	mykey.controlP5.setVisible(false);
			  }
		});
		
		Main.socket.on("putinItempaperball", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 if(!paperball.isInBox){
						itemBox.putinItem(paperball);
					}
			  }
		});
		
		Main.socket.on("checkItempaperball", new Emitter.Listener() {
			
			 @Override
			  public void call(Object... args) {
				 itemBox.checkItem(paperball, itemArr);
					PApplet applet = new PApplet(){
						private PImage paperball_unfolded;
						public void setup(){
							paperball_unfolded = loadImage(path+"paperball_unfolded.png");
							paperball_unfolded.resize(100, 100);
							System.out.println("X:" + paperball_unfolded.width);
							System.out.println("Y:" + paperball_unfolded.height);
						};
						public void draw(){
							background(255);
							image(paperball_unfolded, 30, 0, 100, 100);
						};
					};
					applet.init();
					applet.start();
					applet.setFocusable(true);
					
					JFrame window = new JFrame("BALL");
					window.setContentPane(applet);
					window.setSize( 100, 150);
					window.setVisible(true);
			  }
		});
		
	}

	
	public void setup() {
		size(windowWidth, windowHeight);
		smooth();
		imgBackground = loadImage(path+"LivingRoom.jpg");
		imgBackground.resize(windowWidth-itemboxWidth, windowHeight);
		loginapplet = new LoginApplet(jframe);
		
		
		astronomyApplet = new AstronomyApplet(jframe); 

		    try {
		        
		        faith = MusicPlay.getMusic("src/" + path + "Sounds/faith.wav");
		      
		        hero = MusicPlay.getMusic("src/" + path + "Sounds/hero.wav");
		       
		        vocal = MusicPlay.getMusic("src/" + path + "Sounds/vocal.wav");
		       
		        rurals = MusicPlay.getMusic("src/" + path + "Sounds/rurals.wav");
		        
		       
		        
		        
		        
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
		
		  
		 
	  

		
		musician = new Item(this , 100 , 100 , 1000, 120 , "musician.png" , "musician.png" , "musician.png", Type.FURNITURE);
		//cabinet = new Item(this , 1200 , 700 , 0 , 0 , "cabinet.png" , "cabinet.png" , "cabinet.png", Type.FURNITURE);
		monopoly = new Item(this , 120 , 210 , 120 , 200, "fortune.jpg" , "fortune.jpg" , "fortune.jpg", Type.FURNITURE){
		@Override
		public void controlEvent(CallbackEvent theEvent) {
			if (theEvent.getAction() == 100) {
					
				if( faith.isRunning() )
					faith.stop();
				else{
				 	hero.stop();
				 	vocal.stop();
				 	rurals.stop();
					faith.start();
					faith.loop(Clip.LOOP_CONTINUOUSLY);
				}
				
				}
	    	}
		};
		Napoleon = new Item(this , 100 , 100 , 850 , 130 , "Napoleon.png" , "Napoleon.png" , "Napoleon.png", Type.FURNITURE){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if (theEvent.getAction() == 100) {
					
					if( hero.isRunning() ){
						hero.stop();
						Main.socket.emit("heroStop","data");
					}else{
					faith.stop();
				 	vocal.stop();
				 	rurals.stop();
					hero.start();
					hero.loop(Clip.LOOP_CONTINUOUSLY);
					Main.socket.emit("heroPlay","data");
					}
					
				}
		    }
		};
	
		pentatonix = new Item(this , 100 , 100 , 500 , 465 , "eew.png" , "eew.png" , "eew.png", Type.FURNITURE){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if (theEvent.getAction() == 100) {
					
					if( vocal.isRunning() ){
						vocal.stop();
						Main.socket.emit("vocalStop","data");
					}else{
					faith.stop();
				 	hero.stop();
				 	rurals.stop();
					vocal.start();
					vocal.loop(Clip.LOOP_CONTINUOUSLY);
					Main.socket.emit("vocalStop","data");
					}
				}
		    }
		};
		
		rural = new Item(this , 200 , 120 , 900 , 230, "rural.jpg" , "rural.jpg" , "rural.jpg", Type.FURNITURE){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if (theEvent.getAction() == 100) {
					if( rurals.isRunning() ){
						rurals.stop();
						Main.socket.emit("ruralsStop","data");
					}else{
					hero.stop();
				 	vocal.stop();
				 	faith.stop();
					rurals.start();
					rurals.loop(Clip.LOOP_CONTINUOUSLY);
					Main.socket.emit("ruralsPlay","data");
					}
				}
		    }
		};
		
		
		
//		buttonLeft = new Item(this , 128, 128, 0 , 275 , "arrowLeft.png" , "arrowLeft.png" , "arrowLeftPressed.png", Type.CONTROL){
//			@Override
//			public void controlEvent(CallbackEvent theEvent) {
//		           if (theEvent.getAction() == 100) {
//		        	   loginapplet.init();
//		        	   loginapplet.start();
//		        	   loginapplet.setFocusable(true);
//		        	   jframe.setContentPane(loginapplet);
//		        	   jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		        	   jframe.setSize(windowWidth, windowHeight);
//		        	   jframe.setVisible(true);
//		       		  if(currentRoom!=0)
//		    		  {
//		    			 imgBackground = loadImage(path+filenameRooms[--currentRoom]);
//		    			 imgBackground.resize(windowWidth-itemboxWidth, windowHeight);			
//		    		  }
//		           }
//		       }
//		};
		
//		buttonRight = new Item(this , 128, 128, 900 , 275 , "arrowRight.png" , "arrowRight.png" , "arrowRightPressed.png", Type.CONTROL){
//			@Override
//			public void controlEvent(CallbackEvent theEvent) {
//				if (theEvent.getAction() == 100) {
//				
//		        	   astronomyApplet.init();
//		        	   astronomyApplet.start();
//		        	   astronomyApplet.setFocusable(true);
//		        	   jframe.setContentPane(astronomyApplet);
//		        	   jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		        	   jframe.setSize(windowWidth, windowHeight);
//		        	   jframe.setVisible(true);
//					if(currentRoom!=2)
//					{
//					    imgBackground = loadImage(path+filenameRooms[++currentRoom]);
//					    imgBackground.resize(windowWidth-itemboxWidth, windowHeight);			
//					}
//					nextRoom();
//				}
//		    }
//		};
		
		itemBox = new ItemBox(this, 1200, 0, "propsColumn.png"){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if (theEvent.getAction() == 100) {
					mouseClicked();
				}
		    }
		};
		
		safe = new Item(this , 120, 130, 165 , 430 , "safe_nomove.png" , "safe_nomove.png" , "safe_nomove.png", Type.FURNITURE){
			
			@Override
			
			public void controlEvent(CallbackEvent theEvent) {
				if ((theEvent.getAction() == 100) && (!safe_status)){
					keyboard = new Keyboard(safe,"3596",safe_status);
					
					keyboard.setVisible(true);
					System.out.println("SAFE CLICK");
					Main.socket.emit("safeopen","data");
					System.out.println("safe_status: "+ safe_status);
				}
		    }
		};
		
		mykey = new Item(this , 56, 56, 220 , 500 , "mykey.png" , "mykey.png" , "mykey.png", 60, 60, 1140, 640, "exit.png", Type.TOOL){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("mykey")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
							itemBox.putinItem(this);
							Main.socket.emit("putinItemmykey","data");
						}else{
							itemBox.checkItem(this, itemArr);
							Main.socket.emit("checkItemmykey","data");
						}
					}
				}else if(theEvent.getController().getName().equals("solmykey")){
					if ((theEvent.getAction() == 100) && isInBox && isHolded){
						itemBox.useItem(this);
						nextRoom();
						Main.socket.emit("useItemmykey","data");
					}
				}
		    }
		};
		
		paperball = new Item(this , 20, 20, 900 , 380 , "paperball.png" , "paperball.png" , "paperball.png", Type.MESSAGE){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("paperball")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
							itemBox.putinItem(this);
							Main.socket.emit("putinItempaperball","data");
						}else{
							itemBox.checkItem(this, itemArr);
							PApplet applet = new PApplet(){
								private PImage paperball_unfolded;
								public void setup(){
									paperball_unfolded = loadImage(path+"paperball_unfolded.png");
									paperball_unfolded.resize(400, 400);
									System.out.println("X:" + paperball_unfolded.width);
									System.out.println("Y:" + paperball_unfolded.height);
								};
								public void draw(){
									background(255);
									image(paperball_unfolded, 30, 0, 400, 400);
								};
							};
							applet.init();
							applet.start();
							applet.setFocusable(true);
							
							JFrame window = new JFrame("BALL");
							window.setContentPane(applet);
							window.setSize( 450, 450);
							window.setVisible(true);
							Main.socket.emit("checkItempaperball","data");
						}
					}
				}
		    }
		};
		stage = new Item(this , 100 , 200 , 1100 , 400 , "stage2.png" , "stage2.png" , "stage2.png", Type.FURNITURE){
			
			@Override
				public void controlEvent(CallbackEvent theEvent) {
					if(theEvent.getController().getName().equals("stage1")){
						if (theEvent.getAction() == 100) {
							
						}
					}
			   	}
		};
		
		itemArr = new ArrayList<Item>(Arrays.asList(mykey, paperball));
		server_connection();
		mykey.controlP5.setVisible(false);
		
	}
	public void server_connection(){
		if(safe_status == true){
			Clip cheer;
			       
			cheer = MusicPlay.getMusic("src/" + path + "Sounds/cheers.wav");
	     	cheer.start();
    		safe.updateImage("safe_open.png", "safe_open.png", "safe_open.png");
    		((MusicPuzzleApplet)keyboard.safe.parent).safe_status = true;
    		((MusicPuzzleApplet)keyboard.safe.parent).showkey();
    		keyboard.dispose();
			
		}
	}
	public void draw() {
		image(imgBackground, 0, 0, 1286,700);
		
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
	
	public void showkey(){
		if(safe_status){
			if(paperball.isInBox){
				itemBox.useItem(paperball);
				paperball.controlP5.setVisible(false);
			}
			mykey.controlP5.setVisible(true);
		}
	}
	
	public void nextRoom(){
		System.out.println("music");
	   astronomyApplet.init();
 	   astronomyApplet.start();
 	   astronomyApplet.setFocusable(true);
 	   jframe.setContentPane(astronomyApplet);
 	   jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	   jframe.setSize(1303, 745);
 	   jframe.setVisible(true);
//		if(currentRoom!=2)
//		{
//		    imgBackground = loadImage(path+filenameRooms[++currentRoom]);
//		    imgBackground.resize(windowWidth-itemboxWidth, windowHeight);			
//		}
	}

}
