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
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Alex Meier
 */
public class SquareGrid {
    
    private final int size_x;
    private final int size_y;
    private final int square_size;
    private final Rectangle[][] grid;
    private ColorSet colorSet;
    
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
        
        colorSet = new ColorSet();
    }
    
    public void draw(Graphics2D g2d, LifeGrid lifeGrid) {
        //System.out.println("Made it to paint function");
        for(int x = 0; x < size_x; x++) {
            for(int y = 0; y < size_y; y++) {
                //set color
                g2d.setColor(colorSet.getColor(lifeGrid.getCell(x, y)));
                //draw each individual cell
                g2d.fillRect((int)grid[x][y].getX(), (int)grid[x][y].getY(), this.square_size, this.square_size);
                
            }
        }
    }
    
    public Point convertToGrid(Point p) {
        Point gridP = new Point();
        gridP.x = p.x / this.square_size;
        gridP.y = p.y / this.square_size;
        return gridP;
    }
}
    
