/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.concrete.impl;

import io.github.makhnevvitalik.query.conditions.Condition;
import io.github.makhnevvitalik.query.conditions.Partial;
import io.github.makhnevvitalik.query.nodes.AbstractNode;
import io.github.makhnevvitalik.query.nodes.ComparisonNodeImpl;
import io.github.makhnevvitalik.query.nodes.Operators;
import io.github.makhnevvitalik.query.properties.concrete.BooleanProperty;
import io.github.makhnevvitalik.query.properties.virtual.impl.BasePropertyImpl;

public class BooleanPropertyImpl<T extends Partial<T>> extends BasePropertyImpl<T, Boolean>
    implements BooleanProperty<T> {

    public BooleanPropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }

    @Override
    public Condition<T> isTrue() {
        return condition(ComparisonNodeImpl.create(Operators.EQ, field, true));
    }

    @Override
    public Condition<T> isFalse() {
        return condition(ComparisonNodeImpl.create(Operators.EQ, field, false));
    }


}
