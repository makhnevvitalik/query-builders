/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.testsupport;


import com.github.makhnevvitalik.query.builders.QBuilder;
import com.github.makhnevvitalik.query.conditions.Condition;
import com.github.makhnevvitalik.query.nodes.AbstractNode;
import com.github.makhnevvitalik.query.properties.concrete.BigDecimalProperty;
import com.github.makhnevvitalik.query.properties.concrete.BooleanProperty;
import com.github.makhnevvitalik.query.properties.concrete.ConditionProperty;
import com.github.makhnevvitalik.query.properties.concrete.DoubleProperty;
import com.github.makhnevvitalik.query.properties.concrete.EnumProperty;
import com.github.makhnevvitalik.query.properties.concrete.FloatProperty;
import com.github.makhnevvitalik.query.properties.concrete.InstantProperty;
import com.github.makhnevvitalik.query.properties.concrete.IntegerProperty;
import com.github.makhnevvitalik.query.properties.concrete.LocalDateProperty;
import com.github.makhnevvitalik.query.properties.concrete.LongProperty;
import com.github.makhnevvitalik.query.properties.concrete.PathProperty;
import com.github.makhnevvitalik.query.properties.concrete.ShortProperty;
import com.github.makhnevvitalik.query.properties.concrete.StringProperty;

public class QueryModel extends QBuilder<QueryModel> {

    public static class QueryModelPredef {

        @SafeVarargs
        public static Condition<QueryModel> and(Condition<QueryModel> c1, Condition<QueryModel> c2,
                                                Condition<QueryModel>... cn) {
            return new QueryModel().and(c1, c2, cn);
        }

        @SafeVarargs
        public static Condition<QueryModel> or(Condition<QueryModel> c1, Condition<QueryModel> c2,
                                               Condition<QueryModel>... cn) {
            return new QueryModel().or(c1, c2, cn);
        }

        public static BooleanProperty<QueryModel> myBoolean() {
            return new QueryModel().myBoolean();
        }

        public static StringProperty<QueryModel> myString() {
            return new QueryModel().myString();
        }

        public static LongProperty<QueryModel> myLong() {
            return new QueryModel().myLong();
        }

        public static DoubleProperty<QueryModel> myDouble() {
            return new QueryModel().myDouble();
        }

        public static IntegerProperty<QueryModel> myInteger() {
            return new QueryModel().myInteger();
        }

        public static ShortProperty<QueryModel> myShort() {
            return new QueryModel().myShort();
        }

        public static FloatProperty<QueryModel> myFloat() {
            return new QueryModel().myFloat();
        }

        public static BigDecimalProperty<QueryModel> myBigDecimal() {
            return new QueryModel().myBigDecimal();
        }

        public static StringProperty<QueryModel> myListOfStrings() {
            return new QueryModel().myListOfStrings();
        }

        public static InstantProperty<QueryModel> myInstant() {
            return new QueryModel().myInstant();
        }

        public static LocalDateProperty<QueryModel> myLocalDate() {
            return new QueryModel().myLocalDate();
        }

        public static ConditionProperty<QueryModel, QueryModel> mySubList() {
            return new QueryModel().mySubList();
        }

        public static EnumProperty<QueryModel, MyEnum> myEnum() {
            return new QueryModel().myEnum();
        }

        public static PathProperty<QueryModel, QueryModel> mySubModel() {
            return new QueryModel().mySubModel();
        }
    }

    public QueryModel() {
    }

    public QueryModel(AbstractNode node) {
        super(node);
    }

    @Override
    public QueryModel withNode(AbstractNode node) {
        return new QueryModel(node);
    }

    public EnumProperty<QueryModel, MyEnum> myEnum() {
        return enumeration("myEnum");
    }

    public BooleanProperty<QueryModel> myBoolean() {
        return bool("myBoolean");
    }

    public StringProperty<QueryModel> myString() {
        return string("myString");
    }

    public LongProperty<QueryModel> myLong() {
        return longNum("myLong");
    }

    public DoubleProperty<QueryModel> myDouble() {
        return doubleNum("myDouble");
    }

    public IntegerProperty<QueryModel> myInteger() {
        return intNum("myInteger");
    }

    public ShortProperty<QueryModel> myShort() {
        return shortNum("myShort");
    }

    public FloatProperty<QueryModel> myFloat() {
        return floatNum("myFloat");
    }

    public BigDecimalProperty<QueryModel> myBigDecimal() {
        return bigDecimal("myBigDecimal");
    }

    public StringProperty<QueryModel> myListOfStrings() {
        return string("myListOfStrings");
    }

    public InstantProperty<QueryModel> myInstant() {
        return instant("myInstant");
    }

    public LocalDateProperty<QueryModel> myLocalDate() {
        return localDate("myLocalDate");
    }

    public ConditionProperty<QueryModel, QueryModel> mySubList() {
        return condition("mySubList");
    }

    public PathProperty<QueryModel, QueryModel> mySubModel() {
        return path("mySubModel");
    }
}
