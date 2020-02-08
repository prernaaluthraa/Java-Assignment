package Assignment;

import java.util.Arrays;
import java.util.Scanner;

public class SmallLarge {

	public static void main(String[] args) {
	   Scanner o=new Scanner(System.in);
	   System.out.println("Enter the size of array=>");
	   int t=o.nextInt();
	   int arr[]=new int[t];
	   System.out.println("Enter the values in array =>");
	   for(int i=0;i<t;i++)
	   {
		   arr[i]=o.nextInt();
	   }
	   Arrays.sort(arr);
	   System.out.println("Output=>");
	   int left=0;
	   int right=t-1;
	   while(left<=right)
	   {    if(left==right)
	   {
		   System.out.print(arr[left]);
	   }
	   else {
		   System.out.print(arr[left]+" "+arr[right]+" ");
		   
	   }
	   left++;
	   right--;
	   }

	}

}
