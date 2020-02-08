import java.util.Scanner;
import java.util.Arrays;

public class ArraysAlternatePrinting {

	public static void displayMinMax(int arr[]) {
		Arrays.sort(arr);
		int i = 0;
		int j = arr.length - 1;
		System.out.print("{");
		int c = 0;
		while(i < j) {
			System.out.print(arr[i]);
			if(c < arr.length-1) {
				System.out.print(",");
				c++;
			}
			System.out.print(arr[j]);
			if(c < arr.length-1) {
				System.out.print(",");
				c++;
			}
			i++;
			j--;
		}
		if(arr.length % 2 != 0) {
			System.out.print(arr[arr.length / 2]);
		}
		System.out.print("}");
	}


	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the size of the array: ");
		int size = s.nextInt();
		int arr[] = new int[size];
		System.out.println("Enter the elements of the array");
		for(int i = 0; i < size; i++) {
			arr[i] = s.nextInt();
		}
		displayMinMax(arr);
		s.close();
	}
}
