package gg.raf.suite.ui.controller.file;

import gg.raf.suite.ui.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Allen Kinzalow on 10/18/2015.
 */
public class DefaultRawController extends Controller {

    @FXML private TextArea rawTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    public TextArea getRawTextArea() { return rawTextArea; }

}
