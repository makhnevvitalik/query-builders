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
import com.github.makhnevvitalik.query.properties.concrete.ConditionProperty;
import com.github.makhnevvitalik.query.properties.virtual.impl.WithPropertyImpl;


public class ConditionPropertyImpl<T extends Partial<T>, S extends Partial<S>>
    extends WithPropertyImpl<T, S, ConditionProperty<T, S>>
    implements ConditionProperty<T, S> {

    public ConditionPropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }

    @Override
    public Condition<T> any(Condition<S> condition) {
        if (condition == null) {
            throw new IllegalArgumentException("Sub-condition must not be null");
        }
        return condition(ComparisonNodeImpl.create(Operators.SUB_CONDITION_ANY, field, condition.getNode()));
    }

    @Override
    protected ConditionProperty<T, S> newInstance(T partial, AbstractNode field) {
        return new ConditionPropertyImpl<>(partial, field);
    }
}
