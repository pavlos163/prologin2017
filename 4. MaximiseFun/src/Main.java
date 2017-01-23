import java.util.HashSet;
import java.util.Scanner;

public class Main {
  
  private static int n;
  private static int m;
  private static int[] distances;
  private static HashSet<Integer> connectedPlatforms;
  
  private static final int MIN_FUN = -1000000;
  private static final int MAX_FUN = 1000000;
  
  private static boolean negativeCycleDetected = false;
  
  public static void solve(int[][] adjMatrix, int n, int m) {
    setupHash(adjMatrix);
    
    for (int i = 0; i < n - 1; i++) {
      for (int sourcePlatform = 0; sourcePlatform < n; sourcePlatform++) {
        if (connectedPlatforms.contains(sourcePlatform)) {
          for (int destPlatform = 0; destPlatform < n; destPlatform++) {
            if (adjMatrix[sourcePlatform][destPlatform] != MIN_FUN) {
              int distanceToPlatform = distances[sourcePlatform] +
                  adjMatrix[sourcePlatform][destPlatform];
              if (distances[destPlatform] > distanceToPlatform) {
                distances[destPlatform] = distanceToPlatform;
              }
            }
          }
        }
      }
    }
    
    for (int sourcePlatform = 0; sourcePlatform < n; sourcePlatform++) {
      if (connectedPlatforms.contains(sourcePlatform)) {
        for (int destPlatform = 0; destPlatform < n; destPlatform++) {
          if (adjMatrix[sourcePlatform][destPlatform] != MIN_FUN) {
            if (distances[destPlatform] > distances[sourcePlatform] + 
                adjMatrix[sourcePlatform][destPlatform]) {
              System.out.println("OVERDOSE DE FUN");
              negativeCycleDetected = true;
              return;
            }
          }
        }
      }
    }
    
  }
  
  public static void initializeDistances() {
    distances = new int[n];
    distances[0] = 0;
    for (int i = 1; i < n; i++) {
      distances[i] = MAX_FUN;
    }
  }
    
  public static int mostFunPath() {
    int minFun = MAX_FUN;
    
    for (int i = 0; i < distances.length; i++) {
      if (distances[i] < minFun && distances[i] != -1000000) {
        minFun = distances[i];
      }
    }
    
    int maxFun = minFun * (-1);
    return maxFun;
  }
  
  private static void setupHash(int[][] adjMatrix) {
    connectedPlatforms = new HashSet<Integer>();
    
    dfs(0, adjMatrix);
  }
    
  private static void dfs(int source, int[][] adjMatrix) {
    connectedPlatforms.add(source);
    for (int i = 0; i < adjMatrix[source].length; i++) {
      if (adjMatrix[source][i] != MIN_FUN && !connectedPlatforms.contains(i)) {
        dfs(i, adjMatrix);
      }
    }
  }
  
  public static int[][] initializeAdjMatrix(int n) {
    int[][] adjMatrix = new int[n][n];
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        adjMatrix[i][j] = MIN_FUN;
      }
    }
    
    return adjMatrix;
  }
  
  // Methode main:
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    
    // N: number of platforms (vertices)
    n = sc.nextInt();
    // M: number of edges
    m = sc.nextInt();
    
    int[][] adjMatrix = initializeAdjMatrix(n);
    
    for (int i = 0; i < m; i++) {
      int sourcePlatform = sc.nextInt();
      int destPlatform = sc.nextInt();
      int fun = sc.nextInt();
      
      adjMatrix[sourcePlatform][destPlatform] = fun * (-1);
    }
     
    initializeDistances();
    solve(adjMatrix, n, m);
    
    if (!negativeCycleDetected) System.out.println(mostFunPath());
    sc.close();
  }
  
  // Testing methods:
  private static void printDistances() {
    System.out.println("-------------------");
    System.out.println("DISTANCES:");
    for (int i = 0; i < n; i++) {
      System.out.print(distances[i] + " ");
    }
    System.out.println();
    System.out.println("-------------------");
  }
  
  private static void printMatrix(int[][] matrix) {
    System.out.println("-------------------");
    System.out.println("MATRIX:");
    for (int i = 0; i < matrix.length; i++) {
      System.out.println();
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
    }
    System.out.println();
    System.out.println("-------------------");
  }
   
}
