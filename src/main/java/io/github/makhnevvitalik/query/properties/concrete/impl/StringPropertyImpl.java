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
import io.github.makhnevvitalik.query.properties.concrete.StringProperty;
import io.github.makhnevvitalik.query.properties.virtual.impl.BasePropertyImpl;

public class StringPropertyImpl<T extends Partial<T>> extends BasePropertyImpl<T, String>
    implements StringProperty<T> {

    public StringPropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }

    @Override
    public Condition<T> lexicallyAfter(String value) {
        return condition(ComparisonNodeImpl.create(Operators.GT, field, value));
    }

    @Override
    public Condition<T> lexicallyBefore(String value) {
        return condition(ComparisonNodeImpl.create(Operators.LT, field, value));
    }

    @Override
    public Condition<T> lexicallyNotAfter(String value) {
        return condition(ComparisonNodeImpl.create(Operators.LTE, field, value));
    }

    @Override
    public Condition<T> lexicallyNotBefore(String value) {
        return condition(ComparisonNodeImpl.create(Operators.GTE, field, value));
    }

    @Override
    public Condition<T> pattern(String pattern) {
        return condition(ComparisonNodeImpl.create(Operators.RE, field, pattern));
    }
}
