/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import life.simulation.LifeGrid;
import life.controller.MenuController;
import life.controller.MouseController;
/**
 *
 * @author Alex Meier
 */
public class Playfield{
    
    //Frame
    private JFrame frame;
    
    //Menu components
    private JMenuBar menuBar;
    private JMenu simulationMenu;
    private JMenuItem simulation_resetRandom;
    private JMenuItem simulation_resetLine;
    private JMenuItem simulation_pause;
    private JMenuItem simulation_play;
    
    private LifeGrid lifeGrid;
    private SquareGrid squareGrid;
    private final int size_x;
    private final int size_y;
    
    //enumerations for menuItems
    public static enum menuItems{
        SIMULATION_RESET_RANDOM,
        SIMULATION_RESET_LINE,
        SIMULATION_PAUSE,
        SIMULATION_PLAY
    }
    
    public Playfield(int sizeX, int sizeY, int gridSizeX, int gridSizeY, LifeGrid grid) {
        //grid setup
        this.size_x = sizeX;
        this.size_y = sizeY;
        this.lifeGrid = grid;
        this.squareGrid = new SquareGrid(gridSizeX, gridSizeY, sizeX/gridSizeX);
        
        //Frame initiallization
        this.frame = new JFrame("life");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.frame.setLayout();
        
        
        
        //initialize new MyPanel object and add it to frame
        MyPanel myPanel = new MyPanel(this.size_x, this.size_y);
        this.frame.getContentPane().add(myPanel);
        
        
        //menu initiallization
        this.menuBar = new JMenuBar();
        this.simulationMenu = new JMenu("Simulation");
        this.simulation_resetLine = new JMenuItem("Reset: Line");
        this.simulation_resetRandom = new JMenuItem("Reset: Random");
        this.simulation_pause = new JMenuItem("Pause");
        this.simulation_play = new JMenuItem("Play");
        this.simulationMenu.add(this.simulation_resetLine);
        this.simulationMenu.add(this.simulation_resetRandom);
        this.simulationMenu.add(this.simulation_pause);
        this.simulationMenu.add(this.simulation_play);
        this.menuBar.add(this.simulationMenu);
        this.frame.setJMenuBar(this.menuBar);
        
        System.out.println(this.menuBar.getHeight());
        
        //actionlistener for menus
        MenuController menuCon = new MenuController(this);
        this.simulation_resetLine.addActionListener(menuCon);
        this.simulation_resetRandom.addActionListener(menuCon);
        this.simulation_pause.addActionListener(menuCon);
        this.simulation_play.addActionListener(menuCon);
        
        //mouse listener
        MouseController mouseCon = new MouseController(this);
        myPanel.addMouseListener(mouseCon);
        System.out.println(myPanel.getBounds());
        
        
        this.frame.pack();
        this.frame.setVisible(true);
        //this.frame.setResizable(false);
        
        this.frame.validate();
        this.frame.repaint();
    }
    
    public JFrame getFrame() { 
       return this.frame; 
    }
    
    public LifeGrid getLifeGrid() {
        return this.lifeGrid;
        
    }
    
    public SquareGrid getSquareGrid() {
        return this.squareGrid;
    }
    
    public JMenuItem getMenuItem(menuItems item) {
        switch(item) {
            case SIMULATION_RESET_RANDOM :
                return this.simulation_resetRandom;
            case SIMULATION_RESET_LINE :
                return this.simulation_resetLine;
            case SIMULATION_PAUSE :
                return this.simulation_pause;
            case SIMULATION_PLAY : 
                return this.simulation_play;
            default: 
                return null;
                
        }
    }
    public void draw(Graphics2D g2d) {
        //draw the grid of cells using lifeGrid for coloringa
        squareGrid.draw(g2d, this.lifeGrid);
    }
        
    
    /*
    This class just overides the paint function, so I don't need to extend JPanel 
    in the main Playfield class.
    */
    class MyPanel extends JPanel {
        public MyPanel(int width, int height) {
            super();
            super.setSize(width, height);
            super.setPreferredSize(new Dimension(width, height));
            super.setMinimumSize(new Dimension(width, height));
        }
    
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        
            Graphics2D g2d = (Graphics2D) g;
            Playfield.this.draw(g2d);
            
        }
    }
}

