/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.concrete;

import io.github.makhnevvitalik.query.conditions.Condition;
import io.github.makhnevvitalik.query.conditions.Partial;
import io.github.makhnevvitalik.query.properties.virtual.BaseProperty;


/**
 * A property view for fields with {@link Boolean} values.
 *
 * @param <T> The type of the final builder.
 */
public interface BooleanProperty<T extends Partial<T>> extends BaseProperty<T, Boolean> {

    /**
     * Mandates that the boolean field must be true to match the query.
     *
     * @return The logically complete condition.
     */
    Condition<T> isTrue();

    /**
     * Mandates that the boolean field must be false to match the query.
     *
     * @return The logically complete condition.
     */
    Condition<T> isFalse();

}
