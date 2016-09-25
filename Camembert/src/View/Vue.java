package View;


import Controlleur.IControlleur;
import Model.IModel;
import Model.Item;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class Vue extends JComponent implements MouseListener{


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
        g2d.setPaint(Color.WHITE);
        System.out.println("COULEUR!");
        g2d.fill(new Rectangle2D.Double(xd-70, yd, wd+50, hd));
        g2d.setPaint(Color.BLACK);

        //DESCRIPTION DU QUARTIER
        g2d.drawString("Informations", xd-70, yd - 5);
        g2d.drawString(titre, xd-65, yd+20);
        g2d.drawString(mTexte, xd-65, yd+57);
        g2d.drawString("Prix: "+String.valueOf(valeur)+" euros", xd-65, yd+100);




        List<Item> items = model.getItems();
        double total = 0;
        int i=0;
         for (Shape shape: shapes){
             g2d.setColor(listColor.get(i%50));
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

        boolean click=false;
        int clickX = c.getX();
        int clickY = c.getY();


        for (int j=0;j < shapes.length; j++){
            if(shapes[j].contains(clickX, clickY)){
                click=true;
                indice = j;
                controller.inClick(shapes[j], indice);
            }
        }
        if(this.b1.contains(clickX,clickY)){

            click=true;
            indice = (indice-1+shapes.length)%shapes.length;
            controller.inClick(shapes[indice], indice);

        }
        else if(this.b2.contains(clickX,clickY)){

            click=true;
            indice = (indice+1)%shapes.length;
            controller.inClick(shapes[indice], indice);
        }
        if(!click){

            valeur = 0;
            mTexte ="Selectionnez une portion !";
            titre  ="";
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


    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void initCamembert() { /** * Camembert */
        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        arc.setFrame(100, 150, 200, 200);
        double total = 0;
        for (Item it : this.model.getItems()) {
            total += it.getValeur();
        }
        double currentValue = 0;
        int angleDepart = 0;
        int i = 0;
        for (Item it : this.model.getItems()) {
            angleDepart = (int) (currentValue * 360 / total);
            int arcAngle = (int) (it.getValeur() * 360 / total);
            shapes[i] = new Arc2D.Double(100, 150, 200, 200, angleDepart, arcAngle, Arc2D.PIE);
            currentValue += it.getValeur();
            i++;
        }


    }

    public void init() { /** * Initialisation */
        shapes = new Shape[this.model.getItems().size()];
        color = new Color[this.model.getItems().size()];
        for (int i = 0; i < this.model.getItems().size(); i++) {
            color[i%50] = listColor.get(i%50);
        }
    }

    public void initColor(){

        Random rand = new Random();
        for(int i=0; i<50;i++){
            listColor.add(new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));

        }
    }

}