/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

/**
 *
 * @author Administrador
 */
public class Rect {
    public int left, top, right, bottom;
    
    public Rect(int l, int t, int r, int b){
        left = l;
        top = t;
        right = r;
        bottom = b;
    }
    
    public Rect(){
        left = top = right = bottom = 0;
    }
}
