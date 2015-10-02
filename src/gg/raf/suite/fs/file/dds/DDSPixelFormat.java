package gg.raf.suite.fs.file.dds;

import gg.raf.suite.fs.file.dds.flags.DDSPixelFormatFlags;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen Kinzalow on 10/1/2015.
 *
 * Surface Pixel Format
 *
 * Source Reference: https://msdn.microsoft.com/en-us/library/bb943984(v=vs.85).aspx
 */
public class DDSPixelFormat {

    /**
     * Structure size
     */
    private int dwSize;

    /**
     * Flags which indicate what type of data is in the surface.
     */
    private int dwFlags;

    private List<DDSPixelFormatFlags> flags;

    /**
     * Four-character codes for specifying compressed or custom formats.
     *  Possible values include: DXT1, DXT2, DXT3, DXT4, DXT5.
     *  A FourCC of DX10 indicated the presence of the DXT10 additional header,
     *      and the dxgiFormat number of that structure indicates the true format.
     *  When using a four-characetr code, dwFlags must include DDPF_FOURCC(0x4).
     */
    private int dwFourCC;

    /**
     * The ASCII representation of dwFourCC
     */
    private String dwFourCCString;

    /**
     * Number of bits in an RGB(possibly including alpha) format. Valid when dwFlags includes
     *  DDPF_RGB, DDPF_LUMINANCE, or DDPF_YUV.
     */
    private int dwRGBBitCount;

    /**
     * Red (or luminance or Y) mask for reading color data.
     * For instance, given the A8R8G8B8 format, the red mask
     *  would be 0x00ff0000.
     */
    private int dwRBitMask;

    /**
     * Green (or U) mask for reading color data.
     * For instance, given the A8R8G8B8 format, the green mask
     *  would be 0x0000ff00.
     */
    private int dwGBitMask;

    /**
     * Blue (or V) mask for reading color data.
     * For instance, given the A8R8G8B8 format, the blue mask
     *  would be 0x000000ff.
     */
    private int dwBBitMask;

    /**
     * Alpha mask for reading alpha data.
     * dwFlags must include DDPF_ALPHAPIXELS or DDPF_ALPHA.
     * For instance, given the A8R8G8B8 format, the alpha mask
     *  would be 0xff000000.
     */
    private int dwABitMask;

    public DDSPixelFormat() {
        flags = new ArrayList<>();
    }

    public void populateFlags() {
        for(DDSPixelFormatFlags f : DDSPixelFormatFlags.values())
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

    public int getDwFourCC() { return dwFourCC; }

    public String getDwFourCCString() {
        return dwFourCCString;
    }

    public void setDwFourCC(int dwFourCC) { this.dwFourCC = dwFourCC; }

    public void setDwFourCCString(String dwFourCC) {
        this.dwFourCCString = dwFourCC;
    }

    public int getDwRGBBitCount() {
        return dwRGBBitCount;
    }

    public void setDwRGBBitCount(int dwRGBBitCount) {
        this.dwRGBBitCount = dwRGBBitCount;
    }

    public int getDwRBitMask() {
        return dwRBitMask;
    }

    public void setDwRBitMask(int dwRBitMask) {
        this.dwRBitMask = dwRBitMask;
    }

    public int getDwGBitMask() {
        return dwGBitMask;
    }

    public void setDwGBitMask(int dwGBitMask) {
        this.dwGBitMask = dwGBitMask;
    }

    public int getDwBBitMask() {
        return dwBBitMask;
    }

    public void setDwBBitMask(int dwBBitMask) {
        this.dwBBitMask = dwBBitMask;
    }

    public int getDwABitMask() {
        return dwABitMask;
    }

    public void setDwABitMask(int dwABitMask) {
        this.dwABitMask = dwABitMask;
    }

    public List<DDSPixelFormatFlags> getFlags() { return flags; }

}
