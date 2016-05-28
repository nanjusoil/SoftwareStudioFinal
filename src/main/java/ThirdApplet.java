package main.java;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import ddf.minim.*;


public class ThirdApplet extends PApplet{
	
	PFont f;
	Minim minim;
	AudioPlayer song;
	private String path = "main/resources/";
	
	private int begin = 20;
	private int addition = 40;
	private int temp = 0;
	private int fontSize = 40;
	private PImage bg;
	private int r=204,g=102,b=0;
	private int r1=0;
	private int r2=0;
	private int r3=0;
	private int r4=0;
	private int r5=0;
	private int r6=0;
	private int r7=0;
	private int r8=0;
	private int r9=0;
	private int r10=0;
	private int r11=0;
	private int r12=0;
	private int r13=0;
	private int r14=0;
	private int r15=0;
	private Ani ani_r;
	private Ani ani_g;
	private Ani ani_b;
	private Ani ani_1;
	private Ani ani_2;
	private Ani ani_3;
	private Ani ani_4;
	private Ani ani_5;
	private Ani ani_6;
	private Ani ani_7;
	private Ani ani_8;
	private Ani ani_9;
	private Ani ani_10;
	private Ani ani_11;
	private Ani ani_12;
	private Ani ani_13;
	private Ani ani_14;
	private Ani ani_15;
	
	public void setup(){
		/*minim = new Minim(this);
		//song = minim.loadFile(this.getClass().getResource("audio/toast.mp3").getPath());
		song = minim.loadFile("audio/toast.mp3", 524288);
		
		song.loop();*/
		
		Ani.init(this);
		ani_r = Ani.to(this, 10 , "r", 0);
		ani_g = Ani.to(this, 10 , "g", 0);
		ani_b = Ani.to(this, 10 , "b", 0);
		ani_1 = Ani.to(this, 10 , "r1", 256);
		ani_2 = Ani.to(this, 10 , "r2", 256);
		ani_3 = Ani.to(this, 10 , "r3", 256);
		ani_4 = Ani.to(this, 10 , "r4", 256);
		ani_5 = Ani.to(this, 10 , "r5", 256);
		ani_6 = Ani.to(this, 10 , "r6", 256);
		ani_7 = Ani.to(this, 10 , "r7", 256);
		ani_8 = Ani.to(this, 10 , "r8", 256);
		ani_9 = Ani.to(this, 10 , "r9", 256);
		ani_10 = Ani.to(this, 10 , "r10", 256);
		ani_11 = Ani.to(this, 10 , "r11", 256);
		ani_12 = Ani.to(this, 10 , "r12", 256);
		ani_13 = Ani.to(this, 10 , "r13", 256);
		ani_14 = Ani.to(this, 10 , "r14", 256);
		ani_15 = Ani.to(this, 10 , "r15", 256);
		//ani_12 = Ani.to(this, 10 , "r6", 256);
		f = createFont("標楷體", 20);// Arial, 16 point, anti-aliasing on
		r=0;
		g=0;
		b=0;
		
		/*ani_1.pause();
		ani_2.pause();
		ani_3.pause();
		ani_4.pause();
		ani_5.pause();
		ani_6.pause();
		ani_7.pause();
		ani_8.pause();
		ani_9.pause();
		ani_10.pause();
		ani_11.pause();*/
		/*ani_1.start();
		ani_2.start();
		ani_3.start();
		ani_4.start();
		ani_5.start();
		ani_6.start();
		ani_7.start();
		ani_8.start();
		ani_9.start();
		ani_10.start();
		ani_11.start();
		ani_12.start();
		ani_13.start();
		ani_14.start();
		ani_15.start();*/
		bg = loadImage(path +"paper.jpg");

	}
	//千呼萬喚始出來，猶抱琵琶半遮面(白居易-詩魔)
	public void draw(){
		
		background(255);
		image(bg, 0, 0, 800, 800);
			temp = begin + addition;
			fill(r, g, b);
			textFont(f,fontSize);
			text("千", 300,temp);
			temp = temp + addition;
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("呼", 300,temp);
			temp = temp + addition;
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("萬", 300,temp);
			temp = temp + addition;
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("喚", 300,temp);
			temp = temp + addition;
			
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("始", 300,temp);
			temp = temp + addition;
			
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("出", 300,temp);
			temp = temp + addition;
			
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("來", 300,temp);
			temp = temp + addition;
			
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("，", 300,temp);
			temp = temp + addition;
			
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("猶", 300,temp);
			temp = temp + addition;
			
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("抱", 300,temp);
			temp = temp + addition;
			
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("琵", 300,temp);
			temp = temp + addition;
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("琶", 300,temp);
			temp = temp + addition;
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("半", 300,temp);
			temp = temp + addition;
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("遮", 300,temp);
			temp = temp + addition;
			
			fill(r, g, b);
			textFont(f,fontSize);
			text("面", 300,temp);
			temp = temp + addition;
			
		
	}
	/*private void control_color(){
		if(r1 == 256){
			ani_2.start();
			System.out.println("ani_2.start();");
		}
		if(r2 == 256){
			ani_3.start();
			System.out.println("ani_3.start();");
		}
		if(r3 == 256){
			ani_4.start();
			System.out.println("ani_4.start();");
		}
		if(r4 == 256){
			ani_5.start();
			System.out.println("ani_5.start();");
		}
		if(r5 == 256){
			ani_6.start();
			System.out.println("ani_6.start();");
		}
		if(r6 == 256){
			ani_7.start();
			System.out.println("ani_7.start();");
		}
		if(r7 == 256){
			ani_8.start();
			System.out.println("ani_8.start();");
		}
		if(r8 == 256){
			ani_9.start();
			System.out.println("ani_9.start();");
		}
		if(r9 == 256){
			ani_10.start();
			System.out.println("ani_10.start();");
		}
		if(r10 == 256){
			ani_11.start();
			System.out.println("ani_11.start();");
		}
		
	}*/
	/*public void mousePressed(){ 
		ani_1.start();
		delay(500);
		ani_2.start();
		delay(500);
		ani_3.start();
		delay(500);
		ani_4.start();
		delay(500);
		ani_5.start();
		delay(500);
		ani_6.start();
		delay(500);
		ani_7.start();
		delay(500);
		ani_8.start();
		delay(500);
		ani_9.start();
		delay(500);
		ani_10.start();
		delay(500);
		ani_11.start();
	}*/
}
