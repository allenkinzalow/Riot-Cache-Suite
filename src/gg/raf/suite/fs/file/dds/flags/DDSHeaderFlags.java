package gg.raf.suite.fs.file.dds.flags;

/**
 * Created by Allen Kinzalow on 10/1/2015.
 *
 * Flags to indicate which members contain valid data.
 */
public enum DDSHeaderFlags implements DDSFlags {

    DDSD_CAPS(0x1),
    DDSD_HEIGHT(0x2),
    DDSD_WIDTH(0x4),
    DDSD_PITCH(0x8),
    DDSD_PIXELFORMAT(0x1000),
    DDSD_MIPMAPCOUNT(0x20000),
    DDSD_LINEARSIZE(0x80000),
    DDSD_DEPTH(0x800000);

    private int flag;

    DDSHeaderFlags(int flag) {
        this.flag = flag;
    }

    @Override
    public int getFlag() {
        return flag;
    }

}
