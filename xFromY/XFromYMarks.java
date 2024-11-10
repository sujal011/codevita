package xFromY;

import java.util.*;

public class XFromYMarks {
    
    public static int minStringFactor(String X, String Y, int S, int R) {
        int m = X.length();
        int n = Y.length();
        
        // Reverse Y for the reversed substrings
        String YReversed = new StringBuilder(Y).reverse().toString();
        
        // DP array to store the minimum string factor to form the string X up to index i
        int[] dp = new int[m + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);  // Initially set all values to infinity
        dp[0] = 0;  // No substrings needed to form an empty string

        // Try every substring of X and match it with substrings from Y and reversed Y
        for (int i = 1; i <= m; i++) {
            // Check forward substrings (from Y)
            for (int j = i - 1; j >= 0; j--) {
                String sub = X.substring(j, i);
                if (Y.contains(sub)) {
                    dp[i] = Math.min(dp[i], dp[j] + S);  // Add cost of taking this substring from Y
                }
            }
            
            // Check reversed substrings (from reversed Y)
            for (int j = i - 1; j >= 0; j--) {
                String sub = X.substring(j, i);
                if (YReversed.contains(sub)) {
                    dp[i] = Math.min(dp[i], dp[j] + R);  // Add cost of taking this substring from reversed Y
                }
            }
        }
        
        // If dp[m] is still infinity, it's impossible to form the string
        return dp[m] == Integer.MAX_VALUE ? -1 : dp[m];
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Reading input
        String X = sc.nextLine();
        String Y = sc.nextLine();
        int S = sc.nextInt();
        int R = sc.nextInt();
        
        // Get the minimum String Factor
        int result = minStringFactor(X, Y, S, R);
        
        // Output the result
        if (result == -1) {
            System.out.println("Impossible");
        } else {
            System.out.println(result);
        }
        
        sc.close();
    }
}

