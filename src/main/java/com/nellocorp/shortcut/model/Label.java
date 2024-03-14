package com.nellocorp.shortcut.model;

public interface Label <T> {

    public T alias();

    public static Label<String> valueOf(String label) {
        return () -> label;
    }
}
