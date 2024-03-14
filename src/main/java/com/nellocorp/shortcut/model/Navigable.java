package com.nellocorp.shortcut.model;

public interface Navigable <T extends Navigable<T, L>, L> extends Label<L> {

    public void put(T node);

    public T search(T node);

    public T search(Label<L> label);
}
