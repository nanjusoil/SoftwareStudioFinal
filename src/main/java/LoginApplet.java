package main.java;

import javax.swing.JFrame;

import controlP5.CallbackEvent;
import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PImage;

public class LoginApplet extends PApplet{
	private String path = "main/resources/";
	private PImage imgBackground;
	private final int windowWidth = 1303, windowHeight = 745;
	private ControlP5 controlP5;
	private Item stage1;
	private Item stage2;
	private Item stage3;
	private MusicPuzzleApplet mainApplet;
	private BuddhistTempleApplet btApplet;
	private AstronomyApplet astronomyapplet;
	private MusicPuzzleApplet musicPuzzleApplet;
	private JFrame jframe;
	public LoginApplet(JFrame jframe){
		this.jframe = jframe;
	}
	
	public void setup() {
		size(windowWidth, windowHeight);
		smooth();
		imgBackground = loadImage(path+"BathRoom.jpg");
		
		mainApplet = new MusicPuzzleApplet(jframe);
		btApplet = new BuddhistTempleApplet(jframe);
		//astronomyapplet = new AstronomyApplet(jframe);
		musicPuzzleApplet = new MusicPuzzleApplet(jframe);
		
		stage1 = new Item(this , 78, 83, 275 , 275 , "stageSelect.png" , "stageSelect.png" , "stageSelect.png", Type.CONTROL){
			@Override
			public void controlEvent(CallbackEvent theEvent) {
		           if (theEvent.getAction() == 100) {
//		        	   mainApplet.init();
//		        	   mainApplet.start();
//		        	   mainApplet.setFocusable(true);
//		        	   jframe.setContentPane(mainApplet);
		        	   
		        	   btApplet.init();
		        	   btApplet.start();
		        	   btApplet.setFocusable(true);
		        	   jframe.setContentPane(btApplet);
		        	   
		        	  /* astronomyapplet.init();
		        	   astronomyapplet.start();
		        	   astronomyapplet.setFocusable(true);
		        	   jframe.setContentPane(astronomyapplet);*/
		        	   
		        	   /*musicPuzzleApplet.init();
		        	   musicPuzzleApplet.start();
		        	   musicPuzzleApplet.setFocusable(true);
		        	   jframe.setContentPane(musicPuzzleApplet);*/
		        	   
		        	   jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        	   jframe.setSize(windowWidth, windowHeight);
		        	   jframe.setVisible(true);
		           }
		       }
		};
		//Item stage2 = new Item(this , 500 , 400 , "stageSelect.png" , "stageSelect.png" , "stageSelect.png", Type.CONTROL);
		//Item stage3 = new Item(this , 500 , 400 , "stageSelect.png" , "stageSelect.png" , "stageSelect.png", Type.CONTROL);

	}
	public void draw() {
		image(imgBackground, 0, 0);
	}

}
