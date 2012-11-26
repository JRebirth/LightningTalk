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
package org.jrebirth.presentation.lightningtalk.ui.slides.basic;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.HyperlinkBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GlowBuilder;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.MotionBlurBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPaneBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.PolylineBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.SVGPathBuilder;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.scene.web.WebView;
import javafx.scene.web.WebViewBuilder;
import javafx.util.Duration;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.PrezColors;
import org.jrebirth.presentation.PrezFonts;
import org.jrebirth.presentation.lightningtalk.LtFonts;
import org.jrebirth.presentation.model.SlideContent;
import org.jrebirth.presentation.model.SlideItem;
import org.jrebirth.presentation.ui.base.AbstractSlideView;
import org.jrebirth.presentation.ui.base.SlideStep;

// TODO: Auto-generated Javadoc
/**
 * 
 * The class <strong>AbstractTemplateView</strong>.
 * 
 * The view used to display a standard template slide.
 * 
 * @author Sébastien Bordes
 */
public class BasicView extends AbstractSlideView<BasicModel, AnchorPane, BasicController> {

    /** Prefix used for css class. */
    private static final String ITEM_CLASS_PREFIX = "item";

    /** The sub title of this slide. */
    private Label secondaryTitle;

    /** The label that display the number of the page. */
    private Label pageLabel;

    /** The pane that hold the content. */
    private StackPane slideContent;

    /** The list of nodes taht reprensent each subslide. */
    private final List<Node> subSlides = new ArrayList<>();

    /** The current subslide node displayed. */
    private Node currentSubSlide;

    /** A lock managed by subslide. */
    private final boolean subSlideLock = false;

    /** The transitionn used between subslides. */
    private ParallelTransition subSlideTransition;

    /** The circle. */
    private Circle circle;

    /** The top rectangle. */
    private Rectangle topRectangle;
    
    /** The bottom rectangle. */
    private Rectangle bottomRectangle;

    /** The small pokemon. */
    private SVGPath smallPokemon;

    /** The big pokemon. */
    private SVGPath bigPokemon;

    /** The primary title. */
    private Label primaryTitle;

    /** The prez title. */
    private Label prezTitle;

    /** The place logo. */
    private ImageView placeLogo;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public BasicView(final BasicModel model) throws CoreException {
        super(model);
    }

    /**
     * Gets the sub title.
     * 
     * @return Returns the subTitle.
     */
    protected Label getSubTitle() {
        return this.secondaryTitle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {

        // getRootNode().setPrefSize(1010, 750);
        // getRootNode().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        // getRootNode().setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        this.slideContent = new StackPane();
        this.slideContent.setOpacity(0.9);
        this.slideContent.getStyleClass().add("content");

        this.slideContent.setMinSize(952, 642);
        this.slideContent.setMaxSize(952, 642);
        this.slideContent.setPrefSize(952, 642);

        // this.slideContent.setLayoutX(240);
        // this.slideContent.setLayoutY(420);
        // this.slideContent.setMinWidth(952);
        // this.slideContent.setPrefWidth(952);
        // this.slideContent.setMaxWidth(952);
        // this.slideContent.setMinHeight(642);

        // this.slideContent.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        // this.slideContent.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        // this.slideContent.setStyle("-fx-background-color:#000CCC");

        // Attach the properties view to the center place of the root border pane

        // final Pane bp = PaneBuilder.create().children(this.slideContent).build();
        // bp.relocate(20, 100);
        // bp.setStyle("-fx-background-color:#000CCC");
        // bp.setMinWidth(952);
        // bp.setPrefWidth(952);
        // bp.setMaxWidth(952);
        //
        // bp.setMinHeight(642);
        final Node header = getHeaderPanel();

        if (!getModel().hasStep()) {
            addSubSlide(getContentPanel());
        }

        // initialize the begin properties for the transition
        this.slideContent.setScaleX(0);
        this.slideContent.setScaleY(0);
        // this.slideContent.setRotate(-180);

        // final Node footer = getFooterPanel();

        AnchorPane.setTopAnchor(header, 0.0);
        AnchorPane.setTopAnchor(this.slideContent, 109.0);
        AnchorPane.setLeftAnchor(this.slideContent, 48.0);
        // AnchorPane.setBottomAnchor(footer, 95.0);

        getRootNode().getChildren().addAll(/* footer, */this.slideContent, header);
    }

    /**
     * Show en aempty slide.
     */
    protected void showEmptySlide() {
        this.subSlides.add(getModel().getStepPosition(), null);
    }

    /**
     * Add a subslide node.
     * 
     * @param defaultSubSlide the subslide node
     */
    private void addSubSlide(final Node defaultSubSlide) {

        this.subSlides.add(getModel().getStepPosition(), defaultSubSlide);
        this.slideContent.getChildren().add(defaultSubSlide);

        StackPane.setAlignment(defaultSubSlide, Pos.CENTER);

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
    public void doHide() {

        this.primaryTitle.setTranslateX(3000);
        this.placeLogo.setTranslateX(1200);

        this.smallPokemon.setScaleX(0);
        this.smallPokemon.setScaleY(0);
        this.bigPokemon.setScaleX(0);
        this.bigPokemon.setScaleY(0);
        this.topRectangle.setWidth(0);
        this.bottomRectangle.setWidth(0);

        this.slideContent.setScaleX(0);
        this.slideContent.setScaleY(0);
        // this.slideContent.setRotate(-180);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doReload() {

        // MUST be refactored with property binding

        // this.pageLabel.setText(String.valueOf(getModel().getSlideNumber()));

        // FadeTransitionBuilder.create()
        // .node(getRootNode().getTop())
        // .duration(Duration.millis(600))
        // .fromValue(0).toValue(0.7)
        // .build().play();

        // FadeTransitionBuilder.create()
        // .node(getRootNode().getCenter())
        // .duration(Duration.millis(600))
        // .fromValue(0)
        // .toValue(0.4)
        // .build().play();
        final MotionBlur mb = MotionBlurBuilder.create().angle(180).build();
        this.primaryTitle.setEffect(mb);

        ParallelTransitionBuilder.create().children(

                // ParallelTransitionBuilder.create().children(
                ScaleTransitionBuilder
                        .create()
                        .node(this.smallPokemon)
                        .duration(Duration.millis(400))
                        .fromX(0)
                        .fromY(0)
                        .toX(1)
                        .toY(1)
                        .build(),
                RotateTransitionBuilder.create()
                        .node(this.smallPokemon)
                        .duration(Duration.millis(800))
                        .fromAngle(1080)
                        .toAngle(-135)
                        .build(),
                ScaleTransitionBuilder
                        .create()
                        .node(this.bigPokemon)
                        .delay(Duration.millis(200))
                        .duration(Duration.millis(400))
                        .fromX(0)
                        .fromY(0)
                        .toX(1.2)
                        .toY(1.2)
                        .build(),
                RotateTransitionBuilder.create()
                        .node(this.bigPokemon)
                        .delay(Duration.millis(200))
                        .duration(Duration.millis(800))
                        .fromAngle(0)
                        .toAngle(1080)
                        .build(),

                TimelineBuilder.create()
                        .delay(Duration.millis(200))
                        .keyFrames(
                                new KeyFrame(Duration.millis(0), new KeyValue(this.topRectangle.widthProperty(), 0)),
                                new KeyFrame(Duration.millis(600), new KeyValue(this.topRectangle.widthProperty(), 300))
                        )
                        .build(),

                TimelineBuilder.create()
                        .delay(Duration.millis(1200))
                        .keyFrames(
                                new KeyFrame(Duration.millis(0),
                                        new KeyValue(this.bottomRectangle.widthProperty(), 0),
                                        new KeyValue(this.bottomRectangle.xProperty(), 1000)
                                ),
                                new KeyFrame(Duration.millis(300),
                                        new KeyValue(this.bottomRectangle.widthProperty(), 300),
                                        new KeyValue(this.bottomRectangle.xProperty(), 700)
                                )
                        )
                        .build(),
                TranslateTransitionBuilder.create()
                        .node(this.placeLogo)
                        .delay(Duration.millis(1200))
                        .duration(Duration.millis(300))
                        .toX(-400)
                        .build(),

                TranslateTransitionBuilder.create()
                        .node(this.primaryTitle)
                        .delay(Duration.millis(200))
                        .duration(Duration.millis(400))
                        .toX(-3000 + 130)
                        .build(),
                TimelineBuilder.create()
                        .keyFrames(
                                new KeyFrame(Duration.millis(0), new KeyValue(mb.radiusProperty(), 0)),
                                new KeyFrame(Duration.millis(100), new KeyValue(mb.radiusProperty(), 50)),
                                new KeyFrame(Duration.millis(200), new KeyValue(mb.radiusProperty(), 63)),
                                new KeyFrame(Duration.millis(300), new KeyValue(mb.radiusProperty(), 50)),
                                new KeyFrame(Duration.millis(400), new KeyValue(mb.radiusProperty(), 0))
                        )
                        .build(),
                // ).build()
                ParallelTransitionBuilder
                        .create()
                        .delay(Duration.millis(600))
                        .children(
                                // RotateTransitionBuilder
                                // .create()
                                // .duration(Duration.millis(600))
                                // .fromAngle(-180)
                                // .toAngle(0)
                                // .build(),
                                TranslateTransitionBuilder.create()
                                        .duration(Duration.millis(600))
                                        .fromX(1024 / 2)
                                        .fromY(768 / 2)
                                        .toX(0)
                                        .toY(0)
                                        .build(),
                                ScaleTransitionBuilder
                                        .create()
                                        .duration(Duration.millis(600))
                                        .fromX(0)
                                        .fromY(0)
                                        .toX(1)
                                        .toY(1)
                                        .build()
                        )
                        .node(this.slideContent)
                        .build()
                )
                .build().play();
    }

    /**
     * Build and return the header panel.
     * 
     * @return the header panel
     */
    protected Node getHeaderPanel() {

        final Pane headerPane = PaneBuilder.create()
                .styleClass("header")
                .layoutX(0.0)
                .layoutY(0.0)
                .minWidth(1024)
                .prefWidth(1024)
                .build();

        this.primaryTitle = LabelBuilder.create()
                // .styleClass("slideTitle")
                .font(LtFonts.SLIDE_TITLE.get())
                .textFill(PrezColors.SLIDE_TITLE.get())
                .text(getModel().getSlide().getTitle().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"))
                .layoutX(3000) // 40
                .layoutY(45)
                // .style("-fx-background-color:#CCCB20")
                .build();

        this.secondaryTitle = LabelBuilder.create()
                // .styleClass("slideTitle")
                .font(PrezFonts.SLIDE_SUB_TITLE.get())
                .textFill(PrezColors.SLIDE_TITLE.get())
                // .scaleX(1.5)
                // .scaleY(1.5)
                .layoutX(450)
                .layoutY(14)
                .minWidth(450)
                // .style("-fx-background-color:#E53B20")
                .alignment(Pos.CENTER_RIGHT)
                .textAlignment(TextAlignment.RIGHT)
                .build();

        this.prezTitle = LabelBuilder.create()
                // .styleClass("slideTitle")
                .font(LtFonts.PREZ_TITLE.get())
                .textFill(Color.LIGHTGRAY)
                // .scaleX(1.5)
                // .scaleY(1.5)
                // .layoutX(545)
                // .layoutY(711)
                .layoutX(480)
                .layoutY(14.0)
                .minWidth(450)
                // .style("-fx-background-color:#E53B20")
                .alignment(Pos.CENTER_RIGHT)
                .textAlignment(TextAlignment.RIGHT)
                .build();

        this.placeLogo = ImageViewBuilder.create()
                // .layoutX(680.0)
                // .layoutY(-14.0)
                .layoutX(1200)
                .layoutY(700)
                // .scaleX(0.6)
                // .scaleY(0.6)
                .image(loadImage("images/PlaceLogo.png"))
                .build();

        final Polyline pl = PolylineBuilder.create()
                .strokeWidth(3)
                .stroke(Color.web("F79508"))
                .points(684.0, 12.0, 946.0, 12.0, 946.0, 107.0)
                .build();

        this.topRectangle = RectangleBuilder.create()
                .layoutX(95.0)
                .layoutY(95.0)
                .width(0.0) // 60.0
                .height(14.0)
                .fill(Color.web("1C9A9A"))
                .build();
        this.bottomRectangle = RectangleBuilder.create()
                .layoutX(0)
                .layoutY(738)
                .width(0.0) // 60.0
                .height(14.0)
                .fill(Color.web("1C9A9A"))
                .build();

        // this.circle = CircleBuilder.create()
        // .scaleX(0)
        // .scaleY(0)
        // .layoutX(18 + 54)
        // .layoutY(18 + 54)
        // .radius(54)
        // .fill(Color.web("444442"))
        // .build();
        final Effect smallPokemonEffect =
                InnerShadowBuilder.create()
                        .offsetX(1)
                        .offsetY(1)
                        .color(Color.LIGHTGRAY)
                        .input(GlowBuilder.create()
                                .level(0.6)
                                .build())
                        .build();

        final Effect bigPokemonEffect =
                DropShadowBuilder.create()
                        .offsetX(2)
                        .offsetY(2)
                        .input(GlowBuilder.create()
                                .level(0.6)
                                .build())
                        .build();

        this.smallPokemon = SVGPathBuilder
                .create()
                .scaleX(0)
                .scaleY(0)
                .layoutX(40)
                .layoutY(40)
                .fill(Color.web("F79508"))
                .effect(smallPokemonEffect)
                .content(
                        "M64.332,33.584l3.166-3.166C65.99,14.311,53.104,1.493,36.916,0l-3.167,3.167L30.582,0 C14.394,1.493,1.507,14.312,0,30.419l3.166,3.166L0,36.751c1.508,16.106,14.395,28.925,30.582,30.418l3.167-3.167l3.168,3.168 c16.188-1.493,29.073-14.313,30.58-30.421L64.332,33.584z M37.387,44.951h-7.275c-5.114,0-9.26-4.146-9.26-9.26v-4.917 c0-5.114,4.146-9.26,9.26-9.26h7.275c5.114,0,9.26,4.146,9.26,9.26v4.917C46.646,40.805,42.501,44.951,37.387,44.951z")
                .build();

        this.bigPokemon = SVGPathBuilder
                .create()
                .scaleX(0)
                .scaleY(0)
                .layoutX(40)
                .layoutY(40)
                .fill(Color.web("D9E021"))
                .effect(bigPokemonEffect)
                .content(
                        "M64.332,33.584l3.166-3.166C65.99,14.311,53.104,1.493,36.916,0l-3.167,3.167L30.582,0 C14.394,1.493,1.507,14.312,0,30.419l3.166,3.166L0,36.751c1.508,16.106,14.395,28.925,30.582,30.418l3.167-3.167l3.168,3.168 c16.188-1.493,29.073-14.313,30.58-30.421L64.332,33.584z M37.387,44.951h-7.275c-5.114,0-9.26-4.146-9.26-9.26v-4.917 c0-5.114,4.146-9.26,9.26-9.26h7.275c5.114,0,9.26,4.146,9.26,9.26v4.917C46.646,40.805,42.501,44.951,37.387,44.951z")
                .build();

        this.pageLabel = LabelBuilder.create()
                .layoutX(970)
                .layoutY(18.0)
                .text(String.valueOf(getModel().getSlide().getPage()))
                .font(PrezFonts.PAGE.get())
                .textFill(Color.WHITE)
                .rotate(90.0)
                .build();

        // final FlowPane fp = FlowPaneBuilder.create()
        // .orientation(Orientation.HORIZONTAL)
        // .alignment(Pos.BASELINE_CENTER)
        // .children(this.secondaryTitle)
        // // .style("-fx-background-color:#CCCCCC")
        // .build();

        headerPane.getChildren().addAll(this.topRectangle, this.bottomRectangle,
                this.bigPokemon, this.smallPokemon,
                this.primaryTitle, this.placeLogo, this.secondaryTitle,
                pl, this.pageLabel,
                this.prezTitle);

        // AnchorPane.setLeftAnchor(primaryTitle, 40.0);
        // AnchorPane.setTopAnchor(primaryTitle, 45.0);
        //
        // AnchorPane.setRightAnchor(this.secondaryTitle, 80.0);
        // AnchorPane.setTopAnchor(primaryTitle, 20.0);

        // ap.setStyle("-fx-background-color:#002266");

        // sp.setStyle("-fx-background-color:#663366");
        // StackPane.setAlignment(ap, Pos.BOTTOM_CENTER);
        // sp.getChildren().add(ap);

        return headerPane;

    }

    /**
     * TODO To complete.
     * 
     * @param node the node
     */
    protected void bindNode(final Node node) {
        node.scaleXProperty().bind(bindWidth());
        node.scaleYProperty().bind(bindHeight());
    }

    /**
     * TODO To complete.
     * 
     * @return the number binding
     */
    protected NumberBinding bindHeight() {
        return Bindings.divide(getModel().getLocalFacade().getGlobalFacade().getApplication().getStage().heightProperty(), 768);
    }

    /**
     * TODO To complete.
     * 
     * @return the number binding
     */
    protected NumberBinding bindWidth() {
        return Bindings.divide(getModel().getLocalFacade().getGlobalFacade().getApplication().getStage().widthProperty(), 1024);
    }

    /**
     * Build and return the content panel.
     * 
     * @return the content panel
     */
    protected Node getContentPanel() {
        return buildDefaultContent(getModel().getDefaultContent());
    }

    /**
     * Build and return the footer panel.
     * 
     * @return the footer panel
     */
    protected Node getFooterPanel() {
        this.pageLabel = LabelBuilder.create()
                .text(String.valueOf(getModel().getSlide().getPage()))
                .font(PrezFonts.PAGE.get())
                .build();

        final AnchorPane ap = AnchorPaneBuilder.create()
                .children(this.pageLabel)
                .build();
        AnchorPane.setRightAnchor(this.pageLabel, 20.0);

        final StackPane sp = StackPaneBuilder.create()
                .styleClass("footer")
                .prefHeight(35.0)
                .minHeight(Region.USE_PREF_SIZE)
                .maxHeight(Region.USE_PREF_SIZE)
                .children(ap)
                .build();

        StackPane.setAlignment(ap, Pos.CENTER_RIGHT);

        return sp;
    }

    /**
     * Build the default content slide.
     * 
     * @param slideContent the slide content
     * @return the vbox with default content items
     */
    protected VBox buildDefaultContent(final SlideContent slideContent) {

        if (slideContent.getTitle() != null) {
            this.secondaryTitle.setText(slideContent.getTitle());
        }

        this.prezTitle.setText("JavaFX 2.2\n What's Up ?");

        final VBox vbox = new VBox();
        // vbox.getStyleClass().add("content");

        if (getModel().getSlide().getStyle() != null) {
            vbox.getStyleClass().add(getModel().getSlide().getStyle());
        }

        if (slideContent != null) {
            for (final SlideItem item : slideContent.getItem()) {
                addSlideItem(vbox, item);
            }
        }

        return vbox;
    }

    /**
     * Add a slide item by managing level.
     * 
     * @param vbox the layout node
     * @param item the slide item to add
     */
    protected void addSlideItem(final VBox vbox, final SlideItem item) {

        Node node = null;
        if (item.getLink()) {

            final Hyperlink link = HyperlinkBuilder.create()
                    .opacity(1.0)
                    .text(item.getValue())
                    .build();

            link.getStyleClass().add("link" + item.getLevel());

            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    final ClipboardContent content = new ClipboardContent();
                    content.putString("http://" + ((Hyperlink) e.getSource()).getText());
                    Clipboard.getSystemClipboard().setContent(content);
                }
            });
            node = link;

        } else if (item.getHtml()) {

            final WebView web = WebViewBuilder.create()
                    .fontScale(1.4)
                    // .effect(ReflectionBuilder.create().fraction(0.4).build())
                    .build();
            web.getEngine().loadContent(item.getValue());

            VBox.setVgrow(web, Priority.NEVER);

            node = web; // StackPaneBuilder.create().children(web).style("-fx-border-width:2;-fx-border-color:#000000").build();

        } else if (item.getImage() != null) {

            final Image image = loadImage(item.getImage());
            final ImageView imageViewer = ImageViewBuilder.create()
                    .styleClass(ITEM_CLASS_PREFIX + item.getLevel())
                    .image(image)
                    // .effect(ReflectionBuilder.create().fraction(0.9).build())
                    .build();

            node = imageViewer;
        } else {

            final Text text = TextBuilder.create()
                    .styleClass(ITEM_CLASS_PREFIX + item.getLevel())
                    .text(item.getValue() == null ? "" : item.getValue())
                    .build();

            node = text;
        }

        if (item.getStyle() != null) {
            node.getStyleClass().add(item.getStyle());
        }

        if (item.getScale() != 1.0) {
            node.setScaleX(item.getScale());
            node.setScaleY(item.getScale());
        }

        vbox.getChildren().add(node);
    }

    /**
     * Show the slide step store which match with xml file.
     * 
     * @param slideStep the slide step to show
     */
    public void showSlideStep(final SlideStep slideStep) {

        if (this.subSlides.size() >= getModel().getStepPosition() || this.subSlides.get(getModel().getStepPosition()) == null) {
            addSubSlide(buildDefaultContent(getModel().getContent(slideStep)));
        }
        final Node nextSlide = this.subSlides.get(getModel().getStepPosition());

        if (this.currentSubSlide != null && nextSlide != null) {
            performStepAnimation(nextSlide);
        } else {
            // No Animation
            this.currentSubSlide = nextSlide;
        }

    }

    /**
     * Show custom slide step.
     * 
     * @param node the node
     */
    protected void showCustomSlideStep(final Node node) {

        addSubSlide(node);
        final Node nextSlide = this.subSlides.get(getModel().getStepPosition());
        if (this.currentSubSlide != null && nextSlide != null) {

            performStepAnimation(nextSlide);
        } else {
            // No Animation
            this.currentSubSlide = nextSlide;
        }
    }

    /**
     * TODO To complete.
     * 
     * @param nextSlide the next slide
     */
    private void performStepAnimation(final Node nextSlide) {

        setSlideLocked(true);
        this.subSlideTransition = ParallelTransitionBuilder.create()

                .onFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(final ActionEvent event) {
                        BasicView.this.currentSubSlide = nextSlide;
                        BasicView.this.setSlideLocked(false);
                    }
                })

                .children(
                        SequentialTransitionBuilder.create()
                                .node(this.currentSubSlide)
                                .children(
                                        TranslateTransitionBuilder.create()
                                                .duration(Duration.millis(400))
                                                .fromY(0)
                                                .toY(-700)
                                                // .fromZ(-10)
                                                .build(),
                                        TimelineBuilder.create()
                                                .keyFrames(
                                                        new KeyFrame(Duration.millis(0), new KeyValue(this.currentSubSlide.visibleProperty(), true)),
                                                        new KeyFrame(Duration.millis(1), new KeyValue(this.currentSubSlide.visibleProperty(), false))
                                                )
                                                .build()
                                )

                                .build(),
                        SequentialTransitionBuilder.create()
                                .node(nextSlide)
                                .children(
                                        TimelineBuilder.create()
                                                .keyFrames(
                                                        new KeyFrame(Duration.millis(0), new KeyValue(nextSlide.visibleProperty(), false)),
                                                        new KeyFrame(Duration.millis(1), new KeyValue(nextSlide.visibleProperty(), true))
                                                )
                                                .build(),
                                        TranslateTransitionBuilder.create()
                                                .duration(Duration.millis(400))
                                                .fromY(700)
                                                .toY(0)
                                                // .fromZ(-10)
                                                .build()
                                )
                                .build()
                )
                .build();
        this.subSlideTransition.play();

    }

    /**
     * Gets the slide content.
     * 
     * @return Returns the slideContent.
     */
    protected StackPane getSlideContent() {
        return this.slideContent;
    }
}
