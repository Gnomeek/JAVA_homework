package chap2_12;

public class Book{  
	String title;  
	String author;  
	int sell;   
	Book(){}      
	Book(String str1,String str2,int num){      
		title=str1;      
		author=str2;      
		sell=num;}      
	public void setBook(String str1,String str2,int num){          
		title=str1;      
		author=str2;      
		sell=num;}      
	public void printBook(){      
		System.out.println("title£º"+title);      
		System.out.println("author£º"+author);      
		System.out.println("sell£º"+sell);}}    