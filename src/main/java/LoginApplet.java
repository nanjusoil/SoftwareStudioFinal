package main.java;

import processing.core.PApplet;
import processing.core.PImage;

public class LoginApplet extends PApplet{
	private String path = "main/resources/";
	private PImage imgBackground;
	public LoginApplet(){
		System.out.println("asdsad");
	}
	public void setup() {
		imgBackground = loadImage(path+"BathRoom.jpg");
	}
	public void draw() {
		image(imgBackground, 0, 0);
	}

}
