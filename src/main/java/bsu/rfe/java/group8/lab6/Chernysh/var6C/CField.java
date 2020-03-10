package bsu.rfe.java.group8.lab6.Chernysh.var6C;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CField extends JPanel {
    private boolean paused;
    private ArrayList<CBouncingBall> balls = new ArrayList<CBouncingBall>(10);
    private Timer repaintTimer = new Timer (10, new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            repaint();
        }
    });
    
    public CField() {
        setBackground(Color.WHITE);
        repaintTimer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for (CBouncingBall ball: balls) {
            ball.paint(canvas);
        }
    }
    
    public void addBall() {
        balls.add(new CBouncingBall(this));
    }
    
    public synchronized void pause() {
        paused = true;
    }
    
    public synchronized void resume() {
        paused = false;
        notifyAll();
    }
    
    public synchronized void canMove(CBouncingBall ball) throws InterruptedException {
        if (paused) {
            wait();
        }
    }
}
