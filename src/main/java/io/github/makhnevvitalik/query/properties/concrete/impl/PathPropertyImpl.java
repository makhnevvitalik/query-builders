/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.concrete.impl;

import io.github.makhnevvitalik.query.conditions.Partial;
import io.github.makhnevvitalik.query.nodes.AbstractNode;
import io.github.makhnevvitalik.query.properties.concrete.PathProperty;
import io.github.makhnevvitalik.query.properties.virtual.impl.WithPropertyImpl;


public class PathPropertyImpl<T extends Partial<T>, S extends Partial<S>>
    extends WithPropertyImpl<T, S, PathProperty<T, S>>
    implements PathProperty<T, S> {

    public PathPropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }

    @Override
    protected PathProperty<T, S> newInstance(T partial, AbstractNode field) {
        return new PathPropertyImpl<>(partial, field);
    }
}
