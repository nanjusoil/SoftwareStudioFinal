package main.java;

import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

import controlP5.CallbackEvent;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
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
	private int indexHolded;
	private boolean[] hasCard = {false, false, false, false};//whether a statue has a card at its foot
	private int[] pos = {-1, -1, -1, -1}; //the position of a card being placed(at which statue)
	//used as second parameter of checkItem(), to make different items' isHolded exclusive
	//(There is at most only one item's isHolded being true in any given time)
	
	public Clip slash,slash2, sutra;
	
	private boolean left_box_status = false;//0:box not open 1:box open
	private boolean right_box_status = false;
	private MusicPuzzleApplet musicPuzzleApplet;
	
	

	public BuddhistTempleApplet(JFrame jframe){
		this.jframe = jframe;
		Main.socket.on("leftboxopen", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  System.out.println("asc");
				  left_box_status = true;
				  server_connection();
			  }

			});
		Main.socket.on("rightboxopen", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  right_box_status = true;
				  server_connection();
			  }

		});
		Main.socket.on("putinCardbaijuyi", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  putinCard(baijuyi); 
			  };

		});
		Main.socket.on("checkCardbaijuyi", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  checkCard(baijuyi); 
			  };

		});
		Main.socket.on("useCardbaijuyi", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  useCard(baijuyi, 10, 180); 
			  };

		});
		Main.socket.on("putinCarddufu", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  putinCard(dufu); 
			  };

		});
		Main.socket.on("checkCarddufu", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  checkCard(dufu); 
			  };

		});
		Main.socket.on("useCarddufu", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  useCard(dufu, -10, 130);
			  };

		});
		Main.socket.on("putinCardwangwei", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  putinCard(wangwei); 
			  };

		});
		Main.socket.on("checkCardwangwei", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  checkCard(wangwei); 
			  };

		});
		Main.socket.on("useCardwangwei", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  useCard(wangwei, 80, 130);
			  };

		});
		Main.socket.on("putinCardlibai", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  System.out.println("putinCardlibai");
				  putinCard(libai); 
			  };

		});
		Main.socket.on("checkCardlibai", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  checkCard(libai); 
			  };

		});
		Main.socket.on("useCardlibai", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  useCard(libai, 70, 120);
			  };

		});
	}

	
	public void setup() {
		size(windowWidth, windowHeight);
		smooth();
		//loadMusic();
		try{
			slash = MusicPlay.getMusic("src/" + path + "Sounds/slash.wav");
			slash2 = MusicPlay.getMusic("src/" + path + "Sounds/slash2.wav");
			sutra = MusicPlay.getMusic("src/" + path + "Sounds/sutra.wav");
			sutra.loop(Clip.LOOP_CONTINUOUSLY);
			//lower the volume of BGM
			FloatControl volume = (FloatControl) sutra.getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(-20);
			System.out.println("OK playing sound.");
		}catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
		imgBackground = loadImage(path+"BuddhistTempleWithoutTable.png");
		imgBackground.resize(windowWidth-itemboxWidth, windowHeight);
		loginapplet = new LoginApplet(jframe);
		indexHolded = -1;
		
		musicPuzzleApplet = new MusicPuzzleApplet(jframe);
		
//		buttonLeft = new Item(this , 128, 128, 0 , 275 , "arrowLeft.png" , "arrowLeft.png" , "arrowLeftPressed.png", Type.CONTROL){
//			@Override
//			public void controlEvent(CallbackEvent theEvent) {
//		           if (theEvent.getAction() == 100) {
//		        	   sutra.stop();
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
		
		buttonRight = new Item(this , 128, 128, 900 , 275 , "arrowRight.png" , "arrowRight.png" , "arrowRightPressed.png", Type.CONTROL){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
				if (theEvent.getAction() == 100) {
					nextRoom();
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
					
					if(!baijuyi.isInBox)baijuyi.updateImage(30, 30, 620, 440,"card2.png" , "card2.png" , "card2.png");
					if(!dufu.isInBox)dufu.updateImage(30, 30, 500, 440 ,"card2.png" , "card2.png" , "card2.png");
					if(!wangwei.isInBox)wangwei.updateImage(30, 30, 670, 440 ,"card2.png" , "card2.png" , "card2.png");
					if(!libai.isInBox)libai.updateImage(30, 30, 550, 440 ,"card2.png" , "card2.png" , "card2.png");

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
		
		
		mykey = new Item(this , 56, 56, 500 , 600 , "mykey.png" , "mykey.png" , "mykey.png", 60, 60, 1140, 640, "exit.png", Type.TOOL){
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
						nextRoom();
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
							
							//adjust the cards' position and size
							if(!baijuyi.isInBox)baijuyi.updateImage(50, 50, 640, 305 ,"card2.png" , "card2.png" , "card2.png");
							if(!dufu.isInBox)dufu.updateImage(50, 50, 440, 305 ,"card2.png" , "card2.png" , "card2.png");
							if(!wangwei.isInBox)wangwei.updateImage(50, 50, 740, 305 ,"card2.png" , "card2.png" , "card2.png");
							if(!libai.isInBox)libai.updateImage(50, 50, 540, 305 ,"card2.png" , "card2.png" , "card2.png");
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
		
		baijuyi = new Item(this , 30 , 30 , 620 , 440 , "card2.png" , "card2.png" , "card2.png", 186 , 240 , 330 , 50, "lucifer.png", Type.TOOL){
		
		@Override
		
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("card2")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
//							itemBox.putinItem(this);
//							if(pos[0]>=0){//first card originally at some statue's side
//								hasCard[pos[0]] = false; //first card is originally at pos[0]-th statue
//								pos[0] = -1; //first card is no longer besides statues
//							}
							putinCard(this);
							Main.socket.emit("putinCardbaijuyi","data");
						}else{
//							itemBox.checkItem(this, itemArr);
//							indexHolded = itemArr.indexOf(this);
//							FirstApplet applet = new FirstApplet();
//							applet.init();
//							applet.start();
//							applet.setFocusable(true);
//							
//							JFrame window = new JFrame("WORD");
//							window.setContentPane(applet);
//							window.setSize( 800, 800);
//							window.setVisible(true);
							checkCard(this);
						}
					}
				}else if(theEvent.getController().getName().equals("solcard2")){
					if ((theEvent.getAction() == 100) && (indexHolded!=-1) && (!hasCard[0])){ //a statue can only has one card
//						Item citem = itemArr.get(indexHolded);
//						
////						System.out.println("indexHolded: " + indexHolded);
////						System.out.println("X: " + citem.btnControlP5.getPosition()[0] + 
////								"Y: " + citem.btnControlP5.getPosition()[1]);
////						
//						//set the card besides the statue
//						citem.updateImage(50, 50, 
//								(int)this.btnSolControlP5.getPosition()[0]+10, (int)this.btnSolControlP5.getPosition()[1]+180, 
//								"card2.png" , "card2.png" , "card2.png");
////						System.out.println(numSolved);
//						
////						System.out.println(numSolved);
////						System.out.println("X: " + citem.btnControlP5.getPosition()[0] + 
////								"Y: " + citem.btnControlP5.getPosition()[1]);
////						System.out.println("SolX: " + citem.btnSolControlP5.getPosition()[0] + 
////								"SolY: " + citem.btnSolControlP5.getPosition()[1]);
//						
//						pos[indexHolded] = 0; //pos[card_index] = statue_index
//						checkWin();
//						hasCard[0] = true;
//						indexHolded = -1;
//						itemBox.useItem(citem);
						useCard(this, 10, 180);
					}
				}
		   	}
		};
		
		dufu = new Item(this , 30 , 30 , 500 , 440 , "card2.png" , "card2.png" , "card2.png", 80 , 186 , 520 , 100, "confucius.png", Type.TOOL){
		
		@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("card2")){
					
					if (theEvent.getAction() == 100) {
						if(!isInBox){
//							itemBox.putinItem(this);
//							if(pos[1]>=0){
//								hasCard[pos[1]] = false; 
//								pos[1] = -1; 
//							}
							putinCard(this);
						}else{
//							itemBox.checkItem(this, itemArr);
//							indexHolded = itemArr.indexOf(this);
//							
//							SecondApplet applet = new SecondApplet();
//							applet.init();
//							applet.start();
//							applet.setFocusable(true);
//							
//							JFrame window = new JFrame("WORD");
//							window.setContentPane(applet);
//							window.setSize( 800, 800);
//							window.setVisible(true);
							checkCard(this);
						}
					}
				}else if(theEvent.getController().getName().equals("solcard2")){
					if ((theEvent.getAction() == 100) && (indexHolded!=-1) && (!hasCard[1])){ //a statue can only has one card
//						Item citem = itemArr.get(indexHolded);
//						citem.updateImage(50, 50, 
//								(int)this.btnSolControlP5.getPosition()[0]-10, (int)this.btnSolControlP5.getPosition()[1]+130, 
//								"card2.png" , "card2.png" , "card2.png");
//						pos[indexHolded] = 1; //pos[card_index] = statue_index
//						checkWin();
//						hasCard[1] = true;
//						indexHolded = -1;
//						itemBox.useItem(citem);
						useCard(this, -10, 130);
					}
				}
		   	}
		};
		
		wangwei = new Item(this , 30 , 30 , 670 , 440 , "card2.png" , "card2.png" , "card2.png", 110 , 186 , 630 , 105, "buddha.png", Type.TOOL){
		
		@Override
			public void controlEvent(CallbackEvent theEvent) {
				if(theEvent.getController().getName().equals("card2")){
					if (theEvent.getAction() == 100) {
						if(!isInBox){
//							itemBox.putinItem(this);
//							if(pos[2]>=0){
//								hasCard[pos[2]] = false; 
//								pos[2] = -1; 
//							}
							putinCard(this);
						}else{
//							itemBox.checkItem(this, itemArr);
//							indexHolded = itemArr.indexOf(this);
//							ThirdApplet applet = new ThirdApplet();
//							applet.init();
//							applet.start();
//							applet.setFocusable(true);
//							
//							JFrame window = new JFrame("WORD");
//							window.setContentPane(applet);
//							window.setSize( 800, 800);
//							window.setVisible(true);
							checkCard(this);
						}
					}
				}else if(theEvent.getController().getName().equals("solcard2")){
					if ((theEvent.getAction() == 100) && (indexHolded!=-1) && (!hasCard[2])){ //a statue can only has one card
//						Item citem = itemArr.get(indexHolded);
//						citem.updateImage(50, 50, 
//								(int)this.btnSolControlP5.getPosition()[0]+80, (int)this.btnSolControlP5.getPosition()[1]+130, 
//								"card2.png" , "card2.png" , "card2.png");
//						pos[indexHolded] = 2; //pos[card_index] = statue_index
//						checkWin();
//						hasCard[2] = true;
//						indexHolded = -1;
//						itemBox.useItem(citem);
						useCard(this, 80, 130);
					}
				}
		   	}
		};
		
		
		libai = new Item(this , 30 , 30 ,  550 , 440, "card2.png" , "card2.png" , "card2.png", 118 , 186 , 750 , 105, "ludongbin.png", Type.TOOL){
			
			@Override
				public void controlEvent(CallbackEvent theEvent) {
					if(theEvent.getController().getName().equals("card2")){
						if (theEvent.getAction() == 100) {
							if(!isInBox){
//								itemBox.putinItem(this);
//								if(pos[3]>=0){
//									hasCard[pos[3]] = false; 
//									pos[3] = -1; 
//								}
								putinCard(this);
							}else{
//								itemBox.checkItem(this, itemArr);
//								indexHolded = itemArr.indexOf(this);
//								FourthApplet applet = new FourthApplet();
//								applet.init();
//								applet.start();
//								applet.setFocusable(true);
//								
//								JFrame window = new JFrame("WORD");
//								window.setContentPane(applet);
//								window.setSize( 800, 800);
//								window.setVisible(true);
								checkCard(this);
							}
						}
					}else if(theEvent.getController().getName().equals("solcard2")){
						if ((theEvent.getAction() == 100) && (indexHolded!=-1) && (!hasCard[3])){ //a statue can only has one card
//							Item citem = itemArr.get(indexHolded);
//							citem.updateImage(50, 50, 
//									(int)this.btnSolControlP5.getPosition()[0]+70, (int)this.btnSolControlP5.getPosition()[1]+120, 
//									"card2.png" , "card2.png" , "card2.png");
//							pos[indexHolded] = 3; //pos[card_index] = statue_index
//							checkWin();
//							hasCard[3] = true;
//							indexHolded = -1;
//							itemBox.useItem(citem);
							useCard(this, 70, 120);
						}
					}
			   	}
		};
		leftBox = new Item(this , 100 , 100 , 300 , 500 , "buddaSafe_nomove.png" , "buddaSafe_nomove.png" , "buddaSafe_nomove.png", Type.FURNITURE){
			
			@Override
				public void controlEvent(CallbackEvent theEvent) {
					if(theEvent.getController().getName().equals("buddaSafe_nomove")){
						if (theEvent.getAction() == 100) {
							Main.socket.emit("leftboxopen","data");
							slash.start();
							this.updateImage(100 , 100 , 300 , 500 ,"buddaSafe_open.png", "buddaSafe_open.png", "buddaSafe_open.png");
							dufu.controlP5.setVisible(true);
							libai.controlP5.setVisible(true);
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
								this.updateImage(100 , 100 , 800 , 500 ,"buddaSafe_open.png", "buddaSafe_open.png", "buddaSafe_open.png");
								baijuyi.controlP5.setVisible(true);	
								wangwei.controlP5.setVisible(true);
							}
						}
					   }
			};
		
		
		//they are deactivated until changing to the table background
		baijuyi.controlP5.setVisible(false);	
		dufu.controlP5.setVisible(false);
		wangwei.controlP5.setVisible(false);
		libai.controlP5.setVisible(false);
			
		baijuyi.solControlP5.setVisible(false);
		dufu.solControlP5.setVisible(false);
		wangwei.solControlP5.setVisible(false);
		libai.solControlP5.setVisible(false);
		mykey.controlP5.setVisible(false);
		itemArr = new ArrayList<Item>(Arrays.asList(baijuyi, dufu, wangwei, libai, mykey));
		
//		server_connection();
		
	}
	public void server_connection(){
		if(left_box_status == true){
			slash.start();
			//Main.socket.emit("leftboxopen","data");
			  leftBox.updateImage(100 , 100 , 300 , 500 ,"buddaSafe_open.png", "buddaSafe_open.png", "buddaSafe_open.png");
			  libai.controlP5.setVisible(true);	
			  dufu.controlP5.setVisible(true);
		}
		if(right_box_status == true){
			slash.start();
			  rightBox.updateImage(100 , 100 , 800 , 500 ,"buddaSafe_open.png", "buddaSafe_open.png", "buddaSafe_open.png");
			  wangwei.controlP5.setVisible(true);	
			  baijuyi.controlP5.setVisible(true);
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
	
	public void checkWin(){
		System.out.println("ans:" + pos[0] + pos[1]+ pos[2]+ pos[3]);
		if((pos[0]==0) && (pos[1]==1) && (pos[2]==2) && (pos[3]==3)){
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
	
	public void putinCard(Item item){
		itemBox.putinItem(item);
		int icard = itemArr.indexOf(item);
		System.out.println(icard);
		if(pos[icard]>=0){//first card originally at some statue's side
			hasCard[pos[icard]] = false; //first card is originally at pos[0]-th statue
			pos[icard] = -1; //first card is no longer besides statues
		}
	}

	
	public void checkCard(Item item){
		itemBox.checkItem(item, itemArr);
		indexHolded = itemArr.indexOf(item);
		PApplet applet = new PApplet();
		
		switch(indexHolded){
		case 0:
			applet = new ThirdApplet();
			break;
		case 1:
			applet = new FourthApplet();
			break;
		case 2:
			applet = new SecondApplet();
			break;
		case 3:
			applet = new FirstApplet();
		}
		
		applet.init();
		applet.start();
		applet.setFocusable(true);
		
		JFrame window = new JFrame("WORD");
		window.setContentPane(applet);
		window.setSize( 800, 800);
		window.setVisible(true);
	}
	
	public void useCard(Item item, int xdiff, int ydiff){
		Item uitem = itemArr.get(indexHolded);
		uitem.updateImage(50, 50, 
					(int)item.btnSolControlP5.getPosition()[0]+xdiff, (int)item.btnSolControlP5.getPosition()[1]+ydiff, 
					"card2.png" , "card2.png" , "card2.png");
		int istatue = itemArr.indexOf(item);
		pos[indexHolded] = istatue; //pos[card_index] = statue_index
		checkWin();
		hasCard[istatue] = true;
		indexHolded = -1;
		itemBox.useItem(uitem);
	}
	
	public void nextRoom(){
		//JSONObject obj = new JSONObject();
		//obj.put("data" , "server");
		
		sutra.stop();
		musicPuzzleApplet.init();
		musicPuzzleApplet.start(); 
		musicPuzzleApplet.setFocusable(true);
    	   jframe.setContentPane(musicPuzzleApplet);
    	   jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	   jframe.setSize(1303, 745);
    	   jframe.setVisible(true);
		if(currentRoom!=2)
		{
		    imgBackground = loadImage(path+filenameRooms[++currentRoom]);
		    imgBackground.resize(windowWidth-itemboxWidth, windowHeight);			
		}
	}

}

