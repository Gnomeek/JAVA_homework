package chap7_1;
import java.util.*;

public class Stu {
	class Student { 
		
	    String id;
		String name;
		String gender;
		int birtdh;
		
		Student(String id,String name,String gender,int birtdh){
			this.id=id;
			this.name=name;
			this.gender=gender;
			this.birtdh=birtdh;
			}
		
		public boolean equals(Object a){
			if (a==null||id==null) {
	            return false;
	        }
	        if (a instanceof Student) {
	            Student b = (Student) a;
	            return id.equals(b.id);
	        }
	        return false;
	    }
	}
	public static void main(String[] args) {
		Stu c=new Stu();
		Scanner s = new Scanner(System.in);
		Student[] stu = new Student[4];
		ArrayList<Student> stus = new ArrayList<Student>();                              
		System.out.println("Enter four students' id name gender and birthday£º");
	
		for (int i = 0; i < 4; i++) {
			stu[i] = c.new Student(s.next(),s.next(),s.next(),s.nextInt());
			stus.add(stu[i]);}
		System.out.println("still having" + stus.size() + "£ºstudents here");
	
		for (int i = 0; i < stus.size(); i++) { 
		System.out.println("No." + i + "student: " + stus.get(i).name);}
	
		System.out.println("Enter the you look for student's id name gender and birthday£º");
		Student d = c.new Student(s.next(),s.nextLine(),s.next(),s.nextInt());
		if (stus.contains(d)) {
			System.out.println("Find" + d.name + "£¬Num is" + stus.indexOf(d));
			stus.remove(d); 
			System.out.println("Delete" + d.name + "Still have" + stus.size() + "student£º");
			for (int i = 0; i < stus.size(); i++) { 
				System.out.println("No." + i + "student: " + stus.get(i).name);}}
		else{
			System.out.println("CANT find " + d.name); }	
}
}
