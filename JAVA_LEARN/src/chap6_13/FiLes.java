package chap6_13;
import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

public class FiLes {
	public static void main(String[] args)throws Exception{
		System.out.println("Enter location£º");
		Scanner sc=new Scanner(System.in);
        String fileName=sc.nextLine();
		File file=new File(fileName);
		boolean bl=file.exists();
		if(bl){
			fileDir(file);
		}else 
		{
			System.out.println("DONT EXIST.re enter£º");
	}}
	
	
	public static void fileDir(File dir){
		
		File[] files=dir.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				fileDir(file);
			}
			if(file.getName().endsWith(".java")){
				System.out.println("Name "+file.getName());
				System.out.println("Location "+file.getAbsolutePath());
				System.out.println("Size "+file.length()+"bytes");
			    System.out.println("Edit time "+file.lastModified());
			}
		}
	}}
