import java.util.*;
public class ArrayPairSumEqlK {

	public static void main(String rg[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of 1st array");
		int n=sc.nextInt();
		int a[]=new int[n];
		System.out.println("Enter elements of 1st array");
		for(int i=0;i<n;i++)
		{
			a[i]=sc.nextInt();
		}

		System.out.println("Enter the size of 2nd array");
		int m=sc.nextInt();
		int a1[]=new int[n];
		System.out.println("Enter elements of 2nd array");
		for(int i=0;i<m;i++)
		{
			a1[i]=sc.nextInt();
		}
		int count=0,count1=0;
		System.out.println("Enter number");
		int k=sc.nextInt();

			for(int i=0;i<n;i++)
			{
				for(int j=0;j<m;j++)
				{
                   if(count1==0)
                   {
					if(a[i]+a1[j]==k)
					{
						count++;
						System.out.print(a[i]+" "+a1[j]);
						count1=1;
						
					}
                   }
                   else {
                	   if(a[i]+a1[j]==k)
   					    {
   						count++;
   						System.out.print(","+" "+a[i]+" "+a1[j]);   						
   					    }
                   }

				}
			}
		if(count==0)
			System.out.println("No pair is present,having sum ="+k);

	}
}
