package main.java;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Key extends JPanel implements ActionListener{
	
	private Keyboard keyboard;
	private Board board;
	private JTextArea board_textArea;
	private Item safe;
	//private ArrayList<Character> inputCode; 
	private StringBuilder inputCode;
	private char end = 'e';
	
	private int numberOfButton = 12;
	private Icon number_0;
	private Icon number_1;
	private Icon number_2;
	private Icon number_3;
	private Icon number_4;
	private Icon number_5;
	private Icon number_6;
	private Icon number_7;
	private Icon number_8;
	private Icon number_9;
	private Icon number_10;
	private Icon number_11;

	
	private JButton button_0;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	
	
	
	public Key(Keyboard k, Board b,Item i){
		this.keyboard = k;
		this.board = b;
		this.safe = i;
		//this.board_textArea = board.get_textArea();
		inputCode = new StringBuilder("");
		loadImage();
		loadButton();
		initial();
		//this.setLayout(new GridLayout(4, 3));
		addButton();
		this.setVisible(true);
	}
	
	private void loadImage(){
		number_0 = new ImageIcon("Image/key/0.png");
		number_1 = new ImageIcon("Image/key/1.png");
		number_2 = new ImageIcon("Image/key/2.png");
		number_3 = new ImageIcon("Image/key/3.png");
		number_4 = new ImageIcon("Image/key/4.png");
		number_5 = new ImageIcon("Image/key/5.png");
		number_6 = new ImageIcon("Image/key/6.png");
		number_7 = new ImageIcon("Image/key/7.png");
		number_8 = new ImageIcon("Image/key/8.png");
		number_9 = new ImageIcon("Image/key/9.png");
		number_10 = new ImageIcon("Image/key/11.png");
		number_11 = new ImageIcon("Image/key/hang.png");
		
		
	}
	
	private void loadButton(){
		button_0 = new JButton(number_0);
		button_1 = new JButton(number_1);
		button_2 = new JButton(number_2);
		button_3 = new JButton(number_3);
		button_4 = new JButton(number_4);
		button_5 = new JButton(number_5);
		button_6 = new JButton(number_6);
		button_7 = new JButton(number_7);
		button_8 = new JButton(number_8);
		button_9 = new JButton(number_9);
		button_10 = new JButton(number_10);
		button_11 = new JButton(number_11);
		
		this.button_0.addActionListener(this);
		this.button_1.addActionListener(this);
		this.button_2.addActionListener(this);
		this.button_3.addActionListener(this);
		this.button_4.addActionListener(this);
		this.button_5.addActionListener(this);
		this.button_6.addActionListener(this);
		this.button_7.addActionListener(this);
		this.button_8.addActionListener(this);
		this.button_9.addActionListener(this);
		this.button_10.addActionListener(this);
		this.button_11.addActionListener(this);
	}
	
	private void addButton(){

		this.add(button_0);
		this.add(button_1);
		this.add(button_2);
		this.add(button_3);
		this.add(button_4);
		this.add(button_5);
		this.add(button_6);
		this.add(button_7);
		this.add(button_8);
		this.add(button_9);
		this.add(button_10);
		this.add(button_11);
	}
	
	private void initial(){
		this.setLayout(new GridLayout(4, 3));
	}
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button_0){
        	inputCode.append("0");
        }else if(e.getSource() == button_1){
        	inputCode.append("1");
        }else if(e.getSource() == button_2){
        	inputCode.append("2");
        }else if(e.getSource() == button_3){
        	inputCode.append("3");
        }else if(e.getSource() == button_4){
        	inputCode.append("4");
        }else if(e.getSource() == button_5){
        	inputCode.append("5");
        }else if(e.getSource() == button_6){
        	inputCode.append("6");
        }else if(e.getSource() == button_7){
        	inputCode.append("7");
        }else if(e.getSource() == button_8){
        	inputCode.append("8");
        }else if(e.getSource() == button_9){
        	inputCode.append("9");
        }else if(e.getSource() == button_10){
        	inputCode.deleteCharAt(inputCode.length()-1);
        }else if(e.getSource() == button_11){
         	//System.out.println(board.get_text());
        	System.out.println(keyboard.getPassword())
        	;
        	if(inputCode.toString().equals(keyboard.getPassword())){
        		safe.updateImage("safe_open.png", "safe_open.png", "safe_open.png");
        	}else{
        		safe.updateImage("safe_close.png", "safe_close.png", "safe_close.png");
        	}
        	initial();
        }
        if(inputCode.toString().length()>keyboard.getPassword().length()){
        	//inputCode.setCharAt(inputCode.length()-1, end);
        	inputCode.deleteCharAt(inputCode.length()-1);
        }
        board.update(inputCode);
        //board.set_text(inputCode);
       //System.out.println("Key inputCode:" + inputCode);
        board.repaint();
    }
	
	
	


}
