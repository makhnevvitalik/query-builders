/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.nodes;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Operators {

    public static final Operator AND = new OperatorImpl("and", ValueTypes.AGGREGATION);
    public static final Operator OR = new OperatorImpl("or", ValueTypes.AGGREGATION);
    public static final Operator EQ = new OperatorImpl("eq", ValueTypes.SINGLE);
    public static final Operator NE = new OperatorImpl("ne", ValueTypes.SINGLE);
    public static final Operator GT = new OperatorImpl("gt", ValueTypes.SINGLE);
    public static final Operator LT = new OperatorImpl("lt", ValueTypes.SINGLE);
    public static final Operator GTE = new OperatorImpl("gte", ValueTypes.SINGLE);
    public static final Operator LTE = new OperatorImpl("lte", ValueTypes.SINGLE);
    public static final Operator IN = new OperatorImpl("in", ValueTypes.COLLECTION);
    public static final Operator NIN = new OperatorImpl("nin", ValueTypes.COLLECTION);
    public static final Operator EX = new OperatorImpl("ex", ValueTypes.SINGLE);
    public static final Operator RE = new OperatorImpl("re", ValueTypes.SINGLE);
    public static final Operator SUB_CONDITION_ANY = new OperatorImpl("sub_condition_any", ValueTypes.SUB_CONDITION);
}
