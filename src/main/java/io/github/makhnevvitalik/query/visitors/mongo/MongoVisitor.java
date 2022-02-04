/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.visitors.mongo;

import io.github.makhnevvitalik.query.visitors.BaseVisitor;
import io.github.makhnevvitalik.query.visitors.ConditionFactory;
import org.springframework.data.mongodb.core.query.Criteria;

public class MongoVisitor extends BaseVisitor<Criteria, String> {

    public MongoVisitor() {
        this(MongoConditionFactory.INSTANCE);
    }

    public MongoVisitor(ConditionFactory<String, Criteria> conditionFactory) {
        super(conditionFactory);
    }
}
