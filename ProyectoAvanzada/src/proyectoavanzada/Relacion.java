/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoavanzada;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import proyectoavanzada.Poligonos.Poligono;

/**
 *
 * @author Matias
 */
public class Relacion {
    Pane pane;
    public Text nombre;
    public boolean unoAuno;
    public Poligono poligono;
    public ArrayList <Entidad> entidadesSelec=new ArrayList();

    public ArrayList<Entidad> getEntidadesSelec() {
        return entidadesSelec;
    }
    public ArrayList<Line> lineas=new ArrayList();

    public ArrayList<Line> getLineas() {
        return lineas;
    }
    
    
    
    public Relacion(Text texto, Poligono poligono,ArrayList <Entidad> entidadesSelec){
        this.entidadesSelec=entidadesSelec;
        this.nombre=texto;
        this.poligono=poligono;
    }
    public void dibujarRelacion(Pane pane){
        
        for(int i=0; i<poligono.lineas.size();i++){
            System.out.print("X: "+poligono.puntos.get(i).getX());
            System.out.println("Y: "+poligono.puntos.get(i).getY());
        }
        
        
    }
}
