import java.util.*;
public class ReverseString 
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		char ch[]=str.toCharArray();
		int size=ch.length;
		int size1=size-1;
		int i,j;
		for(i=0;i<size;i++)
		{
			if(i<size1)
			{
				if((ch[i]>='A'&&ch[i]<='Z'||ch[i]>='a'&&ch[i]<='z')&&(ch[size1]>='A'&&ch[size1]<='Z'||ch[size1]>='a'&&ch[size1]<='z'))
				{
					char temp=ch[i];
					ch[i]=ch[size1];
					ch[size1]=temp;
					size1--;
				}
				else if(ch[i]>='A'&&ch[i]<='Z'||ch[i]>='a'&&ch[i]<='z')
				{
					if(true)
					{
						i=i-1;
						size1--;
					}
				}
			}
		}
		for(i=0;i<size;i++)
		{
			System.out.print(ch[i]);
		}
	}

}
