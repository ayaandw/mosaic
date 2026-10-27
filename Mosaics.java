import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Mosaics {
    
    private static JFrame window;
    private static MosaicsPanel canvas;
    private static boolean use3DEffect = true;
    private static int mosaicRows;
    private static int mosaicCols;

    public static void open() {
        open(20, 20, 15, 15);
    }

    public static void open(int rows, int columns) {
        open(rows, columns, 15, 15);
    }

    public static void open(int rows, int columns, int blockWidth, int blockHeight) {
        if(window != null) {
            window.dispose();
        }
        canvas = new MosaicsPanel(rows, columns, blockWidth, blockHeight);
        mosaicRows = rows;
        mosaicCols = columns;
        if(!use3DEffect) {
            canvas.setGroutingColor(null);
            canvas.setUse3D(false);
        }
        window = new JFrame("Mosaics Editor Window");
        window = new JFrame("Mosaic Window");
        window.setContentPane(canvas);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        if(window.getWidth() > screen.width - 20 || window.getHeight() > screen.height - 100) {
            int w = window.getWidth();
            int h = window.getHeight();
            if(window.getWidth() > screen.width - 20) {
                w = screen.width - 20;
            }
            if(window.getHeight() > screen.height - 100) {
                h = screen.height - 100;
            }
            window.setSize(w, h);
        }        
        window.setLocation((screen.width - window.getWidth()) / 2, (screen.height - window.getHeight()) / 2);
        window.setVisible(true);
    }

    public static void close() {
        if(window != null) {
            window.dispose();
            window = null;
            canvas = null;
        }
    }

    public static boolean isOpen() {
        return (window != null);
    }

    public static void delay(int milliseconds) {
        if(milliseconds > 0) {
            try {Thread.sleep(milliseconds);}
            catch (InterruptedException e) {}
        }
    }

    public static Color getColor(int row, int col) {
        if(canvas == null) {
            return Color.black;
        }
        return canvas.getColor(row, col);
    }

    public static int getRed(int row, int col) {
        if(canvas == null) {
            return 0;
        }
        if(row < 0 || row >= mosaicRows || col < 0 || col >= mosaicCols) {
            throw new IllegalArgumentException("(row, col) = (" + row + "," + col + ") is not in the mosaic.");
        }
        return canvas.getRed(row, col);
    }

    public static int getGreen(int row, int col) {
        if(canvas == null) {
            return 0;
        }
        if(row < 0 || row >= mosaicRows || col < 0 || col >= mosaicCols) {
            throw new IllegalArgumentException("(row, col) = (" + row + "," + col + ") is not in the mosaic.");
        }
        return canvas.getGreen(row, col);
    }

    public static int getBlue(int row, int col) {
        if(canvas == null) {
            return 0;
        }
        if(row < 0 || row >= mosaicRows || col < 0 || col >= mosaicCols) {
            throw new IllegalArgumentException("(row, col) = (" + row + "," + col + ") is not in the mosaic.");
        }
        return canvas.getBlue(row, col);
    }

    public static void setColor(int row, int col, Color c) {
        if(canvas == null) {
            return;
        }
        if(row < 0 || row >= mosaicRows || col < 0 || col >= mosaicCols) {
            throw new IllegalArgumentException("(row, col) = (" + row + "," + col + ") is not in the mosaic.");
        }
        canvas.setColor(row, col, c);
    }

    public static void setColor(int row, int col, int red, int green, int blue) {
        if(canvas == null) {
            return;
        }
        if(row < 0 || row >= mosaicRows || col < 0 || col >= mosaicCols) {
            throw new IllegalArgumentException("(row, col) = (" + row + "," + col + ") is not in the mosaic.");
        }
        canvas.setColor(row, col, red, green, blue);
    }

    public static void fill(Color c) {
        canvas.fill(c);
    }

    public static void fill(int red, int green, int blue) {
        canvas.fill(red, green, blue);
    }

    public static void fillRandomly() {
        canvas.fillRandomly();
    }

    public static void setUse3DEffect(boolean use3D) {
        use3DEffect = use3D;
        if(canvas != null) {
            canvas.setGroutingColor(use3DEffect? Color.GRAY : null);
            canvas.setUse3D(use3DEffect);
            canvas.repaint();
        }
    }

    public static void setShapeType(String shapeType) {
        if(canvas != null) {
            canvas.setShapeType(shapeType);
        }
    }

    public boolean getUse3DEffect() {
        return use3DEffect;
    }

    public static void exportImage(String fileName) {
        if (canvas != null) {
            BufferedImage img = canvas.getImage();
            MosaicExporter.saveMosaic(img, fileName);
        } else {
            System.out.println("Mosaic window is not open.");
        }
    }
}