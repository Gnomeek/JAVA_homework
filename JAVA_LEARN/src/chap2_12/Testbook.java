package chap2_12;

public class Testbook {      
	public static void main(String[] args){      
		Book book1=new Book();        
		book1.setBook("C Primer Plus"," Stephen Prata",1000); 
		book1.printBook();       
		Book book2=new Book("C++ Primer"," Stanley B.",500);      
		book2.printBook();}}