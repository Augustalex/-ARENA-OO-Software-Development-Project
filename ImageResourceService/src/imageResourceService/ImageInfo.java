package imageResourceService;

import javafx.scene.image.Image;

/**
 * Created by August on 2016-12-01.
 */
public class ImageInfo {

    public int width;
    public int height;
    public int byteDataSize;

    public ImageInfo(int width, int height, int byteDataSize){
        this.width = width;
        this.height = height;
        this.byteDataSize = byteDataSize;
    }
}
