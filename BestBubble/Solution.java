package BestBubble;

import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        if (size == 0 || size == 1) {
            // If the size is 0 or 1, no swaps are needed
            System.out.println(0);
            return;
        }

        int[] nums = new int[size];
        int aCount = 0; // Swap count for ascending
        int bCount = 0; // Swap count for descending

        // Input the array elements
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }

        // Clone the array for descending sort
        int[] dNums = nums.clone();

        // Ascending bubble sort
        for (int i = 0; i < size - 1; i++) {
            boolean swapped = false; // Optimization: track if we made any swaps
            for (int j = 0; j < size - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // Perform the swap
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;

                    // Increment swap count for ascending
                    aCount++;
                    swapped = true;
                }
            }
            if (!swapped) {
                // If no swaps occurred, array is already sorted
                break;
            }
        }

        // Descending bubble sort
        for (int i = 0; i < size - 1; i++) {
            boolean swapped = false; // Optimization for descending sort as well
            for (int j = 0; j < size - 1 - i; j++) {
                if (dNums[j] < dNums[j + 1]) {
                    // Perform the swap
                    int temp = dNums[j];
                    dNums[j] = dNums[j + 1];
                    dNums[j + 1] = temp;

                    // Increment swap count for descending
                    bCount++;
                    swapped = true;
                }
            }
            if (!swapped) {
                // If no swaps occurred, array is already sorted in descending
                break;
            }
        }

        // Output the minimum of the two swap counts
        System.out.print(Math.min(aCount, bCount));
    }
}
