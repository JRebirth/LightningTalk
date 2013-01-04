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

import javafx.scene.text.Font;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.font.FontBuilder;
import org.jrebirth.core.resource.font.FontEnum;
import org.jrebirth.core.resource.font.FontParams;
import org.jrebirth.core.resource.font.RealFont;

// TODO: Auto-generated Javadoc
/**
 * The class <strong>LtFonts</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum LtFonts implements FontEnum {

    /** The splash font. */
    PAGE(new RealFont(LtFontsLoader.DINk, 24)),

    /** The typewriter font. */
    TYPEWRITER(new RealFont(LtFontsLoader.MIZIKE, 72)),

    /** The slide title font. */
    SLIDE_TITLE(new RealFont(LtFontsLoader.Harabara, 45)),

    /** The slide item. */
    SLIDE_ITEM(new RealFont(LtFontsLoader.Neuton_Cursive, 10)),

    /** The dog splash. */
    DOG_SPLASH(new RealFont(LtFontsLoader.whiterabbit, 20)),

    /** The wazaa splash. */
    WAZAA_SPLASH(new RealFont(LtFontsLoader.Turtles, 20)),

    /** The prez title. */
    PREZ_TITLE(new RealFont(LtFontsLoader.Harabara, 26));

    /**
     * Default Constructor.
     * 
     * @param fontParams the font size
     */
    LtFonts(final FontParams fontParams) {
        factory().storeParams(this, fontParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font get() {
        return factory().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontBuilder factory() {
        return ResourceBuilders.FONT_BUILDER;
    }

}
