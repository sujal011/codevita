package VIPCafe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution {
    
    static class Order {
        int priority;
        int index;

        public Order(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Number of orders
        int[] priorities = new int[n];
        
        for (int i = 0; i < n; i++) {
            priorities[i] = sc.nextInt();
        }

        int k = sc.nextInt() - 1; // Friend's position (convert to zero-indexed)

        Queue<Order> orderQueue = new LinkedList<>();
        
        // Initialize the queue with orders
        for (int i = 0; i < n; i++) {
            Order order = new Order(priorities[i], i);
            orderQueue.add(order);
        }

        int servedCount = 0;

        // Serve orders
        while (!orderQueue.isEmpty()) {
            Order currentOrder = orderQueue.poll();
            boolean isMaxPriority = true;

            // Check if there's any order with higher priority
            for (Order order : orderQueue) {
                if (order.priority > currentOrder.priority) {
                    isMaxPriority = false; // Found a higher priority order
                    break;
                }
            }

            if (isMaxPriority) {
                // Serve the current order
                servedCount++;
                if (currentOrder.index == k) { // If this is friend's order
                    System.out.print(servedCount);
                    return;
                }

                // Increment the priority of orders in the queue before this one
                for (Order order : orderQueue) {
                    if (order.index < currentOrder.index) {
                        order.priority++; // Increment priority for orders before served one
                    }
                }
            } else {
                // Re-add the current order to the end of the queue
                orderQueue.add(currentOrder);
            }
        }
    }
}
