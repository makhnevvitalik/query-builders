/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.visitors.rsql;

import io.github.makhnevvitalik.query.nodes.Operator;
import io.github.makhnevvitalik.query.nodes.Operators;

public class RsqlOperatorSerializer implements OperatorSerializer {

    @Override
    public String apply(Operator operator) {
        if (Operators.AND.equals(operator)) {
            return ";";
        } else if (Operators.OR.equals(operator)) {
            return ",";
        } else if (Operators.EQ.equals(operator)) {
            return "==";
        } else if (Operators.NE.equals(operator)) {
            return "!=";
        } else if (Operators.EX.equals(operator)) {
            return "=ex=";
        } else if (Operators.GT.equals(operator)) {
            return "=gt=";
        } else if (Operators.LT.equals(operator)) {
            return "=lt=";
        } else if (Operators.GTE.equals(operator)) {
            return "=ge=";
        } else if (Operators.LTE.equals(operator)) {
            return "=le=";
        } else if (Operators.IN.equals(operator)) {
            return "=in=";
        } else if (Operators.NIN.equals(operator)) {
            return "=out=";
        } else if (Operators.RE.equals(operator)) {
            return "=re=";
        } else if (Operators.SUB_CONDITION_ANY.equals(operator)) {
            return "=q=";
        } else {
            throw new IllegalStateException("Mapping for operator '" + operator.getRepresentation() + "' is not found");
        }
    }
}
