import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * MosaicExporter saves the current mosaic window
 * as a PNG to the user's drive
 */
public class MosaicExporter {
    public static void saveMosaic(BufferedImage image, String fileName) {
        if (image == null) {
            System.out.println("Error: No image canvas available to export");
            return;
        }
        try {
            File outputFile = new File(fileName);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Successfully exported your masterpiece to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save image file: " + e.getMessage());
        }
    }
}