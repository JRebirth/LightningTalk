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

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

// TODO: Auto-generated Javadoc
/**
 * A Simple Particle that draws its self as a circle.
 */
public class Particle {

    /** The Constant GRAVITY. */
    private static final double GRAVITY = 0.06;
    // properties for animation
    // and colouring
    /** The alpha. */
    double alpha;

    /** The easing. */
    final double easing;

    /** The fade. */
    double fade;

    /** The pos x. */
    double posX;

    /** The pos y. */
    double posY;

    /** The vel x. */
    double velX;

    /** The vel y. */
    double velY;

    /** The target x. */
    final double targetX;

    /** The target y. */
    final double targetY;

    /** The color. */
    final Paint color;

    /** The size. */
    final int size;

    /** The use physics. */
    final boolean usePhysics;

    /** The should explode children. */
    final boolean shouldExplodeChildren;

    /** The has tail. */
    final boolean hasTail;

    /** The last pos x. */
    double lastPosX;

    /** The last pos y. */
    double lastPosY;

    /**
     * Instantiates a new particle.
     * 
     * @param posX the pos x
     * @param posY the pos y
     * @param velX the vel x
     * @param velY the vel y
     * @param targetX the target x
     * @param targetY the target y
     * @param color the color
     * @param size the size
     * @param usePhysics the use physics
     * @param shouldExplodeChildren the should explode children
     * @param hasTail the has tail
     */
    public Particle(final double posX, final double posY, final double velX, final double velY, final double targetX, final double targetY,
            final Paint color, final int size, final boolean usePhysics, final boolean shouldExplodeChildren, final boolean hasTail) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.color = color;
        this.size = size;
        this.usePhysics = usePhysics;
        this.shouldExplodeChildren = shouldExplodeChildren;
        this.hasTail = hasTail;
        this.alpha = 1;
        this.easing = Math.random() * 0.02;
        this.fade = Math.random() * 0.1;
    }

    /**
     * Update.
     * 
     * @return true, if successful
     */
    public boolean update() {
        this.lastPosX = this.posX;
        this.lastPosY = this.posY;
        if (this.usePhysics) { // on way down
            this.velY += GRAVITY;
            this.posY += this.velY;
            this.alpha -= this.fade; // fade out particle
        } else { // on way up
            final double distance = this.targetY - this.posY;
            // ease the position
            this.posY += distance * (0.03 + this.easing);
            // cap to 1
            this.alpha = Math.min(distance * distance * 0.00005, 1);
        }
        this.posX += this.velX;
        return this.alpha < 0.005;
    }

    /**
     * Draw.
     * 
     * @param context the context
     */
    public void draw(final GraphicsContext context) {
        final double x = Math.round(this.posX);
        final double y = Math.round(this.posY);
        final double xVel = (x - this.lastPosX) * -5;
        final double yVel = (y - this.lastPosY) * -5;
        // set the opacity for all drawing of this particle
        context.setGlobalAlpha(Math.random() * this.alpha);
        // draw particle
        context.setFill(this.color);
        context.fillOval(x - this.size, y - this.size, this.size + this.size, this.size + this.size);
        // draw the arrow triangle from where we were to where we are now
        if (this.hasTail) {
            context.setFill(Color.rgb(255, 255, 255, 0.3));
            context.fillPolygon(new double[] { this.posX + 1.5, this.posX + xVel, this.posX - 1.5 },
                    new double[] { this.posY, this.posY + yVel, this.posY }, 3);
        }
    }
}