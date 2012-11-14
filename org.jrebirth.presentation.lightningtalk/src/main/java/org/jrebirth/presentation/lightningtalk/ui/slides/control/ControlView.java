package org.jrebirth.presentation.lightningtalk.ui.slides.control;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.PaginationBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.lightningtalk.ui.slides.base.AbstractBaseView;

/**
 * 
 * The class <strong>ControlView</strong>.
 * 
 * The custom controls demo slide.
 * 
 * @author Sébastien Bordes
 */
public final class ControlView extends AbstractBaseView<ControlModel, AnchorPane, ControlController> {

    private Pagination pagination;
    private final Image[] images = new Image[7];

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public ControlView(final ControlModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node getContentPanel() {
        getSubTitle().setText("");
        return buildDefaultContent(getModel().getDefaultContent());
    }

    /**
     * TODO To complete.
     */
    public void showColorPicker() {
        getSubTitle().setText("Color Picker");

        final ColorPicker colorPicker = new ColorPicker(Color.GRAY);
        // final ToolBar standardToolbar = ToolBarBuilder.create()
        // .items(colorPicker)
        // .build();

        final Text coloredText = new Text("Colors");
        final Font font = new Font(53);
        coloredText.setFont(font);
        final Button coloredButton = new Button("Colored Control");
        final Color c = colorPicker.getValue();
        coloredText.setFill(c);
        coloredButton.setStyle(createRGBString(c));

        // colorPicker.setOnMousePressed(new EventHandler<MouseEvent>() {
        //
        // @Override
        // public void handle(final MouseEvent event) {
        // final Color newColor = colorPicker.getValue();
        // coloredText.setFill(newColor);
        // coloredButton.setStyle(createRGBString(newColor));
        // event.consume();
        // }
        //
        // });

        colorPicker.setOnAction(new EventHandler() {

            @Override
            public void handle(final Event arg0) {
                final Color newColor = colorPicker.getValue();
                coloredText.setFill(newColor);
                coloredButton.setStyle(createRGBString(newColor));

            }
        });

        final VBox coloredObjectsVBox = VBoxBuilder.create()
                .alignment(Pos.CENTER)
                .spacing(20)
                .children(coloredText, coloredButton)
                .build();

        final VBox outerVBox = VBoxBuilder.create()
                .alignment(Pos.CENTER)
                .spacing(150)
                .padding(new Insets(0, 0, 120, 0))
                .children(colorPicker, coloredObjectsVBox)
                .build();

        // ignoreClick(colorPicker);

        showCustomSlideStep(outerVBox);

        BorderPane.setAlignment(outerVBox, Pos.TOP_CENTER);
        BorderPane.setMargin(outerVBox, new Insets(100));
    }

    /**
     * TODO To complete.
     * 
     * @param node
     */
    private void ignoreClick(final Node node) {
        node.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                event.consume();
            }

        });
    }

    private String createRGBString(final Color c) {
        return "-fx-base: rgb(" + c.getRed() * 255 + "," + c.getGreen() * 255 + "," + c.getBlue() * 255 + ");";
    }

    /**
     * TODO To complete.
     */
    public void showPagination() {
        getSubTitle().setText("Pagination");

        final VBox outerBox = new VBox();
        outerBox.setAlignment(Pos.CENTER);
        // Images for our pages
        for (int i = 0; i < 7; i++) {
            this.images[i] = loadImage("images/pictures/picture " + (i + 1) + ".png");
        }

        this.pagination = PaginationBuilder.create()
                .pageCount(7)
                .pageFactory(new Callback<Integer, Node>() {

                    @Override
                    public final Node call(final Integer pageIndex) {
                        return createPicturePage(pageIndex);
                    }
                }
                ).build();
        // Style can be numeric page indicators or bullet indicators
        final Button styleButton = ButtonBuilder.create()
                .text("Toggle pagination style")
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(final ActionEvent me) {
                        if (!ControlView.this.pagination.getStyleClass().contains(Pagination.STYLE_CLASS_BULLET)) {
                            ControlView.this.pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
                        } else {
                            ControlView.this.pagination.getStyleClass().remove(Pagination.STYLE_CLASS_BULLET);
                        }
                    }
                }).build();

        outerBox.getChildren().addAll(this.pagination, styleButton);

        // ignoreClick(this.pagination);

        showCustomSlideStep(outerBox);

        BorderPane.setAlignment(outerBox, Pos.TOP_CENTER);
        BorderPane.setMargin(outerBox, new Insets(100));

    }

    // Creates the page content
    private VBox createPicturePage(final int pageIndex) {
        final VBox box = new VBox();
        final ImageView iv = new ImageView(this.images[pageIndex]);
        box.setAlignment(Pos.CENTER);
        final Label desc = new Label("PAGE " + (pageIndex + 1));
        box.getChildren().addAll(iv, desc);
        return box;
    }
}
