package chap5_18;

public class Mainclass {
    public static void main(String[] args) {
        //ʵ����վ̨���󣬲�Ϊÿһ��վ̨ȡ����
         Station station1=new Station("Station1");
         Station station2=new Station("Station2");
         Station station3=new Station("Station3");
         Station station4=new Station("Station4");
     
         station1.start();
         station2.start();
         station3.start();
         station4.start();
     
    }
 
}
