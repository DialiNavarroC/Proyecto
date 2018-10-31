/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoavanzada.Poligonos;

import java.awt.Point;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author Diali
 */
public class Poligono {
    public ArrayList<Point> puntos=new ArrayList();
    public ArrayList<Point> puntosAux=new ArrayList();
    public ArrayList<Line> lineas=new ArrayList();
    public ArrayList<Boolean> puntosBolean= new ArrayList();
    public int n, largo;
    
    public Pane pane;

    public Poligono(Pane pane) {
        this.puntos = puntos;
        this.lineas = lineas;
        this.pane = pane;
    }

    public ArrayList<Point> getPuntos() {
        return puntos;
    }

    public ArrayList<Point> getPuntosAux() {
        return puntosAux;
    }
    
    public void Dibujar(int n, int largo, Point puntoMouse) {
        this.n=n;
        this.largo=largo;
        generarPuntos(n, largo,puntoMouse);
        generarLineas();
        for (int i = 0; i < this.lineas.size(); i++) {
            lineas.get(i).setStroke(Color.BLACK);
            lineas.get(i).setStrokeWidth(1);
            lineas.get(i).setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineas.get(i));
        }
    }
    

    public void setPuntosBolean(ArrayList<Boolean> puntosBolean) {
        this.puntosBolean = puntosBolean;
    }

    public void generarPuntos(int n, int radio, Point puntoMouse){
        for (int i = 0; i <= n; i++) {
            Point punto=new Point();
            Double angle = i * 2 * 3.14 / n;
            punto.x = (int) ((radio * cos(angle))+ puntoMouse.x)-300;
            punto.y= (int) ((radio * sin(angle))+puntoMouse.y)-15;
            puntos.add(punto);
            puntosBolean.add(Boolean.FALSE);
        }
    }
    public void generarPuntosElipse(int n, int radio, Point puntoMouse){
        for (int i = 0; i <= n; i++) {
            Point punto=new Point();
            Double angle = i * 2 * 3.14 / n;
            punto.x = (int) (((radio+80) * cos(angle))+ puntoMouse.x)-300;
            punto.y= (int) ((radio * sin(angle))+puntoMouse.y)-15;
            puntos.add(punto);
            puntosBolean.add(Boolean.FALSE);
        }
    }
    public void DibujarElipsePunteada(int n, int largo, Point puntoMouse) {
        this.n=n;
        this.largo=largo;
        generarPuntosElipse(n, largo,puntoMouse);
        generarLineas();
        int j=25;
        for (int i = 0; i < this.lineas.size(); i++) {
            if(i==j){
                i=i+25;
                j=j+50;
            }
            else{
            lineas.get(i).setStroke(Color.BLACK);
            lineas.get(i).setStrokeWidth(1);
            lineas.get(i).setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineas.get(i));
            }
        }
    }
    
   public void DibujarElipseNormal(int n, int largo, Point puntoMouse) {
        this.n=n;
        this.largo=largo;
        generarPuntosElipse(n, largo,puntoMouse);
        generarLineas();
        for (int i = 0; i < this.lineas.size(); i++) {
            
            lineas.get(i).setStroke(Color.BLACK);
            lineas.get(i).setStrokeWidth(1);
            lineas.get(i).setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineas.get(i));
            
        }
    }
    
    public void generarLineas(){
        int i;
        for ( i = 0; i < puntos.size()-1; i++) {
            Line linea=new Line(puntos.get(i).x, puntos.get(i).y,puntos.get(i+1).x, puntos.get(i+1).y);
            lineas.add(linea);
        }
    }
    public boolean seleccionar(Point punto){
        int j=0;
        boolean seleccion=false;
        /*if (poligono[i][1] < y and poligono[j][1] >= y) or (poligono[j][1] < y and poligono[i][1] >= y):
            if poligono[i][0] + (y - poligono[i][1]) / (poligono[j][1] - poligono[i][1]) * (poligono[j][0] - poligono[i][0]) < x:*/
        for (int i = 0; i < puntos.size(); i++) {
            if ((puntos.get(i).y < punto.y && puntos.get(j).y >= punto.y) ||(puntos.get(j).y < punto.y && puntos.get(i).y >= punto.y)){
                    if(puntos.get(i).x + (punto.y - puntos.get(i).y) / (puntos.get(j).y - puntos.get(i).y) * (puntos.get(j).x - puntos.get(i).x) < punto.x) {
                        seleccion=true;
                    }  
            }
            j = i;        
        }             
            return seleccion;
    }
    public void borrar(){
        for (int i = 0; i < lineas.size(); i++) {
             pane.getChildren().remove(lineas.get(i));
        }
    }
     public void mover(Point punto){
         borrar();
         lineas.clear();
         puntos.clear();
         Dibujar(n, largo, punto);
     }
        
}
    

