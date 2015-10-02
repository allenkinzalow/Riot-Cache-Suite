package gg.raf.suite.ui.controller.file.dds;

import gg.raf.suite.ui.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Allen Kinzalow on 10/2/2015.
 */
public class DDSViewController extends Controller {

    @FXML
    private ImageView imageView;

    public ImageView getDDSImage() { return imageView; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
