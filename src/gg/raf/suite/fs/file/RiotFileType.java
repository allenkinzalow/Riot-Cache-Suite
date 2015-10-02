package gg.raf.suite.fs.file;

import gg.raf.suite.fs.file.dds.DDSFile;

/**
 * Created by Allen Kinzalow on 10/1/2015.
 */
public enum RiotFileType {

    DDS("dds");

    private String extension;

    RiotFileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static RiotFileType typeForExtension(String extension) {
        System.out.println("Extension: " + extension);
        for(RiotFileType type : RiotFileType.values())
            if(type.getExtension().equalsIgnoreCase(extension))
                return type;
        return null;
    }

    public static RiotFile wrapFileType(String path, RiotFile file) {
        RiotFileType type = typeForExtension(path.substring(path.lastIndexOf('.') + 1, path.length()));
        if(type == null)
            return file;
        switch(type) {
            case DDS:
                System.out.println("DDS File!");
                return new DDSFile(file);
        }
        return file;
    }


}
