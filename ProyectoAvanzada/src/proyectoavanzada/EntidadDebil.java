/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoavanzada;

import javafx.scene.text.Text;
import proyectoavanzada.Poligonos.Rectangulo;

/**
 *
 * @author Pablo
 */
public class EntidadDebil extends Entidad {
    
    public Rectangulo rectangulo2;

    public EntidadDebil( Text nombre, Rectangulo rectangulo, Rectangulo rectangulo2) {
        super(nombre, rectangulo);
        this.rectangulo2 = rectangulo2;
    }

    public Rectangulo getRectangulo2() {
        return rectangulo2;
    }    

}
