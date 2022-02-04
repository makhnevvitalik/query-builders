/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.nodes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public interface ValueNode extends AbstractNode {

    @Override
    default String getId() {
        return Objects.toString(getValue());
    }

    Object getValue();

    default Collection<Object> getValueAsCollection() {
        Object value = getValue();
        if (value == null) {
            return Collections.emptyList();
        } else if (value instanceof Collection) {
            return (Collection<Object>) value;
        } else if (value.getClass().isArray()) {
            return Arrays.asList((Object[]) value);
        } else {
            return Collections.singletonList(value);
        }
    }
}
