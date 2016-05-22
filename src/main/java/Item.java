package main.java;

import java.awt.Color;

import controlP5.Button;
import controlP5.CColor;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PImage;
/*
 * 用法new Item (this , x座標, y座標 , 圖片的檔名(全部放在main/resources/裡不用加路徑) , 滑鼠放到物件上時的圖片, 點擊時的圖片)
 * {
 * public void controlEvent(CallbackEvent theEvent) {
 * 			if (theEvent.getAction() == 100) {//每個動作有不同的action code點擊是100
 *                  callfunction()
 * 			}
 * }
 * public callfunction()
 * {
 * 
 * }
 * }
 * 
 * 也可以新建一個class然後在裡面實做controlEvent 和callfunction
 */



public class Item extends PApplet implements CallbackListener{
	public ControlP5 controlP5;
	public ControlP5 solControlP5;
	public Button btnControlP5; //need to update its size, so declare it explicitly
	private String path = "main/resources/";
	public PImage imgItem;
	public PImage imgItemHover;
	public PImage imgItemOnclick;
	public PImage imgSol;
	public int x, y;
	
	public boolean isInBox; //become true when putinItem() called
	public int colIndex; //become 0~7 when putinItem() called
	public boolean isHolded; //become true when checkItem() called
	public Type type; //TOOL or MESSAGE 
       
//called if the item's type isn't TOOL
public Item (PApplet parent ,int sizeX, int sizeY, int x , int y ,  String sImgItem , String sImgItemHover , String sImgItemOnclick,  Type type) {
		
		imgItem = loadImage(path+sImgItem);
		imgItemHover = loadImage(path+sImgItem);
		imgItemOnclick = loadImage(path+sImgItem);   
		imgItem.resize(sizeX, sizeY);
		imgItemHover.resize(sizeX, sizeY);
		imgItemOnclick.resize(sizeX, sizeY);
		
		this.x = x;
		this.y = y;
		
		this.isInBox = false;
		this.colIndex = -1;
		this.isHolded = false;
		this.type = type;
		
		
		setName(sImgItem.substring(0, sImgItem.indexOf('.')));
		//parent.image(image, x, y);
		
		controlP5 = new ControlP5(parent);
//		   controlP5.addButton(this.getName())
//	       .setPosition(this.x,this.y)
//	       .setImages(imgItem, imgItemHover, imgItemOnclick)
//	       .updateSize()
//	       .addCallback(this);
		btnControlP5 = controlP5.addButton(this.getName());
		btnControlP5.setPosition(this.x,this.y)
	       .setImages(imgItem, imgItemHover, imgItemOnclick)
	       .updateSize()
	       .addCallback(this);
	   	if(type == Type.MESSAGE){
	   			
	   	}
	}
	
//called when the item's type is TOOL
	public Item (PApplet parent ,int sizeX, int sizeY, int x , int y ,  String sImgItem , String sImgItemHover , String sImgItemOnclick, int solSizeX, int solSizeY, int solX, int solY, String sImgSol, Type type) {
		this(parent ,sizeX, sizeY, x , y , sImgItem , sImgItemHover , sImgItemOnclick,  type);
	
		if(type == Type.TOOL){
			imgSol = loadImage(path+sImgSol); 
			imgSol.resize(solSizeX, solSizeY);
			
			solControlP5 = new ControlP5(parent);
   	   		solControlP5.addButton("sol" + this.getName())
//   	   		.setVisible(false)
//   	   		.setColor((new CColor()))
   	   			.setImages(imgSol, imgSol, imgSol)
   	   			.setPosition(solX,solY)
   	   			.updateSize() //important in interaction
   	   			.addCallback(this);
		}
	}	
       
	public void controlEvent(CallbackEvent theEvent) {
           if (theEvent.getAction() == 100) {
//          	 System.out.println(this.getName());
           }
    }
	
	public void updateImage(String string , String string2 , String string3){
		imgItem = loadImage(path+string);
		imgItemHover = loadImage(path+string2);
		imgItemOnclick = loadImage(path+string3); 
		controlP5.addButton(this.getName())
	       .setPosition(this.x,this.y)
	       .setImages(imgItem, imgItemHover, imgItemOnclick)
	       .updateSize()
	       .addCallback(this);
	}
}
