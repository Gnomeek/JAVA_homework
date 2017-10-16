package chap5_18;

public class Mainclass {
    public static void main(String[] args) {
        //实例化站台对象，并为每一个站台取名字
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
