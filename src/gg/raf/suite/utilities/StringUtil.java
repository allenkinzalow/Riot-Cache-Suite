package gg.raf.suite.utilities;

import java.nio.ByteBuffer;

/**
 * Created by Allen Kinzalow on 9/24/2015.
 */
public final class StringUtil {

    /**
     * A method produced in order to parse a
     *  null terminating string from a ByteBuffer.
     * @param buffer
     * @return
     */
    public static String readString(ByteBuffer buffer) {
        int start = buffer.position();
        while (buffer.get() != 0);
        int len = buffer.position() - start;

        byte[] str = new byte[len];
        buffer.position(start);
        buffer.get(str, 0, len - 1);

        return new String(str, 0, len - 1);
    }

}
