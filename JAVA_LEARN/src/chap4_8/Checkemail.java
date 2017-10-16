package chap4_8;
import java.io.*;

public class Checkemail 
{ 
 public static boolean checkEmail(String email)
  {// 验证邮箱的正则表达式 
   String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
   if (email.matches(format))
    { 
     return true;// 邮箱名合法，返回true 
    }
   else
    {
     return false;// 邮箱名不合法，返回false
    }
  } 
 public static void main(String[] args) throws Exception
 {
  String email = "cc**365@163.com"; // 需要进行验证的邮箱
   while(true)
  {
    email = new BufferedReader(new InputStreamReader(System.in)).readLine();
   if (Checkemail.checkEmail(email))// 验证邮箱
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