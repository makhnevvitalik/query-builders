/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.conditions;

import com.github.makhnevvitalik.query.conditions.impl.ConditionImpl;
import com.github.makhnevvitalik.query.nodes.AbstractNode;
import com.github.makhnevvitalik.query.nodes.LogicalNode;
import com.github.makhnevvitalik.query.nodes.LogicalNodeImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConditionUtils {

    public <T extends Partial<T>> Condition<T> condition(T partial, AbstractNode node) {
        if (partial.getNode() == null) {
            return new ConditionImpl<>(partial.withNode(node));
        } else {
            if (partial.getNode() instanceof LogicalNode) {
                LogicalNode n = (LogicalNode) partial.getNode();
                List<AbstractNode> children = new ArrayList<>(n.getChildren());
                children.add(node);
                return new ConditionImpl<>(partial.withNode(
                    new LogicalNodeImpl(n.getOperator(), n.getParent(), children)));
            } else {
                throw new UnsupportedOperationException(
                    String.format("Parent node '%s' does not support children", partial.getNode().getId()));
            }
        }
    }
}
