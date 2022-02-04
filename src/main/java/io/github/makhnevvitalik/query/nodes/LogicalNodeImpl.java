/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.nodes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(exclude = "parent")
public class LogicalNodeImpl implements LogicalNode {

    Operator operator;
    AbstractNode parent;
    List<AbstractNode> children;

    public LogicalNodeImpl(Operator operator, AbstractNode parent, List<? extends AbstractNode> children) {
        this.operator = operator;
        this.parent = parent;
        this.children = Collections.unmodifiableList(
            children.stream()
                .map(it -> it.withParent(this))
                .collect(Collectors.toList())
        );
    }

    public LogicalNodeImpl(Operator operator, AbstractNode parent, AbstractNode... children) {
        this(operator, parent, Arrays.asList(children));
    }

    public LogicalNodeImpl(Operator operator, List<? extends AbstractNode> children) {
        this(operator, null, children);
    }

    @Override
    public AbstractNode withParent(AbstractNode parent) {
        return new LogicalNodeImpl(operator, parent, children);
    }
}
