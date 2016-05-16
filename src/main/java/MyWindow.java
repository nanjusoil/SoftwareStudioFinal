package main.java;
import javax.swing.JFrame;

public class MyWindow extends JFrame{
private Safe safe;
private String password = "12345";
	
	public MyWindow(){
		safe = new Safe(password);
		
		// Add JPanel to JFrame
		this.add(safe);
	}
}
