/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoavanzada;

import java.util.ArrayList;
import javafx.scene.text.Text;
import proyectoavanzada.Poligonos.Poligono;

/**
 *
 * @author Matias
 */
public class RelaciónDebil extends Relacion {
public Poligono poligono2;

    public RelaciónDebil(Poligono poligono2, Text texto, Poligono poligono, ArrayList<Entidad> entidadesSelec) {
        super(texto, poligono, entidadesSelec);
        this.poligono2 = poligono2;
    }

    
}
