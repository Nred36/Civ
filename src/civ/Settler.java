/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ;

import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Nred
 */
public class Settler extends Unit {

    private int a, b, count;

    public Settler() {

    }

    @Override
    double hp() {
        return 10;
    }

    @Override
    double speed() {
        return 2;
    }

    @Override
    int num() {
        return 1;
    }

    @Override
    public void pos(int x, int y, int c) {
        a = x;
        b = y;
        count = c;
    }

    @Override
    int x() {
        return a;
    }

    @Override
    int y() {
        return b;
    }

    @Override
    int c() {
        return count;
    }

    @Override
    public String[] commands() {
        String[] c = {"Settle", "Move", "Wait"};
        return c;
    }

    public Polygon city(int i, ArrayList<Polygon> hex, int rows) {
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
        return p;
    }
}
