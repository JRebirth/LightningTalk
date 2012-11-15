package org.jrebirth.presentation.lightningtalk.ui.slides.control;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.lightningtalk.ui.slides.base.AbstractBaseController;

/**
 * The class <strong>ControlController</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class ControlController extends AbstractBaseController<ControlModel, ControlView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public ControlController(final ControlView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {
        super.customInitializeEventHandlers();

        getRootNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                System.out.println("ss");
                // event.consume();

            }
        });
    }

}
