package main.java;

import javax.swing.JFrame;

import processing.core.PApplet;
import processing.core.PImage;

public class GameWinApplet extends PApplet{
	
	
	private String path = "main/resources/";
	private PImage imgBackground;
	private final int windowWidth = 1286, windowHeight = 700;
	private JFrame jframe;
	private final int itemboxWidth = 86;
	
	
	
	public GameWinApplet(JFrame jframe){
		this.jframe = jframe;
		
	}
	
	public void setup() {
		
		size(windowWidth, windowHeight);
		smooth();
		imgBackground = loadImage(path+"gameWin.jpg");
		imgBackground.resize(windowWidth, windowHeight);
		
		
	}
	
	public void draw() {
		image(imgBackground, 0, 0);
		text("Congratulation You Win !!!!!", 0, 350 );
		textSize(100);
	}
	
	
	

}
