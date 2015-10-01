package gg.raf.suite.fs.archive;

import gg.raf.suite.fs.file.RiotFile;
import gg.raf.suite.fs.file.RiotPath;
import gg.raf.suite.utilities.StringUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

/**
 * Created by Allen Kinzalow on 9/24/2015.
 *
 * A representation of a Riot Archive File that includes
 *  support for fully decoding the raw archive file and its
 *  corresponding data file within a release directory.
 */
public class ArchiveFile {

    /**
     * The archive file.
     */
    private RandomAccessFile riotArchiveFile;

    /**
     * The raw data.
     */
    private byte[] fileData;

    /**
     * The magic number - 0x18be0ef0
     */
    private int magicNumber;

    /**
     * The version of this file.
     */
    private int version;

    /**
     * A value utilized by riot games.
     */
    private int managerIndex;

    /**
     * The offlist of the file list in the archive file.
     */
    private int fileListOffset;

    /**
     * The offlist of the path list in the archive file.
     */
    private int pathListOffset;

    /**
     * The number of files.
     */
    private int fileListCount;

    /**
     * The number of paths.
     */
    private int pathListCount;

    /**
     * The size of bytes in a path list.
     */
    private int pathListSize;

    /**
     * The release manifest for this archive
     *  used for identification.
     */
    private ReleaseManifest manifest;

    /**
     * An array list of the path entries defined in the riot archive fs.
     */
    private ArrayList<RiotPath> pathEntries = new ArrayList<>();

    /**
     * The associated data fs with this riot archive fs.
     */
    private ArchiveDataFile archiveDataFile;

    /**
     * Construct an archive file given a directory, a release number, and a release name.
     * @param file              The directory to a file of releases.
     * @param releaseNumber     The release number.
     * @param releaseName       The release archive name within the release directory.
     */
    public ArchiveFile(File file, String releaseNumber, String releaseName) throws FileNotFoundException {
        this.manifest = new ReleaseManifest(releaseNumber, releaseName);
        File archive = new File(file.getAbsolutePath() + "/" + releaseName + "/" + releaseName);
        this.riotArchiveFile = new RandomAccessFile(archive, "rw");
        this.archiveDataFile = new ArchiveDataFile(new File(archive + ".dat"));
    }

    /**
     * Construct a riot archive file based on the direct path to
     *  a raw archive file.
     * @param file  The direct path to the raw archive file.
     */
    public ArchiveFile(File file) throws FileNotFoundException {
        this.riotArchiveFile = new RandomAccessFile(file, "rw");
        this.archiveDataFile = new ArchiveDataFile(new File(file.getAbsolutePath() + ".dat"));
        String dir = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf('\\'));
        this.manifest = new ReleaseManifest(dir.substring(dir.lastIndexOf('\\') + 1, dir.length()), file.getName());
    }

    /**
     * Construct an archive file with a given file to the archive
     *  and a manifest.
     * @param file
     * @param manifest
     */
    /*public ArchiveFile(File file, ReleaseManifest manifest) {
        this.riotArchiveFile = file;
        this.archiveDataFile = new ArchiveDataFile(new File(file.getAbsolutePath() + ".dat"));
        this.manifest = manifest;
    }*/

    /**
     * Initiate the archive file.
     */
    public void initiate() {
        try {
            fileData = new byte[(int) riotArchiveFile.length()];
            riotArchiveFile.readFully(fileData);
            /**
             * Decode the archive header, fs info, fs data, and path info.
             */
            decodeArchive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Decode the entire riot archive file.
     */
    private void decodeArchive() {
        /**
         * Wrap a byte buffer to the raw file data.
         */
        ByteBuffer buffer = ByteBuffer.wrap(this.fileData);
        /**
         * Set the byte buffer order to little endian.
         */
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        /**
         * Header
         */
        this.magicNumber = buffer.getInt();
        this.version = buffer.getInt();
        this.managerIndex = buffer.getInt();
        this.fileListOffset = buffer.getInt();
        this.pathListOffset = buffer.getInt();
        //System.out.println("Magic Number: " + this.magicNumber +  " Version: " + this.version + " Manager Index: " + this.managerIndex +  " File List Offset: " + this.fileListOffset +  " Path List Offset: " + this.pathListOffset);
        /**
         * File Entries
         */
        this.fileListCount = buffer.getInt();
        //System.out.println("File List Count: " + this.fileListCount);
        for(int fileIndex = 0; fileIndex < this.fileListCount; fileIndex++) {
            int hash = buffer.getInt();
            int dataOffset = buffer.getInt();
            int dataSize = buffer.getInt();
            int pathListIndex = buffer.getInt();
            //System.out.println("File Hash: " + hash + " Data Offset: " + dataOffset + " Data Size: " + dataSize + " PLI: " + pathListIndex);
            archiveDataFile.getFileEntries().add(new RiotFile(hash, dataOffset, dataSize, pathListIndex));
        }
        /**
         * Path Entries
         */
        int pathStringOffset = buffer.position();
        this.pathListSize = buffer.getInt();
        this.pathListCount = buffer.getInt();
        for(int pathIndex = 0; pathIndex < pathListCount; pathIndex++) {
            int pathOffset = buffer.getInt();
            int pathLength = buffer.getInt();
            //System.out.println("Path Offset: " + pathOffset + " Path Length: " + pathLength);
            pathEntries.add(new RiotPath(pathOffset, pathLength));
        }
        /**
         * Read the path strings.
         */
        for(RiotPath pathEntry : pathEntries) {
            buffer.position(pathStringOffset + pathEntry.getPathOffset());
            pathEntry.setString(StringUtil.readString(buffer));
            //System.out.println("Path Offset: " + pathEntry.getPathOffset() + " Path Length: " + pathEntry.getPathLength() + " PLI: " + pathEntries.indexOf(pathEntry) + " Path String: " + pathEntry.getPath());
        }
    }

    /**
     * Retrieve the corresponding archive data file.
     * @return
     */
    public ArchiveDataFile getArchiveDataFile() {
        return archiveDataFile;
    }

    /**
     * Retrieve the manifest for this archive.
     * @return
     */
    public ReleaseManifest getManifest() {
        return manifest;
    }

    /**
     * Retrieve the path entries for this archive.
     * @return
     */
    public ArrayList<RiotPath> getPathEntries() {
        return pathEntries;
    }

    /**
     * Get the path of an index if given.
     * @param path
     * @return
     */
    public int getPathIndex(String path) {
        for(RiotPath p : pathEntries) {
            if(p.getPath().equals(path))
                return pathEntries.indexOf(p);
        }
        return -1;
    }

}
