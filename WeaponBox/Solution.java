package WeaponBox;

import java.util.*;

public class Solution {
    
    // Function to generate triangular numbers up to the maximum weight
    public static Set<Integer> getTriangularNumbers(int maxWeight) {
        Set<Integer> triangularNumbers = new HashSet<>();
        int n = 1;
        int triangularNumber = 1;
        
        while (triangularNumber <= maxWeight) {
            triangularNumbers.add(triangularNumber);
            n++;
            triangularNumber = n * (n + 1) / 2;  // nth triangular number
        }
        
        return triangularNumbers;
    }
    
    // Function to calculate the total cost excluding triangular weights
    public static int calculateTotalCost(List<Integer> shiftedBoxes, Set<Integer> triangularNumbers) {
        int totalCost = 0;
        for (int weight : shiftedBoxes) {
            if (!triangularNumbers.contains(weight)) {
                totalCost += weight;
            }
        }
        return totalCost;
    }

    public static int solveWeaponBoxes(int[] weights, int N, int K) {
        int numBoxes = weights.length;
        Deque<Integer> queue = new ArrayDeque<>();
        
        // Add all weights to a deque
        for (int weight : weights) {
            queue.addLast(weight);
        }
        
        // Track how many cycles each box has been un-shifted
        Map<Integer, Integer> unshiftedCount = new HashMap<>();
        List<Integer> shiftedBoxes = new ArrayList<>();
        
        // Get triangular numbers
        Set<Integer> triangularNumbers = getTriangularNumbers(1000);
        
        boolean halt = false;
        while (!halt) {
            // Process the first N boxes in each cycle
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < N && !queue.isEmpty(); i++) {
                tempList.add(queue.removeFirst());
            }
            
            // Compare boxes and shift the lighter one
            for (int i = 0; i < tempList.size() - 1; i++) {
                int box1 = tempList.get(i);
                int box2 = tempList.get(i + 1);
                
                if (box1 > box2) {
                    // Shift box2 to the end
                    shiftedBoxes.add(box2);
                    queue.addLast(box2);
                    tempList.set(i + 1, box1);  // Move box1 up
                    unshiftedCount.put(box2, 0);  // Reset un-shifted count for box2
                } else {
                    // Shift box1 to the end
                    shiftedBoxes.add(box1);
                    queue.addLast(box1);
                    tempList.set(i, box2);  // Move box2 up
                    unshiftedCount.put(box1, 0);  // Reset un-shifted count for box1
                }
            }
            
            // The last box in the tempList is un-shifted
            if(!tempList.isEmpty()){
                int lastBox = tempList.get(tempList.size() - 1);
                unshiftedCount.put(lastBox, unshiftedCount.getOrDefault(lastBox, 0) + 1);
            
            // Check if the last box has remained un-shifted for K consecutive cycles
                if (unshiftedCount.get(lastBox) >= K) {
                    halt = true;
            }
        }
        }
        
        // Calculate total cost
        return calculateTotalCost(shiftedBoxes, triangularNumbers);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String firstLine = sc.nextLine();
        String[] weightStrings = firstLine.split(" ");  // Split by spaces
        int[] weights = new int[weightStrings.length];
        
        // Converting string array to integer array
        for (int i = 0; i < weightStrings.length; i++) {
            weights[i] = Integer.parseInt(weightStrings[i]);
        }
        int N = sc.nextInt();
        int K = sc.nextInt();
        // int[] weights = {7, 3, 6, 9, 10, 2, 4, 11, 5, 12, 17, 1};
        // int N = 3;
        // int K = 2;
        
        System.out.println(solveWeaponBoxes(weights, N, K));  // Output: 22

        // // Example 2
        // int[] weights2 = {6, 2, 8, 14, 5, 1, 3};
        // int N2 = 2;
        // int K2 = 2;
        
        // System.out.println(solveWeaponBoxes(weights2, N2, K2));  // Output: 15
    }
}