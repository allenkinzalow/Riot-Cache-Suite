package gg.raf.suite.tasks;

import gg.raf.suite.RAFConfig;
import gg.raf.suite.RAFSuite;
import gg.raf.suite.fs.archive.ArchiveFile;
import gg.raf.suite.ui.RAFApplication;

import java.io.File;

/**
 * Created by Allen Kinzalow on 9/30/2015.
 */
public class ExportCache implements Runnable {

    @Override
    public void run() {
        try {
            File directory = new File(RAFSuite.FILE_PATH);
            String out = "C:/Users/allen_000/Desktop/League Cache/5.19/";
            for (File file : directory.listFiles()) {
                for (final File subFile : file.listFiles()) {
                    if (subFile.getName().endsWith(".raf")) {
                        System.out.println("Writing: " + subFile.getAbsolutePath());
                        ArchiveFile archiveFile = new ArchiveFile(subFile);
                        archiveFile.initiate();
                        archiveFile.getArchiveDataFile().initiate();
                        archiveFile.writeArchive(out);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
