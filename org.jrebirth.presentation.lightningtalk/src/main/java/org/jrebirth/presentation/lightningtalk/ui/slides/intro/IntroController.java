package org.jrebirth.presentation.lightningtalk.ui.slides.intro;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.base.AbstractSlideController;

/**
 * The class <strong>IntroController</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class IntroController extends AbstractSlideController<IntroModel, IntroView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public IntroController(final IntroView view) throws CoreException {
        super(view);
    }

}
