/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.properties.concrete.impl;

import com.github.makhnevvitalik.query.conditions.Partial;
import com.github.makhnevvitalik.query.nodes.AbstractNode;
import com.github.makhnevvitalik.query.properties.concrete.IntegerProperty;
import com.github.makhnevvitalik.query.properties.virtual.impl.NumberPropertyImpl;

public class IntegerPropertyImpl<T extends Partial<T>> extends NumberPropertyImpl<T, Integer>
    implements IntegerProperty<T> {

    public IntegerPropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }
}
