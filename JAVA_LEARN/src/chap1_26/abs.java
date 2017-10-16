package chap1_26;

public class abs {

	public static void main(String[] args) {
		for (int i = 0;; i++) {
			   int m = (int) ((Math.random() * 100 + 1));
			   double f = Math.random();
			   if (f > 0.5) {
			    m = m * (-1);
			   }
			   if (m == 50) {
			    System.out.println("Value is £º" + m + "£¬OUT£¡");
			    break;
			   } else {
			    System.out.println("m[" + i + "]=" + m);
			   }
			  }

	}

}
