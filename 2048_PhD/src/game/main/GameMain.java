package game.main;

import game.view.Window;
import javax.swing.*;
import java.awt.*;

public class GameMain {
    public static void main(String[] args) {
        Window win = new Window();
        win.initView();
        win.setTitle("2048 PhD");//命名
        win.getContentPane().setPreferredSize(new Dimension(800, 1000));//设置窗口尺寸
        win.getContentPane().setBackground(new Color(0xfaf8ef));//设置背景色
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使用 System exit 方法退出应用程序 
        win.setResizable(false); //去掉最大化按钮
        win.pack();    			 //获得最佳大小
        win.setVisible(true);    //显示界面
    }
}