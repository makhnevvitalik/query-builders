/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.visitors.rsql;

import io.github.makhnevvitalik.query.nodes.Operator;
import io.github.makhnevvitalik.query.nodes.ValueTypes;
import java.util.Collection;
import java.util.stream.Collectors;

public class RsqlConditionSerializer implements ConditionSerializer {

    private final OperatorSerializer operatorSerializer;
    private final ValueSerializer valueSerializer;

    public RsqlConditionSerializer() {
        this(OperatorSerializer.INSTANCE, ValueSerializer.INSTANCE);
    }

    public RsqlConditionSerializer(OperatorSerializer operatorSerializer, ValueSerializer valueSerializer) {
        this.operatorSerializer = operatorSerializer;
        this.valueSerializer = valueSerializer;
    }

    @Override
    public String apply(Operator operator, Object value) {
        String rsqlOperator = operatorSerializer.apply(operator);
        switch (operator.getValueType()) {
            case ValueTypes.AGGREGATION:
                return String.join(rsqlOperator, ((Collection<String>) value));
            case ValueTypes.COLLECTION: {
                String valueStr = ((Collection<Object>) value).stream()
                    .map(valueSerializer::apply)
                    .collect(Collectors.joining(",", "(", ")"));
                return rsqlOperator + valueStr;
            }
            case ValueTypes.SINGLE:
            case ValueTypes.SUB_CONDITION:
                return rsqlOperator + valueSerializer.apply(value);
            default:
                throw new IllegalStateException("Unexpected value: " + operator.getValueType());
        }
    }
}
