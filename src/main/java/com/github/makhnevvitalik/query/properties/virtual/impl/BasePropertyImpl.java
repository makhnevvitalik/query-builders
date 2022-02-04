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
import com.github.makhnevvitalik.query.properties.AbstractProperty;
import com.github.makhnevvitalik.query.properties.virtual.BaseProperty;
import com.github.makhnevvitalik.query.properties.virtual.EquitableProperty;
import com.github.makhnevvitalik.query.properties.virtual.ExistentialProperty;
import com.github.makhnevvitalik.query.properties.virtual.ListableProperty;
import java.util.Collection;

public class BasePropertyImpl<T extends Partial<T>, S> extends AbstractProperty<T> implements BaseProperty<T, S> {

    private final ExistentialProperty<T> existentialDelegate;
    private final ListableProperty<T, S> listableDelegate;
    private final EquitableProperty<T, S> equitableDelegate;

    protected BasePropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
        existentialDelegate = new ExistentialPropertyImpl<>(partial, field);
        listableDelegate = new ListablePropertyImpl<>(partial, field);
        equitableDelegate = new EquitablePropertyImpl<>(partial, field);
    }

    @Override
    public Condition<T> exists() {
        return existentialDelegate.exists();
    }

    @Override
    public Condition<T> doesNotExist() {
        return existentialDelegate.doesNotExist();
    }

    @Override
    public Condition<T> exists(boolean exists) {
        return existentialDelegate.exists(exists);
    }

    @Override
    public Condition<T> in(S... values) {
        return listableDelegate.in(values);
    }

    @Override
    public Condition<T> in(Collection<S> values) {
        return listableDelegate.in(values);
    }

    @Override
    public Condition<T> nin(S... values) {
        return listableDelegate.nin(values);
    }

    @Override
    public Condition<T> nin(Collection<S> values) {
        return listableDelegate.nin(values);
    }

    @Override
    public Condition<T> eq(S value) {
        return equitableDelegate.eq(value);
    }

    @Override
    public Condition<T> ne(S value) {
        return equitableDelegate.ne(value);
    }
}
