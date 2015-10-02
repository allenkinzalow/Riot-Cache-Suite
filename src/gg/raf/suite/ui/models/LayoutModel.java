package gg.raf.suite.ui.models;

import gg.raf.suite.ui.components.treeview.CacheTreeView;
import gg.raf.suite.ui.controller.LayoutController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Allen Kinzalow on 9/30/2015.
 */
public class LayoutModel extends Model<LayoutController> {

    /**
     * Map of opened tabs with corresponding file path.
     */
    private ArrayList<String> fileMap = new ArrayList<>();

    /**
     * A hashmap of paths & a list of history releases
     *  -does not include the latest release which is apart of the
     *   treeview cache directory.
     */
    private HashMap<String, ArrayList<String>> releaseHistory = new HashMap<>();

    /**
     * A wrapper class for the tree view in layout controller.
     */
    private CacheTreeView cacheTreeView;

    /**
     * Construct a model with a given controller.
     *
     * @param controller
     */
    public LayoutModel(LayoutController controller) {
        super(controller);
    }

    @Override
    public void initialize() {
        cacheTreeView = new CacheTreeView(this.getController().getCacheTree(), this);
        cacheTreeView.populateTree();
        cacheTreeView.setDirectoryEvent();
    }

    /**
     * Get the file map.
     * @return
     */
    public ArrayList<String> getFileMap() {
        return fileMap;
    }

    /**
     * Get the release history.
     * @return
     */
    public HashMap<String, ArrayList<String>> getReleaseHistory() {
        return releaseHistory;
    }

    /**
     * Append a line of text to the text area
     * "logger", with a new line in front of the text.
     * @param s
     */
    public void log(String s) {
        this.getController().getLogger().appendText("\n" + s);
    }

}
