package org.jrebirth.presentation.lightningtalk;

import javafx.scene.paint.Color;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.color.ColorBuilder;
import org.jrebirth.core.resource.color.ColorEnum;
import org.jrebirth.core.resource.color.ColorParams;
import org.jrebirth.core.resource.color.WebColor;

/**
 * The class <strong>LtColors</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum LtColors implements ColorEnum {

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
        return (ColorBuilder) ResourceBuilders.COLOR_BUILDER.use();
    }
}
