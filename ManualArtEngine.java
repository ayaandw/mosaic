import java.awt.Color;
import java.util.Scanner;

/**
 * ManualArt Engine inertacts to create a row-by-row typing mode and
 * a continuous animation loop. This was the original project created
 * for my APCS Unit 4 Project on subroutines.
 */
public class ManualArtEngine {
    public static void runSimulation(int rows, int cols, Scanner scanner) {
        int currCol = 0;
        int currRow = 0;
        String choice = "";
    
        System.out.println("\nHere is the color code:");
        System.out.printf("%-11s %-11s %-11s %s\n%-11s %-11s %-11s %s\n",
            "r: red", "c: cyan", "y: yellow", "w: white",
            "g: green", "b: blue", "m: magenta", "other characters or blank: black"
        );
        
        while(Mosaics.isOpen() && currRow < rows) {
            if (currCol == 0) {
                System.out.print("Row #" + (currRow + 1) + "? ");
                choice = scanner.nextLine();
            }
            Mosaics.setColor(currRow, currCol, returnGridColor(choice, currCol));
            currCol++;
            if (currCol >= cols) {
                currCol = 0;
                currRow++;
            }
        }

        System.out.println("Manual entry complete. Starting color rotation, close window to exit.");
        while (Mosaics.isOpen()) {
            Mosaics.delay(1000);
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    Color currColor = Mosaics.getColor(r, c);
                    Color rotatedColor = rotateColor(currColor);
                    Mosaics.setColor(r, c, rotatedColor);
                }
            }
        }
    }

    private static Color returnGridColor(String choice, int i) {
        if (choice == null || i < 0 || i >= choice.length()) {
            return Color.BLACK; //error case
        }

        char ch = Character.toLowerCase(choice.charAt(i));
        return switch (ch) {
            case 'r' -> Color.RED;
            case 'g' -> Color.GREEN;
            case 'b' -> Color.BLUE;
            case 'y' -> Color.YELLOW;
            case 'c' -> Color.CYAN;
            case 'm' -> Color.MAGENTA;
            case 'w' -> Color.WHITE;
            default -> Color.BLACK;
        };
    }

    private static Color rotateColor(Color color) {
        if (Color.RED.equals(color)) {
            return Color.CYAN;
        } else if (Color.CYAN.equals(color)) {
            return Color.YELLOW;
        } else if (Color.YELLOW.equals(color)) {
            return Color.WHITE;
        } else if (Color.WHITE.equals(color)) {
            return Color.GREEN;
        } else if (Color.GREEN.equals(color)) {
            return Color.BLUE;
        } else if (Color.BLUE.equals(color)) {
            return Color.MAGENTA;
        } else if (Color.MAGENTA.equals(color)) {
            return Color.BLACK;
        } else {
            return Color.RED;
        }
    }
}