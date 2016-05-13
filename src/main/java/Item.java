package main.java;

import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlP5;
import processing.core.PApplet;
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
	private ControlP5 controlP5;
	private String path = "main/resources/";
       
	public Item (MainApplet parent , int x , int y , String imgItem , String imgItemHover , String imgItemOnclick) {
    	   controlP5 = new ControlP5(parent);
   		   controlP5.addButton(this.getName())
	       .setPosition(x,y)
	       .setImages(loadImage(path+imgItem), loadImage(path+imgItemHover) , loadImage(path+imgItemOnclick))
	       .updateSize()
	       .addCallback(this);
    }
       
	public void controlEvent(CallbackEvent theEvent) {
           if (theEvent.getAction() == 100) {
          	 System.out.println(this.getName());
           }
    }
}
