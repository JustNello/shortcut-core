package com.nellocorp.shortcut.model;

import com.nellocorp.shortcut.error.EmptyPayloadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TagTest {

    @Test
    void searchNestedTag() {
        Tag rootTag = new Tag("Java");
        Tag middleTagHibernate = new Tag("Hibernate");
        Tag middleTagOca = new Tag("Oracle Certification Test");

        rootTag.put(middleTagHibernate);
        rootTag.put(middleTagOca);

        Tag result = rootTag.search(middleTagHibernate);
        Assertions.assertEquals("Hibernate", result.alias());
    }

    @Test
    void cannotSearchNestedTag() {
        Tag rootTag = new Tag("Java");
        Tag middleTagHibernate = new Tag("Hibernate");
        Tag middleTagOca = new Tag("Oracle Certification Test");

        rootTag.put(middleTagOca);

        Tag result = rootTag.search(middleTagHibernate);
        Assertions.assertNull(result);
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
