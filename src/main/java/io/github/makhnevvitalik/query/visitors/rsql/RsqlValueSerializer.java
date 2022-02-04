/*
 *  Copyright (c) 2016 Paul Rutledge <paul.v.rutledge@gmail.com>
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.visitors.rsql;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RsqlValueSerializer implements ValueSerializer {

    private static final CharSequence DOUBLE_QUOTE = "\"";
    private static final CharSequence SINGLE_QUOTE = "'";

    @Builder.Default
    private final DateTimeFormatter localDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    @Builder.Default
    private final DateTimeFormatter instantFormatter = DateTimeFormatter.ISO_INSTANT;

    @Override
    public String apply(Object value) {
        return quote(valueToString(value));
    }

    protected String valueToString(Object value) {
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).toPlainString();
        } else if (value instanceof LocalDate) {
            return localDateFormatter.format((LocalDate) value);
        } else if (value instanceof Instant) {
            return instantFormatter.format((Instant) value);
        } else {
            return String.valueOf(value);
        }
    }

    protected String quote(String value) {
        if (value.contains("\\")) {
            value = value.replace("\\", "\\\\");
        }

        boolean containsDoubleQuotes = value.contains("\"");
        boolean containsSingleQuotes = value.contains("'");

        if (!containsDoubleQuotes) {
            return DOUBLE_QUOTE + value + DOUBLE_QUOTE;
        } else if (!containsSingleQuotes) {
            return SINGLE_QUOTE + value + SINGLE_QUOTE;
        } else {
            value = value.replace("\"", "\\\"");
            return DOUBLE_QUOTE + value + DOUBLE_QUOTE;
        }
    }
}
