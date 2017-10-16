package chap9_12;

import javax.swing.*;

import java.awt.*;   //导入必要的包

public class Login extends JFrame{
    JTextField jTextField;
    JPasswordField jPasswordField;
    JLabel jLabel1,jLabel2;
    JPanel jp1,jp2,jp3;
    JButton jb1,jb2;
    public Login(){
        jTextField = new JTextField(12);
        jPasswordField = new JPasswordField(13);
        jLabel1 = new JLabel("Username");
        jLabel2 = new JLabel("Password");
        jb1 = new JButton("Enter");
        jb2 = new JButton("Cancel");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        
        this.setLayout(new GridLayout(3,1));
        
        jp1.add(jLabel1); 
        jp1.add(jTextField);
        
        jp2.add(jLabel2);
        jp2.add(jPasswordField);
        
        jp3.add(jb1);
        jp3.add(jb2); 
        
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);  

        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Log In");
         
    }
    public static void main(String[] args){
        new Login();
    }
}