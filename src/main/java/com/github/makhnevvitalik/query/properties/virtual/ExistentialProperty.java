/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.properties.virtual;

import com.github.makhnevvitalik.query.conditions.Condition;
import com.github.makhnevvitalik.query.conditions.Partial;

/**
 * For properties that may or may not exist.
 *
 * @param <T> The final type of the builder.
 */
public interface ExistentialProperty<T extends Partial<T>> extends Property<T> {

    /**
     * Specifies that the selected field must exist (and be non-null).
     *
     * @return The logically complete condition
     */
    Condition<T> exists();

    /**
     * Specifies that the selected field must not exist (or be equal to null).
     *
     * @return The logically complete condition
     */
    Condition<T> doesNotExist();


    /**
     * @return The logically complete condition
     */
    Condition<T> exists(boolean exists);
}
