/*
 *  Copyright (c) 2022 Vitaly Makhnev <makhnevvitalik@gmail.com>
 *
 *  This software may be modified and distributed under the terms
 *  of the MIT license. See the LICENSE file for details.
 */

package com.github.makhnevvitalik.query.builders;

import com.github.makhnevvitalik.query.nodes.AbstractNode;

class GeneralQueryBuilderImpl extends QBuilder<GeneralQueryBuilder> implements GeneralQueryBuilder {

    GeneralQueryBuilderImpl() {
    }

    private GeneralQueryBuilderImpl(AbstractNode node) {
        super(node);
    }

    @Override
    public GeneralQueryBuilder withNode(AbstractNode node) {
        return new GeneralQueryBuilderImpl(node);
    }
}
