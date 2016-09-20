package View;


import Controlleur.IControlleur;
import Model.IModel;
import Model.Item;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Random;

import javax.swing.*;

public class Vue extends JComponent implements MouseListener {


    private static Rectangle2D.Double b1 =null;
    private static Rectangle2D.Double b2 =null;
    private  List<Color> listColor = new ArrayList<>();
    Graphics2D g2d;
    IModel model;
    IControlleur controller;
    Shape[] shapes;
    Color[] color;
    String mTexte;


    Shape shapeMemory = null;
    int memory;


    int clickX;
    int clickY;


    boolean isClic = false;


    //Coordonnées centre
    int x = 100;
    int y = 100;
    int w = 200;
    int h = 200;
    int pie = Arc2D.PIE;

    //Coordonnées Description
    int xd = 400;
    int yd = 50;
    int wd = 200;
    int hd = 120;

    int xl = 450;
    int yl = 200;
    int wl = 12;
    int hl = 12;

    /**
    int rx1 = 150;
    int rx2 = 290;
    int ry = 400;
    int rw = 20;
    int rh = 20;
    int ryRot = 450;**/


    public Vue() {

    }

    public Vue(IModel im, IControlleur ic) {
        mTexte = new String("Hello");
        model = im;
        controller = ic;
        initColor();
        init();
        initCamembert();
        addMouseListener(this);





    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension d = getSize();

        g2d = (Graphics2D) g;

        g2d.drawString(mTexte, 20, 34);


        b1 = new Rectangle2D.Double(120, 400, 35, 35);
        b2 = new Rectangle2D.Double(250, 400, 35, 35);
        g2d.setPaint(Color.CYAN);
        g2d.fill(b1);
        g2d.setPaint(Color.CYAN);
        g2d.fill(b2);
        g2d.setPaint(Color.WHITE);
        g2d.drawString("<", 150 + 5, 400 + 15);
        g2d.setPaint(Color.BLACK);
        g2d.setPaint(Color.WHITE);
        g2d.drawString(">", 290+ 5, 20 + 15);


        // Dessin du cadre description
        g2d.setPaint(Color.WHITE);
        g2d.fill(new Rectangle2D.Double(xd, yd, wd, hd));
        g2d.setPaint(Color.BLACK);
        g2d.drawString("Description", xd, yd - 5);
        List<Item> items = model.getItem();
        double total = 0;
        int i=0;
         for (Shape shape: shapes){
             g2d.setColor(listColor.get(i));
             i++;
             g2d.fill(shape);

         }


        g2d.setColor(Color.WHITE);
        g2d.fillOval(125, 175, 150, 150);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(140, 190, 120, 120);
        g2d.setColor(Color.white);
        g2d.drawString(String.valueOf(total), 180, 250);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 30));
    }

    @Override
    public void mouseClicked(MouseEvent c) {


        initCamembert();
        //this.controller.inClick();

        clickX = c.getX();
        clickY = c.getY();
        int i = 0;
        while (i < shapes.length && !shapes[i].contains(clickX, clickY)) {
            i++;
        }


        if(isClic == false) {

            System.out.println("false");
            Arc2D arc = (Arc2D) shapes[i];
            shapeMemory = arc; memory = i;
            Arc2D newArc = new Arc2D.Double(50, 100, 300, 300, arc.getAngleStart(), arc.getAngleExtent(), Arc2D.PIE);
            shapes[i] = newArc;
            isClic = true;
        }
        else if(isClic == true){
                System.out.println("true");
                shapes[memory] = shapeMemory;
                Arc2D arc = (Arc2D) shapes[i];
                Arc2D newArc = new Arc2D.Double(100, 150, 200, 200, arc.getAngleStart(), arc.getAngleExtent(), Arc2D.PIE);
                shapes[i] = newArc;
                isClic = false;
        }

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void initCamembert() { /** * Camembert */
        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        arc.setFrame(100, 150, 200, 200);
        double total = 0;
        for (Item it : this.model.getItem()) {
            total += it.getValeur();
        }
        double currentValue = 0;
        int angleDepart = 0;
        int i = 0;
        for (Item it : this.model.getItem()) {
            angleDepart = (int) (currentValue * 360 / total);
            int arcAngle = (int) (it.getValeur() * 360 / total);
            shapes[i] = new Arc2D.Double(100, 150, 200, 200, angleDepart, arcAngle, Arc2D.PIE);
            currentValue += it.getValeur();
            i++;
        }


    }

    public void init() { /** * Initialisation */
        shapes = new Shape[this.model.getItem().size()];
        color = new Color[this.model.getItem().size()];
        for (int i = 0; i < this.model.getItem().size(); i++) {
            color[i] = listColor.get(i);
        }
    }

    public void initColor(){
        Color c1 = new Color(173, 255, 47);
        Color c2 = new Color(0, 127, 213);
        Color c3 = new Color(127, 0, 213);
        Color c4 = new Color(66, 124, 1);
        Color c5 = new Color(42, 42, 42);
        Color c6 = new Color(255, 247, 200);


        listColor.add(c1);listColor.add(c2);
        listColor.add(c3);listColor.add(c4);
        listColor.add(c5);listColor.add(c6);
    }
}