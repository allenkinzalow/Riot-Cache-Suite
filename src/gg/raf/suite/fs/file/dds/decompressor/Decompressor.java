package gg.raf.suite.fs.file.dds.decompressor;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

/**
 * Created by Allen Kinzalow on 10/3/2015.
 */
public interface Decompressor {

    BufferedImage decompress(ByteBuffer buffer, int width, int height);

}
