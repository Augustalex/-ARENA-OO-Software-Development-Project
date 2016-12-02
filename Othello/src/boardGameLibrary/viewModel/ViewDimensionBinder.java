package boardGameLibrary.viewModel;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.layout.Region;

/**
 * Created by August on 2016-10-21.
 */
public class ViewDimensionBinder {

    public static void fixedBindTo(Region binder, Region container){
        binder.minWidthProperty().bind(container.widthProperty());
        binder.maxWidthProperty().bind(container.widthProperty());

        binder.minHeightProperty().bind(container.heightProperty());
        binder.maxHeightProperty().bind(container.heightProperty());
    }

    public static void squareBindTo(Region binder, Region container){
        if(container.widthProperty().get() > container.heightProperty().get()){
            bindTwoToOneDimensions(binder, container.heightProperty());
        }
        else{
            bindTwoToOneDimensions(binder, container.widthProperty());
        }
    }

    public static void bindTwoToOneDimensions(Region binder, ReadOnlyDoubleProperty bindTo){
        bindOneToOneDimension(binder.minWidthProperty(), binder.maxWidthProperty(), bindTo);
        bindOneToOneDimension(binder.minHeightProperty(), binder.maxHeightProperty(), bindTo);
    }

    public static void bindOneToOneDimension(DoubleProperty minDimension, DoubleProperty maxDimension, DoubleExpression bindTo){
        minDimension.unbind();
        minDimension.bind(bindTo);

        maxDimension.unbind();
        maxDimension.bind(bindTo);
    }

    public static void bindDimensionsWidthPadding(Region binder, Region bindTo, Margin margin){
        ViewDimensionBinder.bindWidthWithPadding(binder, bindTo, margin);
        ViewDimensionBinder.bindHeightWithPadding(binder, bindTo, margin);
    }

    public static void bindWidthWithPadding(Region binder, Region bindTo, Margin margin){
        binder.maxWidthProperty().bind(bindTo.widthProperty().multiply(1 - margin.width));
        binder.minWidthProperty().bind(bindTo.widthProperty().multiply(1 - margin.width));
    }

    public static void bindHeightWithPadding(Region binder, Region bindTo, Margin margin){
        binder.maxHeightProperty().bind(bindTo.heightProperty().multiply(1 - margin.height));
        binder.minHeightProperty().bind(bindTo.heightProperty().multiply(1 - margin.height));
    }

    public static Margin margin(double width, double height){
        return new Margin(width, height);
    }

    private static class Margin {
        public double width;
        public double height;

        public Margin(double width, double height) {
            this.width = width;
            this.height = height;
        }
    }
}
