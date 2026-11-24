/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2024
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
package org.jrebirth.af.presentation.lightningtalk.ui.slides.basic;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.image.RelImage;
import org.jrebirth.af.presentation.model.SlideContent;
import org.jrebirth.af.presentation.model.SlideItem;
import org.jrebirth.af.presentation.resources.PrezColors;
import org.jrebirth.af.presentation.resources.PrezFonts;
import org.jrebirth.af.presentation.ui.base.AbstractSlideView;
import org.jrebirth.af.presentation.ui.base.SlideStep;
import org.jrebirth.af.presentation.lightningtalk.resources.LtFonts;
import org.jrebirth.af.presentation.lightningtalk.resources.LtImages;

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

    /** The list of nodes that represent each subslide. */
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

    /** The line revelator rectangle. */
    private Rectangle revelator;

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
    protected void initView() {

        // node().setPrefSize(1010, 750);
        // node().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        // node().setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        this.slideContent = new StackPane();
        this.slideContent.setOpacity(0.95);
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

        if (!model().hasStep()) {
            addSubSlide(getContentPanel());
        }

        // initialize the begin properties for the transition
        this.slideContent.setScaleX(1);
        this.slideContent.setScaleY(1);
        // this.slideContent.setRotate(-180);

        // final Node footer = getFooterPanel();

        AnchorPane.setTopAnchor(header, 0.0);
        AnchorPane.setTopAnchor(this.slideContent, 109.0);
        AnchorPane.setLeftAnchor(this.slideContent, 48.0);
        // AnchorPane.setBottomAnchor(footer, 95.0);

        node().getChildren().addAll(/* footer, */this.slideContent, header);
    }

    /**
     * Show en aempty slide.
     */
    protected void showEmptySlide() {
        this.subSlides.add(model().getStepPosition(), null);
    }

    /**
     * Add a subslide node.
     *
     * @param defaultSubSlide the subslide node
     */
    private void addSubSlide(final Node defaultSubSlide) {

        if (this.subSlides.size() < model().getStepPosition()) {
            for (int i = model().getStepPosition(); i >= 0; i--) {
                this.subSlides.add(null);
            }
        }

        this.subSlides.add(model().getStepPosition(), defaultSubSlide);
        this.slideContent.getChildren().add(defaultSubSlide);

        StackPane.setAlignment(defaultSubSlide, Pos.CENTER);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        reload();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {

        this.primaryTitle.setTranslateX(3000);
        this.placeLogo.setTranslateX(1200);

        this.smallPokemon.setScaleX(0);
        this.smallPokemon.setScaleY(0);
        this.bigPokemon.setScaleX(0);
        this.bigPokemon.setScaleY(0);
        this.topRectangle.setWidth(0);
        this.bottomRectangle.setWidth(0);

        this.slideContent.setScaleX(1);
        this.slideContent.setScaleY(1);
        // this.slideContent.setRotate(-180);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {

        this.primaryTitle.snapshot(null, null);
        // MUST be refactored with property binding

        // this.pageLabel.setText(String.valueOf(model().getSlideNumber()));

        // FadeTransitionBuilder.create()
        // .node(node().getTop())
        // .duration(Duration.millis(600))
        // .fromValue(0).toValue(0.7)
        // .build().play();

        // FadeTransitionBuilder.create()
        // .node(node().getCenter())
        // .duration(Duration.millis(600))
        // .fromValue(0)
        // .toValue(0.4)
        // .build().play();
        final MotionBlur mb = new MotionBlur();
        mb.setAngle(180);
        this.primaryTitle.setEffect(mb);

        ParallelTransition pt = new ParallelTransition();

        ScaleTransition st = new ScaleTransition();
        st.setNode(this.smallPokemon);
        st.setDuration(Duration.millis(400));
        st.setFromX(0);
        st.setFromY(0);
        st.setToX(1);
        st.setToY(1);

        RotateTransition rt = new RotateTransition();
        rt.setNode(this.smallPokemon);
        rt.setDuration(Duration.millis(800));
        rt.setFromAngle(1080);
        rt.setToAngle(-135);

        ScaleTransition st1 = new ScaleTransition();
        st1.setNode(this.bigPokemon);
        st1.setDelay(Duration.millis(200));
        st1.setDuration(Duration.millis(400));
        st1.setFromX(0);
        st1.setFromY(0);
        st1.setToX(1.2);
        st1.setToY(1.2);

        RotateTransition rt1 = new RotateTransition();
        rt1.setNode(this.bigPokemon);
        rt1.setDelay(Duration.millis(200));
        rt1.setDuration(Duration.millis(800));
        rt1.setFromAngle(0);
        rt1.setToAngle(1080);

        Timeline ti = new Timeline();
        ti.setDelay(Duration.millis(200));
        ti.getKeyFrames().addAll(
                                 new KeyFrame(Duration.millis(0), new KeyValue(this.topRectangle.widthProperty(), 0)),
                                 new KeyFrame(Duration.millis(600), new KeyValue(this.topRectangle.widthProperty(), 300)));

        Timeline ti1 = new Timeline();
        ti1.setDelay(Duration.millis(1200));
        ti1.getKeyFrames().addAll(
                                  new KeyFrame(Duration.millis(0),
                                               new KeyValue(this.bottomRectangle.widthProperty(), 0),
                                               new KeyValue(this.bottomRectangle.xProperty(), 1000)),
                                  new KeyFrame(Duration.millis(300),
                                               new KeyValue(this.bottomRectangle.widthProperty(), 300),
                                               new KeyValue(this.bottomRectangle.xProperty(), 700)));

        Timeline ti2 = new Timeline();
        ti2.setDelay(Duration.millis(600));
        ti2.getKeyFrames().addAll(
                                  new KeyFrame(Duration.millis(0),
                                               new KeyValue(this.revelator.widthProperty(), 0)),
                                  new KeyFrame(Duration.millis(1000),
                                               new KeyValue(this.revelator.widthProperty(), 400)));

        // FadeTransitionBuilder.create()
        // .delay(Duration.millis(600))
        // .duration(Duration.millis(1000))
        // .node(this.prezTitle)
        // .fromValue(0).toValue(1)
        // .build(),

        TranslateTransition tt = new TranslateTransition();
        tt.setNode(this.placeLogo);
        tt.setDelay(Duration.millis(1200));
        tt.setDuration(Duration.millis(300));
        tt.setToX(-400);

        TranslateTransition tt1 = new TranslateTransition();
        tt1.setNode(this.primaryTitle);
        tt1.setDelay(Duration.millis(200));
        tt1.setDuration(Duration.millis(400));
        tt1.setToX(-3000 + 130);

        Timeline ti3 = new Timeline();
        ti3.getKeyFrames().addAll(
                                  new KeyFrame(Duration.millis(0), new KeyValue(mb.radiusProperty(), 0)),
                                  new KeyFrame(Duration.millis(100), new KeyValue(mb.radiusProperty(), 50)),
                                  new KeyFrame(Duration.millis(200), new KeyValue(mb.radiusProperty(), 63)),
                                  new KeyFrame(Duration.millis(300), new KeyValue(mb.radiusProperty(), 50)),
                                  new KeyFrame(Duration.millis(400), new KeyValue(mb.radiusProperty(), 0)));

        // ).build()

        // RotateTransitionBuilder
        // .create()
        // .duration(Duration.millis(600))
        // .fromAngle(-180)
        // .toAngle(0)
        // .build(),
        TranslateTransition gtt = new TranslateTransition();
        gtt.setDuration(Duration.millis(600));
        gtt.setFromX(1024 / 2);
        gtt.setFromY(768 / 2);
        gtt.setToX(0);
        gtt.setToY(0);

        ScaleTransition gst = new ScaleTransition();
        gst.setDuration(Duration.millis(600));
        gst.setFromX(0);
        gst.setFromY(0);
        gst.setToX(1);
        gst.setToY(1);
        ParallelTransition pt2 = new ParallelTransition();
        pt2.setDelay(Duration.millis(600));
        pt2.getChildren().addAll(gtt, gst);
        pt2.setNode(this.slideContent);

        pt.getChildren().addAll(st, rt, st1, rt1, ti, ti1, ti2, tt, tt1, ti3);
        pt.play();
        pt2.play();
    }

    /**
     * Build and return the header panel.
     *
     * @return the header panel
     */
    protected Node getHeaderPanel() {

        final Pane headerPane = new Pane();

        headerPane.getStyleClass().add("header");
        headerPane.setLayoutX(0.0);
        headerPane.setLayoutY(0.0);
        headerPane.setMinWidth(1024);
        headerPane.setPrefWidth(1024);

        this.primaryTitle = new Label();
        // .styleClass("slideTitle")
        this.primaryTitle.setFont(LtFonts.SLIDE_TITLE.get());
        this.primaryTitle.setTextFill(PrezColors.SLIDE_TITLE.get());
        this.primaryTitle.setText(model().getSlide().getTitle().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"));
        this.primaryTitle.setLayoutX(3000); // 40
        this.primaryTitle.setLayoutY(45);
        // .style("-fx-background-color:#CCCB20")

        this.secondaryTitle = new Label();
        // .styleClass("slideTitle")
        this.secondaryTitle.setFont(PrezFonts.SLIDE_SUB_TITLE.get());
        this.secondaryTitle.setTextFill(PrezColors.SLIDE_TITLE.get());
        // .scaleX(1.5)
        // .scaleY(1.5)
        this.secondaryTitle.setLayoutX(110);
        this.secondaryTitle.setLayoutY(80);
        this.secondaryTitle.minWidth(450);
        // .style("-fx-background-color:#E53B20")
        this.secondaryTitle.setAlignment(Pos.CENTER_RIGHT);
        this.secondaryTitle.setTextAlignment(TextAlignment.RIGHT);

        /*
         * this.prezTitle = LabelBuilder.create() // .styleClass("slideTitle") .font(LtFonts.PREZ_TITLE.get()) .textFill(JpColors.PREZ_TITLE.get()) // .scaleX(1.5) // .scaleY(1.5) // .layoutX(545) //
         * .layoutY(711) .layoutX(480) .layoutY(14.0) .minWidth(450) .opacity(0.0) // .style("-fx-background-color:#E53B20") .alignment(Pos.CENTER_RIGHT) .textAlignment(TextAlignment.RIGHT) .build();
         */


        this.prezTitle = new Label();
        this.prezTitle.setFont(LtFonts.PREZ_TITLE.get());
        this.prezTitle.setTextFill(Color.LIGHTGRAY);
        this.prezTitle.setLayoutX(480);
        this.prezTitle.setLayoutY(14.0);
        this.prezTitle.setMinWidth(450);
        this.prezTitle.setAlignment(Pos.CENTER_RIGHT);
        this.prezTitle.setTextAlignment(TextAlignment.RIGHT);

        this.placeLogo = new ImageView();
        this.placeLogo.setLayoutX(1200);
        this.placeLogo.setLayoutY(700);
        this.placeLogo.setScaleX(0.6);
        this.placeLogo.setScaleY(0.6);
        this.placeLogo.setImage(LtImages.PLACE_LOGO.get());

        final Polyline pl = new Polyline();
        pl.setStrokeWidth(3);
        pl.setStroke(Color.web("F79508"));
        pl.getPoints().addAll(684.0, 12.0, 946.0, 12.0, 946.0, 107.0);

        Rotate rr = new Rotate();
        rr.setAngle(200);
        rr.setAxis(Rotate.Z_AXIS);
        this.revelator = new Rectangle();
        this.revelator.setLayoutX(946.0);
        this.revelator.setLayoutY(107.0);
        this.revelator.setWidth(0.0);// 60.0
        this.revelator.setHeight(200.0);
        this.revelator.setFill(Color.YELLOW);
        this.revelator.getTransforms().add(rr);

        // Rectangle r = RectangleBuilder.create()
        // .layoutX(946.0)
        // .layoutY(107.0)
        // .width(400.0) // 60.0
        // .height(200.0)
        // .fill(Color.YELLOW)
        // .transforms(RotateBuilder.create().angle(200).axis(Rotate.Z_AXIS).build())
        // .build();

        pl.setClip(this.revelator);

        // Group line = new Group();
        // line.setBlendMode(BlendMode.OVERLAY);
        // line.getChildren().add(pl);
        // line.getChildren().add(revelator);

        this.topRectangle = new Rectangle();
        this.topRectangle.setLayoutX(95.0);
        this.topRectangle.setLayoutY(95.0);
        this.topRectangle.setWidth(0.0); // 60.0
        this.topRectangle.setHeight(14.0);
        this.topRectangle.setFill(Color.web("1C9A9A"));

        this.bottomRectangle = new Rectangle();
        this.bottomRectangle.setLayoutX(0);
        this.bottomRectangle.setLayoutY(738);
        this.bottomRectangle.setWidth(0.0); // 60.0
        this.bottomRectangle.setHeight(14.0);
        this.bottomRectangle.setFill(Color.web("1C9A9A"));

        // this.circle = CircleBuilder.create()
        // .scaleX(0)
        // .scaleY(0)
        // .layoutX(18 + 54)
        // .layoutY(18 + 54)
        // .radius(54)
        // .fill(Color.web("444442"))
        // .build();
        final InnerShadow smallPokemonEffect = new InnerShadow();
        smallPokemonEffect.setOffsetX(1);
        smallPokemonEffect.setOffsetY(1);
        smallPokemonEffect.setColor(Color.LIGHTGRAY);
        smallPokemonEffect.setInput(new Glow(0.6));

        final DropShadow bigPokemonEffect = new DropShadow();
        bigPokemonEffect.setOffsetX(2);
        bigPokemonEffect.setOffsetY(2);
        bigPokemonEffect.setInput(new Glow(0.6));

        this.smallPokemon = new SVGPath();
        this.smallPokemon.setScaleX(0);
        this.smallPokemon.setScaleY(0);
        this.smallPokemon.setLayoutX(40);
        this.smallPokemon.setLayoutY(40);
        this.smallPokemon.setFill(Color.web("F79508"));
        this.smallPokemon.setEffect(smallPokemonEffect);
        this.smallPokemon.setContent(
                                     "M64.332,33.584l3.166-3.166C65.99,14.311,53.104,1.493,36.916,0l-3.167,3.167L30.582,0 C14.394,1.493,1.507,14.312,0,30.419l3.166,3.166L0,36.751c1.508,16.106,14.395,28.925,30.582,30.418l3.167-3.167l3.168,3.168 c16.188-1.493,29.073-14.313,30.58-30.421L64.332,33.584z M37.387,44.951h-7.275c-5.114,0-9.26-4.146-9.26-9.26v-4.917 c0-5.114,4.146-9.26,9.26-9.26h7.275c5.114,0,9.26,4.146,9.26,9.26v4.917C46.646,40.805,42.501,44.951,37.387,44.951z");
        this.bigPokemon = new SVGPath();
        this.bigPokemon.setScaleX(0);
        this.bigPokemon.setScaleY(0);
        this.bigPokemon.setLayoutX(40);
        this.bigPokemon.setLayoutY(40);
        this.bigPokemon.setFill(Color.web("D9E021"));
        this.bigPokemon.setEffect(bigPokemonEffect);
        this.bigPokemon.setContent(
                                   "M64.332,33.584l3.166-3.166C65.99,14.311,53.104,1.493,36.916,0l-3.167,3.167L30.582,0 C14.394,1.493,1.507,14.312,0,30.419l3.166,3.166L0,36.751c1.508,16.106,14.395,28.925,30.582,30.418l3.167-3.167l3.168,3.168 c16.188-1.493,29.073-14.313,30.58-30.421L64.332,33.584z M37.387,44.951h-7.275c-5.114,0-9.26-4.146-9.26-9.26v-4.917 c0-5.114,4.146-9.26,9.26-9.26h7.275c5.114,0,9.26,4.146,9.26,9.26v4.917C46.646,40.805,42.501,44.951,37.387,44.951z");
        this.pageLabel = new Label();
        this.pageLabel.setLayoutX(970);
        this.pageLabel.setLayoutY(18.0);
        this.pageLabel.setText(String.valueOf(model().getSlide().getPage()));
        this.pageLabel.setFont(LtFonts.PAGE.get());
        this.pageLabel.setTextFill(Color.WHITE);
        this.pageLabel.setRotate(90.0);

        // final FlowPane fp = FlowPaneBuilder.create()
        // .orientation(Orientation.HORIZONTAL)
        // .alignment(Pos.BASELINE_CENTER)
        // .children(this.secondaryTitle)
        // // .style("-fx-background-color:#CCCCCC")
        // .build();

        headerPane.getChildren().addAll(this.topRectangle,
                                        this.bigPokemon, this.smallPokemon,
                                        this.primaryTitle, this.placeLogo, this.bottomRectangle, this.secondaryTitle,
                                        pl, this.pageLabel, this.prezTitle);

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
        return Bindings.divide(model().localFacade().globalFacade().application().stage().heightProperty(), 768);
    }

    /**
     * TODO To complete.
     *
     * @return the number binding
     */
    protected NumberBinding bindWidth() {
        return Bindings.divide(model().localFacade().globalFacade().application().stage().widthProperty(), 1024);
    }

    /**
     * Build and return the content panel.
     *
     * @return the content panel
     */
    protected Node getContentPanel() {
        return buildDefaultContent(model().getDefaultContent());
    }

    /**
     * Build and return the footer panel.
     *
     * @return the footer panel
     */
    protected Node getFooterPanel() {
        this.pageLabel = new Label();
        this.pageLabel.setText(String.valueOf(model().getSlide().getPage()));
        this.pageLabel.setFont(PrezFonts.PAGE.get());

        final AnchorPane ap = new AnchorPane();
        ap.getChildren().add(this.pageLabel);
        AnchorPane.setRightAnchor(this.pageLabel, 20.0);

        final StackPane sp = new StackPane();
        sp.getStyleClass().add("footer");
        sp.setPrefHeight(35.0);
        sp.setMinHeight(Region.USE_PREF_SIZE);
        sp.setMaxHeight(Region.USE_PREF_SIZE);
        sp.getChildren().add(ap);

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

        if (model().getSlide().getStyle() != null) {
            vbox.getStyleClass().add(model().getSlide().getStyle());
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
        if (item.isLink()) {

            final Hyperlink link = new Hyperlink();
            link.setOpacity(1.0);
            link.setText(item.getValue());

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

        } else if (item.isHtml()) {

            final WebView web = new WebView();
            web.setFontScale(1.4);
            // .effect(ReflectionBuilder.create().fraction(0.4).build())
            web.getEngine().loadContent(item.getValue());

            VBox.setVgrow(web, Priority.NEVER);

            node = web; // StackPaneBuilder.create().children(web).style("-fx-border-width:2;-fx-border-color:#000000").build();

        } else if (item.getImage() != null) {

            final Image image = Resources.create(new RelImage(item.getImage())).module(getClass().getModule()).get();
            final ImageView imageViewer = new ImageView();
            imageViewer.getStyleClass().add(ITEM_CLASS_PREFIX + item.getLevel());
            imageViewer.setImage(image);
            // .effect(ReflectionBuilder.create().fraction(0.9).build())

            node = imageViewer;
        } else {

            final Text text = new Text();
            text.getStyleClass().add(ITEM_CLASS_PREFIX + item.getLevel());
            text.setText(item.getValue() == null ? "" : item.getValue());

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

    @Override
    public boolean isReadyForSlidesStepUpdate(boolean isReverse) {

        if (this.subSlideTransition != null && this.subSlideTransition.getStatus() == Animation.Status.RUNNING) {

            this.subSlideTransition.setRate(isReverse ? -3 : 3);

            return false;
        }
        return true;
    }

    /**
     * Show the slide step store which match with xml file.
     *
     * @param slideStep the slide step to show
     */
    public void showSlideStep(final SlideStep slideStep) {

        if (this.subSlides.size() >= model().getStepPosition()
                || model().getStepPosition() > this.subSlides.size()
                || this.subSlides.get(model().getStepPosition()) == null) {
            addSubSlide(buildDefaultContent(model().getContent(slideStep)));
        }
        final Node nextSlide = this.subSlides.get(model().getStepPosition());

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
        final Node nextSlide = this.subSlides.get(model().getStepPosition());
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

        this.subSlideTransition = new ParallelTransition();
        this.subSlideTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                BasicView.this.currentSubSlide = nextSlide;
            }
        });

        SequentialTransition seq = new SequentialTransition();
        seq.setNode(this.currentSubSlide);

        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.millis(400));
        tt.setFromY(model().isForwardFlow() ? 0 : 0);
        tt.setToY(model().isForwardFlow() ? -700 : 700);
        // .fromZ(-10)
        Timeline ti = new Timeline();
        ti.getKeyFrames().addAll(
                                 new KeyFrame(Duration.millis(0),
                                              new KeyValue(this.currentSubSlide.visibleProperty(),
                                                           true)),
                                 new KeyFrame(Duration.millis(1),
                                              new KeyValue(this.currentSubSlide.visibleProperty(),
                                                           false)));
        seq.getChildren().addAll(tt, ti);

        SequentialTransition seq1 = new SequentialTransition();
        seq1.setNode(nextSlide);

        Timeline ti1 = new Timeline();
        ti.getKeyFrames().addAll(
                                 new KeyFrame(Duration.millis(0),
                                              new KeyValue(nextSlide.visibleProperty(), false)),
                                 new KeyFrame(Duration.millis(1),
                                              new KeyValue(nextSlide.visibleProperty(), true)));
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.millis(400));
        tt1.setFromY(model().isForwardFlow() ? 700 : -700);
        tt1.setToY(model().isForwardFlow() ? 0 : 0);
        // .fromZ(-10)

        seq1.getChildren().addAll(ti1);

        this.subSlideTransition.getChildren().addAll(seq, seq1);

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
