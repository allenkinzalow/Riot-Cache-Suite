package gg.raf.suite;

import gg.raf.suite.fs.archive.ArchiveFile;
import gg.raf.suite.fs.archive.ReleaseManifest;
import gg.raf.suite.fs.container.ArchiveContainer;
import gg.raf.suite.fs.container.Container;
import gg.raf.suite.fs.container.PathContainer;
import gg.raf.suite.fs.file.RiotPath;
import gg.raf.suite.tasks.ExportCache;
import gg.raf.suite.ui.RAFApplication;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Allen Kinzalow on 9/24/2015.
 *
 * @version 1.0
 *
 * --Riot Archive File Suite--
 * The goal of the RAFSuite is to allow individuals to easily
 * explore the content of the League of Legends cache without the hassle
 * of navigating through patch directories and to provide a number of tools
 * to assist in the use of League of Legends archive files:
 *  -The suite also aims to provide a convenient way of unpacking and packing
 *      files from fs.
 *  -The suite identifies individual files of the fs and attempts to
 *      decode the file to provide meaningful or useful information to the
 *      individual.
 *  -The suite intends to provide a means of encoding identified files
 *      back to the archive allowing for modifications to be re-archived.
 *
 * RAFSuite
 *  This class is the beginning of the program that handles all necessary
 *      tasks before initializing the suite.
 *  This class acts as a modifier for the RAFApplication class in order to contain
 *      an instance of it.
 */
public class RAFSuite extends Application {

    /**
     * The default file path for riot game client archive releases.
     */
    public final static String FILE_PATH = "C:/Riot Games/League of Legends/RADS/projects/lol_game_client/filearchives/";

    public RAFApplication application;

    /**
     *
     */
    public RAFSuite() {
        application = new RAFApplication();
    }

    /**
     * The beginning of everything ;)
     * @param args
     */
    public static void main(String[] args) {
        try {
            /**
             * Launch the user interface.
             */
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        application.start(primaryStage);
    }

}
