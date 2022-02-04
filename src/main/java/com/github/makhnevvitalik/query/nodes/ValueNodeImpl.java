/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.nodes;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(exclude = "parent")
@RequiredArgsConstructor
public class ValueNodeImpl implements ValueNode {

    Object value;
    AbstractNode parent;

    public ValueNodeImpl(Object value) {
        this(value, null);
    }

    @Override
    public ValueNodeImpl withParent(AbstractNode parent) {
        return new ValueNodeImpl(value, parent);
    }
}
