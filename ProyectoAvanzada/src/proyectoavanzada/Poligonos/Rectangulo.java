/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoavanzada.Poligonos;

import java.awt.Point;
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
 public class Rectangulo  {
    @FXML
    public Pane pane;
    public Double largo;
    public Point punto;
    public Point punto2;
    public Point punto3;
    public Point punto4;
    public ArrayList<Boolean> puntosBolean= new ArrayList();
    public boolean seleccionado;

    public Point getPunto() {
        return punto;
    }

    public Point getPunto2() {
        return punto2;
    }

    public Point getPunto3() {
        return punto3;
    }

    public Point getPunto4() {
        return punto4;
    }
    public void setPuntosBolean(ArrayList<Boolean> puntosBolean) {
        this.puntosBolean = puntosBolean;
    }

    public Line lineaSuperior;
    public Line lineaInferior;
    public Line lineaDerecha;
    public Line lineaIzquierda;
    
    
    public ArrayList<Point> puntos=new ArrayList();

    public ArrayList<Point> getPuntos() {
        return puntos;
    }
    
    public Rectangulo(Pane pane, Double largo, Point punto) {
        this.pane = pane;
        this.largo = largo;
        this.punto = punto;
        this.seleccionado=false;
        
        puntosBolean.add(false);
        puntosBolean.add(false);
        puntosBolean.add(false);
        puntosBolean.add(false);
    
        punto= new Point((punto.x)-300,punto.y-25);
        puntos.add(punto);

        punto2= new Point((int) ((punto.x)+largo),punto.y);
        puntos.add(punto2);

        punto3= new Point((int) ((punto.x)),punto.y+40);
        puntos.add(punto3);

        punto4=new Point((int) ((punto.x)+largo),punto.y+40);
        puntos.add(punto4);

        this.lineaSuperior= new Line(punto.x,punto.y,punto.x+largo,punto.y);
        this.lineaInferior= new Line(punto.x,punto.y+40,punto.x+largo,punto.y+40);
        this.lineaIzquierda= new Line(punto.x,punto.y,punto.x,punto.y+40);
        this.lineaDerecha= new Line(punto.x+largo,punto.y,punto.x+largo,punto.y+40);
    }
   
    
    public void Mover(Point punto) {
        Borrar();
        this.punto = punto;
        
        puntos.clear();

        punto= new Point((punto.x)-300,punto.y-25);
        puntos.add(punto);

        punto2= new Point((int) ((punto.x)+largo),punto.y);
        puntos.add(punto2);

        punto3= new Point((int) ((punto.x)),punto.y+40);
        puntos.add(punto3);

        punto4=new Point((int) ((punto.x)+largo),punto.y+40);
        puntos.add(punto4);

        this.lineaSuperior= new Line(punto.x,punto.y,punto2.x,punto2.y);
        this.lineaInferior= new Line(punto3.x,punto3.y,punto4.x,punto4.y);
        this.lineaIzquierda= new Line(punto.x,punto.y,punto3.x,punto3.y);
        this.lineaDerecha= new Line(punto2.x,punto.y,punto4.x,punto4.y);
        
        Dibujar();
    }
    public void MoverRecantulo2(Point punto) {
        Borrar();
        this.punto = punto;
        
        puntos.clear();

        punto= new Point((punto.x)-303,punto.y-28);
        puntos.add(punto);

        punto2= new Point((int) ((punto.x)+largo),punto.y);
        puntos.add(punto2);

        punto3= new Point((int) ((punto.x)),punto.y+46);
        puntos.add(punto3);

        punto4=new Point((int) ((punto.x)+largo),punto.y+46);
        puntos.add(punto4);

        this.lineaSuperior= new Line(punto.x,punto.y,punto2.x,punto2.y);
        this.lineaInferior= new Line(punto3.x,punto3.y,punto4.x,punto4.y);
        this.lineaIzquierda= new Line(punto.x,punto.y,punto3.x,punto3.y);
        this.lineaDerecha= new Line(punto2.x,punto.y,punto4.x,punto4.y);
        Dibujar();
    }

    public void Dibujar() {
        lineaSuperior.setStroke(Color.BLACK);
        lineaSuperior.setStrokeWidth(1);
        lineaSuperior.setStrokeLineCap(StrokeLineCap.ROUND);
        pane.getChildren().add(lineaSuperior);

        lineaInferior.setStroke(Color.BLACK);
        lineaInferior.setStrokeWidth(1);
        lineaInferior.setStrokeLineCap(StrokeLineCap.ROUND);
        pane.getChildren().add(lineaInferior);

        lineaIzquierda.setStroke(Color.BLACK);
        lineaIzquierda.setStrokeWidth(1);
        lineaIzquierda.setStrokeLineCap(StrokeLineCap.ROUND);
        pane.getChildren().add(lineaIzquierda);

        lineaDerecha.setStroke(Color.BLACK);
        lineaDerecha.setStrokeWidth(1);
        lineaDerecha.setStrokeLineCap(StrokeLineCap.ROUND);
        pane.getChildren().add(lineaDerecha);
    }
    public void Dibujar2() {
        lineaSuperior.setStroke(Color.CRIMSON);
        lineaInferior.setStroke(Color.CRIMSON);
        lineaIzquierda.setStroke(Color.CRIMSON);
        lineaDerecha.setStroke(Color.CRIMSON);
        
    }
    

    public void Borrar() {
        this.pane.getChildren().remove(this.lineaDerecha);
        this.pane.getChildren().remove(this.lineaInferior);
        this.pane.getChildren().remove(this.lineaSuperior);
        this.pane.getChildren().remove(this.lineaIzquierda);
    }
}
