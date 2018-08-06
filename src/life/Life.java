/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

/**
 *
 * @author Alex Meier
 */

import life.simulation.LifeGrid;
import life.view.Playfield;
import javax.swing.SwingUtilities;

//constructor
public class Life{
    
    private Playfield playfield;
    private LifeGrid lifeGrid;
    private int sizeX; //size of actual square window
    private int sizeY;
    private static final long STEP_TIME = 250;
    //@var s size
    //@var grid_density grid size 
    public Life(int sx,int sy, int grid_density_x, int grid_density_y) {
        
        
        this.lifeGrid = new LifeGrid(grid_density_x, grid_density_y);
        //this.lifeGrid.randPopulate();
        this.playfield = new Playfield(sx, sy, grid_density_x, grid_density_y, this.lifeGrid);
        this.sizeX = sx;
        this.sizeY = sy;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create thread to start simulation
        ViewThread EDT = new ViewThread();
        long last_step_time = 0;
        while(true) {
            
            //only do this if STEP_TIME milliseconds has passed since the last turn
            if(System.currentTimeMillis() - last_step_time > Life.STEP_TIME) {
                SwingUtilities.invokeLater(EDT);
                last_step_time = System.currentTimeMillis();
            }
        }
        
    }
    
    private static class ViewThread extends Thread {
        private final Life life;
       
        public ViewThread() {
            //constructor\
            this.life = new Life(500, 500, 100, 100);
        }
        
        @Override
        public void run() {
            
            if(!this.life.lifeGrid.isPaused())
            {
                this.life.lifeGrid.turn();
                this.life.playfield.getFrame().repaint();
            }
            
                
             
            
            
        }
    }
   
}
