package xFromY;

import java.util.Scanner;

public class XFromYMarksGreedy {
    
    public static int minStringFactor(String X, String Y, int S, int R) {
        int m = X.length();
        int n = Y.length();
        
        // Reverse Y for the reversed substrings
        String YReversed = new StringBuilder(Y).reverse().toString();
        
        int i = 0;
        int totalCost = 0;
        
        while (i < m) {
            int maxLength = 0;
            int cost = 0;
            
            // Find the longest match in Y
            for (int j = i; j < m; j++) {
                String subX = X.substring(i, j + 1);
                if (Y.contains(subX)) {
                    maxLength = j - i + 1;
                    cost = S;
                }
            }
            
            // Find the longest match in reversed Y
            for (int j = i; j < m; j++) {
                String subX = X.substring(i, j + 1);
                if (YReversed.contains(subX)) {
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        cost = R;
                    }
                }
            }
            
            // If no match is found, return "Impossible"
            if (maxLength == 0) {
                return -1; // Impossible to form X
            }
            
            // Add the cost for this selected substring
            totalCost += cost;
            i += maxLength; // Move forward by the length of the matched substring
        }
        
        return totalCost;
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
