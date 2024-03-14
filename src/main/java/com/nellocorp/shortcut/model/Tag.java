package com.nellocorp.shortcut.model;

import com.nellocorp.shortcut.error.EmptyPayloadException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.lang.String.format;

public class Tag implements Navigable<Tag, String>, Payload<String> {

    private static final String EMPTY_PAYLOAD = "";

    private final String label;
    private final String payload;
    private final HashMap<String, Tag> nestedTags = new HashMap<>(9);

    public Tag(String label, String payload) {
        this.label = label;
        this.payload = payload;
    }

    public Tag(String label) {
        this(label, EMPTY_PAYLOAD);
    }

    @Override
    public String alias() {
        return this.label;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Tag)
                // We need this for the HashMap containing nested tags
                && this.label.equals(((Tag) obj).label);
    }

    @Override
    public void put(Tag node) {
        this.nestedTags.put(node.alias(), node);
    }

    @Override
    public List<Tag> search(Tag node) {
        return search(node.alias());
    }

    @Override
    public List<Tag> search(Label<String> label) {
        return search(label.alias());
    }

    public List<Tag> search(String label) {
        return doSearch(label);
    }

    private List<Tag> doSearch(String label) {
        // Try direct search - cost O(1)
        Tag tag = this.nestedTags.get(label);
        if (tag != null)
            return Collections.singletonList(tag);

        // Iterate every key - cost O(n)
        List<Tag> result = new ArrayList<>();
        for (String l : this.nestedTags.keySet())
            if (l.startsWith(label))
                result.add(this.nestedTags.get(l));
        return result;
    }

    @Override
    public String getPayload() throws EmptyPayloadException {
        if (this.payload.equals(EMPTY_PAYLOAD))
            throw new EmptyPayloadException(
                    format("[%s] tag has an empty payload", alias()));
        return this.payload;
    }
}
