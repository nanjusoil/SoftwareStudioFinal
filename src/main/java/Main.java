package main.java;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	private final static int windowWidth = 1303, windowHeight = 745;
	public static JFrame window;
	private static Socket socket;
	public static String sockettext = "acascascsacsacascacsacsacsacsacascsacsacsacsacsac";
	public static void main(String [] args) throws URISyntaxException{
		
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
		socket = IO.socket("http://localhost:3001");
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

		  @Override
		  public void call(Object... args) {
			System.out.println("on");
		  }

		}).on("message", new Emitter.Listener() {

		  @Override
		  public void call(Object... args) {
			  JSONObject jsonobject = (JSONObject)args[0];
			  sockettext = jsonobject.getString("hello");
			  System.out.println(sockettext);
		  }

		}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

		  @Override
		  public void call(Object... args) {System.out.println("off");}

		});
		socket.connect();

	}
}
