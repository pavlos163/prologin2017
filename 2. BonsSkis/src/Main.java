import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    
    int n = sc.nextInt();
    int a = sc.nextInt();
    
    int[] s = new int[n];
    
    for (int i = 0; i < n; i++) {
      s[i] = sc.nextInt();
    }
    
    System.out.println(solve(a, s));
    
  }
  
  public static int solve(int a, int[] s) {
    int minDiff = Integer.MAX_VALUE;
    int solution = Integer.MAX_VALUE;
    int diff;
    
    for (int i = 0; i < s.length; i++) {
      diff = Math.abs(s[i] - a);
      if (diff < minDiff) {
        solution = s[i];
        minDiff = diff;
      }
      else if (diff == minDiff && s[i] < solution) {
        solution = s[i];
      }
    }
    
    return solution;
  }

}
