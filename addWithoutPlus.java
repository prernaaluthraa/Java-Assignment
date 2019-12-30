package questionsK;
import java.util.*;
public class addWithoutplus 
{
	static void add(int x,int y)
	{
		while(y!=0)
		{
			int carry=x&y;
			x=x^y;
			y=carry<<1;
		}
		System.out.println("The sum is "+x);
	}
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the two numbers");
		int x=sc.nextInt();
		int y=sc.nextInt();
		add(x,y);
	}

}
