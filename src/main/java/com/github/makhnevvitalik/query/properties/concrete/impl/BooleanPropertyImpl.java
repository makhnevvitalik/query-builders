/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.properties.concrete.impl;

import com.github.makhnevvitalik.query.conditions.Condition;
import com.github.makhnevvitalik.query.conditions.Partial;
import com.github.makhnevvitalik.query.nodes.AbstractNode;
import com.github.makhnevvitalik.query.nodes.ComparisonNodeImpl;
import com.github.makhnevvitalik.query.nodes.Operators;
import com.github.makhnevvitalik.query.properties.concrete.BooleanProperty;
import com.github.makhnevvitalik.query.properties.virtual.impl.BasePropertyImpl;

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
