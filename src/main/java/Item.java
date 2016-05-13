package main.java;

import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlP5;
import processing.core.PApplet;
/*
 * �Ϊknew Item (this , x�y��, y�y�� , �Ϥ����ɦW(������bmain/resources/�̤��Υ[���|) , �ƹ���쪫��W�ɪ��Ϥ�, �I���ɪ��Ϥ�)
 * {
 * public void controlEvent(CallbackEvent theEvent) {
 * 			if (theEvent.getAction() == 100) {//�C�Ӱʧ@�����P��action code�I���O100
 *                  callfunction()
 * 			}
 * }
 * public callfunction()
 * {
 * 
 * }
 * }
 * 
 * �]�i�H�s�ؤ@��class�M��b�̭��갵controlEvent �Mcallfunction
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
