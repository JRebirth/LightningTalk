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

package org.jrebirth.af.presentation.lightningtalk.ui.slides.fireworks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

/**
 * The Class FireworksPane.
 *
 * Layering (bottom → top): Mont Saint-Michel JPG, transparent fireworks canvas (sky + manual water
 * reflection), opaque PNG silhouette.
 *
 * <p>
 * JavaFX {@code Reflection} + node {@code BlendMode.ADD} rasterizes the canvas into an opaque black
 * buffer, hiding the photo. We keep the canvas fully cleared each frame (real alpha) and mirror
 * sparks into the water band ourselves.
 */
public class FireworksPane extends Pane {

    private static final double IMAGE_WIDTH = 1024;
    private static final double IMAGE_HEIGHT = 768;
    /** Horizon / bottom of sky band (matches original canvas height ratio). */
    private static final double HORIZON_RATIO = 0.54;
    /** Same as original Reflection topOpacity. */
    private static final double REFLECTION_OPACITY = 0.40;

    private final AnimationTimer timer;
    private final Canvas canvas;
    private final ImageView background;
    private final ImageView opaqueBackground;
    private final List<Particle> particles = new ArrayList<>();
    private final Paint[] colors;
    private int countDownTillNextFirework = 90;
    private double laidOutWidth = -1;
    private double laidOutHeight = -1;

    /**
     * Instantiates a new fireworks pane.
     *
     * @param backgroundImage the background image (JPG)
     * @param opaqueBackgroundImage the opaque silhouette mask (PNG)
     */
    public FireworksPane(final Image backgroundImage, final Image opaqueBackgroundImage) {
        setMinSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        setPrefSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        setMaxSize(IMAGE_WIDTH, IMAGE_HEIGHT);

        this.colors = new Paint[181];
        this.colors[0] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.WHITE),
                new Stop(0.2, Color.hsb(59, 0.38, 1)),
                new Stop(0.6, Color.hsb(59, 0.38, 1, 0.1)),
                new Stop(1, Color.hsb(59, 0.38, 1, 0)));
        for (int h = 0; h < 360; h += 2) {
            this.colors[1 + h / 2] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.WHITE),
                    new Stop(0.2, Color.hsb(h, 1, 1)),
                    new Stop(0.6, Color.hsb(h, 1, 1, 0.1)),
                    new Stop(1, Color.hsb(h, 1, 1, 0)));
        }

        // Full slide size so sky + water reflection share one transparent buffer.
        this.canvas = new Canvas(IMAGE_WIDTH, IMAGE_HEIGHT);
        this.canvas.setMouseTransparent(true);
        this.canvas.setCache(false);
        // Default SRC_OVER: transparent pixels let the JPG show through. (Node ADD + Reflection
        // was baking an opaque black slab over the photo.)

        this.background = new ImageView(backgroundImage);
        this.background.setSmooth(true);
        this.background.setPreserveRatio(false);
        this.background.setMouseTransparent(true);

        this.opaqueBackground = new ImageView(opaqueBackgroundImage);
        this.opaqueBackground.setSmooth(true);
        this.opaqueBackground.setPreserveRatio(false);
        this.opaqueBackground.setMouseTransparent(true);

        getChildren().addAll(this.background, this.canvas, this.opaqueBackground);

        this.timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                final GraphicsContext gc = FireworksPane.this.canvas.getGraphicsContext2D();
                final double cw = FireworksPane.this.canvas.getWidth();
                final double ch = FireworksPane.this.canvas.getHeight();
                gc.setGlobalAlpha(1.0);
                gc.setGlobalBlendMode(BlendMode.SRC_OVER);
                gc.clearRect(0, 0, cw, ch);
                drawFireworks(gc);
                if (FireworksPane.this.countDownTillNextFirework == 0) {
                    FireworksPane.this.countDownTillNextFirework = 50 + (int) (Math.random() * 70);
                    fireParticle();
                }
                FireworksPane.this.countDownTillNextFirework--;
            }
        };
    }

    public void start() {
        this.timer.start();
    }

    public void stop() {
        this.timer.stop();
    }

    @Override
    protected void layoutChildren() {
        final double w = getWidth() > 1 ? getWidth() : IMAGE_WIDTH;
        final double h = getHeight() > 1 ? getHeight() : IMAGE_HEIGHT;
        final int width = (int) IMAGE_WIDTH;
        final int height = (int) IMAGE_HEIGHT;
        final int x = (int) ((w - width) / 2);
        final int y = (int) ((h - height) / 2);

        this.background.relocate(x, y);
        this.background.setFitWidth(width);
        this.background.setFitHeight(height);

        this.opaqueBackground.relocate(x, y);
        this.opaqueBackground.setFitWidth(width);
        this.opaqueBackground.setFitHeight(height);

        this.canvas.relocate(x, y);
        if (width != this.laidOutWidth || height != this.laidOutHeight) {
            this.canvas.setWidth(width);
            this.canvas.setHeight(height);
            this.laidOutWidth = width;
            this.laidOutHeight = height;
        }
    }

    private void drawFireworks(final GraphicsContext gc) {
        final double horizon = this.canvas.getHeight() * HORIZON_RATIO;
        final Iterator<Particle> iter = this.particles.iterator();
        final List<Particle> newParticles = new ArrayList<>();
        while (iter.hasNext()) {
            final Particle firework = iter.next();
            if (firework.update()) {
                iter.remove();
                if (firework.shouldExplodeChildren) {
                    if (firework.size == 9) {
                        explodeCircle(firework, newParticles);
                    } else if (firework.size == 8) {
                        explodeSmallCircle(firework, newParticles);
                    }
                }
            }
            // Sky
            firework.draw(gc);
            // Water mirror (manual stand-in for Reflection effect)
            final double reflY = horizon + (horizon - firework.posY);
            final double reflLastY = horizon + (horizon - firework.lastPosY);
            if (reflY >= horizon && reflY <= this.canvas.getHeight()) {
                firework.draw(gc, firework.posX, reflY, firework.lastPosX, reflLastY, REFLECTION_OPACITY);
            }
        }
        this.particles.addAll(newParticles);
        gc.setGlobalAlpha(1.0);
    }

    private void fireParticle() {
        final double horizon = this.canvas.getHeight() * HORIZON_RATIO;
        this.particles.add(new Particle(
                this.canvas.getWidth() * 0.5, horizon + 10,
                Math.random() * 2.5 - 1.25, 0,
                0, 150 + Math.random() * 100,
                this.colors[0], 9,
                false, true, true));
    }

    private void explodeCircle(final Particle firework, final List<Particle> newParticles) {
        final int count = 20 + (int) (60 * Math.random());
        final boolean shouldExplodeChildren = Math.random() > 0.5;
        final double angle = Math.PI * 2 / count;
        final int color = (int) (Math.random() * this.colors.length);
        for (int i = count; i > 0; i--) {
            final double randomVelocity = 1.4 + Math.random() * 1.4;
            final double particleAngle = i * angle;
            newParticles.add(new Particle(
                    firework.posX, firework.posY,
                    Math.cos(particleAngle) * randomVelocity, Math.sin(particleAngle) * randomVelocity,
                    0, 0,
                    this.colors[color],
                    8,
                    true, shouldExplodeChildren, true));
        }
    }

    private void explodeSmallCircle(final Particle firework, final List<Particle> newParticles) {
        final double angle = Math.PI * 2 / 12;
        for (int count = 12; count > 0; count--) {
            final double randomVelocity = 0.7 + Math.random() * 0.7;
            final double particleAngle = count * angle;
            newParticles.add(new Particle(
                    firework.posX, firework.posY,
                    Math.cos(particleAngle) * randomVelocity, Math.sin(particleAngle) * randomVelocity,
                    0, 0,
                    firework.color,
                    4,
                    true, false, false));
        }
    }
}
