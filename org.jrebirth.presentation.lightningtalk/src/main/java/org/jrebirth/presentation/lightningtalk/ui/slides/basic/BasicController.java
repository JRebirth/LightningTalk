package org.jrebirth.presentation.lightningtalk.ui.slides.basic;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.base.AbstractSlideController;

/**
 * The class <strong>AbstractTemplateController</strong>.
 * 
 * @author Sébastien Bordes
 */
public class BasicController extends AbstractSlideController<BasicModel, BasicView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public BasicController(final BasicView view) throws CoreException {
        super(view);
    }

}
