/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.virtual.impl;

import io.github.makhnevvitalik.query.conditions.Condition;
import io.github.makhnevvitalik.query.conditions.Partial;
import io.github.makhnevvitalik.query.nodes.AbstractNode;
import io.github.makhnevvitalik.query.nodes.ComparisonNode;
import io.github.makhnevvitalik.query.nodes.ComparisonNodeImpl;
import io.github.makhnevvitalik.query.nodes.FieldNode;
import io.github.makhnevvitalik.query.nodes.FieldNodeImpl;
import io.github.makhnevvitalik.query.nodes.LogicalNode;
import io.github.makhnevvitalik.query.nodes.LogicalNodeImpl;
import io.github.makhnevvitalik.query.nodes.OperatorNode;
import io.github.makhnevvitalik.query.properties.virtual.WithProperty;
import java.util.List;
import java.util.stream.Collectors;


public abstract class WithPropertyImpl<T extends Partial<T>, S extends Partial<S>, P extends WithProperty<T, S, P>>
    extends ExistentialPropertyImpl<T>
    implements WithProperty<T, S, P> {

    protected WithPropertyImpl(T partial, AbstractNode field) {
        super(partial, field);
    }

    @Override
    public Condition<T> with(Condition<S> query) {
        return condition(modify(query.getNode()));
    }

    @Override
    public P with(String path) {
        if (path == null) {
            throw new IllegalArgumentException("Field must not be null");
        }
        return newInstance(partial, ((FieldNode) field).combine(new FieldNodeImpl(path)));
    }

    protected abstract P newInstance(T partial, AbstractNode field);

    private OperatorNode modify(AbstractNode node) {
        if (node instanceof LogicalNode) {
            LogicalNode n = (LogicalNode) node;
            List<OperatorNode> nodes = n.getChildren().stream()
                .map(this::modify)
                .collect(Collectors.toList());
            return new LogicalNodeImpl(n.getOperator(), nodes);
        } else if (node instanceof ComparisonNode) {
            ComparisonNode n = (ComparisonNode) node;
            return ComparisonNodeImpl.create(
                n.getOperator(),
                ((FieldNode) field).combine((FieldNode) n.getLeft()),
                n.getRight()
            );
        } else {
            throw new UnsupportedOperationException(
                String.format("'With' property does not support node '%s'", node.getId()));
        }
    }
}
