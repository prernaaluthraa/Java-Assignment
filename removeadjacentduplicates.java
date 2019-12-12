import java.util.*;
public class RemoveAdjacentDuplicates
{
	public static void main(String[] args) 
  {
		Scanner sc=new Scanner(System.in);
    
		String s=sc.next();
		char ch[]=s.toCharArray();
		int n=s.length();
    
		if(ch[0]!=ch[1]) 
    {
			System.out.print(ch[0]);
		}
		for(int i=1;i<n-1;i++) 
    {
			if(ch[i]==ch[i+1])
      {
				i++;
			}
			else if(ch[i]!=ch[i+1]) 
      {
				if(ch[i]!=ch[i-1]&& i>=1)
				System.out.print(ch[i]);
			}
		}
		if(ch[n-1]!=ch[n-2]) 
    {
			System.out.print(ch[s.length()-1]);
		}
	}
}
