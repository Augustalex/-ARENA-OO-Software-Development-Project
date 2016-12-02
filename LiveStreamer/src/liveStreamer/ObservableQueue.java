package liveStreamer;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Queue;

/**
 * Created by August on 2016-11-28.
 */
public class ObservableQueue<E> extends SimpleListProperty<E> implements ObservableList<E>, Queue<E> {

    public ObservableQueue(){
        super(FXCollections.observableArrayList());
    }

    @Override
    public boolean offer(E e) {
        return this.add(e);
    }

    @Override
    public E remove() {
        return this.remove(this.size()-1);
    }

    @Override
    public E poll() {
        return this.remove(this.size()-1);
    }

    @Override
    public E element() {
        return this.get(this.size()-1);
    }

    @Override
    public E peek() {
        return this.get(this.size()-1);
    }
}
