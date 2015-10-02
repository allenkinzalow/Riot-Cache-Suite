package gg.raf.suite.ui.components.treeview;

import gg.raf.suite.RAFSuite;
import gg.raf.suite.fs.archive.ArchiveFile;
import gg.raf.suite.fs.file.RiotFile;
import gg.raf.suite.fs.file.RiotFileType;
import gg.raf.suite.fs.file.RiotPath;
import gg.raf.suite.ui.components.tabs.FileTab;
import gg.raf.suite.ui.models.LayoutModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by Allen Kinzalow on 9/30/2015.
 */
public class CacheTreeView {

    /**
     * The treeview created in layout controller.
     */
    private TreeView cacheTree;

    /**
     * The layout model that this tree belongs too.
     */
    private LayoutModel model;

    /**
     * A comparator for directories in the treeview.
     */
    private final DirectoryComparator comparator = new DirectoryComparator();

    public CacheTreeView(TreeView treeView, LayoutModel model) {
        this.cacheTree = treeView;
        this.model = model;
    }

    /**
     * Populate the tree view.
     */
    public void populateTree() {
        try {
            final TreeItem<String> root = new TreeItem<>("Cache Directories");
            root.setExpanded(true);
            HashMap<String, TreeItem<String>> dirMap = new HashMap<>(); // directory map
            File directory = new File(RAFSuite.FILE_PATH);
            int count = 0, count1 = 0;
            ArchiveFile archiveFile = null;
            for (File file : directory.listFiles()) {
                for (final File subFile : file.listFiles()) {
                    if (subFile.getName().endsWith(".raf")) {
                        archiveFile = new ArchiveFile(subFile);
                        archiveFile.initiate();
                        for (RiotPath path : archiveFile.getPathEntries()) {
                            String dirs = path.getPath();
                            String id = dirs.substring(0, dirs.lastIndexOf('/') + 1);
                            String[] dir = dirs.split("/");
                            if (!dirMap.containsKey(id)) {
                                String key = "";
                                for (int i = 0; i < dir.length - 1; i++) {
                                    String before = key;
                                    key += dir[i] + "/";
                                    if (!dirMap.containsKey(key)) {
                                        TreeItem<String> item = new TreeItem<>(dir[i]);
                                        dirMap.put(key, item);
                                        count++;
                                        if (i > 0) {
                                            TreeItem<String> parent = dirMap.get(before);
                                            parent.getChildren().add(item);
                                        } else
                                            root.getChildren().add(item);
                                    }
                                }
                            } else {
                                String name = dirs.substring(dirs.lastIndexOf('/') + 1, dirs.length());
                                TreeItem<String> item = new TreeItem<>(name);
                                TreeItem<String> parent = dirMap.get(id);
                                String releasePath = archiveFile.getManifest().getReleaseNumber() + "/" + archiveFile.getManifest().getReleaseName();
                                parent.getChildren().remove(getItemForValue(parent, name));
                                /**
                                 * Populate a map for release history of this file.
                                 */
                                ArrayList<String> history = model.getReleaseHistory().get(dirs);
                                if(history == null)
                                    history = new ArrayList<>();
                                history.add(releasePath);
                                model.getReleaseHistory().put(dirs, history);
                                parent.getChildren().add(item);
                            }
                        }
                    }
                }
            }
            System.gc();
            sortDirectory(root);
            cacheTree.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Establish an event for interacting with a tree cell.
     */
    public void setDirectoryEvent() {
        cacheTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> previous, TreeItem<String> clicked) {
                if (clicked == null || clicked.getChildren() == null)
                    return;
                model.getController().getLogger().setText(clicked.getValue());
                try {
                    if (clicked.getChildren().size() == 0 && clicked.getValue().contains(".")) {
                        model.log("Loaded: " + clicked.getValue() + "");
                        HashMap<String, RiotFile> file = new HashMap<>();
                        String path = getFilePathForLeaf(clicked);
                        ArrayList<String> releasePaths = model.getReleaseHistory().get(path);
                        if (!model.getFileMap().contains(path)) {
                            for(String releasePath : releasePaths) {
                                ArchiveFile archive = new ArchiveFile(new File(RAFSuite.FILE_PATH + releasePath));
                                archive.initiate();
                                archive.getArchiveDataFile().initiate();
                                file.put(releasePath, RiotFileType.wrapFileType(path, archive.getArchiveDataFile().getFileForPathIndex(archive.getPathIndex(path))));
                            }
                            final FileTab tab = new FileTab(clicked.getValue(), path, file);
                            tab.setOnClosed(e -> model.getFileMap().remove(path));

                            model.getController().getFileTabs().getTabs().add(tab);
                            model.getFileMap().add(path);
                            model.getController().getCurrentAction().setText("Loaded " + clicked.getValue() + "...");
                        }
                    }
                    System.gc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Traverse up the tree from the leaf to obtain
     *  the full path of the leaf.
     * @param leaf
     * @return
     */
    private String getFilePathForLeaf(TreeItem<String> leaf) {
        String path = leaf.getValue();
        TreeItem<String> parent = leaf;
        while((parent = parent.getParent()) != null && (!parent.getValue().equalsIgnoreCase("cache directories"))) {
            path = parent.getValue() + "/" + path;
        }
        return path;
    }

    /**
     * Sort the given tree item's children.
     * @param root
     */
    private void sortDirectory(TreeItem<String> root) {
        if(root.getChildren().size() == 0)
            return;
        root.getChildren().sort(comparator);
        root.getChildren().forEach(e -> sortDirectory(e));
    }

    /**
     * Return whether or not a tree item's has a leaf that is equal
     *  to a given value.
     * @param parent
     * @param leafValue
     * @return
     */
    private boolean treeItemContains(TreeItem<String> parent, String leafValue) {
        for(TreeItem<String> child : parent.getChildren()) {
            if(child.getValue().equals(leafValue))
                return true;
        }
        return false;
    }

    /**
     * Get an item for a given value in a parent treeitem.
     * @param parent
     * @param name
     * @return
     */
    private TreeItem<String> getItemForValue(TreeItem<String> parent, String name) {
        for(TreeItem<String> child : parent.getChildren())
            if(child.getValue().equals(name))
                return child;
        return null;
    }

    /**
     * A comparator to order TreeItem's
     */
    private class DirectoryComparator implements Comparator<TreeItem<String>> {

        @Override
        public int compare(TreeItem<String> o1, TreeItem<String> o2) {
            if(o1.getChildren().size() >  0 && o2.getChildren().size() == 0)
                return -1;
            else if(o1.getChildren().size() == 0 && o2.getChildren().size() > 0)
                return 1;
            return o1.getValue().compareTo(o2.getValue());
        }

    }

}
