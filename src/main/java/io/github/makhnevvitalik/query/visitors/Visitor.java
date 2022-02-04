/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.visitors;

import io.github.makhnevvitalik.query.nodes.AbstractNode;

public interface Visitor<T> {

    T visit(AbstractNode node);
}
