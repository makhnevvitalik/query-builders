/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.visitors.rsql;

import com.github.makhnevvitalik.query.nodes.FieldNode;
import com.github.makhnevvitalik.query.nodes.LogicalNode;
import com.github.makhnevvitalik.query.nodes.Operator;
import com.github.makhnevvitalik.query.nodes.OperatorNode;
import com.github.makhnevvitalik.query.visitors.ConditionFactory;

public class RsqlConditionFactory implements ConditionFactory<String, String> {

    public static final RsqlConditionFactory INSTANCE = new RsqlConditionFactory();

    private final ConditionSerializer conditionSerializer;

    public RsqlConditionFactory() {
        this(ConditionSerializer.INSTANCE);
    }

    public RsqlConditionFactory(ConditionSerializer conditionSerializer) {
        this.conditionSerializer = conditionSerializer;
    }

    @Override
    public String createField(FieldNode node) {
        return node.getField(".");
    }

    @Override
    public String createCondition(OperatorNode node, String field, Object value) {
        Operator op = node.getOperator();
        String condition;
        if (field == null) {
            condition = conditionSerializer.apply(op, value);
        } else {
            condition = field + conditionSerializer.apply(op, value);
        }

        if (node instanceof LogicalNode && node.getParent() instanceof LogicalNode) {
            return "(" + condition + ")";
        } else {
            return condition;
        }
    }
}
