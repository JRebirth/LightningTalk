/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.presentation.lightningtalk;

import javafx.scene.paint.Color;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.color.ColorBuilder;
import org.jrebirth.core.resource.color.ColorEnum;
import org.jrebirth.core.resource.color.ColorParams;
import org.jrebirth.core.resource.color.WebColor;

// TODO: Auto-generated Javadoc
/**
 * The class <strong>LtColors</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum LtColors implements ColorEnum {

    /** The slide title. */
    SLIDE_TITLE(new WebColor("0088D3", 1.0));

    /**
     * Private Constructor.
     * 
     * @param colorParams the primitive values for the color
     */
    private LtColors(final ColorParams colorParams) {
        factory().storeParams(this, colorParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color get() {
        return factory().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorBuilder factory() {
        return ResourceBuilders.COLOR_BUILDER;
    }
}
