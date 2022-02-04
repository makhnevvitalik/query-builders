/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(exclude = "parent")
@RequiredArgsConstructor
public class FieldNodeImpl implements FieldNode {

    List<String> fieldParts;
    AbstractNode parent;

    public FieldNodeImpl(String... fieldParts) {
        this(Arrays.asList(fieldParts));
    }

    public FieldNodeImpl(List<String> fieldParts) {
        this(fieldParts, null);
    }

    @Override
    public String getField(String delimiter) {
        return String.join(delimiter, fieldParts);
    }

    @Override
    public FieldNode combine(FieldNode... nodes) {
        List<String> parts = new ArrayList<>(this.fieldParts);
        for (FieldNode node : nodes) {
            parts.addAll(node.getFieldParts());
        }
        return new FieldNodeImpl(parts);
    }

    @Override
    public FieldNodeImpl withParent(AbstractNode parent) {
        return new FieldNodeImpl(fieldParts, parent);
    }
}
