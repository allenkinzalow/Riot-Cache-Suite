package gg.raf.suite.fs.file.skl;

import gg.raf.suite.fs.file.RiotFile;
import gg.raf.suite.utilities.StringUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Allen Kinzalow on 10/12/2015.
 */
public class SklFile extends RiotFile {

    private Skeleton skeleton;

    public SklFile(RiotFile file) {
        super(file);
        decode();
    }

    @Override
    public void decode() {
        ByteBuffer buffer = ByteBuffer.wrap(this.getFileData());
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        skeleton = new Skeleton();
        String skeletonId = StringUtil.readString(buffer, 8);
        System.out.println("Skeleton ID: " + skeletonId);
        skeleton.setId(skeletonId);
        int version = buffer.getInt();
        System.out.println("Version: " + version);
        skeleton.setVersion(version);
        if(version == 1 || version == 2) {
            skeleton.setDesignerId(buffer.getInt() & 0xFF);

            int boneCount = buffer.getInt() & 0xFF;
            skeleton.setBoneCount(boneCount);
            for(int bone = 0; bone < boneCount; bone++) {
                SkeletonBone sb = new SkeletonBone();
                String boneName = StringUtil.readString(buffer, 32).toLowerCase();
                System.out.println("--Bone Name: " + boneName);
                sb.setName(boneName);
                sb.setId(bone);
                sb.setParentId(buffer.getInt() & 0xFF);
                sb.setScale(buffer.getFloat());
                float[] orientation = new float[12];
                for(int o = 0; o < orientation.length; o++)
                    orientation[o] = buffer.getFloat();
                sb.setOrientation(orientation);
                float[] position = new float[3];
                position[0] = orientation[3];
                position[1] = orientation[7];
                position[2] = orientation[11];
                sb.setPosition(position);
                skeleton.getBones().add(sb);
            }

            if(version == 2) {
                int boneIDCount = buffer.getInt() & 0xFF;
                skeleton.setBoneIdCount(boneIDCount);
                for(int i = 0; i < boneIDCount; i++)
                    skeleton.getBoneIds().add(buffer.getInt());
            }
        }
    }

    private int getuint(ByteBuffer buffer) {
        byte[] b = new byte[4];
        buffer.get(b, 0, 4);
        return ((b[3] & 0xFF) << 24) + ((b[2] & 0xFF) << 16) + ((b[1] & 0xFF) << 8) + ((b[0] & 0xFF));
    }
}
