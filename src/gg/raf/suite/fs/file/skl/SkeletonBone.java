package gg.raf.suite.fs.file.skl;

/**
 * Created by Allen Kinzalow on 10/12/2015.
 */
public class SkeletonBone {

    private String name;
    private int id;
    private int parentId;
    private float scale;
    private float[] position;
    private float[] orientation;

    public SkeletonBone() {
        this.id = 0;
        this.parentId = 0;
        this.scale = 0.0f;
        this.position = new float[3];
        this.orientation = new float[12];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float[] getOrientation() {
        return orientation;
    }

    public void setOrientation(float[] orientation) {
        this.orientation = orientation;
    }

    public float[] getPosition() { return position; }

    public void setPosition(float[] position) {
        this.position = position;
    }

}
