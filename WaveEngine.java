import java.awt.Color;

/**
 * WaveEngine generates mathematical wave patterns
 * and ripples across the grid using trig functions
 */
public class WaveEngine {
    public static void runSimulation(int rows, int cols, int speedMs) {
        double timeStep = 0.0;

        System.out.println("Wave Generator started. Close window to exit.");

        while (Mosaics.isOpen()) {
            Mosaics.delay(speedMs);

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    double centerX = cols / 2.0;
                    double centerY = rows / 2.0;
                    double distance = Math.sqrt(Math.pow(c - centerX, 2) + Math.pow(r - centerY, 2));

                    double wave1 = Math.sin(distance * 0.3 - timeStep);
                    double wave2 = Math.cos((r * 0.2) + (c * 0.2) + timeStep);
                    double combined = (wave1 + wave2) * 0.5;

                    double hue = (combined + 1.0) * 0.5 + (timeStep * 0.05);
                    hue = hue % 1.0;
                    
                    double brightness = 0.4 + (Math.abs(combined) * 0.6);
                    Color waveColor = MosaicsPanel.makeHSBColor(hue, 0.85, brightness);

                    Mosaics.setColor(r, c, waveColor);
                }
            }
            timeStep += 0.15;
        }
    }
}
