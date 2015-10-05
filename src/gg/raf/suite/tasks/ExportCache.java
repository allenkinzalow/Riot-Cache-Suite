package gg.raf.suite.tasks;

import gg.raf.suite.RAFSuite;
import gg.raf.suite.fs.archive.ArchiveFile;
import javafx.scene.control.TextArea;

import java.io.File;

/**
 * Created by Allen Kinzalow on 9/30/2015.
 */
public class ExportCache implements Runnable {

    /**
     * The directory to write the cache to.
     */
    private File chosenDir;

    /**
     * A reference to the logger.
     */
    private TextArea logger;

    public ExportCache(File file, TextArea logger) {
        this.chosenDir = file;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            File directory = new File(RAFSuite.FILE_PATH);
            for (File file : directory.listFiles()) {
                String path = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('\\') + 1, file.getAbsolutePath().length());
                for (final File subFile : file.listFiles()) {
                    if (subFile.getName().endsWith(".raf")) {
                        logger.appendText("\nWriting Archive: " + path);
                        ArchiveFile archiveFile = new ArchiveFile(subFile);
                        archiveFile.initiate();
                        archiveFile.getArchiveDataFile().initiate();
                        archiveFile.writeArchive(chosenDir.getAbsolutePath() + "\\");

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
