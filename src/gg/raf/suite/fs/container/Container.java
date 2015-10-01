package gg.raf.suite.fs.container;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Allen Kinzalow on 9/24/2015.
 */
public abstract class Container<T> {

    /**
     * The default path for a container.
     */
    private final static String DEFAULT_FILE_PATH = "C:/Riot Games/League of Legends/RADS/projects/lol_game_client/filearchives/";

    /**
     * The root path for this container.
     */
    private String rootPath;

    /**
     * The type T container/collection.
     */
    protected T container;

    /**
     * Construct a container with a given root path and container type.
     * @param rootPath
     */
    public Container(String rootPath, T container) {
        this.rootPath = rootPath;
        this.container = container;
    }

    /**
     * Construct a container with the default root path and container type.
     */
    public Container(T container) { this(DEFAULT_FILE_PATH, container); }

    /**
     * Populate this container.
     */
    public abstract void populate();

    /**
     * Retrieve the root path.
     * @return
     */
    public String getRootPath() {
        return rootPath;
    }

    public T getContainer() {
        return container;
    }

}
