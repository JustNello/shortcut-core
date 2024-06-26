package com.nellocorp.shortcut.model;

import java.util.List;

public interface Navigable <T extends Navigable<T, L>, L>
        extends Label<L>, Iterable<T> {

    public void put(T node);

    public List<T> search(T node);

    public List<T> search(Label<L> label);

    public T get(int index);

}
