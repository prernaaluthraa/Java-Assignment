import java.util.*;
public class Frequency 
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int arr[]=new int[n];
		for(int i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
		}
		int hash[]=new int[100];
		for(int i=0;i<n;i++)
		{
			hash[arr[i]]++;
		}
		for(int i=0;i<100;i++)
		{
			if(hash[i]>0)
			{
				System.out.print(i+" "+hash[i]);
				System.out.println();
			}
		
		}
	}

}
