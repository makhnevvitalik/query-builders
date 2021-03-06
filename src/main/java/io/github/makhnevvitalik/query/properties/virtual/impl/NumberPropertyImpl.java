/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.virtual.impl;


import io.github.makhnevvitalik.query.conditions.Condition;
import io.github.makhnevvitalik.query.conditions.Partial;
import io.github.makhnevvitalik.query.nodes.AbstractNode;
import io.github.makhnevvitalik.query.nodes.ComparisonNodeImpl;
import io.github.makhnevvitalik.query.nodes.Operators;
import io.github.makhnevvitalik.query.properties.virtual.NumberProperty;

public abstract class NumberPropertyImpl<T extends Partial<T>, S extends Number> extends BasePropertyImpl<T, S>
    implements NumberProperty<T, S> {

    protected NumberPropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }

    @Override
    public Condition<T> gt(S number) {
        return condition(ComparisonNodeImpl.create(Operators.GT, field, number));
    }

    @Override
    public Condition<T> lt(S number) {
        return condition(ComparisonNodeImpl.create(Operators.LT, field, number));
    }

    @Override
    public Condition<T> gte(S number) {
        return condition(ComparisonNodeImpl.create(Operators.GTE, field, number));
    }

    @Override
    public Condition<T> lte(S number) {
        return condition(ComparisonNodeImpl.create(Operators.LTE, field, number));
    }
}
