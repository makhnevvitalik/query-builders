/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.conditions.impl;

import com.github.makhnevvitalik.query.conditions.Condition;
import com.github.makhnevvitalik.query.conditions.Partial;
import com.github.makhnevvitalik.query.nodes.AbstractNode;
import com.github.makhnevvitalik.query.nodes.LogicalNode;
import com.github.makhnevvitalik.query.nodes.LogicalNodeImpl;
import com.github.makhnevvitalik.query.nodes.Operator;
import com.github.makhnevvitalik.query.nodes.Operators;
import com.github.makhnevvitalik.query.visitors.Visitor;
import java.util.Collections;
import lombok.Value;

@Value
public class ConditionImpl<T extends Partial<T>> implements Condition<T> {

    T partial;

    @Override
    public AbstractNode getNode() {
        return partial.getNode();
    }

    @Override
    public T and() {
        return create(Operators.AND);
    }

    @Override
    public T or() {
        return create(Operators.OR);
    }

    @Override
    public <Q> Q query(Visitor<Q> visitor) {
        return visitor.visit(partial.getNode());
    }

    private T create(Operator operator) {
        AbstractNode node = partial.getNode();
        if (node instanceof LogicalNode && ((LogicalNode) node).getOperator().equals(operator)) {
            return partial;
        }

        return partial.withNode(
            new LogicalNodeImpl(operator, node.getParent(), Collections.singletonList(node)));
    }
}
