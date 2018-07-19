/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.controller;

import life.view.Playfield;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
/**
 *
 * @author Alex Meier
 */
public class MouseController implements MouseListener{
    
    private final Playfield playfield;
    
    public MouseController(Playfield playfield) {
        this.playfield = playfield;
    }
    
    public void mousePressed(MouseEvent e) {
       //transform to a grid point then toggle cell at that location
       Point location = e.getPoint();
       System.out.println(location);
       location = this.playfield.getSquareGrid().convertToGrid(location);
       this.playfield.getLifeGrid().toggleCell((int)location.getX(), (int)location.getY());
       this.playfield.getFrame().repaint();
       System.out.println(location);
    }
    
    
    //currently unused
    public void mouseReleased(MouseEvent e) {
      
    }

    public void mouseEntered(MouseEvent e) {
       
    }

    public void mouseExited(MouseEvent e) {
       
    }

    public void mouseClicked(MouseEvent e) {
        
    }
    
}
