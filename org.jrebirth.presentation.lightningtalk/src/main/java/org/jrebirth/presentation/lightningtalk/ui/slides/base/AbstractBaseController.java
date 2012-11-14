package org.jrebirth.presentation.lightningtalk.ui.slides.base;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.base.AbstractSlideController;

/**
 * The class <strong>AbstractTemplateController</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseController<M extends AbstractBaseModel<M, V, ?>, V extends AbstractBaseView<M, ?, ? extends AbstractBaseController<M, V>>>
        extends AbstractSlideController<M, V> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public AbstractBaseController(final V view) throws CoreException {
        super(view);
    }

}
