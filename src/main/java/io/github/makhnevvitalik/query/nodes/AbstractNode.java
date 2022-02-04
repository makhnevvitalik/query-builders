/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.nodes;

public interface AbstractNode {

    default String getId() {
        return this.getClass().getSimpleName();
    }

    AbstractNode getParent();

    AbstractNode withParent(AbstractNode parent);
}
