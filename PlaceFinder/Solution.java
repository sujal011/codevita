package PlaceFinder;

import java.util.Scanner;

class Device {
    int deviceId;
    int numNeighbors;
    int[] neighbors;
    double[] distances;
    double[] angles;

    public Device(int deviceId, int numNeighbors) {
        this.deviceId = deviceId;
        this.numNeighbors = numNeighbors;
        this.neighbors = new int[numNeighbors];
        this.distances = new double[numNeighbors];
        this.angles = new double[numNeighbors];
    }
}

public class Solution {

    private static final double PI = 3.141592653589793;

    // Convert polar coordinates to Cartesian coordinates
    public static void polarToCartesian(double distance, double angleDegrees, double[] coords) {
        double angleRadians = angleDegrees * PI / 180.0;
        coords[0] = distance * Math.cos(angleRadians);
        coords[1] = distance * Math.sin(angleRadians);
    }

    // Calculate Euclidean distance between two points
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static void dfs(int deviceId, Device[] devices, double[][] positions, boolean[] visited) {
        visited[deviceId] = true;
        for (int i = 0; i < devices[deviceId].numNeighbors; i++) {
            int neighborId = devices[deviceId].neighbors[i];
            if (!visited[neighborId]) {
                double distance = devices[deviceId].distances[i];
                double angle = devices[deviceId].angles[i];
                
                // Convert polar to Cartesian for the neighbor's position
                double[] coords = new double[2];
                polarToCartesian(distance, angle, coords);
                
                // Calculate the absolute position for the neighbor
                positions[neighborId][0] = positions[deviceId][0] + coords[0];
                positions[neighborId][1] = positions[deviceId][1] + coords[1];
                
                // Recursively visit the neighbor
                dfs(neighborId, devices, positions, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // First line - total number of devices
        int N = sc.nextInt();
        sc.nextLine(); // Consume the newline after the integer input

        // Parse the second line with device ID and number of neighbors
        String inputString = sc.nextLine();
        String[] deviceInfo = inputString.split(" ");
        
        // Initialize devices array dynamically based on N
        Device[] devices = new Device[N];
        
        // Parsing device data
        for (int i = 0; i < N; i++) {
            String[] pair = deviceInfo[i].split(":");
            int deviceId = Integer.parseInt(pair[0]) - 1;
            int numNeighbors = Integer.parseInt(pair[1]);
            
            devices[deviceId] = new Device(deviceId, numNeighbors);
        }

        // Parsing the neighbor data for each device
        for (int i = 0; i < N; i++) {
            int deviceId = devices[i].deviceId;
            sc.nextLine();  // Consume the line with device ID

            for (int j = 0; j < devices[i].numNeighbors; j++) {
                int neighborId = sc.nextInt() - 1;
                double distance = sc.nextDouble();
                double angle = sc.nextDouble();
                
                devices[deviceId].neighbors[j] = neighborId;
                devices[deviceId].distances[j] = distance;
                devices[deviceId].angles[j] = angle;
            }
            if (sc.hasNextLine()) {
                sc.nextLine(); // Consume any remaining newline after device's neighbor data
            }
        }

        // Parsing the last line for the two devices whose distance we need to find
        int startId = sc.nextInt() - 1;
        int endId = sc.nextInt() - 1;

        // Dynamically allocate space for positions based on N devices
        double[][] positions = new double[N][2];
        boolean[] visited = new boolean[N];

        // Start DFS from the startId device to calculate all relative positions
        dfs(startId, devices, positions, visited);

        // Calculate and print the distance between startId and endId
        double distance = calculateDistance(positions[startId][0], positions[startId][1],
                                            positions[endId][0], positions[endId][1]);
        System.out.printf("%.2f\n", distance);
    }
}
