package chap5_18;

public class Station extends Thread {
	 

    public Station(String name) {
         super(name);
    }
     

    static int tick = 20;
     

    static Object ob = "a";//值是任意的

    public void run() {
        while (tick > 0) {
            synchronized (ob) {// 这个很重要，必须使用一个锁，
                // 进去的人会把钥匙拿在手上，出来后才把钥匙拿让出来
                if (tick > 0) {
                    System.out.println(getName() + " sells No. " + tick + " ticket");
                    tick--;
                } else {
                    System.out.println("Selling out all tickets");
                }
            }
            try {
                 sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         
        }
}

}