package gg.raf.suite.fs.archive;

/**
 * Created by Allen Kinzalow on 9/24/2015.
 *
 * A representation of a release manifest and
 *  link to and individual archive for a release.
 *
 * A ReleaseManifest will be used to identify an archive
 *  before or after it is read & decoded.
 *  The importance of this class revolves around the fact that
 *  release directories can contain multiple archive files.
 */
public class ReleaseManifest {

    /**
     * The release number.
     */
    private String releaseNumber;

    /**
     * The release name.
     */
    private String releaseName;

    public ReleaseManifest(String releaseNumber, String releaseName) {
        this.releaseNumber = releaseNumber;
        this.releaseName = releaseName;
    }

    public String getReleaseName() {
        return releaseName;
    }
    public String getDataReleaseName() {
        return releaseName + ".dat";
    }

    public String getReleaseNumber() {
        return releaseNumber;
    }

}
