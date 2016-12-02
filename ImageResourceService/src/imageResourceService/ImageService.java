package imageResourceService;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-12-01.
 */
public class ImageService {

    private int nextId = 0;

    private Map<String, Image> images = new HashMap<>();

    public Image getImage(String imageId){
        return images.get(imageId);
    }

    /**
     * Generates a new String ID for the images and stores it by that ID.
     * The ID is returned from the method so that the caller later can access
     * that image via the ID.
     * @param image
     * @return
     */
    public String storeImage(Image image){
        String id = getNextId();
        images.put(id, image);

        return id;
    }

    public void removeImage(String imageId){
        images.remove(imageId);
    }

    /**
     * Reads a JavaFX image and returns a byte array of its data.
     * The byte array is ordered as rgba.
     * @param image
     * @return
     */
    public byte[] fromJavaFXImageToByteArray(Image image){
        PixelReader pixelReader = image.getPixelReader();

        byte[] imageData = new byte[(int)(image.getWidth() * image.getHeight() * 4)];
        pixelReader.getPixels(0,0, (int)image.getWidth(), (int)image.getHeight(), PixelFormat.getByteBgraInstance(), imageData, 0, (int) image.getWidth() * 4);

        return imageData;
    }

    private String getNextId(){
        return String.valueOf(this.nextId++);
    }


}
