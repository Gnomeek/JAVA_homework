package chap1_31;
import java.io.*;

public class yanghuitriangle {

	public static void main(String[] args) {
		try
        {
	        System.out.print("Enter the rows of YangHui Triangle£º");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            int n=Integer.parseInt(stdin.readLine());
            int a[][]=new int[n][];
            int i,j;
            for(i=0;i<n;i++)
            {
                a[i]=new int[i+1];
                for(j=0;j<i+1;j++)
                {
                    if(j==0||j==i)
                    	a[i][j]=1;
                    else
                        a[i][j]=a[i-1][j-1]+a[i-1][j];
                }
           }
           System.out.println("YangHui Triangle is£º");
           for (i=0;i<n;i++)
           {
               for(j=0;j<n-i;j++)
               System.out.print("  ");
               for(j=0;j<i+1;j++)
               System.out.print(a[i][j]+"   ");
               System.out.print("\n");
          }

      }
     catch(IOException e)
      {
      		System.err.println("IOException");
      }


	}

}
