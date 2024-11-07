
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void findCellsWithScoreK(int n, int m, int[][] table, int k) {
        int[][] dp = new int[n][m];
        dp[0][0] = 1;  // Start with one way at the origin

        // Fill DP table based on the given conditions
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Move downwards if value at (i, j) allows
                if (i + 1 < n && table[i + 1][j] >= table[i][j]) {
                    dp[i + 1][j] += dp[i][j];
                }
                // Move rightwards if value at (i, j) allows
                if (j + 1 < m && table[i][j + 1] >= table[i][j]) {
                    dp[i][j + 1] += dp[i][j];
                }
            }
        }

        // Collect cells with the specified score
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == k) {
                    result.add(new int[]{i, j});
                }
            }
        }

        // Output result
        if (result.isEmpty()) {
            System.out.println("NO");
        } else {
            for (int[] cell : result) {
                System.out.println(cell[0] + " " + cell[1]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: dimensions of the table
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Input: values in the table
        int[][] table = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        // Input: target score k
        int k = scanner.nextInt();

        // Find cells with the specified score
        findCellsWithScoreK(n, m, table, k);
    }
}
