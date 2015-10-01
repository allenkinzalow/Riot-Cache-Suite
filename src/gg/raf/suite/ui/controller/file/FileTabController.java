package gg.raf.suite.ui.controller.file;

import gg.raf.suite.ui.controller.Controller;
import gg.raf.suite.ui.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Allen Kinzalow on 9/29/2015.
 */
public class FileTabController extends Controller {

    @FXML private Label fileSizeCompressed;
    @FXML private Label fileSize;
    @FXML private Label fileOffset;
    @FXML private Label fileHash;
    @FXML private TextArea filePath;
    @FXML private ListView archiveList;
    @FXML private Button exportButton;
    @FXML private Button replaceButton;
    @FXML private Tab rawTab;
    @FXML private Tab viewTab;

    public Label getFileSizeCompressed() {
        return fileSizeCompressed;
    }

    public Label getFileSize() {
        return fileSize;
    }

    public Label getFileOffset() {
        return fileOffset;
    }

    public Label getFileHash() {
        return fileHash;
    }

    public TextArea getFilePath() {
        return filePath;
    }

    public ListView getArchiveList() {
        return archiveList;
    }

    public Tab getRawTab() {
        return rawTab;
    }

    public Tab getViewTab() {
        return viewTab;
    }

    public Button getExportButton() {
        return exportButton;
    }

    public Button getReplaceButton() {
        return replaceButton;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
