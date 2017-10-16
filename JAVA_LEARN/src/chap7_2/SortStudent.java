package chap7_2;
import java.util.*;

public class SortStudent {  
    public static void main(String[] args) {  
    	while(true){
   	 System.out.println("Choose the sorting ways:id or name or gender");
   	 Scanner s = new Scanner(System.in);
   	 String type=s.nextLine();
  
   	 Comparator<Student> comparator = new Comparator<Student>(){  
            public int compare(Student s1, Student s2) {  
               
         	 if(type.equals("id")){   
         		 return s1.id-s2.id;  
             }
         	 else{
             if(type.equals("name")){ 
               return s1.name.compareTo(s2.name);  
              } 
         	 
         	 else{  
             	return s1.gender.compareTo(s2.gender); 
              }  
            }
          }
      };
    
    
     Student stu1 = new Student (001,"Alan","male");  
     Student stu2 = new Student (002,"Susan","female");  
     Student stu3 = new Student (003,"Jack","male");  
     Student stu4 = new Student (004,"Ponny","female");  
     Student stu5 = new Student (004,"Ben","male");  
     
     ArrayList<Student> List = new ArrayList<Student>();  
     List.add(stu1);  
     List.add(stu2);  
     List.add(stu3);  
     List.add(stu4);  
     List.add(stu5);   
     //自动根据规则进行排序  
     Collections.sort(List,comparator);  
     display(List);  
     }
    }
      
    static void display(ArrayList<Student> lst){  
     for(Student s:lst)  
      System.out.println(s);  
    }  
   }  
  
   class Student{  
      
    int id;  
    String gender;  
    String name;  
   
    Student(int id,String name,String gender){  
     this.id=id;  
     this.name=name;  
     this.gender=gender;  

    }  
    public String toString(){  
     return id+"  "+name+"  "+gender;  
    }  
   } 
   