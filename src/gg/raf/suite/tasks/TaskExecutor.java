package gg.raf.suite.tasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Allen Kinzalow on 10/4/2015.
 */
public class TaskExecutor {


    /**
     * An executor service.
     */
    public static ExecutorService executor = Executors.newCachedThreadPool();

}
