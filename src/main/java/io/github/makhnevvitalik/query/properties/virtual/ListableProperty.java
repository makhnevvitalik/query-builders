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
import java.util.Collection;

/**
 * For acting against a field via a list of values.
 */
public interface ListableProperty<T extends Partial<T>, S> extends Property<T> {

    /**
     * Specifies that the field's value must occur at least once in the list of provided values.
     *
     * @param values The values that make up the set of things that the field's value must occur in.
     * @return The logically complete condition.
     */
    Condition<T> in(S... values);

    /**
     * Specifies that the field's value must occur at least once in the list of provided values.
     *
     * @param values The values that make up the set of things that the field's value must occur in.
     * @return The logically complete condition.
     */
    Condition<T> in(Collection<S> values);

    /**
     * Specifies that the field's value must never occur in the list of provided values.
     *
     * @param values The values that make up the set of things that the field's value must never occur in.
     * @return The logically complete condition.
     */
    Condition<T> nin(S... values);

    /**
     * Specifies that the field's value must never occur in the list of provided values.
     *
     * @param values The values that make up the set of things that the field's value must never occur in.
     * @return The logically complete condition.
     */
    Condition<T> nin(Collection<S> values);

}
