/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.visitors;

import com.github.makhnevvitalik.query.nodes.AbstractNode;

public interface Visitor<T> {

    T visit(AbstractNode node);
}