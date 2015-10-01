package gg.raf.suite.fs.file;

/**
 * Created by Allen Kinzalow on 9/24/2015.
 *
 * RiotPath withholds information regarding a file directory
 *  in the buffer of an archive file that correlates to an individual
 *  file in the archive data buffer and serves as identification for
 *  this single file. The file can be matched with a RiotPath based on
 *  the file's path list index and this RiotPath's position in the total
 *  list of directories.
 */
public class RiotPath {

    /**
     * The offset of the path in the buffer.
     */
    int pathOffset;

    /**
     * The length of the path in bytes.
     */
    int pathLength;

    /**
     * The path.
     */
    String path = "";

    public RiotPath(int pathOffset, int pathLength) {
        this.pathOffset = pathOffset;
        this.pathLength = pathLength;
    }

    /**
     * Get the path offset.
     * @return
     */
    public int getPathOffset() {
        return pathOffset;
    }

    /**
     * Get the path length.
     * @return
     */
    public int getPathLength() {
        return pathLength;
    }

    /**
     * Set the entry's path.
     * @param path
     */
    public void setString(String path) {
        this.path = path;
    }

    /**
     * Return the path.
     * @return
     */
    public String getPath() {
        return path;
    }

}
