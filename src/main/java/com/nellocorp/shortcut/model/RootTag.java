package com.nellocorp.shortcut.model;

public final class RootTag extends Tag {

    private RootTag() {
        this("ROOT");
    }

    private RootTag(String label) {
        super(label);
    }

    public static RootTag initSample() {
        RootTag result = new RootTag();

        Tag java = new Tag("Java");
        Tag spring = new Tag("spring");
        Tag oca = new Tag("OCA Oracle Certification");
        java.put(spring);
        java.put(oca);

        Tag python = new Tag("Python");
        Tag async = new Tag("Asyncio");
        Tag coroutine = new Tag("Coroutines");
        python.put(async);
        python.put(coroutine);

        result.put(java);
        result.put(python);

        return result;
    }
}
