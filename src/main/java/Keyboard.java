package main.java;
import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Keyboard extends JFrame{
	
	private Board board;
	private Key key;
	private Safe safe;
	private int keyInput;

	
	//private J
	
	public Keyboard(Safe s){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setLayout(null);
		this.safe = s;
		//keyboard = new JPanel();
		//keyboard.setLayout(null);
		//keyboard.add(board);
		board = new Board(this,safe);
		board.setBounds(0, 0, 150, 200);
		this.add(board);
		key = new Key(this,board,safe);
		key.setBounds(0, 80, 150, 150);
		this.add(key);
		
		//this.setVisible(true);
		
	}
	
	
}
