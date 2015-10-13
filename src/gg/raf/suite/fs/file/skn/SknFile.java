package gg.raf.suite.fs.file.skn;

import gg.raf.suite.fs.file.RiotFile;
import gg.raf.suite.utilities.StringUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Allen Kinzalow on 10/12/2015.
 */
public class SknFile extends RiotFile {

    private Skin skin;

    public SknFile(RiotFile file) {
        super(file);
        decode();
    }

    @Override
    public void decode() {
        ByteBuffer buffer = ByteBuffer.wrap(this.getFileData());
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        skin = new Skin();
        skin.setMagic(buffer.getInt() & 0xFF);
        int version = buffer.getShort() & 0xFF;
        skin.setVersion(version);
        System.out.println("Version: " + version);
        skin.setObjectCount(buffer.getShort() & 0xFF);
        if(version == 1 || version == 2) {
            int materialCount = buffer.getInt() & 0xFF;
            skin.setMaterialCount(materialCount);
            System.out.println("Material Count: " + materialCount);
            for(int material = 0; material < materialCount; material++) {
                SkinMaterial skinMaterial = new SkinMaterial();
                String materialName = StringUtil.readString(buffer, 64);
                skinMaterial.setName(materialName);
                System.out.println("Material Name: " + materialName);
                skinMaterial.setStartVertex(buffer.getInt() & 0xFF);
                skinMaterial.setVerticesCount(buffer.getInt() & 0xFF);
                skinMaterial.setStartIndex(buffer.getInt() & 0xFF);
                skinMaterial.setIndicesCount(buffer.getInt() & 0xFF);
                skin.getMaterialList().add(skinMaterial);
            }

            skin.setIndicesCount(buffer.getInt() & 0xFF);
            skin.setVerticesCount(buffer.getInt() & 0xFF);

            System.out.println("Indices count: " + skin.getIndicesCount());
            for(int index = 0; index < skin.getIndicesCount(); index++) {
                int indexValue = buffer.getShort() & 0xFF;
                skin.getIndices().add(indexValue);
            }

            for(int vertex = 0; vertex < skin.getVerticesCount(); vertex++) {
                SkinVertex sv = new SkinVertex();
                sv.getPosition()[0] = buffer.getFloat();
                sv.getPosition()[1] = buffer.getFloat();
                sv.getPosition()[2] = buffer.getFloat();
                for(int bone = 0; bone < 4; bone++) {
                   sv.getBoneIndex()[bone] = buffer.get();
                }

                sv.getWeights()[0] = buffer.getFloat();
                sv.getWeights()[1] = buffer.getFloat();
                sv.getWeights()[2] = buffer.getFloat();
                sv.getWeights()[3] = buffer.getFloat();

                sv.getNormal()[0] = buffer.getFloat();
                sv.getNormal()[1] = buffer.getFloat();
                sv.getNormal()[2] = buffer.getFloat();

                sv.getTextureCoords()[0] = buffer.getFloat();
                sv.getTextureCoords()[1] = buffer.getFloat();

                skin.getVertices().add(sv);
            }
        }
    }

}
