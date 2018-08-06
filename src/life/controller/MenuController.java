/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.controller;

import life.view.Playfield;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 *
 * @author Alex Meier
 */
public class MenuController implements ActionListener{
    private Playfield playfield;
    
    
    public MenuController(Playfield playfield) {
        this.playfield = playfield; 
    }
    
    public void actionPerformed(ActionEvent e) {
        Object item = e.getSource();
        if(item == this.playfield.getMenuItem(Playfield.menuItems.SIMULATION_RESET_RANDOM)) {
            //reset with a random LifeGrid
            this.playfield.getLifeGrid().randPopulate();
        }else if(item == this.playfield.getMenuItem(Playfield.menuItems.SIMULATION_RESET_LINE)) {
            //reset with line through the middle of grid
            this.playfield.getLifeGrid().linePopulate();
        }else if(item == this.playfield.getMenuItem(Playfield.menuItems.SIMULATION_PLAY)) {
            this.playfield.getLifeGrid().setPauseFlag(false);
        }else if(item == this.playfield.getMenuItem(Playfield.menuItems.SIMULATION_PAUSE)) {
            this.playfield.getLifeGrid().setPauseFlag(true);
        }
    }
    
    
}
