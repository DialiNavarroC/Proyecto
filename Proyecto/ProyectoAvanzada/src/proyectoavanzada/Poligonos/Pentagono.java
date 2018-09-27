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
public class Pentagono implements PoligonosInterface{
    
    @FXML
    public Pane pane;
    Double largo;
    Point punto;
    Point punto2;
    Point punto3;
    Point punto4;
    Point punto5;
    
    Line lineaSuperiorDerecha;
    Line lineaSuperiorIzquierda;
    Line lineaInferior;
    Line lineaInferiorDerecha;
    Line lineaInferiorIzquierda;

    public Pentagono(Pane pane, Double largo, Point punto) {
        this.pane = pane;
        this.largo = largo;
        this.punto= punto;
        this.punto2=new Point((int)(punto.x+largo),(int)(punto.y+largo/2));
        //this.punto3;
        this.lineaSuperiorDerecha=new Line(punto.x, punto.y,punto2.x, punto2.y);
        this.lineaInferiorDerecha=new Line(punto2.x, punto2.y,punto.x+largo/2, punto.y+largo*3/2  );
        this.lineaSuperiorIzquierda=new Line(punto.x, punto.y,punto.x-largo, punto.y+largo/2 );
        this.lineaInferiorIzquierda= new Line(punto.x-largo, punto.y+largo/2 ,punto.x-largo/2, punto.y+largo*3/2 );
        this.lineaInferior=new Line(punto.x-largo/2, punto.y+largo*3/2 ,punto.x+largo/2, punto.y+largo*3/2   );
    }
    @Override
    public void Mover(Point punto) {
        this.Borrar();
        
        this.punto= punto;
        this.lineaSuperiorDerecha=new Line(punto.x, punto.y,punto.x+largo, punto.y+largo/2 );
        this.lineaSuperiorIzquierda=new Line(punto.x, punto.y,punto.x-largo, punto.y+largo/2 );
        this.lineaInferiorIzquierda= new Line(punto.x-largo, punto.y+largo/2 ,punto.x-largo/2, punto.y+largo*3/2 );
        this.lineaInferiorDerecha=new Line(punto.x+largo, punto.y+largo/2,punto.x+largo/2, punto.y+largo*3/2  );
        this.lineaInferior=new Line(punto.x-largo/2, punto.y+largo*3/2 ,punto.x+largo/2, punto.y+largo*3/2   );
        
        this.Dibujar();
    }

    @Override
    public void Dibujar() {
        
            lineaSuperiorDerecha.setStroke(Color.BLACK);
            lineaSuperiorDerecha.setStrokeWidth(1);
            lineaSuperiorDerecha.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineaSuperiorDerecha);
            
            lineaSuperiorIzquierda.setStroke(Color.BLACK);
            lineaSuperiorIzquierda.setStrokeWidth(1);
            lineaSuperiorIzquierda.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineaSuperiorIzquierda);
            
            lineaInferiorIzquierda.setStroke(Color.BLACK);
            lineaInferiorIzquierda.setStrokeWidth(1);
            lineaInferiorIzquierda.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineaInferiorIzquierda);
            
            lineaInferiorDerecha.setStroke(Color.BLACK);
            lineaInferiorDerecha.setStrokeWidth(1);
            lineaInferiorDerecha.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineaInferiorDerecha);
            
            lineaInferior.setStroke(Color.BLACK);
            lineaInferior.setStrokeWidth(1);
            lineaInferior.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(lineaInferior);
    }

    @Override
    public void Borrar() {
        this.pane.getChildren().remove(this.lineaSuperiorDerecha);
        this.pane.getChildren().remove(this.lineaSuperiorIzquierda);
        this.pane.getChildren().remove(this.lineaInferiorIzquierda);
        this.pane.getChildren().remove(this.lineaInferiorDerecha);
        this.pane.getChildren().remove(this.lineaInferior);
    }
    
   
}
