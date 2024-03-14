package com.nellocorp.shortcut.model;

import com.nellocorp.shortcut.error.EmptyPayloadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TagTest {

    @Test
    void searchNestedTag() {
        Tag rootTag = new Tag("Java");
        Tag middleTagHibernate = new Tag("Hibernate");
        Tag middleTagOca = new Tag("Oracle Certification Test");

        rootTag.put(middleTagHibernate);
        rootTag.put(middleTagOca);
        List<Tag> result = rootTag.search(middleTagHibernate);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Hibernate",
                result.stream().iterator().next().alias());
    }

    @Test
    void searchNestedTagByPartialLabel() {
        Tag rootTag = new Tag("Java");
        Tag middleTagHibernate = new Tag("Hibernate");
        Tag middleTagOca = new Tag("Oracle Certification Test");

        rootTag.put(middleTagHibernate);
        rootTag.put(middleTagOca);
        List<Tag> result = rootTag.search("Hib");

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Hibernate",
                result.stream().iterator().next().alias());
        ;
    }

    @Test
    void cannotSearchNestedTag() {
        Tag rootTag = new Tag("Java");
        Tag middleTagHibernate = new Tag("Hibernate");
        Tag middleTagOca = new Tag("Oracle Certification Test");

        rootTag.put(middleTagOca);

        List<Tag> result = rootTag.search(middleTagHibernate);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void tagWithEmptyPayloadWillThrowException() {
        Tag emptyPayload = new Tag("Java");

        EmptyPayloadException exp = Assertions.assertThrows(
                EmptyPayloadException.class, emptyPayload::getPayload);

        Assertions.assertEquals("[Java] tag has an empty payload", exp.getMessage());
    }

    @Test
    void tagWithPayloadWillBeFine() throws EmptyPayloadException {
        String payload = "You can use JDBCTemplate for...";
        Tag tag = new Tag("JDCBTemplate", payload);

        Assertions.assertEquals(payload, tag.getPayload());
    }
}
