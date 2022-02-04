/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.testsupport;

import static com.github.makhnevvitalik.query.testsupport.MyEnum.VALUE1;
import static com.github.makhnevvitalik.query.testsupport.MyEnum.VALUE2;
import static com.github.makhnevvitalik.query.testsupport.MyEnum.VALUE3;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myBigDecimal;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myBoolean;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myDouble;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myEnum;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myFloat;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myInstant;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myInteger;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myLocalDate;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myLong;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myShort;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.myString;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.mySubList;
import static com.github.makhnevvitalik.query.testsupport.QueryModel.QueryModelPredef.mySubModel;

import com.github.makhnevvitalik.query.conditions.Condition;
import com.github.makhnevvitalik.query.testsupport.QBuilderTestBase.Simple.String;
import com.github.makhnevvitalik.query.visitors.Visitor;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;


public abstract class QBuilderTestBase<T extends Visitor<C>, E, C> {

    protected abstract T getVisitor();

    protected abstract void compare(E expected, C converted);

    protected interface Simple {

        interface String {

            Condition<QueryModel> EQ = myString().eq("abcdefg");
            Condition<QueryModel> NE = myString().ne("abcdefg");
            Condition<QueryModel> LT = myString().lexicallyBefore("abcdefg");
            Condition<QueryModel> GT = myString().lexicallyAfter("abcdefg");
            Condition<QueryModel> GTE = myString().lexicallyNotBefore("abcdefg");
            Condition<QueryModel> LTE = myString().lexicallyNotAfter("abcdefg");
            Condition<QueryModel> EX = myString().exists();
            Condition<QueryModel> DNE = myString().doesNotExist();
            Condition<QueryModel> IN = myString().in("a", "b", "c");
            Condition<QueryModel> NIN = myString().nin("d", "e", "f");
            Condition<QueryModel> RE = myString().pattern("(abc|def)");
        }

        interface Enum {

            Condition<QueryModel> EQ = myEnum().eq(VALUE1);
            Condition<QueryModel> NE = myEnum().ne(VALUE1);
            Condition<QueryModel> EX = myEnum().exists();
            Condition<QueryModel> DNE = myEnum().doesNotExist();
            Condition<QueryModel> IN = myEnum().in(VALUE1, VALUE2, VALUE3);
            Condition<QueryModel> NIN = myEnum().nin(VALUE1, VALUE2, VALUE3);
        }

        interface Boolean {

            Condition<QueryModel> TRUE = myBoolean().isTrue();
            Condition<QueryModel> FALSE = myBoolean().isFalse();
            Condition<QueryModel> EX = myBoolean().exists();
            Condition<QueryModel> DNE = myBoolean().doesNotExist();
        }

        interface Short {

            Condition<QueryModel> EQ = myShort().eq((short) 100);
            Condition<QueryModel> NE = myShort().ne((short) 100);
            Condition<QueryModel> GT = myShort().gt((short) 100);
            Condition<QueryModel> LT = myShort().lt((short) 100);
            Condition<QueryModel> GTE = myShort().gte((short) 100);
            Condition<QueryModel> LTE = myShort().lte((short) 100);
            Condition<QueryModel> EX = myShort().exists();
            Condition<QueryModel> DNE = myShort().doesNotExist();
            Condition<QueryModel> IN = myShort().in((short) 98, (short) 99, (short) 100);
            Condition<QueryModel> NIN = myShort().nin((short) 101, (short) 102, (short) 103);
        }

        interface Integer {

            Condition<QueryModel> EQ = myInteger().eq(100);
            Condition<QueryModel> NE = myInteger().ne(100);
            Condition<QueryModel> GT = myInteger().gt(100);
            Condition<QueryModel> LT = myInteger().lt(100);
            Condition<QueryModel> GTE = myInteger().gte(100);
            Condition<QueryModel> LTE = myInteger().lte(100);
            Condition<QueryModel> EX = myInteger().exists();
            Condition<QueryModel> DNE = myInteger().doesNotExist();
            Condition<QueryModel> IN = myInteger().in(98, 99, 100);
            Condition<QueryModel> NIN = myInteger().nin(101, 102, 103);
        }

        interface Long {

            Condition<QueryModel> EQ = myLong().eq(100L);
            Condition<QueryModel> NE = myLong().ne(100L);
            Condition<QueryModel> GT = myLong().gt(100L);
            Condition<QueryModel> LT = myLong().lt(100L);
            Condition<QueryModel> GTE = myLong().gte(100L);
            Condition<QueryModel> LTE = myLong().lte(100L);
            Condition<QueryModel> EX = myLong().exists();
            Condition<QueryModel> DNE = myLong().doesNotExist();
            Condition<QueryModel> IN = myLong().in(98L, 99L, 100L);
            Condition<QueryModel> NIN = myLong().nin(101L, 102L, 103L);
        }

        interface Float {

            Condition<QueryModel> EQ = myFloat().eq(100f);
            Condition<QueryModel> NE = myFloat().ne(100f);
            Condition<QueryModel> GT = myFloat().gt(100f);
            Condition<QueryModel> LT = myFloat().lt(100f);
            Condition<QueryModel> GTE = myFloat().gte(100f);
            Condition<QueryModel> LTE = myFloat().lte(100f);
            Condition<QueryModel> EX = myFloat().exists();
            Condition<QueryModel> DNE = myFloat().doesNotExist();
            Condition<QueryModel> IN = myFloat().in(98f, 99f, 100f);
            Condition<QueryModel> NIN = myFloat().nin(101f, 102f, 103f);
        }

        interface Double {

            Condition<QueryModel> EQ = myDouble().eq(100.0);
            Condition<QueryModel> NE = myDouble().ne(100.0);
            Condition<QueryModel> GT = myDouble().gt(100.0);
            Condition<QueryModel> LT = myDouble().lt(100.0);
            Condition<QueryModel> GTE = myDouble().gte(100.0);
            Condition<QueryModel> LTE = myDouble().lte(100.0);
            Condition<QueryModel> EX = myDouble().exists();
            Condition<QueryModel> DNE = myDouble().doesNotExist();
            Condition<QueryModel> IN = myDouble().in(98.0, 99.0, 100.0);
            Condition<QueryModel> NIN = myDouble().nin(101.0, 102.0, 103.0);
        }

        interface BigDecimal {

            Condition<QueryModel> EQ = myBigDecimal().eq(new java.math.BigDecimal("0.00000001"));
            Condition<QueryModel> NE = myBigDecimal().ne(java.math.BigDecimal.TEN);
            Condition<QueryModel> GT = myBigDecimal().gt(java.math.BigDecimal.TEN);
            Condition<QueryModel> LT = myBigDecimal().lt(java.math.BigDecimal.TEN);
            Condition<QueryModel> GTE = myBigDecimal().gte(java.math.BigDecimal.TEN);
            Condition<QueryModel> LTE = myBigDecimal().lte(java.math.BigDecimal.TEN);
            Condition<QueryModel> EX = myBigDecimal().exists();
            Condition<QueryModel> DNE = myBigDecimal().doesNotExist();
            Condition<QueryModel> IN = myBigDecimal().in(
                java.math.BigDecimal.valueOf(8), java.math.BigDecimal.valueOf(9), java.math.BigDecimal.valueOf(10)
            );
            Condition<QueryModel> NIN = myBigDecimal().nin(
                java.math.BigDecimal.valueOf(11), java.math.BigDecimal.valueOf(12), java.math.BigDecimal.valueOf(13)
            );
        }

        interface Instant {

            java.time.Instant epoch = OffsetDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0))
                .toInstant();

            java.time.Instant dayAfterEpoch = epoch.plus(1, ChronoUnit.DAYS);

            Condition<QueryModel> EQ = myInstant().eq(epoch);
            Condition<QueryModel> NE = myInstant().ne(epoch);
            Condition<QueryModel> GT = myInstant().after(epoch, true);
            Condition<QueryModel> GTE = myInstant().after(epoch, false);
            Condition<QueryModel> LT = myInstant().before(dayAfterEpoch, true);
            Condition<QueryModel> LTE = myInstant().before(dayAfterEpoch, false);
            Condition<QueryModel> BETWEEN = myInstant().between(epoch, false, dayAfterEpoch, false);
            Condition<QueryModel> EX = myInstant().exists();
            Condition<QueryModel> DNE = myInstant().doesNotExist();
        }

        interface LocalDate {

            java.time.LocalDate epoch = java.time.LocalDate.of(1970, 1, 1);

            java.time.LocalDate dayAfterEpoch = epoch.plus(1, ChronoUnit.DAYS);

            Condition<QueryModel> EQ = myLocalDate().eq(epoch);
            Condition<QueryModel> NE = myLocalDate().ne(epoch);
            Condition<QueryModel> GT = myLocalDate().after(epoch, true);
            Condition<QueryModel> GTE = myLocalDate().after(epoch, false);
            Condition<QueryModel> LT = myLocalDate().before(dayAfterEpoch, true);
            Condition<QueryModel> LTE = myLocalDate().before(dayAfterEpoch, false);
            Condition<QueryModel> BETWEEN = myLocalDate().between(epoch, false, dayAfterEpoch, false);
            Condition<QueryModel> EX = myLocalDate().exists();
            Condition<QueryModel> DNE = myLocalDate().doesNotExist();
        }
    }

    protected interface Logical {

        Condition<QueryModel> INLINE_ANDING = myString().eq("Thing").and().myLong().doesNotExist();
        Condition<QueryModel> INLINE_ORING = myString().eq("Thing").or().myLong().doesNotExist();
        Condition<QueryModel> LIST_ANDING = QueryModel.QueryModelPredef
            .and(myString().eq("Thing"), myLong().doesNotExist());
        Condition<QueryModel> LIST_ORING = QueryModel.QueryModelPredef
            .or(myString().eq("Thing"), myLong().doesNotExist());
        Condition<QueryModel> LIST_ORING_OF_INLINE_ANDING = QueryModel.QueryModelPredef
            .or(myString().eq("Thing").and().myLong().doesNotExist(),
                myString().ne("Cats").and().myLong().gt(30L));

        Condition<QueryModel> LIST_ANDING_OF_INLINE_ORING = QueryModel.QueryModelPredef
            .and(myString().eq("Thing").or().myLong().doesNotExist(),
                myString().ne("Cats").or().myLong().gt(30L));

        Condition<QueryModel> LIST_ANDING_OR_LIST_ORING = QueryModel.QueryModelPredef
            .and(myString().eq("Thing").or().myLong().doesNotExist(),
                myString().ne("Cats").or().myLong().gt(30L)).or()
            .or(myString().eq("Thing").and().myLong().doesNotExist(), myString().ne("Cats").and().myLong().gt(30L));

        Condition<QueryModel> LIST_ORING_ANDLIST_ANDING = QueryModel.QueryModelPredef
            .or(myString().eq("Thing").and().myLong().doesNotExist(),
                myString().ne("Cats").and().myLong().gt(30L)).and()
            .and(myString().eq("Thing").or().myLong().doesNotExist(), myString().ne("Cats").or().myLong().gt(30L));
    }

    protected interface Chained {

        Condition<QueryModel> CHAINED_ANDS = myString().eq("Thing").and().myInteger().gt(0)
            .and().myInteger().lt(5).and().myLong().in(0L, 1L, 2L).and().myDouble().lte(2.9)
            .and().myBoolean().isFalse().and().myInstant().doesNotExist();

        Condition<QueryModel> CHAINED_ORS = myString().eq("Thing").or().myInteger().gt(0).or()
            .myInteger().lt(5).or().myLong().in(0L, 1L, 2L).or().myDouble().lte(2.9).or()
            .myBoolean().isFalse().or().myInstant().doesNotExist();

        Condition<QueryModel> CHAINED_ANDS_AND_ORS = myString().eq("Thing").and().myInteger()
            .gt(0).or().myInteger().lt(5).or().myLong().in(0L, 1L, 2L).and().myDouble()
            .lte(2.9).and().myBoolean().isFalse().or().myInstant().doesNotExist();

        Condition<QueryModel> CHAINED_ORS_AND_ANDS = myString().eq("Thing").or().myInteger()
            .gt(0).and().myInteger().lt(5).and().myLong().in(0L, 1L, 2L).or().myDouble()
            .lte(2.9).or().myBoolean().isFalse().and().myInstant().doesNotExist();
    }

    protected interface Composed {

        Condition<QueryModel> SUB_QUERY = mySubList().any(Logical.INLINE_ANDING).and().myBoolean().isTrue();

        Condition<QueryModel> PATH_SUB_QUERY = mySubModel().with(Logical.INLINE_ANDING);

        Condition<QueryModel> PATH_EQ = mySubModel().with("myField").with(String.EQ);
    }

    protected interface VariedInputs {

        Condition<QueryModel> NULL_EQUALITY = myString().eq(null);
        Condition<QueryModel> NULL_INEQUALITY = myString().ne(null);
    }

    protected E Enum_EQ;
    protected E Enum_NE;
    protected E Enum_EX;
    protected E Enum_DNE;
    protected E Enum_IN;
    protected E Enum_NIN;

    @Test
    public void simple_Enum() {
        compare(Enum_EQ, Simple.Enum.EQ);
        compare(Enum_NE, Simple.Enum.NE);
        compare(Enum_EX, Simple.Enum.EX);
        compare(Enum_DNE, Simple.Enum.DNE);
        compare(Enum_IN, Simple.Enum.IN);
        compare(Enum_NIN, Simple.Enum.NIN);
    }

    protected E String_EQ;
    protected E String_NE;
    protected E String_LT;
    protected E String_GT;
    protected E String_LTE;
    protected E String_GTE;
    protected E String_EX;
    protected E String_DNE;
    protected E String_IN;
    protected E String_NIN;
    protected E String_RE;

    @Test
    public void simple_String() {
        compare(String_EQ, Simple.String.EQ);
        compare(String_NE, Simple.String.NE);
        compare(String_LT, Simple.String.LT);
        compare(String_GT, Simple.String.GT);
        compare(String_LTE, Simple.String.LTE);
        compare(String_GTE, Simple.String.GTE);
        compare(String_EX, Simple.String.EX);
        compare(String_DNE, Simple.String.DNE);
        compare(String_IN, Simple.String.IN);
        compare(String_NIN, Simple.String.NIN);
        compare(String_RE, Simple.String.RE);
    }

    protected E Boolean_TRUE;
    protected E Boolean_FALSE;
    protected E Boolean_EX;
    protected E Boolean_DNE;

    @Test
    public void simple_Boolean() {
        compare(Boolean_TRUE, Simple.Boolean.TRUE);
        compare(Boolean_FALSE, Simple.Boolean.FALSE);
        compare(Boolean_EX, Simple.Boolean.EX);
        compare(Boolean_DNE, Simple.Boolean.DNE);
    }

    protected E Short_EQ;
    protected E Short_NE;
    protected E Short_LT;
    protected E Short_GT;
    protected E Short_LTE;
    protected E Short_GTE;
    protected E Short_EX;
    protected E Short_DNE;
    protected E Short_IN;
    protected E Short_NIN;

    @Test
    public void simple_Short() {
        compare(Short_EQ, Simple.Short.EQ);
        compare(Short_NE, Simple.Short.NE);
        compare(Short_LT, Simple.Short.LT);
        compare(Short_LTE, Simple.Short.LTE);
        compare(Short_GT, Simple.Short.GT);
        compare(Short_GTE, Simple.Short.GTE);
        compare(Short_EX, Simple.Short.EX);
        compare(Short_DNE, Simple.Short.DNE);
        compare(Short_IN, Simple.Short.IN);
        compare(Short_NIN, Simple.Short.NIN);
    }

    protected E Integer_EQ;
    protected E Integer_NE;
    protected E Integer_LT;
    protected E Integer_GT;
    protected E Integer_LTE;
    protected E Integer_GTE;
    protected E Integer_EX;
    protected E Integer_DNE;
    protected E Integer_IN;
    protected E Integer_NIN;

    @Test
    public void simple_Integer() {
        compare(Integer_EQ, Simple.Integer.EQ);
        compare(Integer_NE, Simple.Integer.NE);
        compare(Integer_LT, Simple.Integer.LT);
        compare(Integer_LTE, Simple.Integer.LTE);
        compare(Integer_GT, Simple.Integer.GT);
        compare(Integer_GTE, Simple.Integer.GTE);
        compare(Integer_EX, Simple.Integer.EX);
        compare(Integer_DNE, Simple.Integer.DNE);
        compare(Integer_IN, Simple.Integer.IN);
        compare(Integer_NIN, Simple.Integer.NIN);
    }

    protected E Long_EQ;
    protected E Long_NE;
    protected E Long_LT;
    protected E Long_GT;
    protected E Long_LTE;
    protected E Long_GTE;
    protected E Long_EX;
    protected E Long_DNE;
    protected E Long_IN;
    protected E Long_NIN;

    @Test
    public void simple_Long() {
        compare(Long_EQ, Simple.Long.EQ);
        compare(Long_NE, Simple.Long.NE);
        compare(Long_LT, Simple.Long.LT);
        compare(Long_LTE, Simple.Long.LTE);
        compare(Long_GT, Simple.Long.GT);
        compare(Long_GTE, Simple.Long.GTE);
        compare(Long_EX, Simple.Long.EX);
        compare(Long_DNE, Simple.Long.DNE);
        compare(Long_IN, Simple.Long.IN);
        compare(Long_NIN, Simple.Long.NIN);
    }

    protected E Float_EQ;
    protected E Float_NE;
    protected E Float_LT;
    protected E Float_GT;
    protected E Float_LTE;
    protected E Float_GTE;
    protected E Float_EX;
    protected E Float_DNE;
    protected E Float_IN;
    protected E Float_NIN;

    @Test
    public void simple_Float() {
        compare(Float_EQ, Simple.Float.EQ);
        compare(Float_NE, Simple.Float.NE);
        compare(Float_LT, Simple.Float.LT);
        compare(Float_LTE, Simple.Float.LTE);
        compare(Float_GT, Simple.Float.GT);
        compare(Float_GTE, Simple.Float.GTE);
        compare(Float_EX, Simple.Float.EX);
        compare(Float_DNE, Simple.Float.DNE);
        compare(Float_IN, Simple.Float.IN);
        compare(Float_NIN, Simple.Float.NIN);
    }

    protected E Double_EQ;
    protected E Double_NE;
    protected E Double_LT;
    protected E Double_GT;
    protected E Double_LTE;
    protected E Double_GTE;
    protected E Double_EX;
    protected E Double_DNE;
    protected E Double_IN;
    protected E Double_NIN;

    @Test
    public void simple_Double() {
        compare(Double_EQ, Simple.Double.EQ);
        compare(Double_NE, Simple.Double.NE);
        compare(Double_LT, Simple.Double.LT);
        compare(Double_LTE, Simple.Double.LTE);
        compare(Double_GT, Simple.Double.GT);
        compare(Double_GTE, Simple.Double.GTE);
        compare(Double_EX, Simple.Double.EX);
        compare(Double_DNE, Simple.Double.DNE);
        compare(Double_IN, Simple.Double.IN);
        compare(Double_NIN, Simple.Double.NIN);
    }

    protected E BigDecimal_EQ;
    protected E BigDecimal_NE;
    protected E BigDecimal_LT;
    protected E BigDecimal_GT;
    protected E BigDecimal_LTE;
    protected E BigDecimal_GTE;
    protected E BigDecimal_EX;
    protected E BigDecimal_DNE;
    protected E BigDecimal_IN;
    protected E BigDecimal_NIN;

    @Test
    public void simple_BigDecimal() {
        compare(BigDecimal_EQ, Simple.BigDecimal.EQ);
        compare(BigDecimal_NE, Simple.BigDecimal.NE);
        compare(BigDecimal_LT, Simple.BigDecimal.LT);
        compare(BigDecimal_LTE, Simple.BigDecimal.LTE);
        compare(BigDecimal_GT, Simple.BigDecimal.GT);
        compare(BigDecimal_GTE, Simple.BigDecimal.GTE);
        compare(BigDecimal_EX, Simple.BigDecimal.EX);
        compare(BigDecimal_DNE, Simple.BigDecimal.DNE);
        compare(BigDecimal_IN, Simple.BigDecimal.IN);
        compare(BigDecimal_NIN, Simple.BigDecimal.NIN);
    }

    protected E Instant_EQ;
    protected E Instant_NE;
    protected E Instant_LT;
    protected E Instant_GT;
    protected E Instant_LTE;
    protected E Instant_GTE;
    protected E Instant_EX;
    protected E Instant_DNE;
    protected E Instant_BETWEEN;

    @Test
    public void simple_Instant() {
        compare(Instant_EQ, Simple.Instant.EQ);
        compare(Instant_NE, Simple.Instant.NE);
        compare(Instant_LT, Simple.Instant.LT);
        compare(Instant_LTE, Simple.Instant.LTE);
        compare(Instant_GT, Simple.Instant.GT);
        compare(Instant_GTE, Simple.Instant.GTE);
        compare(Instant_EX, Simple.Instant.EX);
        compare(Instant_DNE, Simple.Instant.DNE);
        compare(Instant_BETWEEN, Simple.Instant.BETWEEN);
    }

    protected E LocalDate_EQ;
    protected E LocalDate_NE;
    protected E LocalDate_LT;
    protected E LocalDate_GT;
    protected E LocalDate_LTE;
    protected E LocalDate_GTE;
    protected E LocalDate_EX;
    protected E LocalDate_DNE;
    protected E LocalDate_BETWEEN;

    @Test
    public void simple_LocalDate() {
        compare(LocalDate_EQ, Simple.LocalDate.EQ);
        compare(LocalDate_NE, Simple.LocalDate.NE);
        compare(LocalDate_LT, Simple.LocalDate.LT);
        compare(LocalDate_LTE, Simple.LocalDate.LTE);
        compare(LocalDate_GT, Simple.LocalDate.GT);
        compare(LocalDate_GTE, Simple.LocalDate.GTE);
        compare(LocalDate_EX, Simple.LocalDate.EX);
        compare(LocalDate_DNE, Simple.LocalDate.DNE);
        compare(LocalDate_BETWEEN, Simple.LocalDate.BETWEEN);
    }


    protected E INLINE_ANDING;

    @Test
    public void inline_Anding() {
        compare(INLINE_ANDING, Logical.INLINE_ANDING);
    }

    protected E INLINE_ORING;

    @Test
    public void inline_Oring() {
        compare(INLINE_ORING, Logical.INLINE_ORING);
    }

    protected E LIST_ANDING;

    @Test
    public void list_Anding() {
        compare(LIST_ANDING, Logical.LIST_ANDING);
    }

    protected E LIST_ORING;

    @Test
    public void list_Oring() {
        compare(LIST_ORING, Logical.LIST_ORING);
    }


    protected E LIST_ORING_OF_INLINE_ANDING;

    @Test
    public void listOringOfInlineAnding() {
        compare(LIST_ORING_OF_INLINE_ANDING, Logical.LIST_ORING_OF_INLINE_ANDING);
    }

    protected E LIST_ANDING_OF_INLINE_ORING;

    @Test
    public void listAndingOfInlineOring() {
        compare(LIST_ANDING_OF_INLINE_ORING, Logical.LIST_ANDING_OF_INLINE_ORING);
    }

    protected E LIST_ANDING_OR_LIST_ORING;

    @Test
    public void listAndingOrListOring() {
        compare(LIST_ANDING_OR_LIST_ORING, Logical.LIST_ANDING_OR_LIST_ORING);
    }

    protected E LIST_ORING_AND_LIST_ANDING;

    @Test
    public void listOringAndListAnding() {
        compare(LIST_ORING_AND_LIST_ANDING, Logical.LIST_ORING_ANDLIST_ANDING);
    }

    protected E CHAINED_ANDS;

    @Test
    public void chainedAnds() {
        compare(CHAINED_ANDS, Chained.CHAINED_ANDS);
    }

    protected E CHAINED_ORS;

    @Test
    public void chainedOrs() {
        compare(CHAINED_ORS, Chained.CHAINED_ORS);
    }

    protected E CHAINED_ANDS_AND_ORS;

    @Test
    public void chainedAndsAndOrs() {
        compare(CHAINED_ANDS_AND_ORS, Chained.CHAINED_ANDS_AND_ORS);
    }

    protected E CHAINED_ORS_AND_ANDS;

    @Test
    public void chainedOrsAndAnds() {
        compare(CHAINED_ORS_AND_ANDS, Chained.CHAINED_ORS_AND_ANDS);
    }

    protected E SUB_QUERY;

    @Test
    public void subquery() {
        compare(SUB_QUERY, Composed.SUB_QUERY);
    }

    protected E PATH_SUB_QUERY;
    protected E PATH_EQ;

    @Test
    public void pathquery() {
        compare(PATH_SUB_QUERY, Composed.PATH_SUB_QUERY);
        compare(PATH_EQ, Composed.PATH_EQ);
    }

    protected E NULL_EQUALITY;
    protected E NULL_INEQUALITY;

    @Test
    public void variedInputs() {
        compare(NULL_EQUALITY, VariedInputs.NULL_EQUALITY);
        compare(NULL_INEQUALITY, VariedInputs.NULL_INEQUALITY);
    }

    protected void compare(E expected, Condition<QueryModel> condition) {
        T visitor = getVisitor();
        compare(expected, condition.query(visitor));
    }
}
