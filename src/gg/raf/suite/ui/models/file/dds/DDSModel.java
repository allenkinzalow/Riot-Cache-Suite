package gg.raf.suite.ui.models.file.dds;

import gg.raf.suite.fs.file.dds.DDSFile;
import gg.raf.suite.ui.controller.Controller;
import gg.raf.suite.ui.controller.file.dds.DDSRawController;
import gg.raf.suite.ui.controller.file.dds.DDSViewController;
import gg.raf.suite.ui.models.file.FileModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;

/**
 * Created by Allen Kinzalow on 9/30/2015.
 */
public class DDSModel extends FileModel<DDSFile> {

    /**
     * Construct a model with a given controller.
     *
     * @param controller
     */
    public DDSModel(Controller ... controller) {
        super(controller);
    }

    @Override
    public void initialize() {
        this.file.decode();
        System.out.println("Decoding dds");

        DDSRawController rawController = (DDSRawController)this.getControllers()[0];
        DDSViewController viewController = (DDSViewController)this.getControllers()[1];

        rawController.getDDSWidth().setText(this.file.getHeader().getDwWidth() + "");
        rawController.getDDSHeight().setText(this.file.getHeader().getDwHeight() + "");
        rawController.getMipMapCount().setText(this.file.getHeader().getDwMipMapCount() + "");
        rawController.getFourCC().setText(this.file.getHeader().getDwPixelFormat().getDwFourCCString() + "");
        rawController.getRgbBitCount().setText(this.file.getHeader().getDwPixelFormat().getDwRGBBitCount() + "");
        rawController.getrBitMask().setText(this.file.getHeader().getDwPixelFormat().getDwRBitMask() + "");
        rawController.getgBitMask().setText(this.file.getHeader().getDwPixelFormat().getDwGBitMask() + "");
        rawController.getbBitMask().setText(this.file.getHeader().getDwPixelFormat().getDwBBitMask() + "");
        rawController.getaBitMask().setText(this.file.getHeader().getDwPixelFormat().getDwABitMask() + "");

        int[] pixels = new int[this.file.getPixelBuffer().array().length];
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = this.file.getPixelBuffer().array()[i];
        }

        BufferedImage image = new BufferedImage(this.file.getHeader().getDwWidth(), this.file.getHeader().getDwHeight(), BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, this.file.getHeader().getDwWidth(), this.file.getHeader().getDwHeight(), pixels, 0, this.file.getHeader().getDwWidth());
        WritableImage wImage = new WritableImage(this.file.getHeader().getDwWidth(), this.file.getHeader().getDwHeight());
        wImage = SwingFXUtils.toFXImage(image, wImage);
        viewController.getDDSImage().setFitHeight(this.file.getHeader().getDwHeight());
        viewController.getDDSImage().setFitWidth(this.file.getHeader().getDwWidth());
        viewController.getDDSImage().setImage(wImage);

        //viewController.getDDSImage().setImage();
        System.out.println("Pixel Buffer Size: " + this.file.getPixelBuffer().array().length);
    }
}
