package chap8_5;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Draw {
public static void main (String [] args){
    JFrame window = new DrawFrame();
    window.setTitle("Draw the picture");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setBounds(100,100,600,400);
    window.setVisible(true);
}
}

class DrawFrame extends JFrame{
    public DrawFrame(){
        add(new DrawComponent());
        pack();
    }
}

class DrawComponent extends JComponent{
    private static final int DEAFULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        double leftx = 100;
        double topy = 100;
        double width = 200;
        double height = 150;
        Rectangle2D rect = new Rectangle2D.Double(leftx,topy ,width,height);
        g2.draw(rect);

        double centerx = rect.getCenterX();
        double centery = rect.getCenterY();
        double radius = 150;
        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerx, centery,centerx+radius,centery+radius);
        g2.draw(circle);
    }
}