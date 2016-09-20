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
import java.util.*;
import java.util.List;

import javax.swing.*;

public class Vue extends JComponent implements MouseListener, Observer {


    private  Rectangle2D.Double b1 =null;
    private  Rectangle2D.Double b2 =null;
    private  List<Color> listColor = new ArrayList<>();
    private Graphics2D g2d;
    public  IModel model;
    private IControlleur controller;
    public Shape[] shapes;
    private Color[] color;



    public String mTexte = "";
    public double valeur;
    public String amount = "";
    public String titre  = "";

    public boolean isClick;
    private int  indice=0;

    //Coordonnées centre
    int x = 100;
    int y = 100;
    int w = 200;
    int h = 200;
    int pie = Arc2D.PIE;

    //Coordonnées Description
    private int xd = 400;
    private int yd = 50;
    private int wd = 200;
    private int hd = 120;

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
        mTexte = "Selectionnez une portion ! ";
        model = im;
        controller = ic;
        initColor();
        init();
        initCamembert();
        addMouseListener(this);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2d = (Graphics2D) g;


        //Création boutons déplacement
        b1 = new Rectangle2D.Double(120, 400, 35, 35);
        b2 = new Rectangle2D.Double(250, 400, 35, 35);
        g2d.setPaint(Color.BLUE);
        g2d.fill(b1);
        g2d.setPaint(Color.BLUE);
        g2d.fill(b2);
        g2d.setPaint(Color.WHITE);
        g2d.drawString("<", 130, 420);
        g2d.setPaint(Color.BLACK);
        g2d.setPaint(Color.WHITE);
        g2d.drawString(">", 260, 420);


        // Dessin du cadre description
        g2d.setPaint(Color.BLACK);
        g2d.draw(new Rectangle2D.Double(xd, yd, wd+50, hd));
        g2d.setPaint(Color.BLACK);

        //DESCRIPTION DU QUARTIER
        g2d.drawString("Informations", xd, yd - 5);
        g2d.drawString(titre, xd+3, yd+20);
        g2d.drawString(mTexte, xd+3, yd+57);
        g2d.drawString("Prix: "+String.valueOf(valeur)+" euros", xd+3, yd+100);




        List<Item> items = model.getItem();
        double total = 0;
        int i=0;
         for (Shape shape: shapes){
             g2d.setColor(listColor.get(i));
             i++;
             g2d.fill(shape);
         }

         for(Item item:items){
             total+=item.getValeur();
         }

        g2d.setColor(Color.WHITE);
        g2d.fillOval(125, 175, 150, 150);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(140, 190, 120, 120);
        g2d.setColor(Color.white);
        g2d.drawString(String.valueOf(total), 180, 250);
        g2d.setFont(new Font("Arial",Font.CENTER_BASELINE, 21));

    }

    @Override
    public void mouseClicked(MouseEvent c) {

        initCamembert();

        int clickX = c.getX();
        int clickY = c.getY();

        valeur = 0.0;
        mTexte = "Desciption:";
        titre  = "Titre:";

        for (int j=0;j < shapes.length; j++){
            if(shapes[j].contains(clickX, clickY)){
                indice = j;
                controller.inClick(shapes[j], indice);
            }
        }

        if(this.b1.contains(clickX,clickY)){
            indice = (indice-1+shapes.length)%shapes.length;
            controller.inClick(shapes[indice], indice);

        }

        if(this.b2.contains(clickX,clickY)){

            indice = (indice+1)%shapes.length;
            controller.inClick(shapes[indice], indice);
        }

        repaint();

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {


    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
       //System.out.println(arg0.getX() );
      // System.out.println(arg0.getY());

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


    @Override
    public void update(Observable observable, Object o) {
        repaint();
    }
}

/**arc = (Arc2D) shapes[j];
 shapes[j] = new Arc2D.Double(50, 100, 300, 300, arc.getAngleStart(), arc.getAngleExtent(), Arc2D.PIE);
 **/
/**valeur = model.getItem(indice).getValeur();
 mTexte ="Description: " + model.getItem(indice).getDescription();
 titre  ="Titre: "+  model.getItem(indice).getTitre();**/


/**
 Arc2D newArc = (Arc2D) shapes[indice];
 shapes[indice] = new Arc2D.Double(50, 100, 300, 300, newArc.getAngleStart(), newArc.getAngleExtent(), Arc2D.PIE);
 **/
/**valeur = model.getItem(indice).getValeur();
 mTexte ="Description: " + model.getItem(indice).getDescription();
 titre  ="Titre: "+  model.getItem(indice).getTitre();**/

/**
 Arc2D newArc = (Arc2D) shapes[indice];
 shapes[indice] = new Arc2D.Double(50, 100, 300, 300, newArc.getAngleStart(), newArc.getAngleExtent(), Arc2D.PIE);


 valeur = model.getItem(indice).getValeur();
 mTexte ="Description: " + model.getItem(indice).getDescription();
 titre  ="Titre: "+  model.getItem(indice).getTitre();**/