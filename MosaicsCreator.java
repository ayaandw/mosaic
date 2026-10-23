import java.awt.*;
import java.util.Scanner;

public class MosaicsCreator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Mosaic Creator!");
        System.out.print("How many rows do you want in your mosaic? ");
        int rows = scanner.nextInt();
        System.out.print("How many columns do you want in your mosaic? ");
        int cols = scanner.nextInt();
        scanner.nextLine();

        Mosaics.open(rows, cols, 40, 40);

        int currCol = 0;
        int currRow = 0;
        String choice = "";

        System.out.println("Here is the color code: ");
        System.out.printf("%-11s %-11s %-11s %s\n%-11s %-11s %-11s %s\n", "r: red", "c: cyan", "y: yellow", "w: white", "g: green", "b: blue", "m: magenta", "other characters or blank: black");

        while(Mosaics.isOpen() && currRow < rows) {
            if(currCol == 0) {
                System.out.print("Row #" + (currRow + 1) + "? ");
                choice = scanner.nextLine();
            }
            Mosaics.setColor(currRow, currCol, returnGridColor(choice, currCol));
            currCol++;
            if(currCol >= cols) {
                currCol = 0;
                currRow++;
            }
        }

        while(Mosaics.isOpen()) {
            Mosaics.delay(1000);
            currCol = 0;
            currRow = 0;
            for(currRow = 0; currRow < rows; currRow++) {
                for(currCol = 0; currCol < cols; currCol++) {
                    Color currColor = Mosaics.getColor(currRow, currCol);                    
                    Color rotatedColor = rotateColor(currColor);
                    Mosaics.setColor(currRow, currCol, rotatedColor);
                }
            }
        }
        scanner.close();
    }

    private static Color returnGridColor(String choice, int i) {
        if(choice == null || i < 0 || i >= choice.length()) {
            return Color.BLACK;
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
        if(color.equals(Color.RED)) {
            return Color.CYAN;
        }
        else if(color.equals(Color.CYAN)) {
            return Color.YELLOW;
        }
        else if(color.equals(Color.YELLOW)) {
            return Color.WHITE;
        }
        else if(color.equals(Color.WHITE)) {
            return Color.GREEN;
        }
        else if(color.equals(Color.GREEN)) {
            return Color.BLUE;
        }
        else if(color.equals(Color.BLUE)) {
            return Color.MAGENTA;
        }
        else if(color.equals(Color.MAGENTA)) {
            return Color.BLACK;
        }
        else {
            return Color.RED;
        }
    }
}
