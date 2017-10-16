package chap9_13;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Fontsize extends JFrame implements MouseWheelListener{
	private JLabel label;
	private int x=20;
	
	public Fontsize(){
		label=new JLabel("ÕÔ",JLabel.CENTER);
		label.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,x));
		this.add(label);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setVisible(true);
		label.addMouseWheelListener(this); }
		      
		public void mouseWheelMoved(MouseWheelEvent e) { 
			// TODO Auto-generated method stub 
			x= x+e.getWheelRotation(); 
			label.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,x));	
			this.repaint();  //Ë¢ÐÂÆÁÄ» 
	}
	public static void main(String[] args){
		new Fontsize();
	}


}