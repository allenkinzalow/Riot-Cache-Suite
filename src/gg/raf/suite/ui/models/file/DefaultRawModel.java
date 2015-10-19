package gg.raf.suite.ui.models.file;

import gg.raf.suite.fs.file.RiotFile;
import gg.raf.suite.ui.controller.Controller;
import gg.raf.suite.ui.controller.file.DefaultRawController;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

/**
 * Created by Allen Kinzalow on 10/18/2015.
 */
public class DefaultRawModel extends FileModel<RiotFile> {

    private String textArea;

    public DefaultRawModel(Controller ... controllers) { super(controllers); }

    @Override
    public void initialize() {

        DefaultRawController controller = (DefaultRawController)this.getControllers()[0];
        parseText(this.getFile().getFileData());
        controller.getRawTextArea().setText(textArea);

    }

    private void parseText(byte[] data) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data)));
            String line = "";
            while ((line = reader.readLine()) != null)
                textArea += line + "\n";
            if(textArea.startsWith("null"))
                textArea = textArea.substring(4, textArea.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
