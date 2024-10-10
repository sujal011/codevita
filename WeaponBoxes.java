import java.util.*;

public class WeaponBoxes {

    // Method to check if a number is triangular
    private static boolean isTriangular(int num) {
        int sum = 0;
        for (int i = 1; sum < num; i++) {
            sum += i;
            if (sum == num) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Reading the input weights of the boxes
        String[] input = sc.nextLine().split(" ");
        int[] weights = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();

        // Reading N and K
        int N = sc.nextInt();
        int K = sc.nextInt();

        // Queue to simulate the box arrangement
        LinkedList<Integer> queue = new LinkedList<>();
        for (int weight : weights) {
            queue.add(weight);
        }

        // Set to store shifted boxes
        Set<Integer> shiftedBoxes = new HashSet<>();
        int consecutiveUnshifted = 0;
        int lastUnshiftedBox = -1;

        // Simulate the cycles until halting condition is met
        while (consecutiveUnshifted < K) {
            List<Integer> selectedBoxes = new ArrayList<>();
            for (int i = 0; i < N && !queue.isEmpty(); i++) {
                selectedBoxes.add(queue.poll());
            }

            // Process the cycle and find the unshifted box
            int unshiftedBox = processCycle(selectedBoxes);

            // If the same box remains unshifted in K consecutive cycles, halt
            if (unshiftedBox == lastUnshiftedBox) {
                consecutiveUnshifted++;
            } else {
                consecutiveUnshifted = 0;
                lastUnshiftedBox = unshiftedBox;
            }

            // Re-add the boxes back to the queue
            for (int box : selectedBoxes) {
                queue.add(box);  // Put boxes back to the queue
            }

            // Add all shifted boxes except the unshifted one to the set
            for (int box : selectedBoxes) {
                if (box != unshiftedBox) {
                    shiftedBoxes.add(box);  // Track only shifted boxes
                }
            }
        }

        // Calculating the sum of non-triangular shifted boxes
        int totalAmount = 0;
        for (int box : shiftedBoxes) {
            if (!isTriangular(box)) {
                totalAmount += box;
            }
        }

        System.out.println(totalAmount);
    }

    // Method to process a cycle and return the unshifted box
    private static int processCycle(List<Integer> selectedBoxes) {
        while (selectedBoxes.size() > 1) {
            int first = selectedBoxes.remove(0);
            int second = selectedBoxes.remove(0);
            if (first > second) {
                selectedBoxes.add(second);
            } else {
                selectedBoxes.add(first);
            }
        }
        return selectedBoxes.get(0);  // This is the unshifted box
    }
}
