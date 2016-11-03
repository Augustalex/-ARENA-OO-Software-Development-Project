package boardGameLibrary.viewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * When the Bucket is full the ChangeListener for onIsFull will fire and the overflow will
 * be handled by the ChangeLister of the internal {@link ObjectProperty} for overflow
 * elements.
 *
 * Imitates a stack but maintains internal list as an {@link ObservableList}.
 *
 * @param <T> element type of elements in Bucket internal list.
 */
public class Bucket<T>{

    protected final int capacity;
    protected ObservableList<T> contents;

    private final BooleanProperty isFull = new SimpleBooleanProperty();
    private final ObjectProperty<T> overflow = new SimpleObjectProperty<>();

    private ChangeListener<Boolean> onIsFullHandler = null;
    private ChangeListener<T> onOverflowHandler = null;

    /**
     * Takes the max capacity of the Bucket as an argument. This capacity
     * is immutable.
     * @param capacity Integer for max capacity of new Bucket.
     */
    public Bucket(int capacity) {
        this.contents = FXCollections.observableArrayList();
        this.capacity = capacity;
    }

    /**
     * Adds object to Bucket. If Bucket is about to reach capacity
     * (that is, if the argument puts the bucket at max capacity)
     * then the onFull ChangeListener is activated.
     *
     * If the object is overflow (that is, if the bucket has already
     * reached max capacity), the the onOverflow ChangeListener
     * will be activated.
     *
     * @param object Object to be added to the contents of the Bucket.
     */
    public void add(T object){
        if(hasReachedCapacity())
            this.overflow.set(object);
        else {
            this.contents.add(object);
            if (hasReachedCapacity())
                setIsFull();
        }
    }

    /**
     * Removes referred object from the contents of the Bucket.
     *
     * @param object Object reference to identify element.
     */
    public void remove(T object){
        this.contents.remove(object);
    }

    /**
     * Returns the contents of the Bucket and as such empties the contents
     * of the Bucket.
     *
     * The internal list of the Bucket is returned and the internal
     * list reference is referred to a new list object.
     * @return The contents of the Bucket.
     */
    public List<T> pour(){
        List<T> contents = this.contents;
        this.contents = FXCollections.observableArrayList();

        return contents;
    }

    /**
     * Set listener to fire once the bucket is full. It will only fire when
     * the bucket reaches capacity and not every time overflow is added.
     * @param listener
     */
    public void onIsFull(ChangeListener<Boolean> listener){
        this.onIsFullHandler = listener;
    }

    /**
     * Each time an element is added to Bucket beyond internal capacity,
     * the overflown element will be handled by the ChangeLister for
     * onOverflow.
     * @param listener
     */
    public void onOverflow(ChangeListener<T> listener){
        this.removeOnOverflowHandler();
        this.overflow.addListener(listener);
    }

    /**
     * Returns true if the objects exists inside the contents
     * of the Bucket.
     * @param object the object reference to look for.
     * @return boolean for if the object is found.
     */
    public boolean contains(T object){
        return this.contents.contains(object);
    }

    @Override
    public String toString(){
        return this.contents.toString();
    }

    private boolean hasReachedCapacity(){
        return this.contents.size() == this.capacity;
    }

    private void removeOnOverflowHandler(){
        if(this.onOverflowHandler != null)
            this.overflow.removeListener(this.onOverflowHandler);
    }

    private void setIsFull(){
        if(this.onIsFullHandler != null)
            this.onIsFullHandler.changed(this.isFull, false, true);
    }
}
