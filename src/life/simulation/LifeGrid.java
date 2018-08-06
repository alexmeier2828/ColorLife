/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.simulation;

import java.util.Random;
/**
 *
 * @author Alex Meier
 */
public class LifeGrid {
    private int grid[][];
    private final int size_x;
    private final int size_y;
    private boolean paused;
    
    //constructor
    public LifeGrid(int width, int height) {
        this.grid  = new int[width][height];
        this.paused = false;
        size_x = width;
        size_y = height;
    }
    
    public int getCell(int x, int y) {
        return grid[x][y];
    }
    
    public boolean isPaused() {
        return this.paused;
    }
    public void setPauseFlag(boolean flag) {
        this.paused = flag;
    }
    
    public void toggleCell(int x, int y) {
        if(grid[x][y] == 0) {
            grid[x][y] = 1;
        } else {
            grid[x][y] = 0;
        }
    }
    //populate grid with random status
    public void randPopulate() {
        Random rand = new Random();
        int next_int;
        
        
        for(int x = 0; x < this.size_x; x++) {
            for(int y = 0; y < this.size_y; y++) {
                if(rand.nextBoolean()){
                    this.grid[x][y] = 1;
                } else {
                    this.grid[x][y] = 0;
                }
                
            }
        }
        
    }
    
    //populate with a single cell line across the center of the playfield
    public void linePopulate() {
        int new_grid[][] = new int[this.size_x][this.size_y];
        for(int x = 0; x < this.size_x; x++){
            new_grid[x][this.size_y / 2] = 1;
        }
        this.grid = new_grid;
    }
    
    //advance the grid by 1 turn
    public void turn() {
        int newGrid[][] = new int[this.size_x][this.size_y];
        for(int x = 0; x < this.size_x; x++) {
            for(int y = 0; y < this.size_y; y++) {
                newGrid[x][y] = grid[x][y];
            }
        }
        //call is alive next turn for each cell;
        for(int x = 0; x < this.size_x; x++) {
            for(int y = 0; y < this.size_y; y++) {
                if(this.isAliveNextTurn(x, y)){
                    if(newGrid[x][y] < 9){
                       newGrid[x][y]++; //increase age by 1                       newGrid[x][y]++; //increase age by 1 
 
                    }
                    
                } else {
                    newGrid[x][y] = 0; //reset to 0 (dead)
                }
                
            }
        }
        
        //replace grid with the updated newGrid
        this.grid = newGrid;
        //System.out.println(grid);
    }
    
    //returns true if cell is alive next turn at the chordinates x,y.
    public boolean isAliveNextTurn(int x, int y)
    {
       
        //just flip the values for now;
        int crowding = this.crowding(x, y);
        if(this.grid[x][y] >= 1)
        {   
            if(crowding > 1 && crowding(x, y) < 4)
            {
                return true;
            } else {
                return false;
            }
        
        } else if(this.grid[x][y] == 0 && crowding == 3) {
            return true;
        }
     return false;   
    }
    
    
    //returns the number of cells surounding a given cell
    public int crowding(int x, int y) {
        int check_x;
        int check_y;
        int total = 0;
        for(int rad_x = -1; rad_x <= 1; rad_x++) {
            for(int rad_y = -1; rad_y <= 1; rad_y++) {
                
                //set check_x value
                if(x + rad_x >= this.size_x)
                {
                    check_x = 0;
                }else if(x + rad_x < 0) {
                    check_x = size_x - 1;
                }else {
                    check_x = x + rad_x;
                }
                
                //set check_y
                if(y + rad_y >= this.size_y)
                {
                    check_y = 0;
                }else if(y + rad_y < 0) {
                    check_y = size_y - 1;
                }else {
                    check_y = y + rad_y;
                }
                
                if(this.grid[check_x][check_y] >= 1)
                {
                    total++;
                }
                
                
            }
                
        }
        //if cell is alive, we don't want to count the cell in total
        if(grid[x][y] >= 1) {
            total--;
        }
        
        return total;
    }
}
