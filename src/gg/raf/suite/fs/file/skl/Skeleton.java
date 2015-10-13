package gg.raf.suite.fs.file.skl;

import java.util.ArrayList;

/**
 * Created by Allen Kinzalow on 10/12/2015.
 */
public class Skeleton {

    private String id;
    private int primaryIdentifier;
    private int secondaryIdentifier;
    private int version;
    private int designerId;
    private int boneCount;
    private ArrayList<SkeletonBone> bones = new ArrayList<SkeletonBone>();
    private int boneIdCount;
    private ArrayList<Integer> boneIds = new ArrayList<Integer>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrimaryIdentifier(int primaryIdentifier) { this.primaryIdentifier = primaryIdentifier; }

    public void setSecondaryIdentifier(int secondaryIdentifier) { this.secondaryIdentifier = secondaryIdentifier; }

    public int getPrimaryIdentifier() { return primaryIdentifier; }

    public int getSecondaryIdentifier() { return secondaryIdentifier; }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setDesignerId(int designerId) {
        this.designerId = designerId;
    }

    public void setBoneCount(int boneCount) {
        this.boneCount = boneCount;
    }

    public void setBoneIdCount(int boneIdCount) {
        this.boneIdCount = boneIdCount;
    }

    public ArrayList<SkeletonBone> getBones() {
        return bones;
    }

    public ArrayList<Integer> getBoneIds() {
        return boneIds;
    }


}
