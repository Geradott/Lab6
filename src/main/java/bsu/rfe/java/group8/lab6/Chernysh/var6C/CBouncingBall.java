package bsu.rfe.java.group8.lab6.Chernysh.var6C;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class CBouncingBall implements Runnable {
    private static final int iMaxRadius = 40;
    private static final int iMinRadius = 3;
    private static final int iMaxSpeed = 15;
    
    private CField field;
    private int iRadius;
    private Color color;
    
    private double dX;
    private double dY;
    
    private int iSpeed;
    private double dSpeedX;
    private double dSpeedY;
    
    public CBouncingBall(CField field) {
        this.field = field;
        iRadius = new Double(Math.random() * (iMaxRadius - iMinRadius)).intValue() + iMinRadius;
        iSpeed = new Double(Math.round(5 * iMaxSpeed / iRadius)).intValue();
        if (iSpeed > iMaxSpeed) {
            iSpeed = iMaxSpeed;
        }
        double dAngle = Math.random() * 2 * Math.PI;
        dSpeedX = 3 * Math.cos(dAngle);
        dSpeedY = 3 * Math.sin(dAngle);
        color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        dX = Math.random() * (field.getSize().getWidth() - 2 * iRadius) + iRadius;
        dY = Math.random() * (field.getSize().getHeight() - 2 * iRadius) + iRadius;
        Thread thisThread = new Thread(this);
        thisThread.start();
    }
    
    public void run() {
        try {
            while(true) {
                field.canMove(this);
                if (dX + dSpeedX <= iRadius) {
                    dSpeedX = -dSpeedX;
                    dX = iRadius;
                } 
                else if (dX + dSpeedX >= field.getWidth() - iRadius) {
                    dSpeedY = -dSpeedY;
                    dX = new Double(field.getWidth() - iRadius).intValue();
                    }
                else if (dY + dSpeedY <= iRadius) {
                    dSpeedY = -dSpeedY;
                    dY = iRadius;
                }
                else if (dY + dSpeedY >= field.getWidth() - iRadius) {
                    dSpeedY = - dSpeedY;
                    dY = new Double(field.getHeight() - iRadius).intValue();
                }
                else {
                    dX += dSpeedX;
                    dY += dSpeedY;
                }
                Thread.sleep(16 - iSpeed);
            }
        }
        catch (InterruptedException ex) {
            
        }
    }
    
    public void paint(Graphics2D canvas) {
        canvas.setColor(color);
        canvas.setPaint(color);
        Ellipse2D.Double ball = new Ellipse2D.Double(dX - iRadius, dY - iRadius, 2 * iRadius, 2 * iRadius);
        canvas.draw(ball);
        canvas.fill(ball);
    }
}
