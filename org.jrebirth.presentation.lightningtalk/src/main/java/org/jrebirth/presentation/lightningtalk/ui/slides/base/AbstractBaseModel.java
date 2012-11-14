package org.jrebirth.presentation.lightningtalk.ui.slides.base;

import org.jrebirth.presentation.ui.base.AbstractSlideModel;
import org.jrebirth.presentation.ui.base.SlideStep;

/**
 * The class <strong>BasicModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseModel<M extends AbstractBaseModel<M, V, S>, V extends AbstractBaseView<?, ?, ?>, S extends SlideStep> extends AbstractSlideModel<M, V, S> {

    /**
     * Return the title string from the slide object.
     * 
     * @return the slide title
     */
    protected String getTitle() {
        return getSlide().getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getSlideNumber() + "/" + getStepPosition() + " " + getTitle() + this.getClass().getSimpleName();
    }

}
