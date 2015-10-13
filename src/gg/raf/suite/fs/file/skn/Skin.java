package gg.raf.suite.fs.file.skn;

import java.util.ArrayList;

/**
 * Created by Allen Kinzalow on 10/12/2015.
 */
public class Skin {

    private int magic;
    private int version;
    private int objectCount;

    private int materialCount;
    private ArrayList<SkinMaterial> materialList = new ArrayList<SkinMaterial>();

    private int indicesCount;
    private int verticesCount;
    private ArrayList<Integer> indices = new ArrayList<Integer>();
    private ArrayList<SkinVertex> vertices = new ArrayList<SkinVertex>();

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setObjectCount(int objectCount) {
        this.objectCount = objectCount;
    }

    public void setMaterialCount(int materialCount) {
        this.materialCount = materialCount;
    }

    public ArrayList<SkinMaterial> getMaterialList() {
        return materialList;
    }

    public int getIndicesCount() {
        return indicesCount;
    }

    public void setIndicesCount(int indicesCount) {
        this.indicesCount = indicesCount;
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public void setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;
    }

    public ArrayList<Integer> getIndices() {
        return indices;
    }

    public ArrayList<SkinVertex> getVertices() {
        return vertices;
    }

}
