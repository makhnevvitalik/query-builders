/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.properties.virtual;

import com.github.makhnevvitalik.query.conditions.Partial;

/**
 * A marker interface for properties that have been defined. Properties
 * represent queryable field types and expose mechanisms to define the
 * constraints against hte field for the query.
 *
 * @param <T> The final type of the builder.
 */
public interface Property<T extends Partial<T>> {
}
