import java.util.*;
public class PrefixToPostfix 
{
static boolean sign(char x)
	{
		switch(x)
		{
			case '*':
			case '/':
			case '+':
			case '-':
				return true;
		}
		return false;
	}
	static String convert(String pre)
	{
		Stack<String> s1=new Stack<String>();
		int l=pre.length();
		for(int i=l-1;i>=0;i--)
		{
			if(sign(pre.charAt(i)))
			{
				String a=s1.pop();
				String b=s1.pop();
				String z=a+b+pre.charAt(i);
				s1.push(z);
			}
			else
			{
				s1.push(pre.charAt(i)+"");
			}
		}
		return s1.pop();
	}
	public static void main(String args[])
	{
		          Scanner sc=new Scanner(System.in);
		System.out.println("Enter the expression:");
		String pre=sc.next();
		String post=convert(pre);
		System.out.println("Postfix:");
		System.out.println(post);
	}    
}
