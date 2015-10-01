package gg.raf.suite.ui.models;

import gg.raf.suite.ui.controller.Controller;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Allen Kinzalow on 9/30/2015.
 * A class that provides functionality to controllers.
 */
public abstract class Model<T extends Controller> {

    /**
     * The controller attached to this model.
     */
    private T controller;

    /**
     * Construct a model with a given controller.
     * @param controller
     */
    public Model(T controller) {
        this.controller = controller;
    }

    /**
     * Initialize anything necessary for the controller.
     *  i.e events, default values, etc...
     */
    public abstract void initialize();

    /**
     * Retrieve the controller.
     * @return
     */
    public T getController() {
        return controller;
    }

}
