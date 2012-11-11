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
    Neuton_Cursive;

    /**
     * {@inheritDoc}
     */
    @Override
    public String get() {
        return name();
    }

}
