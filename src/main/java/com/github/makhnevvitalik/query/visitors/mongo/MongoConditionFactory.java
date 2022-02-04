/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.visitors.mongo;

import com.github.makhnevvitalik.query.nodes.FieldNode;
import com.github.makhnevvitalik.query.nodes.Operator;
import com.github.makhnevvitalik.query.nodes.OperatorNode;
import com.github.makhnevvitalik.query.nodes.Operators;
import com.github.makhnevvitalik.query.visitors.ConditionFactory;
import java.util.Collection;
import org.springframework.data.mongodb.core.query.Criteria;

public class MongoConditionFactory implements ConditionFactory<String, Criteria> {

    public static final MongoConditionFactory INSTANCE = new MongoConditionFactory();

    @Override
    public String createField(FieldNode node) {
        return node.getField(".");
    }

    @Override
    public Criteria createCondition(OperatorNode node, String field, Object value) {
        Operator operator = node.getOperator();
        if (Operators.AND.equals(operator)) {
            return new Criteria().andOperator((Collection<Criteria>) value);
        } else if (Operators.OR.equals(operator)) {
            return new Criteria().orOperator((Collection<Criteria>) value);
        } else if (Operators.EQ.equals(operator)) {
            return Criteria.where(field).is(value);
        } else if (Operators.NE.equals(operator)) {
            return Criteria.where(field).ne(value);
        } else if (Operators.EX.equals(operator)) {
            return Criteria.where(field).exists((Boolean) value);
        } else if (Operators.GT.equals(operator)) {
            return Criteria.where(field).gt(value);
        } else if (Operators.LT.equals(operator)) {
            return Criteria.where(field).lt(value);
        } else if (Operators.GTE.equals(operator)) {
            return Criteria.where(field).gte(value);
        } else if (Operators.LTE.equals(operator)) {
            return Criteria.where(field).lte(value);
        } else if (Operators.IN.equals(operator)) {
            return Criteria.where(field).in((Collection<?>) value);
        } else if (Operators.NIN.equals(operator)) {
            return Criteria.where(field).nin((Collection<?>) value);
        } else if (Operators.RE.equals(operator)) {
            return Criteria.where(field).regex((String) value);
        } else if (Operators.SUB_CONDITION_ANY.equals(operator)) {
            return Criteria.where(field).elemMatch((Criteria) value);
        } else {
            throw new IllegalStateException("Mapping for operator '" + operator.getRepresentation() + "' is not found");
        }
    }
}
