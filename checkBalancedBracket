import java.util.*;
public class CheckBalancedBracket {

	public static void main(String arg[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the parentheses to check whether it is balanced or not.");
		String s=sc.next();
		char ch[]=s.toCharArray();
		Stack stk=new Stack();
		int i;
		for( i=0;i<ch.length;i++)
		{
			if(ch[i]=='['||ch[i]=='{'||ch[i]=='(')
			{
				stk.push(ch[i]);

			}
			else if(ch[i]==']')
			{
				char c=(char)stk.peek();
				if(c=='[')
				{
					stk.pop();
				}
				else {
					break;
				}
			}
			else if(ch[i]=='}')
			{
				char c=(char)stk.peek();
				if(c=='{')
				{
					stk.pop();
				}
				else {
					break;
				}
			}
			else
			{
				char c=(char)stk.peek();
				if(c=='(')
				{
					stk.pop();

				}
				else {
					break;
				}
			}
		}
		if(stk.isEmpty())
		{
			System.out.println("Balanced");
		}
		else {
			System.out.println("Not Balanced");
		}
	}
}
