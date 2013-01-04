/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.presentation.lightningtalk.ui.slides.fireworks;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.base.AbstractSlideView;

/**
 * The class <strong>PlaceView</strong>.
 * 
 * The custom introduction slide.
 * 
 * @author Sébastien Bordes
 */
public final class FireworksView extends AbstractSlideView<FireworksModel, Pane, FireworksController> {

    /** The pane. */
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
        doReload();
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
        this.pane.start();
    }
}
