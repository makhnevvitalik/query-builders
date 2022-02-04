/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.properties;

import com.github.makhnevvitalik.query.conditions.Condition;
import com.github.makhnevvitalik.query.conditions.ConditionUtils;
import com.github.makhnevvitalik.query.conditions.Partial;
import com.github.makhnevvitalik.query.nodes.AbstractNode;

public abstract class AbstractProperty<T extends Partial<T>> {

    protected final T partial;
    protected final AbstractNode field;

    protected AbstractProperty(T partial, AbstractNode field) {
        this.partial = partial;
        this.field = field;
    }

    protected Condition<T> condition(AbstractNode node) {
        return ConditionUtils.condition(partial, node);
    }
}
