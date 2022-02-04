/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.conditions;

import io.github.makhnevvitalik.query.nodes.AbstractNode;
import io.github.makhnevvitalik.query.properties.concrete.BigDecimalProperty;
import io.github.makhnevvitalik.query.properties.concrete.BooleanProperty;
import io.github.makhnevvitalik.query.properties.concrete.ConditionProperty;
import io.github.makhnevvitalik.query.properties.concrete.DoubleProperty;
import io.github.makhnevvitalik.query.properties.concrete.EnumProperty;
import io.github.makhnevvitalik.query.properties.concrete.FloatProperty;
import io.github.makhnevvitalik.query.properties.concrete.InstantProperty;
import io.github.makhnevvitalik.query.properties.concrete.IntegerProperty;
import io.github.makhnevvitalik.query.properties.concrete.LocalDateProperty;
import io.github.makhnevvitalik.query.properties.concrete.LongProperty;
import io.github.makhnevvitalik.query.properties.concrete.PathProperty;
import io.github.makhnevvitalik.query.properties.concrete.ShortProperty;
import io.github.makhnevvitalik.query.properties.concrete.StringProperty;
import io.github.makhnevvitalik.query.properties.virtual.Property;
import java.util.List;
import java.util.function.BiFunction;

public interface Partial<T extends Partial<T>> {

    AbstractNode getNode();

    T withNode(AbstractNode node);

    /**
     * For usage when the field is known to contain values of an enum type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    <S extends Enum<S>> EnumProperty<T, S> enumeration(String field);

    /**
     * For usage when the field is known to contain values of a boolean type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    BooleanProperty<T> bool(String field);

    /**
     * For usage when the field is known to contain values of a string type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    StringProperty<T> string(String field);

    /**
     * For usage when the field is known to contain values of a long type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    LongProperty<T> longNum(String field);

    /**
     * For usage when the field is known to contain values of an integer type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    IntegerProperty<T> intNum(String field);

    /**
     * For usage when the field is known to contain values of a numerical short type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    ShortProperty<T> shortNum(String field);

    /**
     * For usage when the field is known to contain values of a numerical float type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    FloatProperty<T> floatNum(String field);

    /**
     * For usage when the field is known to contain values of a numerical double type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    DoubleProperty<T> doubleNum(String field);

    /**
     * For usage when the field is known to contain values of a numerical BigDecimal type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    BigDecimalProperty<T> bigDecimal(String field);

    /**
     * For usage when the field is known to contain values of {@link java.time.Instant} type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    InstantProperty<T> instant(String field);

    /**
     * For usage when the field is known to contain values of {@link java.time.LocalDate} type.
     *
     * @param field The name of the field.
     * @return The property interface so that a constraint can be set against the field.
     */
    LocalDateProperty<T> localDate(String field);

    /**
     * For usage when the field is a multivalued field of objects who themselves can
     * be tested against a condition.
     *
     * @param field The name of the multivalued field.
     * @param <S> The kind of the subquery condition.
     * @return The property interface so that a condition constraint can be set against the field.
     */
    <S extends Partial<S>> ConditionProperty<T, S> condition(String field);

    <S extends Partial<S>> PathProperty<T, S> path(String field);

    <P extends Property<T>> P property(String field, BiFunction<T, AbstractNode, P> factory);

    /**
     * Allows for composing a list of conditions in a "any match" fashion.
     *
     * @param conditions The list of conditions to combine.
     * @return The logical condition that represents the composition of the list.
     */
    Condition<T> or(List<Condition<T>> conditions);

    /**
     * Allows for composing a list of conditions in a "all match" fashion.
     *
     * @param conditions The list of conditions to combine.
     * @return The logical condition that represents the composition of the list.
     */
    Condition<T> and(List<Condition<T>> conditions);

    /**
     * Allows for composing a list of conditions in a "any match" fashion.
     *
     * @param c1 The first condition to combine.
     * @param cn Any other conditions to combine.
     * @return The logical condition that represents the composition of the list.
     */
    Condition<T> or(Condition<T> c1, Condition<T> c2, Condition<T>... cn);

    /**
     * Allows for composing a list of conditions in a "all match" fashion.
     *
     * @param c1 The first condition to combine.
     * @param c2 The second condition to combine.
     * @param cn Any other conditions to combine.
     * @return The logical condition that represents the composition of the list.
     */
    Condition<T> and(Condition<T> c1, Condition<T> c2, Condition<T>... cn);
}
