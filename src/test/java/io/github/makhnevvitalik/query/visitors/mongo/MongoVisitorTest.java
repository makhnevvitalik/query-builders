/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.visitors.mongo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.makhnevvitalik.query.testsupport.MyEnum;
import io.github.makhnevvitalik.query.testsupport.QBuilderTestBase;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.opentest4j.AssertionFailedError;
import org.springframework.data.mongodb.core.query.Criteria;

class MongoVisitorTest extends QBuilderTestBase<MongoVisitor, Criteria, Criteria> {

    public MongoVisitorTest() {
        Enum_EQ = Criteria.where("myEnum").is(MyEnum.VALUE1);
        Enum_NE = Criteria.where("myEnum").ne(MyEnum.VALUE1);
        Enum_EX = Criteria.where("myEnum").exists(true);
        Enum_DNE = Criteria.where("myEnum").exists(false);
        Enum_IN = Criteria.where("myEnum").in(MyEnum.values());
        Enum_NIN = Criteria.where("myEnum").nin(MyEnum.values());

        String_EQ = Criteria.where("myString").is("abcdefg");
        String_NE = Criteria.where("myString").ne("abcdefg");
        String_LT = Criteria.where("myString").lt("abcdefg");
        String_GTE = Criteria.where("myString").gte("abcdefg");
        String_GT = Criteria.where("myString").gt("abcdefg");
        String_LTE = Criteria.where("myString").lte("abcdefg");
        String_EX = Criteria.where("myString").exists(true);
        String_DNE = Criteria.where("myString").exists(false);
        String_IN = Criteria.where("myString").in("a", "b", "c");
        String_NIN = Criteria.where("myString").nin("d", "e", "f");
        String_RE = Criteria.where("myString").regex("(abc|def)");

        Boolean_TRUE = Criteria.where("myBoolean").is(true);
        Boolean_FALSE = Criteria.where("myBoolean").is(false);
        Boolean_EX = Criteria.where("myBoolean").exists(true);
        Boolean_DNE = Criteria.where("myBoolean").exists(false);

        Short_EQ = Criteria.where("myShort").is((short) 100);
        Short_NE = Criteria.where("myShort").ne((short) 100);
        Short_LT = Criteria.where("myShort").lt((short) 100);
        Short_GT = Criteria.where("myShort").gt((short) 100);
        Short_LTE = Criteria.where("myShort").lte((short) 100);
        Short_GTE = Criteria.where("myShort").gte((short) 100);
        Short_EX = Criteria.where("myShort").exists(true);
        Short_DNE = Criteria.where("myShort").exists(false);
        Short_IN = Criteria.where("myShort").in((short) 98, (short) 99, (short) 100);
        Short_NIN = Criteria.where("myShort").nin((short) 101, (short) 102, (short) 103);

        Integer_EQ = Criteria.where("myInteger").is(100);
        Integer_NE = Criteria.where("myInteger").ne(100);
        Integer_LT = Criteria.where("myInteger").lt(100);
        Integer_GT = Criteria.where("myInteger").gt(100);
        Integer_LTE = Criteria.where("myInteger").lte(100);
        Integer_GTE = Criteria.where("myInteger").gte(100);
        Integer_EX = Criteria.where("myInteger").exists(true);
        Integer_DNE = Criteria.where("myInteger").exists(false);
        Integer_IN = Criteria.where("myInteger").in(98, 99, 100);
        Integer_NIN = Criteria.where("myInteger").nin(101, 102, 103);

        Long_EQ = Criteria.where("myLong").is(100L);
        Long_NE = Criteria.where("myLong").ne(100L);
        Long_LT = Criteria.where("myLong").lt(100L);
        Long_GT = Criteria.where("myLong").gt(100L);
        Long_LTE = Criteria.where("myLong").lte(100L);
        Long_GTE = Criteria.where("myLong").gte(100L);
        Long_EX = Criteria.where("myLong").exists(true);
        Long_DNE = Criteria.where("myLong").exists(false);
        Long_IN = Criteria.where("myLong").in(98L, 99L, 100L);
        Long_NIN = Criteria.where("myLong").nin(101L, 102L, 103L);

        Float_EQ = Criteria.where("myFloat").is(100.0f);
        Float_NE = Criteria.where("myFloat").ne(100.0f);
        Float_LT = Criteria.where("myFloat").lt(100.0f);
        Float_GT = Criteria.where("myFloat").gt(100.0f);
        Float_LTE = Criteria.where("myFloat").lte(100.0f);
        Float_GTE = Criteria.where("myFloat").gte(100.0f);
        Float_EX = Criteria.where("myFloat").exists(true);
        Float_DNE = Criteria.where("myFloat").exists(false);
        Float_IN = Criteria.where("myFloat").in(98.0f, 99.0f, 100.0f);
        Float_NIN = Criteria.where("myFloat").nin(101.0f, 102.0f, 103.0f);

        Double_EQ = Criteria.where("myDouble").is(100.0);
        Double_NE = Criteria.where("myDouble").ne(100.0);
        Double_LT = Criteria.where("myDouble").lt(100.0);
        Double_GT = Criteria.where("myDouble").gt(100.0);
        Double_LTE = Criteria.where("myDouble").lte(100.0);
        Double_GTE = Criteria.where("myDouble").gte(100.0);
        Double_EX = Criteria.where("myDouble").exists(true);
        Double_DNE = Criteria.where("myDouble").exists(false);
        Double_IN = Criteria.where("myDouble").in(98.0, 99.0, 100.0);
        Double_NIN = Criteria.where("myDouble").nin(101.0, 102.0, 103.0);

        BigDecimal_EQ = Criteria.where("myBigDecimal").is(new java.math.BigDecimal("0.00000001"));
        BigDecimal_NE = Criteria.where("myBigDecimal").ne(BigDecimal.TEN);
        BigDecimal_LT = Criteria.where("myBigDecimal").lt(BigDecimal.TEN);
        BigDecimal_GT = Criteria.where("myBigDecimal").gt(BigDecimal.TEN);
        BigDecimal_LTE = Criteria.where("myBigDecimal").lte(BigDecimal.TEN);
        BigDecimal_GTE = Criteria.where("myBigDecimal").gte(BigDecimal.TEN);
        BigDecimal_EX = Criteria.where("myBigDecimal").exists(true);
        BigDecimal_DNE = Criteria.where("myBigDecimal").exists(false);
        BigDecimal_IN = Criteria.where("myBigDecimal")
            .in(BigDecimal.valueOf(8), BigDecimal.valueOf(9), BigDecimal.valueOf(10));
        BigDecimal_NIN = Criteria.where("myBigDecimal")
            .nin(BigDecimal.valueOf(11), BigDecimal.valueOf(12), BigDecimal.valueOf(13));

        Instant instant = Instant.parse("1970-01-01T00:00:00.000Z");
        Instant_EQ = Criteria.where("myInstant").is(instant);
        Instant_NE = Criteria.where("myInstant").ne(instant);
        Instant_LT = Criteria.where("myInstant").lt(instant.plus(1, ChronoUnit.DAYS));
        Instant_LTE = Criteria.where("myInstant").lte(instant.plus(1, ChronoUnit.DAYS));
        Instant_GT = Criteria.where("myInstant").gt(instant);
        Instant_GTE = Criteria.where("myInstant").gte(instant);
        Instant_EX = Criteria.where("myInstant").exists(true);
        Instant_DNE = Criteria.where("myInstant").exists(false);
        Instant_BETWEEN = new Criteria().andOperator(
            Criteria.where("myInstant").gte(instant),
            Criteria.where("myInstant").lte(instant.plus(1, ChronoUnit.DAYS))
        );

        LocalDate localDate = LocalDate.parse("1970-01-01");
        LocalDate_EQ = Criteria.where("myLocalDate").is(localDate);
        LocalDate_NE = Criteria.where("myLocalDate").ne(localDate);
        LocalDate_LT = Criteria.where("myLocalDate").lt(localDate.plus(1, ChronoUnit.DAYS));
        LocalDate_LTE = Criteria.where("myLocalDate").lte(localDate.plus(1, ChronoUnit.DAYS));
        LocalDate_GT = Criteria.where("myLocalDate").gt(localDate);
        LocalDate_GTE = Criteria.where("myLocalDate").gte(localDate);
        LocalDate_EX = Criteria.where("myLocalDate").exists(true);
        LocalDate_DNE = Criteria.where("myLocalDate").exists(false);
        LocalDate_BETWEEN = new Criteria().andOperator(
            Criteria.where("myLocalDate").gte(localDate),
            Criteria.where("myLocalDate").lte(localDate.plus(1, ChronoUnit.DAYS))
        );

        INLINE_ANDING = new Criteria().andOperator(
            Criteria.where("myString").is("Thing"),
            Criteria.where("myLong").exists(false)
        );

        INLINE_ORING = new Criteria().orOperator(
            Criteria.where("myString").is("Thing"),
            Criteria.where("myLong").exists(false)
        );

        LIST_ANDING = new Criteria().andOperator(
            Criteria.where("myString").is("Thing"),
            Criteria.where("myLong").exists(false)
        );

        LIST_ORING = new Criteria().orOperator(
            Criteria.where("myString").is("Thing"),
            Criteria.where("myLong").exists(false)
        );

        LIST_ORING_OF_INLINE_ANDING = new Criteria().orOperator(
            new Criteria().andOperator(
                Criteria.where("myString").is("Thing"),
                Criteria.where("myLong").exists(false)
            ),
            new Criteria().andOperator(
                Criteria.where("myString").ne("Cats"),
                Criteria.where("myLong").gt(30L)
            )
        );

        LIST_ANDING_OF_INLINE_ORING = new Criteria().andOperator(
            new Criteria().orOperator(
                Criteria.where("myString").is("Thing"),
                Criteria.where("myLong").exists(false)
            ),
            new Criteria().orOperator(
                Criteria.where("myString").ne("Cats"),
                Criteria.where("myLong").gt(30L)
            )
        );

        LIST_ANDING_OR_LIST_ORING = new Criteria().orOperator(
            new Criteria().andOperator(
                new Criteria().orOperator(
                    Criteria.where("myString").is("Thing"),
                    Criteria.where("myLong").exists(false)
                ),
                new Criteria().orOperator(
                    Criteria.where("myString").ne("Cats"),
                    Criteria.where("myLong").gt(30L)
                )
            ),
            new Criteria().orOperator(
                new Criteria().andOperator(
                    Criteria.where("myString").is("Thing"),
                    Criteria.where("myLong").exists(false)
                ),
                new Criteria().andOperator(
                    Criteria.where("myString").ne("Cats"),
                    Criteria.where("myLong").gt(30L)
                )
            )
        );

        LIST_ORING_AND_LIST_ANDING = new Criteria().andOperator(
            new Criteria().orOperator(
                new Criteria().andOperator(
                    Criteria.where("myString").is("Thing"),
                    Criteria.where("myLong").exists(false)
                ),
                new Criteria().andOperator(
                    Criteria.where("myString").ne("Cats"),
                    Criteria.where("myLong").gt(30L)
                )
            ),
            new Criteria().andOperator(
                new Criteria().orOperator(
                    Criteria.where("myString").is("Thing"),
                    Criteria.where("myLong").exists(false)
                ),
                new Criteria().orOperator(
                    Criteria.where("myString").ne("Cats"),
                    Criteria.where("myLong").gt(30L)
                )
            )
        );

        CHAINED_ORS = new Criteria().orOperator(
            Criteria.where("myString").is("Thing"),
            Criteria.where("myInteger").gt(0),
            Criteria.where("myInteger").lt(5),
            Criteria.where("myLong").in(0L, 1L, 2L),
            Criteria.where("myDouble").lte(2.9),
            Criteria.where("myBoolean").is(false),
            Criteria.where("myInstant").exists(false)
        );

        CHAINED_ANDS_AND_ORS = new Criteria().orOperator(
            new Criteria().andOperator(
                new Criteria().orOperator(
                    new Criteria().andOperator(
                        Criteria.where("myString").is("Thing"),
                        Criteria.where("myInteger").gt(0)
                    ),
                    Criteria.where("myInteger").lt(5),
                    Criteria.where("myLong").in(0L, 1L, 2L)
                ),
                Criteria.where("myDouble").lte(2.9),
                Criteria.where("myBoolean").is(false)
            ),
            Criteria.where("myInstant").exists(false)
        );

        CHAINED_ANDS = new Criteria().andOperator(
            Criteria.where("myString").is("Thing"),
            Criteria.where("myInteger").gt(0),
            Criteria.where("myInteger").lt(5),
            Criteria.where("myLong").in(0L, 1L, 2L),
            Criteria.where("myDouble").lte(2.9),
            Criteria.where("myBoolean").is(false),
            Criteria.where("myInstant").exists(false)
        );

        CHAINED_ORS_AND_ANDS = new Criteria().andOperator(
            new Criteria().orOperator(
                new Criteria().andOperator(
                    new Criteria().orOperator(
                        Criteria.where("myString").is("Thing"),
                        Criteria.where("myInteger").gt(0)
                    ),
                    Criteria.where("myInteger").lt(5),
                    Criteria.where("myLong").in(0L, 1L, 2L)
                ),
                Criteria.where("myDouble").lte(2.9),
                Criteria.where("myBoolean").is(false)
            ),
            Criteria.where("myInstant").exists(false)
        );

        SUB_QUERY = new Criteria().andOperator(
            Criteria.where("mySubList").elemMatch(new Criteria().andOperator(
                Criteria.where("myString").is("Thing"),
                Criteria.where("myLong").exists(false)
            )),
            Criteria.where("myBoolean").is(true)
        );

        PATH_SUB_QUERY = new Criteria().andOperator(
            Criteria.where("mySubModel.myString").is("Thing"),
            Criteria.where("mySubModel.myLong").exists(false)
        );

        PATH_EQ = Criteria.where("mySubModel.myField.myString").is("abcdefg");

        NULL_EQUALITY = Criteria.where("myString").exists(false);
        NULL_INEQUALITY = Criteria.where("myString").exists(true);
    }

    @Override
    protected MongoVisitor getVisitor() {
        return new MongoVisitor();
    }

    @Override
    protected void compare(Criteria expected, Criteria converted) {
        try {
            assertEquals(expected, converted);
        } catch (AssertionFailedError e) {
            String expectedStr = String.valueOf(expected.getCriteriaObject());
            String actualStr = String.valueOf(converted.getCriteriaObject());
            throw new AssertionFailedError("expected:\n" + expectedStr + " but was:\n" + actualStr,
                expectedStr, actualStr);
        }
    }
}