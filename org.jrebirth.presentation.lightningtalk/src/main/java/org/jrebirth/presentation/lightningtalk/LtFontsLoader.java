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
