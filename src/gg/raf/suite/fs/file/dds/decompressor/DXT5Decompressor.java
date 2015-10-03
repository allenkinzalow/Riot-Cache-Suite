package gg.raf.suite.fs.file.dds.decompressor;

import gg.raf.suite.fs.file.dds.color.Color24;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Allen Kinzalow on 10/3/2015.
 *
 * Source: https://msdn.microsoft.com/en-us/library/windows/desktop/bb694531(v=vs.85).aspx
 */
public class DXT5Decompressor implements Decompressor {

    public static final int DXT5_BLOCK_SIZE = 4;

    @Override
    public BufferedImage decompress(ByteBuffer buffer, int width, int height) {
        return decodeDXT5Buffer(buffer, width, height);
    }

    public BufferedImage decodeDXT5Buffer(ByteBuffer buffer, int width, int height) {
        if (width < DXT5_BLOCK_SIZE || height < DXT5_BLOCK_SIZE) {
            throw new IllegalArgumentException("Invalid width height");
        }
        try {
            if (buffer.order() != ByteOrder.LITTLE_ENDIAN) {
                buffer.order(ByteOrder.LITTLE_ENDIAN);
            }

            int numTilesWide = width / DXT5_BLOCK_SIZE;
            int numTilesHigh = height / DXT5_BLOCK_SIZE;

            System.out.println("Width: " + width + " Height: " + height + " numtiles: " + numTilesHigh);

            // 8 bit per color ARGB packed in to an integer as a8r8g8b8
            int[] pixels = new int[DXT5_BLOCK_SIZE * width];

            BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);

            for (int row = 0; row < numTilesHigh; row++) {
                for (int col = 0; col < numTilesWide; col++) {

                    short[] alphas = new short[8];
                    short[] bits = new short[6];

                    alphas[0] = buffer.get();
                    alphas[1] = buffer.get();

                    for(int i = 0; i < 6; i++) {
                        bits[i] = buffer.get();
                        //System.out.println("bit" + i + ": " + bits[i]);
                    }

                    if(alphas[0] > alphas[1]) {
                        alphas[2] = (short) (6/7*alphas[0] + 1/7*alphas[1]); // bit code 010
                        alphas[3] = (short) (5/7*alphas[0] + 2/7*alphas[1]); // bit code 011
                        alphas[4] = (short) (4/7*alphas[0] + 3/7*alphas[1]); // bit code 100
                        alphas[5] = (short) (3/7*alphas[0] + 4/7*alphas[1]); // bit code 101
                        alphas[6] = (short) (2/7*alphas[0] + 5/7*alphas[1]); // bit code 110
                        alphas[7] = (short) (1 / 7 * alphas[0] + 6/7*alphas[1]); // bit code 111
                    } else {
                        // 4 interpolated alpha values.
                        alphas[2] = (short) (4/5 * alphas[0] + 1/5*alphas[1]); // bit code 010
                        alphas[3] = (short) (3/5 * alphas[0] + 2/5*alphas[1]); // bit code 011
                        alphas[4] = (short) (2/5 * alphas[0] + 3/5*alphas[1]); // bit code 100
                        alphas[5] = (short) (1/5 * alphas[0] + 4/5*alphas[1]); // bit code 101
                        alphas[6] = 0;                         // bit code 110
                        alphas[7] = 255;                       // bit code 111
                    }

                    short minColor = buffer.getShort();
                    short maxColor = buffer.getShort();
                    int colorIndexMask = buffer.getInt();

                    Color24[] lookupTable = Color24.expandLookupTable(minColor, maxColor);

                    for (int k = DXT5_BLOCK_SIZE * DXT5_BLOCK_SIZE - 1; k >= 0; k--) {
                        int alpha = (int) ((alphas[0] > alphas[1] ? alphas[0] : alphas[1]) >>> (k * 4)) & 0xF; // Alphas are just 4 bits per pixel
                        alpha <<= 4;

                        int colorIndex = (colorIndexMask >>> k * 2) & 0x03;

                        // No need to multiply alpha, it is already pre-multiplied
//                      Color24 color = Color24.multiplyAlpha(lookupTable[colorIndex], alpha );

                        Color24 color = lookupTable[colorIndex];
                        int pixel8888 = (alpha << 24) | color.getPixel888();

                        int h = k / DXT5_BLOCK_SIZE, w = k % DXT5_BLOCK_SIZE;
                        int pixelIndex = h * width + (col * DXT5_BLOCK_SIZE + w);

                        pixels[pixelIndex] = pixel8888;
                    }
                }

                result.setRGB(0, row * DXT5_BLOCK_SIZE, width, DXT5_BLOCK_SIZE, pixels, 0, width);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
