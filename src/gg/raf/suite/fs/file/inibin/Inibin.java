package gg.raf.suite.fs.file.inibin;

import gg.raf.suite.fs.file.RiotFile;

/**
 * Created by Allen Kinzalow on 10/18/2015.
 */
public class Inibin extends RiotFile {

    public Inibin(int hash, int dataOffset, int dataSize, int pathListIndex) {
        super(hash, dataOffset, dataSize, pathListIndex);
    }

    @Override
    public void decode() {

    }

}
