package main.java;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Safe extends JPanel implements MouseMotionListener,ActionListener,MouseListener{
	private String password;
	public Icon safe_close;
	public Icon safe_nomove;
	public Icon safe_open;
	public Icon safe_screen_open;
	private int centerX;
	private int centerY;
	private JLabel lb;
	JLabel label ;
	
	private Keyboard keyboard;
	
	public Safe(String password){
		this.password = password;
		loadImage();
		lb = new JLabel();
		lb.setIcon(this.safe_nomove);
		this.setLayout(null);
		lb.setBounds(100, 100, 500, 500);
		
		keyboard = new Keyboard(this);
		
		this.add(lb);
		lb.setVisible(true);
		lb.addMouseListener(new MouseAdapter() 
		{
		    @Override
		    public void mouseClicked(MouseEvent e) 
		    {
		        //statement    
		    	System.out.println("click safe");
		    	keyboard.setVisible(true);
		    	
		    }
		});
		
		label = new JLabel("Hello StackOverflow!");//Make a label
		this.add(label);//Add it to the panel (which is on the frame)
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
	public void set_safe_Image(Icon image){
		lb.setIcon(image);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		centerX = e.getX();
		centerY = e.getY();
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
