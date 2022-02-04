/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.concrete;

import io.github.makhnevvitalik.query.conditions.Partial;
import io.github.makhnevvitalik.query.properties.virtual.WithProperty;

public interface PathProperty<T extends Partial<T>, S extends Partial<S>>
    extends WithProperty<T, S, PathProperty<T, S>> {
}
