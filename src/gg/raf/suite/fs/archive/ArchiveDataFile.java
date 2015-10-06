package gg.raf.suite.fs.archive;

import gg.raf.suite.fs.file.RiotFile;
import gg.raf.suite.tasks.ExportCache;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by Allen Kinzalow on 9/24/2015.
 *
 * Representation of the corresponding data file of
 *  a Riot Archive File{@link ArchiveFile} that
 *  contains support for fully decoding the raw data
 *  file and populating a list of files entries.
 */
public class ArchiveDataFile {

    /**
     * The data file for the archive.
     */
    private RandomAccessFile dataFile;

    /**
     * An array list of file entries defined in the riot archive fs.
     */
    private ArrayList<RiotFile> fileEntries = new ArrayList<>();

    /**
     * Has the archive data file been initiated?
     * We include this here so that the data file can be
     * initiated and decoded when it's actually used instead of
     * when its created.
     */
    private boolean initiated = false;

    /**
     * Size of uncompressed data.
     */
    private int uncompressedSize = 0;

    /**
     * Construct a riot archive data file instance with a given
     *  data File.
     * @param dataFile
     */
    public ArchiveDataFile(File dataFile) throws FileNotFoundException {
        this.dataFile = new RandomAccessFile(dataFile, "rw");
    }

    /**
     * Setup and begin decoding individual files within the archive data fs.
     */
    public void initiate() {
        try {
            byte[] fileData = new byte[(int)dataFile.length()];
            this.dataFile.readFully(fileData);

            decodeFiles(fileData);
            initiated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Decode and store all of the individual files within the archive data file.
     * @param fileData  The raw data of the archive dat fs.
     */
    public void decodeFiles(byte[] fileData) {
        //IOBuffer buffer = new IOBuffer(fileData);
        ByteBuffer buffer = ByteBuffer.wrap(fileData);
        try {
            for (RiotFile fileEntry : fileEntries) {
                byte[] entryData = new byte[fileEntry.getDataSize()];
                buffer.position(fileEntry.getDataOffset());
                buffer.get(entryData, 0, fileEntry.getDataSize());

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                Inflater inflater = new Inflater();
                inflater.setInput(entryData);
                byte[] tmp = new byte[4 * 1024];
                try{
                    while(!inflater.finished()){
                        int size = inflater.inflate(tmp);
                        bos.write(tmp, 0, size);
                    }
                    byte[] uncompressed = bos.toByteArray();
                    uncompressedSize += uncompressed.length;
                    bos.close();
                    fileEntry.setFileData(uncompressed);
                    fileEntry.setCompressed(true);
                } catch (Exception e){
                    fileEntry.setFileData(entryData);
                    fileEntry.setCompressed(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addFile(int hash, File file) {
        int fileIndex = getFileIndexForHash(hash);
        RiotFile toReplace = fileEntries.get(fileIndex);
        if(toReplace != null) {
            byte[] fileData = new byte[(int)file.length()];
            byte[] compressed = null;
            try {
                DataInputStream is = new DataInputStream(new FileInputStream(file));
                is.readFully(fileData);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                Deflater deflater = new Deflater();
                deflater.setInput(fileData);
                byte[] tmp = new byte[1024];
                int size = deflater.deflate(tmp);
                bos.write(tmp, 0, size);
                compressed = bos.toByteArray();
                bos.close();
                if(compressed == null || compressed.length == 0)
                    return false;
                int sizeOffset = fileData.length - toReplace.getFileData().length;
                toReplace.setDataOffset(toReplace.getDataOffset() + sizeOffset);
                toReplace.setDataSize(compressed.length);
                toReplace.setFileData(fileData);
                for(int i = fileIndex + 1; i < fileEntries.size(); i++) {
                    RiotFile fil = fileEntries.get(i);
                    fil.setDataOffset(fil.getDataOffset() + sizeOffset);
                }

                ByteBuffer buffer = ByteBuffer.allocate(uncompressedSize + sizeOffset);
                for(RiotFile rf : fileEntries) {
                    bos = new ByteArrayOutputStream();
                    deflater.setInput(rf.getFileData());
                    tmp = new byte[rf.getFileData().length];
                    size = deflater.deflate(tmp);
                    bos.write(tmp, 0, size);
                    System.out.println("Pos: " + buffer.position() + " Cap: " + buffer.capacity() + " bos: " + bos.toByteArray().length + " Size: " + rf.getDataSize());
                    buffer.put(bos.toByteArray(), rf.getDataOffset(), rf.getDataSize());
                }

                dataFile.write(buffer.array());
                bos.close();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * Get a file for its hash.
     * @param hash
     * @return
     */
    public int getFileIndexForHash(int hash) {
        for(int i = 0; i < fileEntries.size(); i++) {
            RiotFile file = fileEntries.get(i);
            if (file != null && file.getHash() == hash)
                return i;
        }
        return -1;
    }

    /**
     * Return the fs entries.
     * @return
     */
    public ArrayList<RiotFile> getFileEntries() {
        return fileEntries;
    }

    /**
     * Return a fs entry for its given path index.
     * @param index
     * @return
     */
    public RiotFile getFileForPathIndex(int index) {
        for(RiotFile file : fileEntries)
            if(file.getPathListIndex() == index)
                return file;
        return null;
    }

    public boolean isInitiated() {
        return initiated;
    }

}
