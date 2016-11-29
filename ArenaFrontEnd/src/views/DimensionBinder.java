package views;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Region;

/**
 * A utility for binding dimensions between JavaFX View Objects (such as Region, Pane etc.).
 *
 * The "margin" method can be used to create Margin objects that is a common
 * parameter in this class in regards to padding methods.
 *
 * The "dimensionsFactors" creates DimensionsFactors objects that is a common
 * parameter in methods binding two view objects with more than one unique factor.
 */
public class DimensionBinder {

    /**
     * Binds two objects to identical dimensions.
     * @param binder
     * @param container
     */
    public static void fixedBindTo(Region binder, Region container){
        binder.minWidthProperty().bind(container.widthProperty());
        binder.maxWidthProperty().bind(container.widthProperty());

        binder.minHeightProperty().bind(container.heightProperty());
        binder.maxHeightProperty().bind(container.heightProperty());
    }

    /**
     * Binds a view object "squarely" to another view object.
     * The "binder" will always stay as a square, but the dominating
     * dimension (the one deciding the other) will depend on the
     * containing view objects form (if it is tall or wide).
     * @param binder
     * @param container
     */
    public static void squareBindTo(Region binder, Region container){
        if(container.widthProperty().get() > container.heightProperty().get())
            bindTwoToOneDimensions(binder, container.heightProperty());
        else
            bindTwoToOneDimensions(binder, container.widthProperty());
    }

    /**
     * Binds both min- and max properties of the binders dimensions to a
     * single dimension property.
     * @param binder
     * @param bindTo
     */
    public static void bindTwoToOneDimensions(Region binder, DoubleExpression bindTo){
        bindOneToOneDimension(binder.minWidthProperty(), binder.maxWidthProperty(), bindTo);
        bindOneToOneDimension(binder.minHeightProperty(), binder.maxHeightProperty(), bindTo);
    }

    /**
     * Binds a min property and a max property to a single dimension property.
     * @param minDimension
     * @param maxDimension
     * @param bindTo
     */
    public static void bindOneToOneDimension(DoubleProperty minDimension, DoubleProperty maxDimension, DoubleExpression bindTo){
        minDimension.unbind();
        minDimension.bind(bindTo);

        maxDimension.unbind();
        maxDimension.bind(bindTo);
    }

    /**
     * Binds the width of a view object to the factor of a width of another view object.
     * @param region
     * @param widthInPercentage
     * @param container
     */
    public static void bindWidthToPercentageOfContainer(Region region, double widthInPercentage, Region container){
        DimensionBinder.bindOneToOneDimension(
                region.minWidthProperty(),
                region.maxWidthProperty(),
                container.widthProperty().multiply(widthInPercentage)
        );
    }

    /**
     * Binds the height of a view object to the factor of a height of another view object.
     * @param region
     * @param heightInPercentage
     * @param container
     */
    public static void bindHeightToPercentageOfContainer(Region region, double heightInPercentage, Region container){
        DimensionBinder.bindOneToOneDimension(
                region.minHeightProperty(),
                region.maxHeightProperty(),
                container.heightProperty().multiply(heightInPercentage)
        );
    }

    /**
     * Bind the dimensions of a view object to factors of the dimensions of another view object.
     * @param region
     * @param factors
     * @param container
     */
    public static void bindDimensionsToFactorsOfContainer(Region region, DimensionsFactors factors, Region container){
        DimensionBinder.bindWidthToPercentageOfContainer(region, factors.widthFactor, container);
        DimensionBinder.bindHeightToPercentageOfContainer(region, factors.heightFactor, container);
    }

    public static void bindDimensionsWidthPadding(Region binder, Region bindTo, Margin margin){
        DimensionBinder.bindWidthWithPadding(binder, bindTo, margin);
        DimensionBinder.bindHeightWithPadding(binder, bindTo, margin);
    }

    public static void bindWidthWithPadding(Region binder, Region bindTo, Margin margin){
        binder.maxWidthProperty().bind(bindTo.widthProperty().multiply(1 - margin.width));
        binder.minWidthProperty().bind(bindTo.widthProperty().multiply(1 - margin.width));
    }

    public static void bindHeightWithPadding(Region binder, Region bindTo, Margin margin){
        binder.maxHeightProperty().bind(bindTo.heightProperty().multiply(1 - margin.height));
        binder.minHeightProperty().bind(bindTo.heightProperty().multiply(1 - margin.height));
    }

    /**
     * Returns a DimensionsFactors used in certain methods of this class.
     * @param widthFactor
     * @param heightFactor
     * @return
     */
    public static DimensionsFactors dimensionsFactors(double widthFactor, double heightFactor){
        return new DimensionsFactors(widthFactor, heightFactor);
    }

    /**
     * Returns a Margin object used in certain methods of this class.
     * @param width
     * @param height
     * @return
     */
    public static Margin margin(double width, double height){
        return new Margin(width, height);
    }

    /**
     * Data structure containing two variables for margin: width and height.
     * (Differs only on a semantic level to the class of {@link DimensionsFactors})
     */
    private static class Margin {
        public final double width;
        public final double height;

        public Margin(double width, double height) {
            this.width = width;
            this.height = height;
        }
    }

    /**
     * Data structure containing two factors, one for width and one for height.
     */
    private static class DimensionsFactors{
        public final double widthFactor;
        public final double heightFactor;

        public DimensionsFactors(double widthFactor, double heightFactor){
            this.widthFactor = widthFactor;
            this.heightFactor = heightFactor;
        }
    }
}
