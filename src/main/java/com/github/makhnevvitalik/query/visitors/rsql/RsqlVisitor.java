/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.visitors.rsql;

import com.github.makhnevvitalik.query.visitors.BaseVisitor;
import com.github.makhnevvitalik.query.visitors.ConditionFactory;

public class RsqlVisitor extends BaseVisitor<String, String> {

    public RsqlVisitor() {
        this(RsqlConditionFactory.INSTANCE);
    }

    public RsqlVisitor(ConditionFactory<String, String> conditionFactory) {
        super(conditionFactory);
    }
}
