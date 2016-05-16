package main.java;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	private final static int windowWidth = 1303, windowHeight = 745;
	public static JFrame window;
	public static void main(String [] args){
		
		window = new JFrame("±K«Ç°k²æ");
		//MainApplet applet = new MainApplet(window);
		LoginApplet applet = new LoginApplet(window);
		applet.init();
		applet.start();
		applet.setFocusable(true);
		window.setContentPane(applet);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(windowWidth, windowHeight);
		window.setVisible(true);
	}
}
