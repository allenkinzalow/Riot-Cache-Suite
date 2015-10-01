package gg.raf.suite.ui.components.tabs;

import gg.raf.suite.fs.file.RiotFile;
import gg.raf.suite.ui.RAFApplication;
import gg.raf.suite.ui.controller.file.FileTabController;
import gg.raf.suite.ui.layouts.Layout;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 * Created by Allen Kinzalow on 9/28/2015.
 */
public class FileTab extends Tab {

    /**
     * A list of all of this file(all of its historical files).
     */
    private HashMap<String, RiotFile> file;

    /**
     * The current open file.
     */
    private RiotFile openFile;

    /**
     * This file's path.
     */
    private String path;

    /**
     * This tab's controller.
     */
    private FileTabController controller;
    /**
     * The format for file data.
     */
    private NumberFormat format;

    public FileTab(String name, String path, HashMap<String, RiotFile> file) {
        super(name);
        this.file = file;
        this.path = path;
        this.openFile = file.get(file.keySet().toArray()[file.size() - 1]);
        this.format = new DecimalFormat("###,###,###");
        try {
            /**
             * Initialize the controller and fxml layout.
             */
            controller = new FileTabController();
            FXMLLoader fxmlLoader = new FXMLLoader(Layout.class.getResource("tab_layout.fxml"));
            fxmlLoader.setController(controller);
            AnchorPane pane = fxmlLoader.load();
            this.updateFileData();
            this.initializeArchiveList();
            this.setButtonEvents();
            this.setContent(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the file data labes.
     */
    private void updateFileData() {
        controller.getFileSizeCompressed().setText("" + format.format(openFile.getDataSize()));
        controller.getFileSize().setText("" + format.format(openFile.getFileData().length));
        controller.getFileHash().setText("" + openFile.getHash());
        controller.getFileOffset().setText("" + format.format(openFile.getDataOffset()));
        controller.getFilePath().setText("" + path);
    }

    /**
     * Set the button events for this tab.
     */
    private void setButtonEvents() {
        this.controller.getExportButton().setOnMouseClicked(e -> {
            File file = RAFApplication.CHOOSER.showSaveDialog(RAFApplication.STAGE);
            openFile.saveFileData(file);
        });
        this.controller.getReplaceButton().setOnMouseClicked(e -> {
            File file = RAFApplication.CHOOSER.showOpenDialog(RAFApplication.STAGE);
        });
    }

    /**
     * Initialize the archive release history list.
     */
    private void initializeArchiveList() {
        if(controller.getArchiveList() != null) {
            for(String s : file.keySet()) {
                controller.getArchiveList().getItems().add(s);
            }
            controller.getArchiveList().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    openFile = file.get(controller.getArchiveList().getSelectionModel().getSelectedItem());
                    updateFileData();
                }
            });
            controller.getArchiveList().getSelectionModel().selectLast();
        }
    }

}
