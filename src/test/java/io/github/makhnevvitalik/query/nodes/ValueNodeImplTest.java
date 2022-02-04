/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.nodes;

import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValueNodeImplTest {

    @Test
    void nullValueAsCollection() {
        ValueNode node = new ValueNodeImpl(null);
        Assertions.assertIterableEquals(Collections.emptyList(), node.getValueAsCollection());
    }

    @Test
    void collectionValueAsCollection() {
        ValueNode setNode = new ValueNodeImpl(Collections.singleton("1"));
        Assertions.assertIterableEquals(Collections.singletonList("1"), setNode.getValueAsCollection());

        ValueNode listNode = new ValueNodeImpl(Collections.singletonList("1"));
        Assertions.assertIterableEquals(Collections.singletonList("1"), listNode.getValueAsCollection());
    }

    @Test
    void arrayValueAsCollection() {
        ValueNode node = new ValueNodeImpl(new String[]{"1"});
        Assertions.assertIterableEquals(Collections.singletonList("1"), node.getValueAsCollection());
    }

    @Test
    void stringValueAsCollection() {
        ValueNode node = new ValueNodeImpl("1");
        Assertions.assertIterableEquals(Collections.singletonList("1"), node.getValueAsCollection());
    }
}