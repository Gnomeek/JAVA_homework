package chap4_8;
import java.io.*;

public class Checkemail 
{ 
 public static boolean checkEmail(String email)
  {// ��֤�����������ʽ 
   String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
   if (email.matches(format))
    { 
     return true;// �������Ϸ�������true 
    }
   else
    {
     return false;// ���������Ϸ�������false
    }
  } 
 public static void main(String[] args) throws Exception
 {
  String email = "cc**365@163.com"; // ��Ҫ������֤������
   while(true)
  {
    email = new BufferedReader(new InputStreamReader(System.in)).readLine();
   if (Checkemail.checkEmail(email))// ��֤����
   {   
    System.out.println(email+" is legal");
   }
   else
   {
    System.out.println(email+" is not legal");
   }
  }
 }
}