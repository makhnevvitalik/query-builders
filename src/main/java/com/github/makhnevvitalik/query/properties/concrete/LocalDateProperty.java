/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.properties.concrete;

import com.github.makhnevvitalik.query.conditions.Partial;
import com.github.makhnevvitalik.query.properties.virtual.TemporalProperty;
import java.time.LocalDate;

/**
 * A property view for fields with {@link LocalDate} values.
 *
 * @param <T> The type of the final builder.
 */
public interface LocalDateProperty<T extends Partial<T>> extends TemporalProperty<T, LocalDate> {}
