package main.java;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Board extends JPanel{
	private JTextArea textArea;
	private Keyboard keyboard;
	private Safe safe;
	private Icon board;
	JLabel label;
	private StringBuilder inputCode;
	
	private Image show_number_0;
	private Image show_number_1;
	private Image show_number_2;
	private Image show_number_3;
	private Image show_number_4;
	private Image show_number_5;
	private Image show_number_6;
	private Image show_number_7;
	private Image show_number_8;
	private Image show_number_9;
	private Image show_number_10;//means nothing
	private Image temp;
	
	public Board(Keyboard k,Safe s){
		this.keyboard = k;
		this.safe = s;
		this.inputCode = new StringBuilder();
		this.textArea = new JTextArea("");
		loadImage();
		this.add(textArea);
		//loadImage();
		//label = new JLabel("123", board, JLabel.CENTER);
		//label.setText("123");
		//this.add(label);
	}
	private void loadImage(){
		board = new ImageIcon("Image/key/0.png");
		
		show_number_0 = new ImageIcon("Image/show/show_0.png").getImage();
		show_number_1 = new ImageIcon("Image/show/show_1.png").getImage();
		show_number_2 = new ImageIcon("Image/show/show_2.png").getImage();
		show_number_3 = new ImageIcon("Image/show/show_3.png").getImage();
		show_number_4 = new ImageIcon("Image/show/show_4.png").getImage();
		show_number_5 = new ImageIcon("Image/show/show_5.png").getImage();
		show_number_6 = new ImageIcon("Image/show/show_6.png").getImage();
		show_number_7 = new ImageIcon("Image/show/show_7.png").getImage();
		show_number_8 = new ImageIcon("Image/show/show_8.png").getImage();
		show_number_9 = new ImageIcon("Image/show/show_9.png").getImage();
		show_number_10 = new ImageIcon("Image/show/show_10.png").getImage();
		
		temp = new ImageIcon().getImage();
		/*try {
			board = ImageIO.read(new File("Image/key/board.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	public void set_text(StringBuilder inputCode){
		textArea.setText(inputCode.toString());
	}
	public String get_text(){
		return textArea.getText();
	}
	public JTextArea get_textArea(){
		return textArea;
		
	}
	 /*@Override
	    protected void paintComponent(Graphics g) {
	        g.drawImage(board,0,0,null);
	        super.paintComponent(g);
	    }*/
	private Image get_inputCode_Image(char inputChar){
		if(inputChar == '0'){
			return show_number_0;
		}else if(inputChar == '1'){
			return show_number_1;
		}else if(inputChar == '2'){
			return show_number_2;
		}else if(inputChar == '3'){
			return show_number_3;
		}else if(inputChar == '4'){
			return show_number_4;
		}else if(inputChar == '5'){
			return show_number_5;
		}else if(inputChar == '6'){
			return show_number_6;
		}else if(inputChar == '7'){
			return show_number_7;
		}else if(inputChar == '8'){
			return show_number_8;
		}else if(inputChar == '9'){
			return show_number_9;
		}else{
			return show_number_10;
		}
		
	}
	public void update(StringBuilder in){
		this.inputCode = in;
	}
	
	protected void paintComponent(Graphics g) {
       // super.paintComponent(g);
		System.out.println("Board inputCode:" + inputCode);
        for(int i=0;i<safe.getPassword().length();i++){
        	if(i<inputCode.length()){
        		temp = get_inputCode_Image(inputCode.toString().charAt(i));
        		System.out.println("get_inputCode_Image:" + inputCode.toString().charAt(i));
            	g.drawImage((Image) temp, i*30, 0, 35, 80, null);
        	}else{
        		temp = show_number_10;
        		g.drawImage((Image) temp, i*30, 0, 35, 80, null);
        		System.out.println("paintComponent");
        	}
        	
        }
    }
	
}
