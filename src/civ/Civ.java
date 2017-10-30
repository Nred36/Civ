/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.scene.shape.Line;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rkaune
 */
public class Civ extends JPanel implements MouseListener, KeyListener, MouseMotionListener, MouseWheelListener {

    ArrayList<Polygon> hex = new ArrayList<Polygon>();
    ArrayList<Polygon> borders = new ArrayList<Polygon>();
    boolean[] pres = {false, false, false, false};
    int mx, my, zoom = 3, mode = 0, temp = 0;
    Unit[] units = new Unit[20];
    int numUn = 0, select = 99, turn, rows = 14;
    int[][][] grid = new int[rows][9][4];
    double research, gold, happiness;

    public static void main(String[] args) {
        Civ panel = new Civ();
        JFrame application = new JFrame();
        application.setResizable(false);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(panel);
        application.setSize(520, 560);
        application.setVisible(true);
        application.setLocationRelativeTo(null);
    }

    public Civ() {
        try {
            FileReader fr = new FileReader("map.txt");
            BufferedReader br = new BufferedReader(fr); //reads map from text file
            for (int i = 0; i < rows; i++) {
                for (int x = 0; x < 9; x++) {
                    grid[i][x][0] = Integer.parseInt(br.readLine());
                }
            }
            fr.close();
            br.close();
        } catch (IOException a) {
            System.out.println("Couldn't Load");
        }

        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    public void Hexagon(int c, int l, int size) {
        int x = c * 12 * size, y = 30;
        if (c % 2 == 0) {
            y += (int) (8 * size * (Math.sqrt(3.0) / 2));
        } else {
            y += 0;
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
        Graphics2D dr = (Graphics2D) g;
        super.paintComponent(g);
        int curr = 0;
        for (int l = 0; l < 9; l++) {
            for (int i = 0; i < rows; i++) {

                Hexagon(i, l, zoom);

                dr.setColor(col(grid[i][l][0]));

                dr.fillPolygon(hex.get(curr));
                dr.setColor(Color.black);
                dr.drawPolygon(hex.get(curr));

                for (int d = 1; d < 4; d++) {
                    if (grid[i][l][d] == 1) {
                        dr.setColor(col(units[numUn - 1].num() + 5));
                        dr.fillRect(hex.get(curr).xpoints[0] + 3, hex.get(curr).ypoints[0] + 20, 12, 12);

                    }
                }
                //dr.drawString(curr + "", hex.get(curr).xpoints[0] +3, hex.get(curr).ypoints[0] + 20);
                curr++;
            }

            if (mode == 1) {
                units[numUn] = new Settler();
                temp = units[numUn].num();
                dr.setColor(col(units[numUn].num() + 5));
                dr.fillRect(mx - 6, my - 6, 12, 12);
            } else if (mode == 7) {
                dr.setColor(col(temp));
                dr.fillRect(mx - 6, my - 6, 12, 12);
            }
            dr.setColor(new Color(112, 255, 255));
            dr.setStroke(new BasicStroke(3));
            for (int i = 0; i < borders.size(); i++) {
                dr.drawPolygon(borders.get(i));
            }
            if (numUn > 0) {
                dr.setColor(new Color(64, 255, 255));
                dr.drawRect(select * 25 + 9, 429, 16, 16);//selected unit
                dr.drawRect(select * 25 + 9, 429, 16, 16);//selected unit
                dr.drawRect(hex.get(units[select].c()).xpoints[0] + 2, hex.get(units[select].c()).ypoints[0] + 19, 13, 13);
                dr.setStroke(new BasicStroke(1));
                if (numUn > 0) {
                    for (int i = 0; i < numUn; i++) {
                        dr.setColor(col(units[numUn - 1].num() + 5));
                        dr.fillRect(i * 25 + 10, 430, 15, 15);//units
                    }
                }
            }
            dr.setStroke(new BasicStroke(1));
            dr.setColor(Color.black);
            dr.drawRect(0, 0, 513, 25);//top bar
            dr.setColor(Color.blue);
            dr.fillOval(5, 5, 15, 15);
            dr.drawString(research + "", 24, 18);
            dr.setColor(Color.yellow);
            dr.fillOval(65, 5, 15, 15);
            dr.drawString(gold + "", 84, 18);
            dr.setColor(Color.orange);
            dr.fillOval(125, 5, 15, 15);
            dr.drawString(happiness + "", 144, 18);

            dr.drawString("Turn " + turn, 464, 18);

            dr.setColor(Color.black);
            dr.drawLine(0, 424, 524, 424);//split line 
            dr.drawRect(460, 450, 40, 15);//next turn
            dr.drawRect(10, 450, 60, 60);

            if (mode == 2) {
                dr.setColor(Color.white);
                dr.fillRect(50, 50, 420, 330);
                dr.setColor(Color.black);
                dr.drawString("CITY NAME", 60, 72);
                dr.drawLine(50, 80, 470, 80);

                dr.drawString("PRODUCTION", 60, 100);
                dr.drawRect(60, 105, 160, 264);

                dr.drawString("QUEUE", 230, 100);
                dr.drawRect(230, 105, 140, 264);

                dr.drawString("FOCUS", 385, 100);
                dr.drawRect(380, 105, 80, 264);
            } else if (mode == 3) {
                dr.setColor(Color.white);
                dr.fillRect(50, 50, 420, 330);
                dr.setColor(Color.black);
                dr.drawString("RESEARCH", 60, 72);
                dr.drawLine(50, 80, 470, 80);

            }

            repaint();
            requestFocus();
            setFocusTraversalKeysEnabled(false);
        }
    }

    public Color col(int i) {
        Color c;
        switch (i) {
            case (0):
                c = new Color(12, 12, 33);
                break;
            case (1):
                c = new Color(12, 112, 140);
                break;
            case (2):
                c = new Color(142, 212, 233);
                break;
            case (3):
                c = new Color(182, 192, 133);
                break;
            case (4):
                c = new Color(122, 92, 13);
                break;
            case (5):
                c = new Color(1, 72, 73);
                break;
            case (6):
                c = new Color(255, 72, 73);
                break;
            default:
                c = new Color(250, 250, 250);
                break;
        }

        return c;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Rectangle m = new Rectangle(mx, my, 1, 1);
        for (int i = 0; i < hex.size(); i++) {
            if (hex.get(i).intersects(m)) {
                if (mode == 0) {
                    if (m.intersects(hex.get(units[select].c()).xpoints[0] + 2, hex.get(units[select].c()).ypoints[0] + 19, 13, 13)) {
                        mode = 2;
                    }
                } else if (mode == 1) {
                    int t = i / rows;
                    grid[i - (rows * t)][t][1] = temp;
                    units[numUn].pos(i - (rows * t), t, i);
                    select = numUn;
                    Polygon p = new Polygon();
                    if (i % 2 != 0) {
                        p.addPoint(hex.get(i - (rows + 1)).xpoints[0], hex.get(i - (rows + 1)).ypoints[0]);
                        p.addPoint(hex.get(i - (rows + 1)).xpoints[1], hex.get(i - (rows + 1)).ypoints[1]);
                        p.addPoint(hex.get(i - rows).xpoints[0], hex.get(i - rows).ypoints[0]);
                        p.addPoint(hex.get(i - rows).xpoints[1], hex.get(i - rows).ypoints[1]);
                        p.addPoint(hex.get(i - (rows - 1)).xpoints[0], hex.get(i - rows - 1).ypoints[0]);
                        p.addPoint(hex.get(i - (rows - 1)).xpoints[0], hex.get(i - (rows - 1)).ypoints[0]);
                        p.addPoint(hex.get(i - (rows - 1)).xpoints[1], hex.get(i - (rows - 1)).ypoints[1]);
                        p.addPoint(hex.get(i - (rows - 1)).xpoints[2], hex.get(i - (rows - 1)).ypoints[2]);
                        p.addPoint(hex.get(i + 1).xpoints[1], hex.get(i + 1).ypoints[1]);
                        p.addPoint(hex.get(i + 1).xpoints[2], hex.get(i + 1).ypoints[2]);
                        p.addPoint(hex.get(i + 1).xpoints[3], hex.get(i + 1).ypoints[3]);
                        p.addPoint(hex.get(i + 1).xpoints[4], hex.get(i + 1).ypoints[4]);
                        p.addPoint(hex.get(i + rows).xpoints[3], hex.get(i + rows).ypoints[3]);
                        p.addPoint(hex.get(i + rows).xpoints[4], hex.get(i + rows).ypoints[4]);
                        p.addPoint(hex.get(i + rows).xpoints[5], hex.get(i + rows).ypoints[5]);
                        p.addPoint(hex.get(i - 1).xpoints[3], hex.get(i - 1).ypoints[3]);
                        p.addPoint(hex.get(i - 1).xpoints[4], hex.get(i - 1).ypoints[4]);
                        p.addPoint(hex.get(i - 1).xpoints[5], hex.get(i - 1).ypoints[5]);
                        p.addPoint(hex.get(i - 1).xpoints[0], hex.get(i - 1).ypoints[0]);
                        p.addPoint(hex.get(i - (rows + 1)).xpoints[4], hex.get(i - (rows + 1)).ypoints[4]);
                        p.addPoint(hex.get(i - (rows + 1)).xpoints[5], hex.get(i - (rows + 1)).ypoints[5]);
                    } else {
                        p.addPoint(hex.get(i - 1).xpoints[0], hex.get(i - 1).ypoints[0]);
                        p.addPoint(hex.get(i - 1).xpoints[1], hex.get(i - 1).ypoints[1]);
                        p.addPoint(hex.get(i - rows).xpoints[0], hex.get(i - rows).ypoints[0]);
                        p.addPoint(hex.get(i - rows).xpoints[1], hex.get(i - rows).ypoints[1]);
                        p.addPoint(hex.get(i - rows).xpoints[2], hex.get(i - rows).ypoints[2]);
                        p.addPoint(hex.get(i + 1).xpoints[1], hex.get(i + 1).ypoints[1]);
                        p.addPoint(hex.get(i + 1).xpoints[2], hex.get(i + 1).ypoints[2]);
                        p.addPoint(hex.get(i + 1).xpoints[3], hex.get(i + 1).ypoints[3]);
                        p.addPoint(hex.get(i + 1 + rows).xpoints[2], hex.get(i + 1 + rows).ypoints[2]);
                        p.addPoint(hex.get(i + 1 + rows).xpoints[3], hex.get(i + 1 + rows).ypoints[3]);
                        p.addPoint(hex.get(i + 1 + rows).xpoints[4], hex.get(i + 1 + rows).ypoints[4]);
                        p.addPoint(hex.get(i + rows).xpoints[3], hex.get(i + rows).ypoints[3]);
                        p.addPoint(hex.get(i + rows).xpoints[4], hex.get(i + rows).ypoints[4]);
                        p.addPoint(hex.get(i + rows).xpoints[5], hex.get(i + rows).ypoints[5]);
                        p.addPoint(hex.get(i + (rows - 1)).xpoints[4], hex.get(i + (rows - 1)).ypoints[4]);
                        p.addPoint(hex.get(i + (rows - 1)).xpoints[5], hex.get(i + (rows - 1)).ypoints[5]);
                        p.addPoint(hex.get(i + (rows - 1)).xpoints[0], hex.get(i + (rows - 1)).ypoints[0]);
                        p.addPoint(hex.get(i - 1).xpoints[5], hex.get(i - 1).ypoints[5]);
                    }
                    borders.add(p);
                    numUn++;
                    i = 9999;
                    mode = 0;
                    break;
                } else if (mode == 7) {
                    int t = i / rows;
                    grid[i - (rows * t)][t][0] = temp;
                    i = 9999;
                    break;
                }
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
        if (e.getKeyCode() == KeyEvent.VK_T) {
            if (mode != 3) {
                mode = 3;
            } else {
                mode = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F1) {
            if (mode != 1) {
                mode = 1;
            } else {
                mode = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F2) {
            if (mode != 2) {
                mode = 2;
            } else {
                mode = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F7) {
            if (mode != 7) {
                mode = 7;

            } else {
                mode = 0;
                try {
                    FileWriter fw = new FileWriter("map.txt");
                    PrintWriter pw = new PrintWriter(fw);
                    for (int i = 0; i < rows; i++) {
                        for (int x = 0; x < 9; x++) {
                            pw.println(grid[i][x]);
                        }
                    }
                    pw.close();
                    fw.close();
                } catch (IOException a) {
                    System.out.println("ERROR");
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
            if (numUn - 1 > select) {
                select++;
            } else {
                select = 0;
            }
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

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (mode == 0) {
            if (zoom < 5 && e.getPreciseWheelRotation() < 0) {
                zoom++;
            } else if (zoom > 2 && e.getPreciseWheelRotation() > 0) {
                zoom--;
            }
        } else if (mode == 7) {
            if (e.getPreciseWheelRotation() < 0) {
                temp++;
            } else if (e.getPreciseWheelRotation() > 0) {
                temp--;
            }
        }
    }
}
