import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * ParticleEngine simulates random fireworks
 * rendered across the mosaic grid.
 */
public class ParticleEngine {

    private static class Particle {
        double x, y;
        double vx, vy;
        Color color;
        int life;
        int maxLife;

        public Particle(double x, double y, double vx, double vy, Color color, int life) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = color;
            this.life = life;
            this.maxLife = life;
        }

        public void update() {
            x += vx;
            y += vy;
            vy += 0.08;
            life--;
        }
    }

    public static void runSimulation(int rows, int cols, int speedMs) {
        ArrayList<Particle> particles = new ArrayList<>();
        Random rand = new Random();

        System.out.println("Particle & Firework Simulation started. Close window to exit.");
        int frameCounter = 0;

        while (Mosaics.isOpen()) {
            Mosaics.delay(speedMs);
            frameCounter++;

            // Spawn a random firework every 15 frames
            if (frameCounter % 15 == 0) {
                double startX = rand.nextDouble() * (cols - 4) + 2;
                double startY = rows - 1;
                Color baseColor = MosaicsPanel.makeHSBColor(rand.nextDouble(), 0.9, 1.0);
                
                int numSparks = 12;
                for (int i = 0; i < numSparks; i++) {
                    double angle = (2 * Math.PI / numSparks) * i;
                    double speed = rand.nextDouble() * 1.5 + 0.8;
                    double vx = Math.cos(angle) * speed;
                    double vy = Math.sin(angle) * speed - 1.2; // Initial upward kick
                    int lifespan = rand.nextInt(15) + 25;
                    particles.add(new Particle(startX, startY, vx, vy, baseColor, lifespan));
                }
            }

            Mosaics.fill(Color.BLACK);

            for (int i = particles.size() - 1; i >= 0; i--) {
                Particle p = particles.get(i);
                p.update();

                int gridX = (int) Math.round(p.x);
                int gridY = (int) Math.round(p.y);

                if (p.life > 0 && gridX >= 0 && gridX < cols && gridY >= 0 && gridY < rows) {
                    float factor = (float) p.life / p.maxLife;
                    Color faded = new Color(
                        Math.max(0, (int)(p.color.getRed() * factor)),
                        Math.max(0, (int)(p.color.getGreen() * factor)),
                        Math.max(0, (int)(p.color.getBlue() * factor))
                    );
                    Mosaics.setColor(gridY, gridX, faded);
                } else {
                    particles.remove(i);
                }
            }
        }
    }
}