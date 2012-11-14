package org.jrebirth.presentation.lightningtalk.ui.slides.place;

import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.Pane;

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
public final class PlaceView extends AbstractSlideView<PlaceModel, Pane, PlaceController> {

    /**
     * Default Constructor.
     * 
     * @param model the IntroModel view model
     * 
     * @throws CoreException if build fails
     */
    public PlaceView(final PlaceModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {

        final ImageView toulouse = ImageViewBuilder.create()
                .image(loadImage("images/intro/ToulouseJug.jpg"))
                // .fitHeight(Double.MAX_VALUE)
                // .fitWidth(Double.MAX_VALUE)
                .build();

        getRootNode().getChildren().add(toulouse);

        getRootNode().getStyleClass().add(getModel().getSlide().getStyle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doStart() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doReload() {
    }
}
