package gg.raf.suite.fs.container;

import gg.raf.suite.fs.archive.ArchiveFile;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Allen Kinzalow on 9/24/2015.
 *
 * A direct archive container that populates an
 *  ArrayList with all decoded archives.
 *
 *  *WARNING* - Using this class will take up a
 *      substantial amount of memory since it loads
 *      the <b>ENTIRE</b> League of Legends cache in
 *      one instance - excluding parsing archive data files;
 *      however, the memory usage is still very large.
 */
public class ArchiveContainer extends Container<ArrayList<ArchiveFile>> {

    /**
     * A collection of all archive releases.
     */
    public ArchiveContainer(ArrayList<ArchiveFile> container) {
        super(container);
    }

    /**
     * Navigate through all of the releases and decode each
     *  archive that the release contains.
     * In doing so, populate the collection of archive releases.
     */
    public void populate() {
        try {
            File directory = new File(this.getRootPath());
            for (File file : directory.listFiles()) {
                for (final File subFile : file.listFiles()) {
                    if (subFile.getName().endsWith(".raf")) {
                        ArchiveFile archiveFile = new ArchiveFile(subFile);
                        archiveFile.initiate();
                        container.add(archiveFile);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Get the container.
     * @return
     */
    public ArrayList<ArchiveFile> getContainer() {
        return container;
    }

}
