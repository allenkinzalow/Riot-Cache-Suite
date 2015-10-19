package gg.raf.suite.fs.file;

import gg.raf.suite.fs.file.anm.AnmFile;
import gg.raf.suite.fs.file.dds.DDSFile;
import gg.raf.suite.fs.file.skl.SklFile;
import gg.raf.suite.fs.file.skn.SknFile;

/**
 * Created by Allen Kinzalow on 10/1/2015.
 */
public enum RiotFileType {

    DDS("dds"),
    ANM("anm"),
    SKL("skl"),
    SKN("skn"),
    DEFAULT("");

    private String extension;

    RiotFileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static RiotFileType typeForExtension(String extension) {
        for(RiotFileType type : RiotFileType.values())
            if(type.getExtension().equalsIgnoreCase(extension))
                return type;
        return DEFAULT;
    }

    public static RiotFile wrapFileType(String path, RiotFile file) {
        RiotFileType type = typeForExtension(path.substring(path.lastIndexOf('.') + 1, path.length()));
        if(type == null)
            return file;
        switch(type) {
            case DDS:
                return new DDSFile(file);
            case ANM:
                return new AnmFile(file);
            case SKL:
                return new SklFile(file);
            case SKN:
                return new SknFile(file);
        }
        return file;
    }


}
