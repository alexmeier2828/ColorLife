/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.view;
import java.awt.Color;
/**
 *
 * @author AM0718
 */
public class ColorSet {
    private Color colors[];
    public ColorSet() {
        colors = new Color[10];
        colors[0] = new Color(0, 180, 0); //white
        for(int i = 1; i < 10; i++) {
            int r, g, b;
            r = i* 25;
            g = 0;
            b = 200 - (i * 10);
            colors[i] = new Color(r, g, b); 
        }
    }
    public Color getColor(int age) {
       if( age < 10) {
           return colors[age];
       }else {
           return colors[age%10];
       }
    }
}
