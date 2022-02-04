/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.properties.concrete;

import com.github.makhnevvitalik.query.conditions.Condition;
import com.github.makhnevvitalik.query.conditions.Partial;
import com.github.makhnevvitalik.query.properties.virtual.WithProperty;

/**
 * A property view for multi-value fields containing objects who themselves
 * may have additional fields.
 *
 * @param <T> The type of the final builder.
 */
public interface ConditionProperty<T extends Partial<T>, S extends Partial<S>>
    extends WithProperty<T, S, ConditionProperty<T, S>> {

    /**
     * Mandates that at least one of the elements of the multivalued fields must match the
     * provided condition exactly.
     *
     * @param condition The condition that should be imposed individually against each element
     * in the multivalued field.
     * @return The logically complete condition.
     */
    Condition<T> any(Condition<S> condition);

}
