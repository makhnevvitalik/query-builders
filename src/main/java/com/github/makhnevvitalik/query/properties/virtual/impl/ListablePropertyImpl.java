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
import com.github.makhnevvitalik.query.properties.virtual.ListableProperty;
import java.util.Arrays;
import java.util.Collection;

public class ListablePropertyImpl<T extends Partial<T>, S> extends AbstractProperty<T>
    implements ListableProperty<T, S> {

    public ListablePropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }

    @Override
    public Condition<T> in(S... values) {
        return in(Arrays.asList(values));
    }

    @Override
    public Condition<T> in(Collection<S> values) {
        return condition(ComparisonNodeImpl.create(Operators.IN, field, values));
    }

    @Override
    public Condition<T> nin(S... values) {
        return nin(Arrays.asList(values));
    }

    @Override
    public Condition<T> nin(Collection<S> values) {
        return condition(ComparisonNodeImpl.create(Operators.NIN, field, values));
    }
}
