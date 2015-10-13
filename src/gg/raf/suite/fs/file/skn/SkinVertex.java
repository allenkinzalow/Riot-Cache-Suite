package gg.raf.suite.fs.file.skn;

/**
 * Created by Allen Kinzalow on 10/12/2015.
 */
public class SkinVertex {

    private float[] position;
    private int[] boneIndex;
    private float[] weights;
    private float[] normal;
    private float[] textureCoords;

    public SkinVertex() {
        position = new float[3];
        boneIndex = new int[4];
        weights = new float[4];
        normal = new float[3];
        textureCoords = new float[2];
    }

    public float[] getPosition(){
        return position;
    }

    public int[] getBoneIndex(){
        return boneIndex;
    }

    public float[] getWeights(){
        return weights;
    }

    public float[] getNormal() {
        return normal;
    }

    public float[] getTextureCoords(){
        return textureCoords;
    }

}
