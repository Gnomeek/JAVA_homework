package chap5_18;

public class Station extends Thread {
	 

    public Station(String name) {
         super(name);
    }
     

    static int tick = 20;
     

    static Object ob = "a";//ֵ�������

    public void run() {
        while (tick > 0) {
            synchronized (ob) {// �������Ҫ������ʹ��һ������
                // ��ȥ���˻��Կ���������ϣ�������Ű�Կ�����ó���
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