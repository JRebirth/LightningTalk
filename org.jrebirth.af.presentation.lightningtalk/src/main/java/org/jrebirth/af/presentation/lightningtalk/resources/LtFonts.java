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
package org.jrebirth.af.presentation.lightningtalk.resources;

import javafx.scene.text.Font;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.api.resource.font.FontParams;
import org.jrebirth.af.core.resource.ResourceBuilders;
import org.jrebirth.af.core.resource.font.FontBuilder;
import org.jrebirth.af.core.resource.font.RealFont;

// TODO: Auto-generated Javadoc
/**
 * The class <strong>LtFonts</strong>.
 *
 * @author Sébastien Bordes
 *
 */
public enum LtFonts implements FontItem {

    /** The splash font. */
    PAGE(new RealFont(LtFontsName.DINk, 24)),

    /** The typewriter font. */
    TYPEWRITER(new RealFont(LtFontsName.MIZIKE, 72)),

    /** The slide title font. */
    SLIDE_TITLE(new RealFont(LtFontsName.Harabara, 45)),

    /** The slide item. */
    SLIDE_ITEM(new RealFont(LtFontsName.Neuton_Cursive, 10)),

    /** The dog splash. */
    DOG_SPLASH(new RealFont(LtFontsName.whiterabbit, 20)),

    /** The wazaa splash. */
    WAZAA_SPLASH(new RealFont(LtFontsName.Turtles, 20)),

    /** The prez title. */
    PREZ_TITLE(new RealFont(LtFontsName.Harabara, 26));

    /**
     * Default Constructor.
     *
     * @param fontParams the font size
     */
    LtFonts(final FontParams fontParams) {
        builder().storeParams(this, fontParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontBuilder builder() {
        return ResourceBuilders.FONT_BUILDER;
    }

}
