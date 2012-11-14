package org.jrebirth.presentation.lightningtalk;

import javafx.scene.text.Font;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.font.FontBuilder;
import org.jrebirth.core.resource.font.FontEnum;
import org.jrebirth.core.resource.font.FontParams;
import org.jrebirth.core.resource.font.RealFont;

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

    SLIDE_ITEM(new RealFont(LtFontsLoader.Neuton_Cursive, 10)),

    DOG_SPLASH(new RealFont(LtFontsLoader.whiterabbit, 20)),

    WAZAA_SPLASH(new RealFont(LtFontsLoader.Turtles, 20)),

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
        return (FontBuilder) ResourceBuilders.FONT_BUILDER.use();
    }

}
