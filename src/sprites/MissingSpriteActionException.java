/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

/**
 *
 * @author Administrador
 */
public class MissingSpriteActionException extends Exception {

    public MissingSpriteActionException(String actionName) {
        super("No se encuentra la acción: '"+actionName+"' en el mapa de sprites");
    }

    public MissingSpriteActionException() {
        super("No se encuentra la acción solicitada en el mapa de sprites");
    }
   
}
