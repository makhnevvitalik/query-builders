/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.nodes;

import java.util.List;

public interface OperatorNode extends AbstractNode {

    @Override
    default String getId() {
        return getOperator().getRepresentation();
    }

    Operator getOperator();

    List<AbstractNode> getChildren();
}
