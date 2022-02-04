/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.virtual;

import io.github.makhnevvitalik.query.conditions.Partial;

public interface BaseProperty<T extends Partial<T>, S>
    extends EquitableProperty<T, S>, ListableProperty<T, S>, ExistentialProperty<T> {
}
