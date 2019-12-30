package questions;
import java.util.*;
public class Ques2 
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		Stack<Integer> s= new Stack<Integer>();
		int n=sc.nextInt();
		for(int i=0;i<n;i++)
		{
			s.push(sc.nextInt());
		}
		while(!s.empty())
		{
			System.out.print((int)s.pop() + ", ");
		}
	}

}
