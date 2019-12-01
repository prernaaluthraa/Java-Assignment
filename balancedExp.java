import java.util.*;
public class BalancedExp 
{
	public static void main(String args[])
	{
	Scanner sc = new Scanner(System.in);
	String str=sc.next();
	int size=str.length();
	Stack<Character> st=new Stack<>();
	for(int i=0;i<size;i++)
	{
		char current=str.charAt(i);
		if(current=='('||current=='{'||current=='[')
		{
			st.push(current);			
		}
		else if( !st.empty() && ((current == ']' && st.peek() == '[') || 
                (current == '}' && st.peek() == '{') ||
                (current == ')' && st.peek() == '('))) 
		{
           st.pop();
     }
		else
		{
			st.push(current);
		}
	}
	if(st.empty())
	{
		System.out.print("Balanced");
	}
	else
	{
		System.out.print("Not Balanced");
	}

}
}
