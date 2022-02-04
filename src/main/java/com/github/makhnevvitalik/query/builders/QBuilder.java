/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.builders;

import com.github.makhnevvitalik.query.conditions.Condition;
import com.github.makhnevvitalik.query.conditions.ConditionUtils;
import com.github.makhnevvitalik.query.conditions.Partial;
import com.github.makhnevvitalik.query.nodes.AbstractNode;
import com.github.makhnevvitalik.query.nodes.FieldNodeImpl;
import com.github.makhnevvitalik.query.nodes.LogicalNodeImpl;
import com.github.makhnevvitalik.query.nodes.Operator;
import com.github.makhnevvitalik.query.nodes.Operators;
import com.github.makhnevvitalik.query.properties.concrete.BigDecimalProperty;
import com.github.makhnevvitalik.query.properties.concrete.BooleanProperty;
import com.github.makhnevvitalik.query.properties.concrete.ConditionProperty;
import com.github.makhnevvitalik.query.properties.concrete.DoubleProperty;
import com.github.makhnevvitalik.query.properties.concrete.EnumProperty;
import com.github.makhnevvitalik.query.properties.concrete.FloatProperty;
import com.github.makhnevvitalik.query.properties.concrete.InstantProperty;
import com.github.makhnevvitalik.query.properties.concrete.IntegerProperty;
import com.github.makhnevvitalik.query.properties.concrete.LocalDateProperty;
import com.github.makhnevvitalik.query.properties.concrete.LongProperty;
import com.github.makhnevvitalik.query.properties.concrete.PathProperty;
import com.github.makhnevvitalik.query.properties.concrete.ShortProperty;
import com.github.makhnevvitalik.query.properties.concrete.StringProperty;
import com.github.makhnevvitalik.query.properties.concrete.impl.BigDecimalPropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.BooleanPropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.ConditionPropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.DoublePropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.EnumPropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.FloatPropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.InstantPropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.IntegerPropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.LocalDatePropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.LongPropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.PathPropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.ShortPropertyImpl;
import com.github.makhnevvitalik.query.properties.concrete.impl.StringPropertyImpl;
import com.github.makhnevvitalik.query.properties.virtual.Property;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * The single class that can be used to construct an abstract representation of a query. Designed
 * to be extended for each domain model that might be queried against, with each field exposed as
 * a property condition builder.
 *
 * @param <T> The final type of the builder, used for a fluid chaining interface.
 */
public abstract class QBuilder<T extends Partial<T>> implements Partial<T> {

    private final AbstractNode node;

    protected QBuilder() {
        node = null;
    }

    protected QBuilder(AbstractNode node) {
        this.node = node;
    }

    public static GeneralQueryBuilder create() {
        return new GeneralQueryBuilderImpl();
    }

    @Override
    public AbstractNode getNode() {
        return node;
    }

    @Override
    public <S extends Enum<S>> EnumProperty<T, S> enumeration(String field) {
        return property(field, EnumPropertyImpl::new);
    }

    @Override
    public BooleanProperty<T> bool(String field) {
        return property(field, BooleanPropertyImpl::new);
    }

    @Override
    public StringProperty<T> string(String field) {
        return property(field, StringPropertyImpl::new);
    }

    @Override
    public LongProperty<T> longNum(String field) {
        return property(field, LongPropertyImpl::new);
    }

    @Override
    public IntegerProperty<T> intNum(String field) {
        return property(field, IntegerPropertyImpl::new);
    }

    @Override
    public ShortProperty<T> shortNum(String field) {
        return property(field, ShortPropertyImpl::new);
    }

    @Override
    public FloatProperty<T> floatNum(String field) {
        return property(field, FloatPropertyImpl::new);
    }

    @Override
    public DoubleProperty<T> doubleNum(String field) {
        return property(field, DoublePropertyImpl::new);
    }

    @Override
    public BigDecimalProperty<T> bigDecimal(String field) {
        return property(field, BigDecimalPropertyImpl::new);
    }

    @Override
    public InstantProperty<T> instant(String field) {
        return property(field, InstantPropertyImpl::new);
    }

    @Override
    public LocalDateProperty<T> localDate(String field) {
        return property(field, LocalDatePropertyImpl::new);
    }

    @Override
    public <S extends Partial<S>> ConditionProperty<T, S> condition(String field) {
        return property(field, ConditionPropertyImpl::new);
    }

    @Override
    public <S extends Partial<S>> PathProperty<T, S> path(String field) {
        return property(field, PathPropertyImpl::new);
    }

    @Override
    public <P extends Property<T>> P property(String field, BiFunction<T, AbstractNode, P> factory) {
        return factory.apply(self(), new FieldNodeImpl(field));
    }

    @Override
    public Condition<T> or(List<Condition<T>> conditions) {
        return condition(Operators.OR, conditions);
    }

    @Override
    public Condition<T> and(List<Condition<T>> conditions) {
        return condition(Operators.AND, conditions);
    }

    @Override
    public Condition<T> or(Condition<T> c1, Condition<T> c2, Condition<T>... cn) {
        List<Condition<T>> conditions = new ArrayList<>();
        conditions.add(c1);
        conditions.add(c2);
        conditions.addAll(Arrays.asList(cn));
        return or(conditions);
    }

    @Override
    public Condition<T> and(Condition<T> c1, Condition<T> c2, Condition<T>... cn) {
        List<Condition<T>> conditions = new ArrayList<>();
        conditions.add(c1);
        conditions.add(c2);
        conditions.addAll(Arrays.asList(cn));
        return and(conditions);
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    private Condition<T> condition(Operator operator, List<Condition<T>> conditions) {
        List<AbstractNode> nodes = conditions.stream()
            .map(Condition::getNode)
            .collect(Collectors.toList());
        return ConditionUtils.condition(self(), new LogicalNodeImpl(operator, nodes));
    }
}
