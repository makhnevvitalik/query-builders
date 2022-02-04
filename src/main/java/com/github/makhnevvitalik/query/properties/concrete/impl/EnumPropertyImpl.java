/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.properties.concrete.impl;

import com.github.makhnevvitalik.query.conditions.Partial;
import com.github.makhnevvitalik.query.nodes.AbstractNode;
import com.github.makhnevvitalik.query.properties.concrete.EnumProperty;
import com.github.makhnevvitalik.query.properties.virtual.impl.BasePropertyImpl;

public class EnumPropertyImpl<T extends Partial<T>, S extends Enum<S>> extends BasePropertyImpl<T, S>
    implements EnumProperty<T, S> {

    public EnumPropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }
}
