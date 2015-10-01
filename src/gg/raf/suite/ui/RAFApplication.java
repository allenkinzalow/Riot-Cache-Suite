package gg.raf.suite.ui;

import gg.raf.suite.RAFConfig;
import gg.raf.suite.ui.controller.LayoutController;
import gg.raf.suite.ui.layouts.Layout;
import gg.raf.suite.ui.models.LayoutModel;
import gg.raf.suite.ui.resources.Resource;
import gg.raf.suite.ui.styles.Style;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by Allen Kinzalow on 9/25/2015.
 */
public class RAFApplication extends Application {

    /**
     * The stage for the application,
     *  accessible to everything.
     */
    public static Stage STAGE = null;

    /**
     * A universal file chooser.
     */
    public final static FileChooser CHOOSER = new FileChooser();

    @Override
    public void start(Stage stage) throws Exception {
        STAGE = stage;
        stage.setTitle(RAFConfig.TITLE + " - v" + RAFConfig.VERSION);
        stage.getIcons().add(new Image(Resource.class.getResource("icon.png").openStream()));

        /**
         * Setup the main controller for the application.
         */
        LayoutController controller = new LayoutController();
        FXMLLoader fxmlLoader = new FXMLLoader(Layout.class.getResource("layout.fxml"));
        fxmlLoader.setController(controller);
        AnchorPane pane = fxmlLoader.load();
        LayoutModel model = new LayoutModel(controller);
        model.initialize();

        /**
         * Setup the scene and define a style.
         */
        Scene scene = new Scene(pane, 1024, 703, Color.web("#202020"));
        scene.getStylesheets().add(Style.class.getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
