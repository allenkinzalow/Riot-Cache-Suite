package gg.raf.suite.fs.file.dds;

import gg.raf.suite.fs.file.dds.flags.DDSHeaderFlags;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen Kinzalow on 10/1/2015.
 *
 * Describes a DDS file header.
 *
 * Reference Source: https://msdn.microsoft.com/en-us/library/bb943982(v=vs.85).aspx
 */
public class DDSHeader {

    /**
     * Size of structure - this member must be set to 124.
     */
    private int dwSize;

    /**
     * Flag to indicate which members contian valid date.
     */
    private int dwFlags;

    /**
     * A list of flags
     */
    private List<DDSHeaderFlags> flags;

    /**
     * Surface height in pixels
     */
    private int dwHeight;

    /**
     * Surface width in pixels.
     */
    private int dwWidth;

    /**
     * The pitch or number of bytes per scan line in an uncompressed texture;
     *  The total number of bytes in the top level texture for a compressed texture.
     */
    private int dwPitchOrLinearSize;

    /**
     * Depth of a volume texture in pixels - otherwise unused.
     */
    private int dwDepth;

    /**
     * Number of mipmap levels.
     */
    private int dwMipMapCount;

    /**
     * Unused..?
     */
    private int[] dwReserved;

    /**
     * Pixel format for this DDS file.
     */
    private DDSPixelFormat dwPixelFormat;

    /**
     * Specifices the complexity of surfaces stored.
     */
    private int dwCaps;

    /**
     * Required for cube map textures.
     */
    private int dwCaps2;

    /**
     * Unused
     */
    private int dwCaps3;

    /**
     * Unused
     */
    private int dwCaps4;

    /**
     * Unused
     */
    private int dwReserved2;


    public DDSHeader() {
        dwPixelFormat = new DDSPixelFormat();
        dwReserved = new int[11];
        flags = new ArrayList<>();
    }

    /**
     * Populate a list of flags based on the given set.
     */
    public void populateFlags() {
        for(DDSHeaderFlags f : DDSHeaderFlags.values())
            if((dwFlags & f.getFlag()) == f.getFlag())
                flags.add(f);
    }


    public int getDwSize() {
        return dwSize;
    }

    public void setDwSize(int dwSize) {
        this.dwSize = dwSize;
    }

    public int getDwFlags() {
        return dwFlags;
    }

    public void setDwFlags(int dwFlags) {
        this.dwFlags = dwFlags;
    }

    public int getDwHeight() {
        return dwHeight;
    }

    public void setDwHeight(int dwHeight) {
        this.dwHeight = dwHeight;
    }

    public int getDwWidth() {
        return dwWidth;
    }

    public void setDwWidth(int dwWidth) {
        this.dwWidth = dwWidth;
    }

    public int getDwPitchOrLinearSize() {
        return dwPitchOrLinearSize;
    }

    public void setDwPitchOrLinearSize(int dwPitchOrLinearSize) {
        this.dwPitchOrLinearSize = dwPitchOrLinearSize;
    }

    public int getDwDepth() {
        return dwDepth;
    }

    public void setDwDepth(int dwDepth) {
        this.dwDepth = dwDepth;
    }

    public int getDwMipMapCount() {
        return dwMipMapCount;
    }

    public void setDwMipMapCount(int dwMipMapCount) {
        this.dwMipMapCount = dwMipMapCount;
    }

    public int[] getDwReserved() {
        return dwReserved;
    }

    public void setDwReserved(int[] dwReserved) {
        this.dwReserved = dwReserved;
    }

    public DDSPixelFormat getDwPixelFormat() {
        return dwPixelFormat;
    }

    public void setDwPixelFormat(DDSPixelFormat dwPixelFormat) {
        this.dwPixelFormat = dwPixelFormat;
    }

    public int getDwCaps() {
        return dwCaps;
    }

    public void setDwCaps(int dwCaps) {
        this.dwCaps = dwCaps;
    }

    public int getDwCaps2() {
        return dwCaps2;
    }

    public void setDwCaps2(int dwCaps2) {
        this.dwCaps2 = dwCaps2;
    }

    public int getDwCaps3() {
        return dwCaps3;
    }

    public void setDwCaps3(int dwCaps3) {
        this.dwCaps3 = dwCaps3;
    }

    public int getDwCaps4() {
        return dwCaps4;
    }

    public void setDwCaps4(int dwCaps4) {
        this.dwCaps4 = dwCaps4;
    }

    public int getDwReserved2() {
        return dwReserved2;
    }

    public void setDwReserved2(int dwReserved2) {
        this.dwReserved2 = dwReserved2;
    }

    public List<DDSHeaderFlags> getFlags() { return flags; }


}
