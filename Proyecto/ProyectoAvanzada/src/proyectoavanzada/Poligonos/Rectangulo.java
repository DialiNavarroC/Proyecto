/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoavanzada.Poligonos;

import java.awt.Point;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author Diali
 */
public class Rectangulo implements PoligonosInterface {
    @FXML
    public Pane pane;
    Double largo;
    Point punto;
    Point punto2;
    Point punto3;
    Point punto4;
    
    Line lineaSuperior;
    Line lineaInferior;
    Line lineaDerecha;
    Line lineaIzquierda;

    public Rectangulo(Pane pane, Double largo, Point punto) {
        this.pane = pane;
        this.largo = largo;
        this.punto= new Point(punto.x-10,punto.y-25);   
        this.lineaSuperior= new Line(punto.x,punto.y,punto.x-10,punto.y-25);
        this.lineaInferior= new Line(punto.x-10,punto.y+25,punto.x+largo,punto.y+25);
        this.lineaIzquierda= new Line(punto.x-10,punto.y-25,punto.x-10,punto.y+25);
        this.lineaDerecha= new Line(punto.x+largo,punto.y-25,punto.x+largo,punto.y+25);
    }
   
    @Override
    public void Mover(Point punto) {
        this.Borrar();
        this.punto=punto;
        this.lineaSuperior= new Line(punto.x-10,punto.y-25,punto.x+largo,punto.y-25);
        this.lineaInferior= new Line(punto.x-10,punto.y+25,punto.x+largo,punto.y+25);
        this.lineaIzquierda= new Line(punto.x-10,punto.y-25,punto.x-10,punto.y+25);
        this.lineaDerecha= new Line(punto.x+largo,punto.y-25,punto.x+largo,punto.y+25);
        this.Dibujar();
    }

    @Override
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

    @Override
    public void Borrar() {
        this.pane.getChildren().remove(this.lineaDerecha);
        this.pane.getChildren().remove(this.lineaInferior);
        this.pane.getChildren().remove(this.lineaSuperior);
        this.pane.getChildren().remove(this.lineaIzquierda);
    }
    
   
}
