/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.properties.virtual;

import io.github.makhnevvitalik.query.conditions.Condition;
import io.github.makhnevvitalik.query.conditions.Partial;

public interface WithProperty<T extends Partial<T>, S extends Partial<S>, P extends WithProperty<T, S, P>>
    extends ExistentialProperty<T> {

    Condition<T> with(Condition<S> query);

    P with(String path);
}
