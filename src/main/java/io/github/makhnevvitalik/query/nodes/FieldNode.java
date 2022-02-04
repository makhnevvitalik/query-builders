/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package io.github.makhnevvitalik.query.nodes;

import java.util.List;
import java.util.Objects;

public interface FieldNode extends AbstractNode {

    @Override
    default String getId() {
        return Objects.toString(getFieldParts());
    }

    String getField(String delimiter);

    List<String> getFieldParts();

    FieldNode combine(FieldNode... nodes);
}
