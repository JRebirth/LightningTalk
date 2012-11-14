package org.jrebirth.presentation.lightningtalk.ui.slides.control;

import org.jrebirth.presentation.lightningtalk.ui.slides.base.AbstractBaseModel;

/**
 * The class <strong>ControlModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class ControlModel extends AbstractBaseModel<ControlModel, ControlView, ControlSlideStep> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected ControlSlideStep[] initializeSlideStep() {
        return ControlSlideStep.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSlideStep(final ControlSlideStep slideStep) {

        switch (slideStep) {
            case ColorPicker:
                getView().showColorPicker();
                break;
            case Pagination:
                getView().showPagination();
                break;

            default:
                getView().showSlideStep(slideStep);
        }
    }

}
