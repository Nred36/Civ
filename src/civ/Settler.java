/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civ;

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
}
