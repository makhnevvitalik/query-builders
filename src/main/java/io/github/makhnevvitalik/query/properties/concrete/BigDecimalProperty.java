/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.concrete;

import io.github.makhnevvitalik.query.conditions.Partial;
import io.github.makhnevvitalik.query.properties.virtual.NumberProperty;
import java.math.BigDecimal;

/**
 * A property view for fields with {@link BigDecimal} values.
 *
 * @param <T> The type of the final builder.
 */
public interface BigDecimalProperty<T extends Partial<T>> extends NumberProperty<T, BigDecimal> {}
