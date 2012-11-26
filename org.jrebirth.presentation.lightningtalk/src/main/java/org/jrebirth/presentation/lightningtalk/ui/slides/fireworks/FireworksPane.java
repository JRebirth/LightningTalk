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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

// TODO: Auto-generated Javadoc
/**
 * The Class FireworksPane.
 */
public class FireworksPane extends Pane {

    /** The timer. */
    private final AnimationTimer timer;
    
    /** The canvas. */
    private final Canvas canvas;
    
    /** The background. */
    private final ImageView background;
    
    /** The opaque background. */
    private final ImageView opaqueBackground;
    
    /** The particles. */
    private final List<Particle> particles = new ArrayList<Particle>();
    
    /** The colors. */
    private final Paint[] colors;
    
    /** The count down till next firework. */
    private int countDownTillNextFirework = 40;

    /**
     * Instantiates a new fireworks pane.
     * 
     * @param backgroundImage the background image
     * @param opaqueBackgroundImage the opaque background image
     */
    public FireworksPane(final Image backgroundImage, final Image opaqueBackgroundImage) {
        // create a color palette of 180 colors
        this.colors = new Paint[181];
        this.colors[0] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.WHITE),
                new Stop(0.2, Color.hsb(59, 0.38, 1)),
                new Stop(0.6, Color.hsb(59, 0.38, 1, 0.1)),
                new Stop(1, Color.hsb(59, 0.38, 1, 0))
                );
        for (int h = 0; h < 360; h += 2) {
            this.colors[1 + h / 2] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.WHITE),
                    new Stop(0.2, Color.hsb(h, 1, 1)),
                    new Stop(0.6, Color.hsb(h, 1, 1, 0.1)),
                    new Stop(1, Color.hsb(h, 1, 1, 0))
                    );
        }
        // create canvas
        this.canvas = new Canvas(1024, 500);

        this.canvas.setBlendMode(BlendMode.ADD);
        this.canvas.setEffect(new Reflection(0, 1.0, 0.40, 0));
        this.background = new ImageView(backgroundImage);

        this.opaqueBackground = new ImageView(opaqueBackgroundImage);
        getChildren().addAll(this.background, this.canvas, this.opaqueBackground);
        // create animation timer that will be called every frame
        // final AnimationTimer timer = new AnimationTimer() {
        this.timer = new AnimationTimer() {

            @Override
            public void handle(final long now) {
                final GraphicsContext gc = FireworksPane.this.canvas.getGraphicsContext2D();
                // clear area with transparent black
                gc.setFill(Color.rgb(0, 0, 0, 0.2));
                gc.fillRect(0, 0, 1024, 708);
                // draw fireworks
                drawFireworks(gc);
                // countdown to launching the next firework
                if (FireworksPane.this.countDownTillNextFirework == 0) {
                    FireworksPane.this.countDownTillNextFirework = 10 + (int) (Math.random() * 30);
                    fireParticle();
                }
                FireworksPane.this.countDownTillNextFirework--;
            }
        };
    }

    /**
     * Start.
     */
    public void start() {
        this.timer.start();
    }

    /**
     * Stop.
     */
    public void stop() {
        this.timer.stop();
    }

    /**
     * Make resizable and keep background image proportions and center.
     */
    @Override
    protected void layoutChildren() {
        // final double w = 480.0;
        // final double h = 360.0;
        final double w = getWidth();
        final double h = getHeight();
        final double scale = 1;// Math.min(w / 1024d, h / 708d);
        final int width = (int) (1024 * scale);
        final int height = (int) (768 * scale);
        final int x = (int) ((w - width) / 2);
        final int y = (int) ((h - height) / 2);
        this.background.relocate(x, y);
        this.background.setFitWidth(width);
        this.background.setFitHeight(height);
        this.opaqueBackground.relocate(x, y);
        this.opaqueBackground.setFitWidth(width);
        this.opaqueBackground.setFitHeight(height);
        this.canvas.relocate(x, y);
        this.canvas.setWidth(width);
        this.canvas.setHeight(height * 0.54);
    }

    /**
     * Draw fireworks.
     * 
     * @param gc the gc
     */
    private void drawFireworks(final GraphicsContext gc) {
        final Iterator<Particle> iter = this.particles.iterator();
        final List<Particle> newParticles = new ArrayList<Particle>();
        while (iter.hasNext()) {
            final Particle firework = iter.next();
            // if the update returns true then particle has expired
            if (firework.update()) {
                // remove particle from those drawn
                iter.remove();
                // check if it should be exploded
                if (firework.shouldExplodeChildren) {
                    if (firework.size == 9) {
                        explodeCircle(firework, newParticles);
                    } else if (firework.size == 8) {
                        explodeSmallCircle(firework, newParticles);
                    }
                }
            }
            firework.draw(gc);
        }
        this.particles.addAll(newParticles);
    }

    /**
     * Fire particle.
     */
    private void fireParticle() {
        this.particles.add(new Particle(
                this.canvas.getWidth() * 0.5, this.canvas.getHeight() + 10,
                Math.random() * 5 - 2.5, 0,
                0, 150 + Math.random() * 100,
                this.colors[0], 9,
                false, true, true));
    }

    /**
     * Explode circle.
     * 
     * @param firework the firework
     * @param newParticles the new particles
     */
    private void explodeCircle(final Particle firework, final List<Particle> newParticles) {
        final int count = 20 + (int) (60 * Math.random());
        final boolean shouldExplodeChildren = Math.random() > 0.5;
        final double angle = Math.PI * 2 / count;
        final int color = (int) (Math.random() * this.colors.length);
        for (int i = count; i > 0; i--) {
            final double randomVelocity = 4 + Math.random() * 4;
            final double particleAngle = i * angle;
            newParticles.add(
                    new Particle(
                            firework.posX, firework.posY,
                            Math.cos(particleAngle) * randomVelocity, Math.sin(particleAngle) * randomVelocity,
                            0, 0,
                            this.colors[color],
                            8,
                            true, shouldExplodeChildren, true));
        }
    }

    /**
     * Explode small circle.
     * 
     * @param firework the firework
     * @param newParticles the new particles
     */
    private void explodeSmallCircle(final Particle firework, final List<Particle> newParticles) {
        final double angle = Math.PI * 2 / 12;
        for (int count = 12; count > 0; count--) {
            final double randomVelocity = 2 + Math.random() * 2;
            final double particleAngle = count * angle;
            newParticles.add(
                    new Particle(
                            firework.posX, firework.posY,
                            Math.cos(particleAngle) * randomVelocity, Math.sin(particleAngle) * randomVelocity,
                            0, 0,
                            firework.color,
                            4,
                            true, false, false));
        }
    }
}