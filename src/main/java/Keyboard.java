package main.java;
import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Keyboard extends JFrame{
	
	public Item safe;
	private Board board;
	private Key key;
	//private Safe safe;
	private int keyInput;
	private String password;
	public Icon safe_close;
	public Icon safe_nomove;
	public Icon safe_open;
	public Icon safe_screen_open;
	private boolean safe_status;
	

	
	//private J
	
	public Keyboard(Item i,String p,boolean safe_s){
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 300);
		this.setLayout(null);
		this.safe = i;
		this.password = p;
		this.safe_status = safe_s;
		//keyboard = new JPanel();
		//keyboard.setLayout(null);
		//keyboard.add(board);
		board = new Board(this,safe);
		board.setBounds(0, 0, 150, 200);
		this.add(board);
		key = new Key(this,board,safe,safe_s);
		key.setBounds(0, 80, 150, 150);
		this.add(key);
		
		//this.setVisible(true);
		
	}
	private void loadImage(){
		safe_close =  new ImageIcon("Image/safe_close.png");
		safe_nomove =  new ImageIcon("Image/safe_nomove.png");
		safe_open =  new ImageIcon("Image/safe_open.png");
		safe_screen_open =  new ImageIcon("Image/safe_screen_open.png");
		System.out.println("loadImage");
	}
	public String getPassword(){
		return password;
	}
	
}
