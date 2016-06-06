package main.java;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import io.socket.emitter.Emitter;

public class Key extends JPanel implements ActionListener{
	
	private Keyboard keyboard;
	private Board board;
	private JTextArea board_textArea;
	private Item safe;
	//private ArrayList<Character> inputCode; 
	private StringBuilder inputCode;
	private char end = 'e';
	private String path = "main/resources/";
	Clip press;
	Clip close;
	Clip cheer;
	
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
	
	
	
	private boolean safe_status = false;
	
	public Key(Keyboard k, Board b,Item i,boolean safe_s){
		this.keyboard = k;
		this.board = b;
		this.safe = i;
		this.safe_status =  safe_s;
		//this.board_textArea = board.get_textArea();
		inputCode = new StringBuilder("");
		loadImage();
		loadButton();
		initial();
		//this.setLayout(new GridLayout(4, 3));
		addButton();
		this.setVisible(true);
		 try {       
			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
      	        
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
		 try {       
				close = MusicPlay.getMusic("src/" + path + "Sounds/close.wav");
	     	        
			    } catch(Exception ex) {
			        System.out.println("Error with playing sound.");
			        ex.printStackTrace();
			    }
		Main.socket.on("safeopen", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
				  
			  }

		});
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
        	 try {       
    			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
          	        
    		    } catch(Exception ex) {
    		        System.out.println("Error with playing sound.");
    		        ex.printStackTrace();
    		 }
        	inputCode.append("0");
        	press.start();
        }else if(e.getSource() == button_1){
        	try {       
   			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
         	        
   		    } catch(Exception ex) {
   		        System.out.println("Error with playing sound.");
   		        ex.printStackTrace();
   		    }
        	inputCode.append("1");
        	press.start();
        }else if(e.getSource() == button_2){
        	try {       
   			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
         	        
   		    } catch(Exception ex) {
   		        System.out.println("Error with playing sound.");
   		        ex.printStackTrace();
   		    }
        	inputCode.append("2");
        	press.start();
        }else if(e.getSource() == button_3){
        	try {       
   			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
         	        
   		    } catch(Exception ex) {
   		        System.out.println("Error with playing sound.");
   		        ex.printStackTrace();
   		    }
        	inputCode.append("3");
        	press.start();
        }else if(e.getSource() == button_4){
        	try {       
      			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
            	        
      		    } catch(Exception ex) {
      		        System.out.println("Error with playing sound.");
      		        ex.printStackTrace();
      		    }
        	inputCode.append("4");
        	press.start();
        }else if(e.getSource() == button_5){
        	try {       
      			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
            	        
      		    } catch(Exception ex) {
      		        System.out.println("Error with playing sound.");
      		        ex.printStackTrace();
      		    }
        	inputCode.append("5");
        	press.start();
        }else if(e.getSource() == button_6){
        	try {       
      			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
            	        
      		    } catch(Exception ex) {
      		        System.out.println("Error with playing sound.");
      		        ex.printStackTrace();
      		    }
        	inputCode.append("6");
        	press.start();
        }else if(e.getSource() == button_7){
        	try {       
      			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
            	        
      		    } catch(Exception ex) {
      		        System.out.println("Error with playing sound.");
      		        ex.printStackTrace();
      		    }
        	inputCode.append("7");
        	press.start();
        }else if(e.getSource() == button_8){
        	try {       
      			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
            	        
      		    } catch(Exception ex) {
      		        System.out.println("Error with playing sound.");
      		        ex.printStackTrace();
      		    }
        	inputCode.append("8");
        	press.start();
        }else if(e.getSource() == button_9){
        	try {       
      			 press = MusicPlay.getMusic("src/" + path + "Sounds/press.wav");
            	        
      		    } catch(Exception ex) {
      		        System.out.println("Error with playing sound.");
      		        ex.printStackTrace();
      		    }
        	inputCode.append("9");
        	press.start();
        }else if(e.getSource() == button_10){
        	press.start();
        	inputCode.deleteCharAt(inputCode.length()-1);
        }else if(e.getSource() == button_11){
        	press.start();
         	//System.out.println(board.get_text());
        	System.out.println(keyboard.getPassword())
        	;
        	if(inputCode.toString().equals(keyboard.getPassword())|| safe_status == true){
        		try {       
    				cheer = MusicPlay.getMusic("src/" + path + "Sounds/cheers.wav");
    	     	        
    			    } catch(Exception ex) {
    			        System.out.println("Error with playing sound.");
    			        ex.printStackTrace();
    			 }
        		cheer.start();
        		safe.updateImage("safe_open.png", "safe_open.png", "safe_open.png");
        		((MusicPuzzleApplet)keyboard.safe.parent).safe_status = true;
        		((MusicPuzzleApplet)keyboard.safe.parent).showkey();
        		keyboard.dispose();
        		Main.socket.emit("safeopen","data");
        	}else{
        		safe.updateImage("safe_close.png", "safe_close.png", "safe_close.png");
        		try {       
    				close = MusicPlay.getMusic("src/" + path + "Sounds/close.wav");
    	     	        
    			    } catch(Exception ex) {
    			        System.out.println("Error with playing sound.");
    			        ex.printStackTrace();
    			 }
        		close.start();
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
