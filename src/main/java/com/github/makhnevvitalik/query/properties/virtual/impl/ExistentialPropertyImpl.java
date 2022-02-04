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
import com.github.makhnevvitalik.query.properties.virtual.ExistentialProperty;

public class ExistentialPropertyImpl<T extends Partial<T>> extends AbstractProperty<T>
    implements ExistentialProperty<T> {

    public ExistentialPropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }

    @Override
    public Condition<T> exists() {
        return exists(true);
    }

    @Override
    public Condition<T> doesNotExist() {
        return exists(false);
    }

    @Override
    public Condition<T> exists(boolean exists) {
        return condition(ComparisonNodeImpl.create(Operators.EX, field, exists));
    }
}
