/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawsmileytest;

import javax.swing.JFrame;

/**
 *
 * @author rkaune
 */
public class DrawSmileyTest {

    public static void main(String[] args) {
        DrawSmiley panel = new DrawSmiley();        
        JFrame application = new JFrame();
        application.setResizable(false);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(panel);
        application.setSize(520, 520);
        application.setVisible(true);
        application.setLocationRelativeTo(null);
    }
} // end class DrawSmileyTest
