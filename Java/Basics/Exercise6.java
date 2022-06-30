package Assignment;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.*;


import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Excerise6 extends JPanel {
    private int size;
    public static void main(String[] args) {
        JFrame f = new JFrame("Board");
        Container c = new Excerise6(400);

        f.setContentPane(c);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.pack();
        f.setVisible(true);
    }

    public Excerise6(int size) {
        this.setPreferredSize(new Dimension(size, size));

        this.size = size;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tileSize = size / 8;
        // Fill gray .
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Color white blocks
        g.setColor(Color.WHITE);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == j % 2) {
                    g.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
                }
            }
        }
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        //Draws the line
        g.drawOval(100,100,50,50);

        //draws filled circle
        g.setColor(Color.blue);
        g.fillOval(100,100,50, 50);

    }







}




