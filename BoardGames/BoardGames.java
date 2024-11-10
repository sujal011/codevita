package BoardGames;

import java.util.*;

public class BoardGames {
    static class Cell {
        int row, col, moves;

        Cell(int row, int col, int moves) {
            this.row = row;
            this.col = col;
            this.moves = moves;
        }
    }

    public static int minMovesToReachDestination(int[][] grid, int[] source, int[] destination, int[] moveRule) {
        int M = grid.length;
        int N = grid[0].length;

        // Directions based on moveRule
        int[][] directions = {
            {moveRule[0], moveRule[1]},             // Forward
            {moveRule[1], -moveRule[0]},            // Right (90° clockwise)
            {-moveRule[1], moveRule[0]},            // Left (90° counterclockwise)
            {-moveRule[0], -moveRule[1]}            // Backward (180° rotation)
        };

        // Queue for BFS
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(source[0], source[1], 0));

        // Visited matrix to prevent revisiting cells
        boolean[][] visited = new boolean[M][N];
        visited[source[0]][source[1]] = true;

        // Perform BFS
        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            // Check if we've reached the destination
            if (current.row == destination[0] && current.col == destination[1]) {
                return current.moves;
            }

            // Explore all four directions
            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                // Check if the new cell is within bounds, passable (0), and unvisited
                if (newRow >= 0 && newRow < M && newCol >= 0 && newCol < N &&
                    grid[newRow][newCol] == 0 && !visited[newRow][newCol]) {
                    
                    // Mark as visited and add to queue
                    visited[newRow][newCol] = true;
                    queue.add(new Cell(newRow, newCol, current.moves + 1));
                }
            }
        }

        // If destination is unreachable, return -1
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input grid dimensions
        int M = sc.nextInt();
        int N = sc.nextInt();
        int[][] grid = new int[M][N];

        // Input grid cells
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // Input source and destination coordinates
        int[] source = {sc.nextInt(), sc.nextInt()};
        int[] destination = {sc.nextInt(), sc.nextInt()};

        // Input move rule
        int[] moveRule = {sc.nextInt(), sc.nextInt()};

        // Output minimum moves to reach destination
        int result = minMovesToReachDestination(grid, source, destination, moveRule);
        System.out.println(result);

        sc.close();
    }
}
