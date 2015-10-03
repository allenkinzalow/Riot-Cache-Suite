package gg.raf.suite.fs.file.dds.decompressor;

import gg.raf.suite.fs.file.dds.color.Color24;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Allen Kinzalow on 10/3/2015.
 *
 * Source: http://worldwind31.arc.nasa.gov/svn/trunk/WorldWind/src/gov/nasa/worldwind/formats/dds/DXT3Decompressor.java
 * Source: https://msdn.microsoft.com/en-us/library/windows/desktop/bb694531(v=vs.85).aspx
 */
public class DXT3Decompressor implements Decompressor {

    public static final int DXT3_BLOCK_SIZE = 4;

    @Override
    public BufferedImage decompress(ByteBuffer buffer, int width, int height) {
        return decodeDxt3Buffer(buffer, width, height);
    }

    private BufferedImage decodeDxt3Buffer(ByteBuffer buffer, int width, int height) {
        if (width < DXT3_BLOCK_SIZE || height < DXT3_BLOCK_SIZE) {
            throw new IllegalArgumentException("Invalid width height");
        }
        try {
            if (buffer.order() != ByteOrder.LITTLE_ENDIAN) {
                buffer.order(ByteOrder.LITTLE_ENDIAN);
            }

            int numTilesWide = width / DXT3_BLOCK_SIZE;
            int numTilesHigh = height / DXT3_BLOCK_SIZE;

            // 8 bit per color ARGB packed in to an integer as a8r8g8b8
            int[] pixels = new int[DXT3_BLOCK_SIZE * width];

            BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);

            for (int row = 0; row < numTilesHigh; row++) {
                for (int col = 0; col < numTilesWide; col++) {
                    long alphaData = buffer.getLong();
                    short minColor = buffer.getShort();
                    short maxColor = buffer.getShort();
                    int colorIndexMask = buffer.getInt();

                    Color24[] lookupTable = Color24.expandLookupTable(minColor, maxColor);

                    for (int k = DXT3_BLOCK_SIZE * DXT3_BLOCK_SIZE - 1; k >= 0; k--) {
                        int alpha = (int) (alphaData >>> (k * 4)) & 0xF; // Alphas are just 4 bits per pixel
                        alpha <<= 4;

                        int colorIndex = (colorIndexMask >>> k * 2) & 0x03;

                        // No need to multiply alpha, it is already pre-multiplied
//                      Color24 color = Color24.multiplyAlpha(lookupTable[colorIndex], alpha );

                        Color24 color = lookupTable[colorIndex];
                        int pixel8888 = (alpha << 24) | color.getPixel888();

                        int h = k / DXT3_BLOCK_SIZE, w = k % DXT3_BLOCK_SIZE;
                        int pixelIndex = h * width + (col * DXT3_BLOCK_SIZE + w);

                        pixels[pixelIndex] = pixel8888;
                    }
                }

                result.setRGB(0, row * DXT3_BLOCK_SIZE, width, DXT3_BLOCK_SIZE, pixels, 0, width);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
