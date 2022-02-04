/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.virtual;

import io.github.makhnevvitalik.query.conditions.Condition;
import io.github.makhnevvitalik.query.conditions.Partial;

/**
 * For properties that may or may not be equal.
 *
 * @param <T> The final type of the builder.
 */
public interface EquitableProperty<T extends Partial<T>, S> extends Property<T> {

    /**
     * Specifies that the value of the field must be equal to the provided value.
     *
     * @param value The value that the field must be equal to.
     * @return The logically complete condition.
     */
    Condition<T> eq(S value);

    /**
     * Specifies that the value of the field must not be equal to the provided value.
     *
     * @param value The value that the field must not be equal to.
     * @return The logically complete condition.
     */
    Condition<T> ne(S value);

}
