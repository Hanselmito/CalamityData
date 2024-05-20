package com.github.Hanselmito.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Takes an image path as input and returns a byte array.
 * A File object is created from the provided image path.
 * It checks if the image file exists. If it does not exist, an error message is printed and null is returned.
 * It tries to read the image into a BufferedImage object. If the image cannot be read, an error message is printed and null is returned.
 * A ByteArrayOutputStream is created, which is an output stream that can be used to capture the data in a byte array.
 * The image is written to the output stream in PNG format.
 * Finally, the byte array representing the image is returned.
 */
public class ImagenAByts {
    public static byte[] imageToBytes(String imagePath) {
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            System.err.println("La imagen no existe: " + imagePath);
            return null;
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            if (bufferedImage == null) {
                System.err.println("no as puesto una imagen: " + imagePath);
                return null;
            }

            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteOutStream);
            return byteOutStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
