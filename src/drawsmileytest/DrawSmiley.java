/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawsmileytest;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author rkaune
 */
public class DrawSmiley extends JPanel {

    private double t = 1;

    /**
     * draws a target with a random colour for randomly placed oval and rectangle drawn
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 10; i++) {
            g.setColor(new Color((int) Math.ceil(Math.random() * 256 - 1), (int) Math.ceil(Math.random() * 256 - 1), (int) Math.ceil(Math.random() * 256 - 1)));

            if (Math.random() > 0.5) {
                g.fillRect((int) Math.ceil(Math.random() * 300), (int) Math.ceil(Math.random() * 300), (int) Math.ceil(Math.random() * 300), (int) Math.ceil(Math.random() *300));
            } else {
                g.fillOval((int) Math.ceil(Math.random() * 300), (int) Math.ceil(Math.random() * 300), (int) Math.ceil(Math.random() * 300), (int) Math.ceil(Math.random() *300));
            }
        }
    }
} // end class DrawSmiley
