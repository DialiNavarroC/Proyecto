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
public class Hexagono implements PoligonosInterface {
    @FXML
    public Pane pane;
    
    Double largo;
    Point punto;
    
    Line lineaSuperior;
    Line lineaInferior;
    Line lineaSuperiorDerecha;
    Line lineaSuperiorIzquierda;
    Line lineaInferiorDerecha;
    Line lineaInferiorIzquierda;
    
    Point punto2;
    Point punto3;
    Point punto4;
    Point punto5;
    Point punto6;
    
    

    public Hexagono(Pane pane, Double largo, Point punto) {
        this.pane = pane;
        this.largo = largo;
        this.punto = punto;
        punto2= new Point((int)(punto.x+largo), punto.y);
        punto3= new Point((int)(punto.x+largo*3/2), (int)(punto.y+largo));
        punto4=new Point((int)(punto.x+largo),(int) (punto.y+2*largo));
        punto5=new Point(punto.x, (int)(punto.y+ 2*largo));
        punto6=new Point((int)(punto.x-largo/2),(int)(punto.y+largo));
        
        this.lineaSuperior=new Line(punto.x, punto.y,punto2.x, punto2.y);
        this.lineaSuperiorDerecha= new Line(punto2.x, punto2.y, punto3.x, punto3.y);
        this.lineaInferiorDerecha= new Line( punto3.x, punto3.y, punto4.x , punto4.y);
        this.lineaInferior=new Line(punto4.x, punto4.y, punto5.x, punto5.y);
        this.lineaInferiorIzquierda=new Line(punto5.x, punto5.y, punto6.x, punto6.y);  
        this.lineaSuperiorIzquierda=new Line(punto6.x, punto6.y,punto.x, punto.y);
    }
    
    
    @Override
    public void Dibujar() {
        lineaSuperior.setStroke(Color.BLACK);
        lineaSuperior.setStrokeWidth(1);
        lineaSuperior.setStrokeLineCap(StrokeLineCap.ROUND);
        pane.getChildren().add(lineaSuperior);
        
        lineaSuperiorDerecha.setStroke(Color.BLACK);
        lineaSuperiorDerecha.setStrokeWidth(1);
        lineaSuperiorDerecha.setStrokeLineCap(StrokeLineCap.ROUND);
        pane.getChildren().add(lineaSuperiorDerecha);
        
        lineaInferiorDerecha.setStroke(Color.BLACK);
        lineaInferiorDerecha.setStrokeWidth(1);
        lineaInferiorDerecha.setStrokeLineCap(StrokeLineCap.ROUND);
        pane.getChildren().add(lineaInferiorDerecha);
        
        lineaInferior.setStroke(Color.BLACK);
        lineaInferior.setStrokeWidth(1);
        lineaInferior.setStrokeLineCap(StrokeLineCap.ROUND);
        pane.getChildren().add(lineaInferior);
        
        lineaInferiorIzquierda.setStroke(Color.BLACK);
        lineaInferiorIzquierda.setStrokeWidth(1);
        lineaInferiorIzquierda.setStrokeLineCap(StrokeLineCap.ROUND);
        pane.getChildren().add(lineaInferiorIzquierda);
        
        lineaSuperiorIzquierda.setStroke(Color.BLACK);
        lineaSuperiorIzquierda.setStrokeWidth(1);
        lineaSuperiorIzquierda.setStrokeLineCap(StrokeLineCap.ROUND);
        pane.getChildren().add(lineaSuperiorIzquierda);
    }

    @Override
    public void Borrar() {
        this.pane.getChildren().remove(this.lineaSuperior);
        this.pane.getChildren().remove(this.lineaSuperiorDerecha);
        this.pane.getChildren().remove(this.lineaInferiorDerecha);
        this.pane.getChildren().remove(this.lineaInferior);
        this.pane.getChildren().remove(this.lineaInferiorIzquierda);
        this.pane.getChildren().remove(this.lineaSuperiorIzquierda);
    }

    @Override
    public void Mover(Point punto) {
        this.Borrar();
        this.punto.y=punto.y;
        this.punto.x=punto.x;
        
        punto2= new Point((int)(punto.x+largo), punto.y);
        punto3= new Point((int)(punto.x+largo*3/2),(int)( punto.y+largo));
        punto4=new Point((int)(punto.x+largo),(int) (punto.y+2*largo));
        punto5=new Point(punto.x,(int)( punto.y+ 2*largo));
        punto6=new Point((int)(punto.x-largo/2),(int) (punto.y+largo));
        
        this.lineaSuperior=new Line(punto.x, punto.y,punto2.x, punto2.y);
        this.lineaSuperiorDerecha= new Line(punto2.x, punto2.y, punto3.x, punto3.y);
        this.lineaInferiorDerecha= new Line( punto3.x, punto3.y, punto4.x , punto4.y);
        this.lineaInferior=new Line(punto4.x , punto4.y, punto5.x, punto5.y);
        this.lineaInferiorIzquierda=new Line(punto5.x, punto5.y, punto6.x, punto6.y);  
        this.lineaSuperiorIzquierda=new Line(punto6.x, punto6.y,punto.x, punto.y);
    }
    
}
