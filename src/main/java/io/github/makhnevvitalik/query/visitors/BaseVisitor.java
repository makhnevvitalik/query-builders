/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.visitors;

import io.github.makhnevvitalik.query.nodes.AbstractNode;
import io.github.makhnevvitalik.query.nodes.ComparisonNode;
import io.github.makhnevvitalik.query.nodes.FieldNode;
import io.github.makhnevvitalik.query.nodes.LogicalNode;
import io.github.makhnevvitalik.query.nodes.Operator;
import io.github.makhnevvitalik.query.nodes.OperatorNode;
import io.github.makhnevvitalik.query.nodes.ValueNode;
import io.github.makhnevvitalik.query.nodes.ValueTypes;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseVisitor<T, F> implements Visitor<T> {

    private final ConditionFactory<F, T> conditionFactory;

    @Override
    public T visit(AbstractNode node) {
        if (node instanceof LogicalNode) {
            LogicalNode n = (LogicalNode) node;
            List<T> values = n.getChildren().stream()
                .map(this::visit)
                .collect(Collectors.toList());
            return conditionFactory.createCondition(n, null, values);
        } else if (node instanceof ComparisonNode) {
            ComparisonNode n = (ComparisonNode) node;
            Operator operator = n.getOperator();
            F field = conditionFactory.createField(visitLeftNode(n.getLeft()));
            Object value = visitRightNode(n.getRight(), operator.getValueType());
            return conditionFactory.createCondition(n, field, value);
        } else {
            throw new UnsupportedOperationException(String.format("Unsupported node '%s'", node.getId()));
        }
    }

    protected FieldNode visitLeftNode(AbstractNode left) {
        if (left instanceof FieldNode) {
            return (FieldNode) left;
        } else {
            throw new UnsupportedOperationException(
                String.format("Unsupported node '%s' as left value", left.getId()));
        }
    }

    protected Object visitRightNode(AbstractNode right, String valueType) {
        if (right instanceof OperatorNode) {
            return visit(right);
        } else if (right instanceof FieldNode) {
            return ((FieldNode) right).getFieldParts();
        } else if (right instanceof ValueNode) {
            return getValue((ValueNode) right, valueType);
        } else {
            throw new UnsupportedOperationException(
                String.format("Unsupported node '%s' as right value", right.getId()));
        }
    }

    protected Object getValue(ValueNode valueNode, String valueType) {
        switch (valueType) {
            case ValueTypes.SINGLE:
                return valueNode.getValue();
            case ValueTypes.COLLECTION:
                return valueNode.getValueAsCollection();
            default:
                throw new IllegalStateException("Unexpected value: " + valueType);
        }
    }
}
