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
abstract public class Unit {

    abstract double hp();

    abstract double speed();

    abstract int num();

    abstract int x();

    abstract int y();

    abstract int c();

    public Unit() {

    }

    abstract public String[] commands();

    abstract Polygon city(int i, ArrayList<Polygon> hex, int rows);

    abstract public void pos(int x, int y, int c);

}
