package gg.raf.suite.ui.controller.file.dds;

import gg.raf.suite.ui.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Allen Kinzalow on 9/30/2015.
 */
public class DDSRawController extends Controller {

    @FXML private Label width;
    @FXML private Label height;
    @FXML private Label mipMapCount;
    @FXML private Label fourCC;
    @FXML private Label rgbBitCount;
    @FXML private Label rBitMask;
    @FXML private Label gBitMask;
    @FXML private Label bBitMask;
    @FXML private Label aBitMask;
    @FXML private Button savePngButton;

    public Label getMipMapCount() {
        return mipMapCount;
    }

    public Label getDDSWidth() {
        return width;
    }

    public Label getDDSHeight() {
        return height;
    }

    public Label getFourCC() {
        return fourCC;
    }

    public Label getRgbBitCount() {
        return rgbBitCount;
    }

    public Label getrBitMask() {
        return rBitMask;
    }

    public Label getbBitMask() {
        return bBitMask;
    }

    public Label getgBitMask() {
        return gBitMask;
    }

    public Label getaBitMask() {
        return aBitMask;
    }

    public Button getSavePngButton() {
        return savePngButton;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
