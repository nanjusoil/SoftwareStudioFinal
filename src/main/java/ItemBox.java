package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlP5;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.Event;
import processing.event.MouseEvent;


public class ItemBox extends PApplet implements CallbackListener{
	//five properties in the property box
	private PApplet parent; //use MainApplet to declare so that the variable can be accessed
	private PImage box;
	private int x, y, width, height; //location and size
	private int cellWidth, cellHeight;
	private final int numCells = 8;
	private List<Item> items = Arrays.asList(new Item[numCells]);
	private ControlP5 controlP5;
	private String path = "main/resources/";
	private final int itemboxWidth = 86;
	private int indexActiveBox;
	
	public ItemBox(PApplet parent, int x, int y, String imgItemBox){
		this.parent = parent;
		this.box = this.loadImage(path + imgItemBox);
		this.box.resize(itemboxWidth, 700);
		this.setName("ItemBox");
		this.x = x;
		this.y = y;
		this.width = this.box.width;
		this.height = this.box.height;
		this.cellWidth = this.width;
		this.cellHeight = this.height/this.numCells;
		
//		System.out.println("size:" + items.size());
		for(int i = 0; i < this.items.size(); i++){
			this.items.set(i, null);
		}
		
		controlP5 = new ControlP5(parent);
		controlP5.addButton(this.getName())
	       .setPosition(x,y)
	       .setImages(this.box, this.box, this.box)
	       .updateSize()
	       .addCallback(this);
 		indexActiveBox = -1;
	}
	
//	public void display(){
//		this.parent.image(this.box, this.x, this.y);
//		Item p;
//		for(int i = 0; i< items.size(); i++){
//			p = items.get(i);
//			if(p!=null){
//				this.parent.image(p.imgItem, this.x - this.cellWidth/2, this.y + this.cellHeight*i - this.height/2);
//			}
//		}
//	}
	
	//player click on a property, then the property shows in the box automatically
	public void putinItem(Item initem){
		for(int i = 0; i< items.size(); i++){
			if(items.get(i)==null){//find first empty cell in the box
				int max = Math.max(initem.imgItem.width, initem.imgItem.height);
				//fit into the cell
				int neww, newh;
				neww = initem.imgItem.width*(cellWidth*8/10)/max;
				newh = initem.imgItem.height*(cellWidth*8/10)/max;

//				initem.imgItem.resize(neww,newh);
//				initem.imgItemHover.resize(neww,newh);
//				initem.imgItemOnclick.resize(neww,newh);
//				//the size of button muse be updated correspondingly
//				initem.btnControlP5.updateSize();
				items.set(i, initem);
				initem.isInBox = true;
				initem.colIndex = i;
				
				initem.updateImage(neww, newh, this.x+10, this.y +10 + this.cellHeight*i);
//				initem.controlP5.setPosition(this.x-initem.x+10, this.y - initem.y+10 + this.cellHeight*i);
//				this.parent.image(initem.imgItem, this.x - this.cellWidth/2, this.y + this.cellHeight*i - this.height/2);
				break;
			}
		}
	}
	
	//property become ready to be used or show its content
	public void checkItem(Item citem, ArrayList<Item> itemArr){
		
		/*the item falls into one of the two groups
		 * TOOL: it can be used, 
 		 * MESSAGE:  shows hints and can't be used
		 */
		
		/*group one : the color of cell's border becomes deeper
		 * meaning that the property is ready to be used
		 */
		if(citem.type == Type.TOOL){
			//can only check one item at a time
			int cindex = itemArr.indexOf(citem);
			for(int i = 0;i<itemArr.size(); i++){
				if(i!=cindex){
					itemArr.get(i).isHolded = false;
				}else{
					itemArr.get(i).isHolded = true;
				}
			}
			//the holded item is in which cells in the box
			this.indexActiveBox = citem.colIndex;
			//make the cell's border become deeper
//			this.parent.rect(this.x-citem.x, this.y + citem.colIndex*this.cellHeight, this.cellWidth, this.cellHeight, 20);
//			this.parent.rect(20,20,20,20);
		}else if(citem.type == Type.MESSAGE){
		//group two : show its content
//			JOptionPane.showMessageDialog(parent, "Here's the hint.");
//			//still can't show picture in the dialog box?
//			JDialog dialog = new JDialog();
//			
//			dialog.setBounds(this.parent.getWidth()/2 - 150, this.parent.getHeight()/2 - 100, 300, 200); 
//			dialog.setVisible(true);
////			dialog.setUndecorated(true);
//			JLabel label = new JLabel( new ImageIcon(path + "key.png") );
//			label.setSize(100, 100);
//			label.setVisible(true);
//			dialog.add( label );
//			dialog.pack();
//			dialog.setSize(300, 200); 
		}
	
	}
	
	/*if the mouse click on the right place(where item can be used),
	 * the item won't show in the itemBox
	 * It return the item clicked
	 */
	//list of items in MainApplet
	//pass in item's index, return 
	public Item useItem(Item uitem){
		Item img = items.get(uitem.colIndex);
		items.set(uitem.colIndex, null);
//		uitem.controlP5.setVisible(false);
		uitem.isHolded = false;
		uitem.isInBox = false;
		return uitem;
	}

	@Override
	public void controlEvent(CallbackEvent theEvent) {
		if (theEvent.getAction() == 100) {
          }
	}
}
