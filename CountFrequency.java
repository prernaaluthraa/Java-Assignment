package questionsK;
import java.util.*;
public class countFrequency 
{
	int []a;
	int[]b;
	void input()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of the array");
		int size=sc.nextInt();
		a=new int[size];
		b=new int[size];
		for(int i=0;i<size;i++)
		{
			a[i]=sc.nextInt();
			b[i]=-1;
		}
		int count=0;
		for(int i=0;i<size;i++)
		{
			if(b[i]==0)
				continue;
			count=1;
			for(int j=i+1;j<size;j++)
			{
				if(a[i]==a[j])
				{
					b[j]=0;
					count++;
				}
			}
			System.out.println("Count of "+a[i]+" is "+count);
		}
	}
	public static void main(String args[])
	{
		countFrequency f=new countFrequency();
		f.input();
	}

}
