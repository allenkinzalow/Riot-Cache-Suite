package gg.raf.suite.fs.file.anm;

import gg.raf.suite.fs.file.RiotFile;
import gg.raf.suite.utilities.StringUtil;
import javafx.scene.layout.AnchorPane;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Allen Kinzalow on 10/12/2015.
 */
public class AnmFile extends RiotFile {


    public AnmFile(RiotFile file) {
        super(file);
        decode();
    }

    @Override
    public void decode() {
        ByteBuffer buffer = ByteBuffer.wrap(this.getFileData());
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        Animation animation = new Animation();
        String animID = StringUtil.readString(buffer, 8);
        System.out.println("Anim ID: " + animID);
        animation.setId(animID);
        int version = buffer.getInt() & 0xFF;
        System.out.println("Version: " + version);
        animation.setVersion(version);
        if(version == 0 || version == 1 || version == 2 || version == 3) {
            animation.setMagic(buffer.getInt() & 0xFF);
            animation.setBoneCount(buffer.getInt() & 0xFF);
            animation.setFrameCount(buffer.getInt() & 0xFF);
            animation.setPlaybackFPS(buffer.getInt() & 0xFF);

            System.out.println("Bone Count: " + animation.getBoneCount() + " Frame Count: " + animation.getFrameCount() + " Playback: " + animation.getPlaybackFPS());
            for(int bone = 0; bone < animation.getBoneCount(); bone++) {
                AnimationBone ab = new AnimationBone();
                String animBoneName = StringUtil.readString(buffer, 32).toLowerCase();
                System.out.println("--Anim Bone ID: " + animBoneName);
                ab.setName(animBoneName);
                buffer.getInt();

                for(int frame = 0; frame < animation.getFrameCount(); frame++) {
                    AnimationFrame animFrame = new AnimationFrame();
                    animFrame.getOrientation()[0] = buffer.getFloat();
                    animFrame.getOrientation()[1] = buffer.getFloat();
                    animFrame.getOrientation()[2] = buffer.getFloat();
                    animFrame.getOrientation()[3] = buffer.getFloat();

                    animFrame.getPosition()[0] = buffer.getFloat();
                    animFrame.getPosition()[1] = buffer.getFloat();
                    animFrame.getPosition()[2] = buffer.getFloat();
                    System.out.println("----- Ori: " + animFrame.getOrientation()[0] + " " + animFrame.getOrientation()[1] + " " + animFrame.getOrientation()[2] + " " + animFrame.getOrientation()[3]);
                    System.out.println("----- Pos: " + animFrame.getPosition()[0] + " " + animFrame.getPosition()[1] + " " + animFrame.getPosition()[2]);
                    ab.getFrames().add(animFrame);
                }
                animation.getBones().add(ab);
            }
        }
    }
}
