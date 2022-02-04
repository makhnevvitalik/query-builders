/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.concrete;

import io.github.makhnevvitalik.query.conditions.Partial;
import io.github.makhnevvitalik.query.properties.virtual.BaseProperty;

/**
 * A property view for fields with {@link Enum} values.
 *
 * @param <T> The type of the final builder.
 */
public interface EnumProperty<T extends Partial<T>, S extends Enum<S>> extends BaseProperty<T, S> {}
