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

    /**
     * Write a string to the buffer.
     * @param buffer
     * @param s
     */
    public static void writeString(ByteBuffer buffer, String s) {
        byte[] data = s.getBytes();
        for(byte b : data) {
            buffer.put(b);
        }
        buffer.put((byte)0);
    }

    /**
     * Convert an integer to a string based on its binary value.
     * @param value
     * @return
     */
    public static final String intToString(int value) {
        StringBuffer buf = new StringBuffer();

        buf.append((char) (value & 0xFF));
        buf.append((char) ((value & 0xFF00) >> 8));
        buf.append((char) ((value & 0xFF0000) >> 16));
        buf.append((char) ((value & 0xFF00000) >> 24));

        return buf.toString();
    }

}
