package org.jrebirth.presentation.lightningtalk;

import org.jrebirth.core.resource.font.FontName;

/**
 * The class <strong>LtFontsLoader</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum LtFontsLoader implements FontName {

    /** . */
    DINk,

    /** . */
    Report_1942,

    Essays1743,

    Harabara,

    MIZIKE,

    whiterabbit,

    Turtles,

    /** . */
    Neuton_Cursive;

    /**
     * {@inheritDoc}
     */
    @Override
    public String get() {
        return name();
    }

}
