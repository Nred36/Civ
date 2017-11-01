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
 * @author naree1878
 */
public class City {
    private int x;
    private int y;
    private int c;
    public City(int a,int b, int d){
        x=a;
        y=b;    
        c=d;
    }
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    public int getc(){
        return c;
    }
    public Polygon settle(ArrayList<Polygon> hex, int rows) {
        int i=c;
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
