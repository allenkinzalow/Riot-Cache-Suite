package gg.raf.suite.fs.file.anm;

import java.util.ArrayList;

/**
 * Created by Allen Kinzalow on 10/12/2015.
 */
public class Animation {

    String id;
    private int primaryIdentifier;
    private int secondaryIdentifier;
    private int version;
    private int magic;
    private int boneCount;
    private int frameCount;
    private int playbackFPS;
    private ArrayList<AnimationBone> bones = new ArrayList<AnimationBone>();

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public void setPrimaryIdentifier(int primaryIdentifier) { this.primaryIdentifier = primaryIdentifier; }

    public void setSecondaryIdentifier(int secondaryIdentifier) { this.secondaryIdentifier = secondaryIdentifier; }

    public int getPrimaryIdentifier() { return primaryIdentifier; }

    public int getSecondaryIdentifier() { return secondaryIdentifier; }

    public void setVersion(int version){
        this.version = version;
    }

    public void setMagic(int magic){
        this.magic = magic;
    }

    public int getBoneCount(){
        return boneCount;
    }

    public void setBoneCount(int boneCount){
        this.boneCount = boneCount;
    }

    public int getFrameCount(){
        return frameCount;
    }

    public void setFrameCount(int frameCount){
        this.frameCount = frameCount;
    }

    public void setPlaybackFPS(int playbackFPS){
        this.playbackFPS = playbackFPS;
    }

    public int getPlaybackFPS() { return this.playbackFPS; }

    public ArrayList<AnimationBone> getBones(){
        return bones;
    }

}
