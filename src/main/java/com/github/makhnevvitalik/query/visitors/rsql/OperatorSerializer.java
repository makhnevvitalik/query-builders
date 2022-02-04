/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.visitors.rsql;

import com.github.makhnevvitalik.query.nodes.Operator;

public interface OperatorSerializer {

    OperatorSerializer INSTANCE = new RsqlOperatorSerializer();

    String apply(Operator operator);
}
