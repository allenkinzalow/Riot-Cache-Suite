package gg.raf.suite.ui.models;

import gg.raf.suite.fs.file.RiotFile;
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
    private T[] controllers;

    /**
     * Construct a model with a set of controllers.
     * @param controllers
     */
    public Model(T ... controllers) {
        this.controllers = controllers;
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
    public T[] getControllers() {
        return controllers;
    }

    /**
     * Get the first controller.
     * @return
     */
    public T getController() {
        return controllers[0];
    }

}
