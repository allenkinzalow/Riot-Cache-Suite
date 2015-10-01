package gg.raf.suite.fs.container;

import gg.raf.suite.fs.archive.ArchiveFile;
import gg.raf.suite.fs.archive.ReleaseManifest;
import gg.raf.suite.fs.file.RiotPath;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;

/**
 * Created by Allen Kinzalow on 9/24/2015.
 */
public class PathContainer extends Container<SortedMap<String,ReleaseManifest>> {

    /**
     * A HashMap of the following:
     *  key: A string directory of the riot cache
     *  value: a list of the manifest for archives that contain files
     *      in this directory
     */
    public PathContainer(SortedMap<String, ReleaseManifest> container) {
        super(container);
    }

    @Override
    public void populate() {
        try {
            File directory = new File(this.getRootPath());
            for (File file : directory.listFiles()) {
                for (final File subFile : file.listFiles()) {
                    if (subFile.getName().endsWith(".raf")) {
                        ArchiveFile archiveFile = new ArchiveFile(subFile);
                        archiveFile.initiate();
                        for(RiotPath path : archiveFile.getPathEntries()) {
                            if(!container.containsKey(path.getPath())) {
                                container.put(path.getPath(), archiveFile.getManifest());
                            } else {
                                ReleaseManifest manifest = container.get(path.getPath());
                                if(archiveFile.getManifest().getReleaseNumber().compareTo(manifest.getReleaseNumber()) > 0)
                                    container.put(path.getPath(), archiveFile.getManifest());
                            }
                        }
                    }
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieve the container.
     * @return
     */
    public SortedMap<String,ReleaseManifest> getContainer() {
        return container;
    }

}
