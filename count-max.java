  
import java.util.*;

public class CountSarayMaxgrtrThanK {
	public static void main(String arg[])
	{
		Scanner sc=new Scanner(System.in);
		int n;
		System.out.println("Enter the size of array");
		n=sc.nextInt();
		int a[]=new int[n];
		
		System.out.println("Enter elements");
		for(int i=0;i<n;i++)
		{
			a[i]=sc.nextInt();
			
		}
		
    	int temp=a[0],count=0;
 		System.out.println("Enter number");
		int m=sc.nextInt();
         
		for(int i=0;i<n;i++)
		{
			for(int j=i;j<n;j++)
			{
				for(int k=i;k<=j;k++)
				{
			 		System.out.print(a[k]);

					if(temp<a[k]) {
						temp=a[k];
					}
				}
				
		 		System.out.println("--");

				if(temp>m)
					count++;
				
				temp=0;
			}
		}
		
 		System.out.println(count);


	}
}
