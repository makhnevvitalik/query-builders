/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.properties.virtual.impl;


import com.github.makhnevvitalik.query.conditions.Condition;
import com.github.makhnevvitalik.query.conditions.Partial;
import com.github.makhnevvitalik.query.nodes.AbstractNode;
import com.github.makhnevvitalik.query.nodes.ComparisonNodeImpl;
import com.github.makhnevvitalik.query.nodes.Operators;
import com.github.makhnevvitalik.query.properties.AbstractProperty;
import com.github.makhnevvitalik.query.properties.virtual.EquitableProperty;

public class EquitablePropertyImpl<T extends Partial<T>, S> extends AbstractProperty<T>
    implements EquitableProperty<T, S> {

    public EquitablePropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }

    @Override
    public Condition<T> eq(Object value) {
        if (value == null) {
            return condition(ComparisonNodeImpl.create(Operators.EX, field, false));
        }
        return condition(ComparisonNodeImpl.create(Operators.EQ, field, value));
    }

    @Override
    public Condition<T> ne(Object value) {
        if (value == null) {
            return condition(ComparisonNodeImpl.create(Operators.EX, field, true));
        }
        return condition(ComparisonNodeImpl.create(Operators.NE, field, value));
    }
}
