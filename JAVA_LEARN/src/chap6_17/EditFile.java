package chap6_17;
import java.io.*;
import java.util.Scanner;

public class EditFile {
      private static String message="";    	  
      public static void main(String[] args){
          try {
			editFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      
      private static void editFile()throws Exception{
    	  System.out.println("Input files");
    	  Scanner sc=new Scanner(System.in);
    	  String filename=sc.nextLine();
    	  File file=new File(filename);
    	  if(file!=null&&!(filename.endsWith(".txt")))
    			  {
    		  System.out.println("plz enter right location");
    		  return;
    			  }
    	  System.out.println("enter what to edit,"
    			  +"stop edit to input \"stop\":");
    	  String inputMessage="";
    	  while(!inputMessage.equals("stop")){
    		   inputMessage=sc.nextLine();
    		   if(inputMessage!=null&&inputMessage.length()>0){
    			   String[] editMessage=inputMessage.split(":");
    			   if(editMessage!=null&&editMessage.length>1){
    				   FileReader in=new FileReader(file);
    			    	  char[] charArray=new char[1024];
    			    	  int len=0;
    			    	  StringBuffer sb=new StringBuffer();
    			    	  while((len=in.read(charArray))!=-1){
    			    		  sb.append(charArray);
    			    	  }
    			    	  message=sb.toString();
    			    	  in.close();
    				   message=message.replace(editMessage[0],editMessage[1]);
    				   System.out.println("Success");
    	    			 
    			   }
    			   
    		   }
    	  }
    	 
    	  System.out.println("after edit is :"+message);
      
    	  
      }
}
