package gg.raf.suite.ui.models.file;

import gg.raf.suite.fs.file.RiotFile;
import gg.raf.suite.ui.controller.Controller;
import gg.raf.suite.ui.models.Model;

/**
 * Created by Allen Kinzalow on 10/2/2015.
 */
public abstract class FileModel<T extends RiotFile> extends Model {

    /**
     * The file associated with this model.
     */
    protected T file;

    /**
     * Construct a model with a given controller.
     *
     * @param controller
     */
    public FileModel(Controller... controller) {
        super(controller);
    }

    public T getFile() {
        return file;
    }

    public void setFile(RiotFile file) {
        this.file = (T) file;
    }

    @Override
    public void initialize() {}

}
