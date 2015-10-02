package gg.raf.suite.fs.file.dds.flags;

/**
 * Created by Allen Kinzalow on 10/1/2015.
 *
 * Specifies the complexity of the surfaces stored.
 */
public enum DDSCapsFlags implements DDSFlags {

    DDSCAPS_COMPLEX(0x8),
    DDSCAPS_MIPMAP(0x400000),
    DDSCAPS_TEXTURE(0x1000);

    private int flag;

    DDSCapsFlags(int flag) {
        this.flag = flag;
    }

    @Override
    public int getFlag() {
        return flag;
    }
}
