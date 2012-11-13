package org.jrebirth.presentation.lightningtalk.ui.slides.basic;

import org.jrebirth.presentation.ui.base.AbstractSlideModel;
import org.jrebirth.presentation.ui.base.SlideStep;

/**
 * The class <strong>BasicModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public class BasicModel extends AbstractSlideModel<BasicModel, BasicView, SlideStep> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected SlideStep[] initializeSlideStep() {
        return new SlideStep[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSlideStep(final SlideStep slideStep) {

    }

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
