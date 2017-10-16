package chap8_14;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JFrame{
	private static int m;
	private static int[][] x = new int[10][25];
	private static int[][] y = new int[10][25];
	private static int[] n = new int[25];
	public DrawPanel(){
		this.setBounds(100, 200, 300, 300);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawPanel t = new DrawPanel();
		JPanel jp = new JPanel(){
			public void paint(Graphics g){
				g.clearRect(0, 0, this.getWidth(), this.getHeight());
				for(int i = 0;i<=m;i++){
					for(int j = 1;j<=n[i];j++){
						g.drawLine(x[i][j], y[i][j], x[i][j+1], y[i][j+1]);
					}
				}
			}
		};
		jp.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				n[m]++;
				x[m][n[m]] = e.getX();
				y[m][n[m]] = e.getY();
				x[m][n[m]+1] = x[m][n[m]];
				y[m][n[m]+1] = y[m][n[m]];
				if((x[m][n[m]]-x[m][1])*(x[m][n[m]]-x[m][1])+(y[m][n[m]]-y[m][1])*(y[m][n[m]]-y[m][1])<=16&&n[m]>1){
					x[m][n[m]] = x[m][1];
					y[m][n[m]] = y[m][1];
					m++;
				}
				jp.repaint();
			}
			
		});
		jp.addMouseMotionListener(new MouseMotionAdapter(){

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				x[m][n[m]+1] = e.getX();
				y[m][n[m]+1] = e.getY();
				jp.repaint();
			}
			
		});
		t.getContentPane().add(jp, BorderLayout.CENTER);
		t.setVisible(true);
	}
}
