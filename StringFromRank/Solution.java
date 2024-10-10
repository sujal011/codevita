package StringFromRank;

import java.util.Scanner;
public class Solution {
    public static String generateStringFromRank(int rank, int length) {
        StringBuilder result = new StringBuilder();
        char[] alphabets = new char[26];
        for (int i = 0; i < 26; ++i) {
            alphabets[i] = (char) ('a' + i);
        }
        rank -= 1;
        for (int i = 0; i < length; ++i) {
            int index = rank % 26;
            result.insert(0, alphabets[index]);
            rank /= 26;
        }
        return result.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Input
        int rank = scanner.nextInt();
        int length = scanner.nextInt();
        // Output
        String resultString = generateStringFromRank(rank, length);
        System.out.println(resultString);
        scanner.close();
    }
}