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
 * For numerical fields.
 *
 * @param <T> The final type of the builder.
 * @param <S> The type of the numbers that the property supports.
 */
public interface NumberProperty<T extends Partial<T>, S extends Number> extends BaseProperty<T, S> {

    /**
     * Specifies that the field's value must be greater than the provided value.
     *
     * @param number The value that the field's value must be greater than.
     * @return The logically complete condition.
     */
    Condition<T> gt(S number);

    /**
     * Specifies that the field's value must be less than the provided value.
     *
     * @param number The value that the field's value must be less than.
     * @return The logically complete condition.
     */
    Condition<T> lt(S number);

    /**
     * Specifies that the field's value must be greater than or equal to the provided value.
     *
     * @param number The value that the field's value must be greater than or equal to.
     * @return The logically complete condition.
     */
    Condition<T> gte(S number);

    /**
     * Specifies that the field's value must be less than or equal to the provided value.
     *
     * @param number The value that the field's value must be less than or equal to.
     * @return The logically complete condition.
     */
    Condition<T> lte(S number);

}
