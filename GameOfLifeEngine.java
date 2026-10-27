import java.awt.Color;
import java.util.Random;

/**
 * GameOfLifeEngine implements Conway's Game of Life simulation
 * with true random patterns and rules for cells.
 */
public class GameOfLifeEngine {
    
    public static void runSimulation(int rows, int cols, int speedMs) {
        boolean[][] currentGrid = new boolean[rows][cols];
        Random rand = new Random();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                currentGrid[r][c] = rand.nextDouble() < 0.4;
                updateCellDisplay(r, c, currentGrid[r][c]);
            }
        }
        
        System.out.println("Conway's Game of Life simulation started. Close window to exit.");

        while (Mosaics.isOpen()) {
            Mosaics.delay(speedMs);
            boolean[][] nextGrid = new boolean[rows][cols];

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    int neighbors = countLiveNeighbors(currentGrid, r, c, rows, cols);
                    boolean isAlive = currentGrid[r][c];

                    if (isAlive && (neighbors == 2 || neighbors == 3)) {
                        nextGrid[r][c] = true;
                    } else if (!isAlive && neighbors == 3) {
                        nextGrid[r][c] = true;
                    } else {
                        nextGrid[r][c] = false;
                    }
                    updateCellDisplay(r, c, nextGrid[r][c]);
                }
            }
            currentGrid = nextGrid;
        }
    }

    private static int countLiveNeighbors(boolean[][] grid, int row, int col, int rows, int cols) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int nr = (row + dr + rows) % rows;
                int nc = (col + dc + cols) % cols;
                if (grid[nr][nc]) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void updateCellDisplay(int row, int col, boolean isAlive) {
        if (isAlive) {
            double hue = (double)(row + col) / 60.0 % 1.0;
            Color liveColor = MosaicsPanel.makeHSBColor(hue, 0.9, 0.9);
            Mosaics.setColor(row, col, liveColor);
        } else {
            Mosaics.setColor(row, col, Color.BLACK);
        }
    }
}