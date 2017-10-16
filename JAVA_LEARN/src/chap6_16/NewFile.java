package chap6_16;
import java.io.*;
import java.util.*;

public class NewFile{
	public static void main(String[] args)throws Exception{
		System.out.println("Enter new file's name:");
		Scanner sc=new Scanner(System.in);
		FileWriter writer=new FileWriter("D:\\"+sc.nextLine()+".txt");
		
		BufferedWriter bw=new BufferedWriter(writer);
		String line;
		while((line=sc.nextLine()).endsWith("over")==false)
		{
			bw.write(line);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		
	}
	

}
