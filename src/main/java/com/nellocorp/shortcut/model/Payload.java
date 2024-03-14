package com.nellocorp.shortcut.model;

import com.nellocorp.shortcut.error.EmptyPayloadException;

public interface Payload <T> {

    public T getPayload() throws EmptyPayloadException;
}
