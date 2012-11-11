package org.jrebirth.presentation.lightningtalk.ui.slides.fireworks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * A Simple Particle that draws its self as a circle.
 */
public class Particle {

    private static final double GRAVITY = 0.06;
    // properties for animation
    // and colouring
    double alpha;
    final double easing;
    double fade;
    double posX;
    double posY;
    double velX;
    double velY;
    final double targetX;
    final double targetY;
    final Paint color;
    final int size;
    final boolean usePhysics;
    final boolean shouldExplodeChildren;
    final boolean hasTail;
    double lastPosX;
    double lastPosY;

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