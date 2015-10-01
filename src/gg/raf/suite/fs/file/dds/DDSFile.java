package gg.raf.suite.fs.file.dds;

import gg.raf.suite.fs.file.RiotFile;

/**
 * Created by Allen Kinzalow on 9/30/2015.
 */
public class DDSFile extends RiotFile {

    public DDSFile(int hash, int dataOffset, int dataSize, int pathListIndex) {
        super(hash, dataOffset, dataSize, pathListIndex);
    }

    @Override
    public void decode() {

    }
}
