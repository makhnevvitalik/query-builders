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
import com.github.makhnevvitalik.query.nodes.LogicalNodeImpl;
import com.github.makhnevvitalik.query.nodes.Operators;
import com.github.makhnevvitalik.query.properties.virtual.TemporalProperty;
import java.time.temporal.Temporal;

public abstract class TemporalPropertyImpl<T extends Partial<T>, S extends Temporal> extends BasePropertyImpl<T, S>
    implements TemporalProperty<T, S> {

    protected TemporalPropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }

    @Override
    public Condition<T> before(S dateTime, boolean exclusive) {
        return condition(beforeNode(dateTime, exclusive));
    }

    @Override
    public Condition<T> after(S dateTime, boolean exclusive) {
        return condition(afterNode(dateTime, exclusive));
    }

    @Override
    public Condition<T> between(S after, boolean exclusiveAfter, S before, boolean exclusiveBefore) {
        AbstractNode node = new LogicalNodeImpl(Operators.AND, null,
            afterNode(after, exclusiveAfter), beforeNode(before, exclusiveBefore));
        return condition(node);
    }

    private AbstractNode beforeNode(S dateTime, boolean exclusive) {
        return ComparisonNodeImpl.create(exclusive ? Operators.LT : Operators.LTE, field, dateTime);
    }

    private AbstractNode afterNode(S dateTime, boolean exclusive) {
        return ComparisonNodeImpl.create(exclusive ? Operators.GT : Operators.GTE, field, dateTime);
    }
}
