/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoavanzada;

import java.util.ArrayList;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import proyectoavanzada.Poligonos.Rectangulo;

/**
 *
 * @author Matias
 */
public class Entidad {
    public Text nombre;
    public Rectangulo rectangulo;
    public ArrayList <Atributo> atributos = new ArrayList();
    public ArrayList<Line> lineas=new ArrayList();
    public ArrayList<Relacion> relaciones=new ArrayList();

    public ArrayList<Relacion> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(ArrayList<Relacion> relaciones) {
        this.relaciones = relaciones;
    }
    public ArrayList<Line> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<Line> lineas) {
        this.lineas = lineas;
    }

    public Entidad(Text nombre, Rectangulo rectangulo) {
        this.nombre = nombre;
        this.rectangulo = rectangulo;
    }
    
    
   
}
