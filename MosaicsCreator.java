import java.util.Scanner;

/**
 * MosaicsCreator is the control center for all the programs
 * it sets everything up, interacts with the user, and activates the different programs.
 */
public class MosaicsCreator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Mosaic Creator Studio!");
        System.out.print("How many rows do you want in your mosaic? ");
        int rows = scanner.nextInt();
        System.out.print("How many columns do you want in your mosaic? ");
        int cols = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nChoose your mosaic shape style:");
        System.out.println("  1. Rectangles");
        System.out.println("  2. Circles");
        System.out.println("  3. Rounded Rectangles");
        System.out.println("  4. Diamonds");
        System.out.print("Select shape option (1-4): ");
        int shapeChoice = scanner.nextInt();
        scanner.nextLine();

        String chosenShape = switch (shapeChoice) {
            case 2 -> MosaicsPanel.SHAPE_CIRCLE;
            case 3 -> MosaicsPanel.SHAPE_ROUND_RECT;
            case 4 -> MosaicsPanel.SHAPE_DIAMOND;
            default -> MosaicsPanel.SHAPE_RECT;
        };

        // 3. Program Mode Selection Menu
        System.out.println("\nChoose execution mode:");
        System.out.println("  1. Manual Row Coloring & Infinite Animation");
        System.out.println("  2. Game of Life Simulation");
        System.out.println("  3. Wave & Ripple Generator");
        System.out.println("  4. Particle & Firework Simulation");
        System.out.print("Select mode (1-4): ");
        int modeChoice = scanner.nextInt();
        scanner.nextLine();

        if (modeChoice == 2 && (rows < 10 || cols < 10)) {
            System.out.println("Note: Small grid selected for Game of Life. Expanding to minimum (15x15) for stable simulation.");
            rows = Math.max(rows, 15);
            cols = Math.max(cols, 15);
        }

        Mosaics.open(rows, cols, 40, 40);
        Mosaics.setShapeType(chosenShape);

        switch (modeChoice) {
            case 2 -> GameOfLifeEngine.runSimulation(rows, cols, 150);
            case 3 -> WaveEngine.runSimulation(rows, cols, 50);
            case 4 -> ParticleEngine.runSimulation(rows, cols, 60);
            default -> ManualArtEngine.runSimulation(rows, cols, scanner);
        }

        scanner.close();
    }
}