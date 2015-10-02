package gg.raf.suite.fs.file.dds.flags;

/**
 * Created by Allen Kinzalow on 10/1/2015.
 *
 * Values which indicate what type of data is in the surface.
 */
public enum DDSPixelFormatFlags implements DDSFlags {

    DDPF_ALPHAPIXELS(0x1),
    DDPF_ALPHA(0x2),
    DDPF_FOURCC(0x4),
    DDPF_RGB(0x40),
    DDPF_YUV(0x200),
    DDPF_LUMINANCE(0x20000);

    private int flag;

    DDSPixelFormatFlags(int flag) {
        this.flag = flag;
    }

    @Override
    public int getFlag() {
        return flag;
    }
}
