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
public class Triangulo implements PoligonosInterface{
    
    @FXML
    public Pane pane;
    Double largo;
    Double altura;
    Point punto;
    
    Line lineaIzquierda;
    Line lineaInferior;
    Line lineaDerecha;

    public Triangulo(Pane pane, Double largo, Point punto) {
        this.pane = pane;
        this.largo = largo;
        this.punto= punto;
        this.altura=(1.73*this.largo)/2;
        this.punto.x=(int)(punto.x-largo/2);
        this.lineaDerecha= new Line(punto.x,punto.y,punto.x+largo/2,punto.y+this.altura);
        this.lineaIzquierda= new Line(punto.x,punto.y,punto.x-largo/2,punto.y+this.altura);
        this.lineaInferior= new Line(punto.x+largo/2,punto.y+this.altura,punto.x-largo/2,punto.y+this.altura);
    }
    @Override
    public void Dibujar(){
         
            lineaIzquierda.setStroke(Color.BLACK);
            lineaIzquierda.setStrokeWidth(1);
            lineaIzquierda.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineaIzquierda);
            
            lineaInferior.setStroke(Color.BLACK);
            lineaInferior.setStrokeWidth(1);
            lineaInferior.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineaInferior);
            
            
            lineaDerecha.setStroke(Color.BLACK);
            lineaDerecha.setStrokeWidth(1);
            lineaDerecha.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineaDerecha);
    }
    @Override
    public void Borrar(){
        this.pane.getChildren().remove(this.lineaDerecha);
        this.pane.getChildren().remove(this.lineaInferior);
        this.pane.getChildren().remove(this.lineaIzquierda);
    }


    @Override
    public void Mover(Point punto) {
        this.Borrar();
        this.punto= punto;
        this.punto.x=(int)(punto.x-largo/2);
        this.lineaDerecha= new Line(punto.x,punto.y,punto.x+largo/2,punto.y+this.altura);
        this.lineaIzquierda= new Line(punto.x,punto.y,punto.x-largo/2,punto.y+this.altura);
        this.lineaInferior= new Line(punto.x+largo/2,punto.y+this.altura,punto.x-largo/2,punto.y+this.altura);
        this.Dibujar();
    }
    
}
