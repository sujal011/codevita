// package TreeRows;

import java.util.Scanner;

class Solution {

    // Check if the input string contains only 'M' or 'L'
    public static boolean isValidInput(String row) {
        return row.matches("[ML]+");
    }

    // DP function to count valid combinations of non-adjacent trees
    public static int countPossibilities(String row) {
        int n = row.length();
        if (n < 3) return 0;  // No valid combinations if less than 3 trees

        // Arrays to store valid subsequences
        int[] dpM = new int[n]; // dpM[i] is valid subsequences ending with 'M' at i
        int[] dpL = new int[n]; // dpL[i] is valid subsequences ending with 'L' at i

        int totalCombinations = 0;

        // Loop through the row to fill dpM and dpL
        for (int i = 1; i < n; i++) {
            if (row.charAt(i) == 'M') {
                // A subsequence ending with 'M' can be formed if the previous was 'L'
                dpM[i] = dpL[i - 1] + 1; // Add valid subsequence if previous was 'L'
            } else {
                // A subsequence ending with 'L' can be formed if the previous was 'M'
                dpL[i] = dpM[i - 1] + 1; // Add valid subsequence if previous was 'M'
            }

            // Accumulate total combinations
            totalCombinations += dpM[i] + dpL[i];
        }

        return totalCombinations;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        // Input validation
        if (!isValidInput(s1) || !isValidInput(s2)) {
            System.out.println("Invalid input");
            return;
        }

        // Calculate possibilities using DP
        int f1 = countPossibilities(s1);
        int f2 = countPossibilities(s2);

        // Determine the winner
        if (f1 > f2) {
            System.out.print("Ashok");
        } else if (f1 < f2) {
            System.out.print("Anand");
        } else {
            System.out.print("Draw");
        }
    }
}
