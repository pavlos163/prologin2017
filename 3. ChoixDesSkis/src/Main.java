import java.util.Arrays;
import java.util.Scanner;

public class Main {
  
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    
    int n = sc.nextInt();
    
    int[] p = new int[n];
    int[] s = new int[n];
    
    for (int i = 0; i < n; i++) {
      p[i] = sc.nextInt();
    }
    
    for (int i = 0; i < n; i++) {
      s[i] = sc.nextInt();
    }
    
    Arrays.sort(p);
    Arrays.sort(s);
    
    int result = 0;
    
    for (int i = 0; i < n; i++) {
      result += Math.abs(p[i] - s[i]);
    }
    
    System.out.println(result);
  }
  
}
