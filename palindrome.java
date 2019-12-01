import java.util.*;
public class Palindrome 
{
	public static void main(String argsp[])
	{
		Scanner sc=new Scanner(System.in);
        String input=sc.next();
        String result=longestPalindrome(input);
        if(result.length()>1)
        {
        	System.out.print(result);
        }
        else {
        	System.out.print("No Such Substring present!");
        }
      
    }
	public static String longestPalindrome(String s) {
        String max_str = "";
        int maxlen = 0;
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
            	String temp=s.substring(i, j);
                if(isPalindrome(temp)){
                    if((j-i)>maxlen){
                        maxlen = j-i;
                        max_str = temp;
                    }
                }
            }
        }
        return max_str;
    }
    
    public static boolean isPalindrome(String s){
        for(int i=0;i<s.length()/2;i++){
            if(s.charAt(i)!=s.charAt(s.length()-i-1)){
                return false;
            }
        }
        return true;
	}

}
