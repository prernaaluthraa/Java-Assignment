package sumArrayInd;
import java.util.*;
public class Pythogorean {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] arr=new int[n];
		boolean flag=false;
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
		}
		int a,b,c;
		for(int i=0;i<n;i++) {
			a=arr[i];
			for(int j=0;j<n;j++) {
				b=arr[j];
				for(int k=0;k<n;k++) {
					c=arr[k];
					if((a*a)+(b*b)==(c*c)) {
						flag=true;
						System.out.println(a+" "+b+" "+c);
					}
				}
			}
		}
		if(flag==true) {
			System.out.println("Yes");
		}
		else {
			System.out.println("No");
		}
	}
}
