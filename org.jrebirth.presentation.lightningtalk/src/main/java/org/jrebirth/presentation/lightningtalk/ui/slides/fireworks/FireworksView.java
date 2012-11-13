package org.jrebirth.presentation.lightningtalk.ui.slides.fireworks;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.base.AbstractSlideView;

/**
 * 
 * The class <strong>PlaceView</strong>.
 * 
 * The custom introduction slide.
 * 
 * @author Sébastien Bordes
 */
public final class FireworksView extends AbstractSlideView<FireworksModel, Pane, FireworksController> {

    private FireworksPane pane;

    /**
     * Default Constructor.
     * 
     * @param model the IntroModel view model
     * 
     * @throws CoreException if build fails
     */
    public FireworksView(final FireworksModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {
        this.pane = new FireworksPane(loadImage("images/canvas/MontSaintMichel.jpg"), loadImage("images/canvas/MontSaintMichel.png"));
        StackPane.setAlignment(this.pane, Pos.CENTER);
        getRootNode().getChildren().add(this.pane);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doStart() {
        // this.pane.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doHide() {
        this.pane.stop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doReload() {
        // Not used yet !!!!
        this.pane.start();

    }
}
