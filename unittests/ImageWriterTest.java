package unittests;

import org.junit.Test;
import renderer.ImageWriter;

import java.awt.*;
/**
 * Unit tests for imageWriter.Renderer class
 * @author Shoshana Chaya and Yael
 */

public class ImageWriterTest {
    /**
     * Test method for writeToImage renderer
     */
    @Test
    public void writeToImage() {
        String imagename = "image.1";
        int width = 1600;
        int height = 1000;
        int nx = 800;
        int ny = 500;
        ImageWriter imageWriter = new ImageWriter(imagename, width, height, nx, ny);
        for (int col = 0; col < ny; col++) {
            for (int row = 0; row < nx; row++) {
                if (col % 50 == 0 || row % 50 == 0) {
                    imageWriter.writePixel(row, col, Color.CYAN);
                }
            }
        }
        imageWriter.writeToImage();
    }
}