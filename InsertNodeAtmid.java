package questionsK;
import java.util.*;
public class largestNoForm {
	static void find(Vector<String> s)
	{
		Collections.sort(s,new Comparator<String>()
		{
			public int compare(String x,String y)
			{
				String XY=x+y;
				String YX=y+x;
				return XY.compareTo(YX)>0?-1:1;
			}
		});
		Iterator it=s.iterator();
		while(it.hasNext())
		{
			System.out.print(it.next());
		}
	}
	public static void main(String args[])
	{
		Vector<String> v=new Vector<String>();
		System.out.println("Enter the size");
		Scanner sc=new Scanner(System.in);
		int size=sc.nextInt();
		for(int i=0;i<size;i++)
		{
			v.add(sc.next());
		}
		find(v);
	}

}
