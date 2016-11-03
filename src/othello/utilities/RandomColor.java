package utilities;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Static utility. Generates random colors from RGB values.
 * All generated colors are not opaque.
 */
public class RandomColor {

    public static Color nextColor(){
        Random random = new Random(System.currentTimeMillis());
        return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255), 1);
    }
}
