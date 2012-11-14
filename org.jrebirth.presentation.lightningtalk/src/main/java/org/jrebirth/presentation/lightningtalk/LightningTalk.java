package org.jrebirth.presentation.lightningtalk;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jrebirth.core.application.AbstractApplication;
import org.jrebirth.core.resource.font.FontEnum;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.presentation.ui.stack.StackModel;

/**
 * The class <strong>LightningTalk</strong>.
 * 
 * Application as support for live javafx 2.2 lightning talk.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class LightningTalk extends AbstractApplication<StackPane> {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(LightningTalk.class, args);
    }

    /**
     * {@inheritDoc}
     */
    // @Override
    public Class<? extends Model> getFirstModelClass() {
        return StackModel.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationTitle() {
        return "JavaFX 2.2 - What's up ?";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeStage(final Stage stage) {
        stage.setFullScreen(false);
        stage.setWidth(1040);
        stage.setHeight(800);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeScene(final Scene scene) {

        scene.getStylesheets().add("style/template.css");

        // Manage F11 button to switch full screen
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            // @Override
            public void handle(final KeyEvent event) {
                if (event.isControlDown()) {
                    if (event.getCode() == KeyCode.ADD || event.getCode() == KeyCode.PLUS) {
                        getScene().getRoot().setScaleX(getScene().getRoot().getScaleX() + 0.05);
                        getScene().getRoot().setScaleY(getScene().getRoot().getScaleY() + 0.05);
                        event.consume();
                    } else if (event.getCode() == KeyCode.SUBTRACT || event.getCode() == KeyCode.MINUS) {
                        getScene().getRoot().setScaleX(getScene().getRoot().getScaleX() - 0.05);
                        getScene().getRoot().setScaleY(getScene().getRoot().getScaleY() - 0.05);
                        event.consume();
                    } else if (event.getCode() == KeyCode.DIGIT0 || event.getCode() == KeyCode.NUMPAD0) {
                        getScene().getRoot().setScaleX(1.0);
                        getScene().getRoot().setScaleY(1.0);
                        event.consume();
                    }
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<FontEnum> getFontToPreload() {
        return Arrays.asList(new FontEnum[] {
                LtFonts.SLIDE_ITEM,
                LtFonts.TYPEWRITER,
                LtFonts.DOG_SPLASH,
                LtFonts.WAZAA_SPLASH
        });
    }

    /**
     * {@inheritDoc}
     */
    // @Override
    public List<Wave> getPreBootWaveList() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    // @Override
    public List<Wave> getPostBootWaveList() {
        return Collections.emptyList();
    }

}
