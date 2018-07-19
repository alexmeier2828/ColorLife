/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.view;
import life.simulation.LifeGrid;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JPanel;
/**
 *
 * @author Am0718
 */
public class SquareGrid {
    
    private final int size_x;
    private final int size_y;
    private final int square_size;
    private final Rectangle[][] grid;
    
    public SquareGrid(int sizeX, int sizeY, int square_size) {
        this.size_x = sizeX;
        this.size_y = sizeY;
        this.square_size = square_size;
        //initialize grid of squares;
        grid = new Rectangle[size_x][size_y];
        for(int x = 0; x < size_x; x++) {
            for(int y = 0; y < size_y; y++) {
                grid[x][y] = new Rectangle(x*square_size, y*square_size, square_size, square_size);
                
            }
        }
        
    }
    
    public void draw(Graphics2D g2d, LifeGrid lifeGrid) {
        //System.out.println("Made it to paint function");
        for(int x = 0; x < size_x; x++) {
            for(int y = 0; y < size_y; y++) {
                //set color
                if(!lifeGrid.getCell(x, y))
                {
                    g2d.setColor(Color.WHITE);
                } else {
                    g2d.setColor(Color.BLACK);
                }
                
                //draw each individual cell
                g2d.fillRect((int)grid[x][y].getX(), (int)grid[x][y].getY(), this.square_size, this.square_size);
                
            }
        }
    }
}
    
