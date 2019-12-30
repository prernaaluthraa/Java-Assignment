package questionsK;
import java.util.*;
public class addLink
{
	static class Node
	{
		Node next;
		int data;
		Node(int data)
		{
			this.data=data;
		}
	}
	static Node head,head1;
	void insert(int data)
	{
		Node tem=new Node(data);
		if(head==null)
		{
			head=tem;
			return;
		}
		Node current=head;
		while(current.next!=null)
		{
			current=current.next;
		}
		current.next=tem;
	}
	void insert1(int data)
	{
		Node tem=new Node(data);
		if(head1==null)
		{
			head1=tem;
			return;
		}
		Node current=head1;
		while(current.next!=null)
		{
			current=current.next;
		}
		current.next=tem;
	}
	void print(Node h)
	{
		Node cur=h;
		while(cur!=null)
		{
			System.out.print(cur.data+" ");
			cur=cur.next;
		}
		System.out.println();
	}
	static Node add(Node a,Node b)
	{
		Node prev=null;
		Node n=null,n1=null;
		int carry=0,sum;
		while(a!=null||b!=null)
		{
			sum=carry+(a!=null?a.data:0)+(b!=null?b.data:0);
			carry=(sum>=10)?1:0;
			sum=sum%10;
			n=new Node(sum);
			if(n1==null)
				n1=n;
			else
			{
				prev.next=n;
			}
			prev=n;
			if(a!=null)
				a=a.next;
			if(b!=null)
				b=b.next;
		}
		if(carry>0)
			n.next=new Node(carry);
		return n1;
	}
	public static void main(String args[])
	{
		addLink a=new addLink();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of first list");
		int size=sc.nextInt();
		for(int i=0;i<size;i++)
		{
			a.insert(sc.nextInt());
		}
		a.print(a.head);
		System.out.println("Enter the size of second list");
		int size1=sc.nextInt();
		for(int i=0;i<size1;i++)
		{
			a.insert1(sc.nextInt());
		}
		a.print(a.head1);
		Node h=add(head,head1);
		a.print(h);
	}
}
