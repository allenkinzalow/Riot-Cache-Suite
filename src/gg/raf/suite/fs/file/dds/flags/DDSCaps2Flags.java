package gg.raf.suite.fs.file.dds.flags;

/**
 * Created by Allen Kinzalow on 10/1/2015.
 *
 * Required flags for a cube map texture.
 */
public enum DDSCaps2Flags implements DDSFlags {

    DDSCAPS2_CUBEMAP(0x200),
    DDSCAPS2_CUBEMAP_POSITIVEX(0x400),
    DDSCAPS2_CUBEMAP_NEGATIVEX(0x800),
    DDSCAPS2_CUBEMAP_POSITIVEY(0x1000),
    DDSCAPS2_CUBEMAP_NEGATIVEY(0x2000),
    DDSCAPS2_CUBEMAP_POSITIVEZ(0x4000),
    DDSCAPS2_CUBEMAP_NEGATIVEZ(0x8000),
    DDSCAPS2_VOLUME(0x200000);

    private int flag;

    DDSCaps2Flags(int flag) {
        this.flag = flag;
    }

    @Override
    public int getFlag() {
        return flag;
    }

}
