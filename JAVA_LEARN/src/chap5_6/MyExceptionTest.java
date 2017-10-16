package chap5_6;


public class MyExceptionTest {

    public static void main(String[] args) {
        
         String[] sexs = {"Male","Female","NA"};
         for(int i = 0; i < sexs.length; i++){
             if("NA".equals(sexs[i])){
                 throw new MyException("NA£¡");
             }else{
                 System.out.println(sexs[i]);
             }
         } 
    }
}