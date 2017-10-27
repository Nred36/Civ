/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawsmileytest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javafx.scene.shape.Line;
import javax.swing.JPanel;

/**
 *
 * @author rkaune
 */
public class DrawSmiley extends JPanel implements MouseListener, KeyListener, MouseMotionListener {

    ArrayList<Polygon> hex = new ArrayList<Polygon>();
    boolean[] pres = {false, false, false, false};
    int mx, my;

    public DrawSmiley() {
        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
    }

    public void Hexagon(int c, int l, int size) {
        int x = c * 12 * size, y;
        if (c % 2 == 0) {
            y = (int) (8 * size * (Math.sqrt(3.0) / 2));
        } else {
            y = 0;
        }
        //y+=(int) Math.ceil((double) l / 2);
        y += (int) (16 * size * l * (Math.sqrt(3.0) / 2));
        Polygon p = new Polygon();
        p.addPoint(4 * size + x, y);
        p.addPoint(12 * size + x, y);
        p.addPoint(16 * size + x, y + (int) Math.ceil(8 * size * (Math.sqrt(3.0) / 2)));
        p.addPoint(12 * size + x, y + (int) Math.ceil(16 * size * (Math.sqrt(3.0) / 2)));
        p.addPoint(4 * size + x, y + (int) Math.ceil(16 * size * (Math.sqrt(3.0) / 2)));
        p.addPoint(x, y + (int) Math.ceil(8 * size * (Math.sqrt(3.0) / 2)));
        hex.add(p);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int curr = 0;
        for (int l = 0; l < 9; l++) {

            for (int i = 0; i < 14; i++) {
                Hexagon(i, l, 4);
                g.drawPolygon(hex.get(curr));
                g.drawString(curr + "", hex.get(curr).xpoints[0] + 8, hex.get(curr++).ypoints[0] + 20);
            }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Rectangle m = new Rectangle(mx, my, 1, 1);
        for (int i = 0; i < hex.size(); i++) {
            if (hex.get(i).intersects(m)) {
                hex.get(i).translate(0, 1);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            pres[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            pres[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            pres[2] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            pres[3] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            pres[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            pres[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            pres[2] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            pres[3] = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
    }
}
