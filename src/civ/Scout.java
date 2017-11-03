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
public class Scout extends Unit {

    private int a, b, count;

    public Scout() {

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
        return 2;
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
        String[] c = {"Attack", "Move", "Wait"};
        return c;
    }

}
