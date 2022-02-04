/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.nodes;

import java.util.Arrays;
import java.util.List;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(exclude = "parent")
public class ComparisonNodeImpl implements ComparisonNode {

    Operator operator;
    AbstractNode parent;
    List<AbstractNode> children;

    public ComparisonNodeImpl(Operator operator, AbstractNode parent, AbstractNode left, AbstractNode right) {
        this.operator = operator;
        this.parent = parent;
        this.children = Arrays.asList(left.withParent(this), right.withParent(this));
    }

    public static ComparisonNodeImpl create(Operator operator, AbstractNode left, Object right) {
        return create(operator, left, new ValueNodeImpl(right));
    }

    public static ComparisonNodeImpl create(Operator operator, AbstractNode left, AbstractNode right) {
        return new ComparisonNodeImpl(operator, null, left, right);
    }

    @Override
    public AbstractNode getLeft() {
        return children.get(0);
    }

    @Override
    public AbstractNode getRight() {
        return children.get(1);
    }

    @Override
    public AbstractNode withParent(AbstractNode parent) {
        return new ComparisonNodeImpl(operator, parent, children.get(0), children.get(1));
    }
}
