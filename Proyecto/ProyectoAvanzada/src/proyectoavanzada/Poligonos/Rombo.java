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
public class Rombo implements PoligonosInterface {
    @FXML
    public Pane pane;
    
    Double largo;
    Point punto;
    Point punto2;
    Point punto3;
    Point punto4;
    
    Line lineaSuperiorDerecha;
    Line lineaSuperiorIzquierda;
    Line lineaInferiorDerecha;
    Line lineaInferiorIzquierda;

    public Rombo(Pane pane, Double largo, Point punto) {
        this.pane = pane;
        this.largo = largo;
        this.punto= punto;
        largo=largo/2;
        this.punto.y= (int)(punto.y-largo);
        this.punto.x=(int)(punto.x+largo-10);
        
        this.punto2=new Point((int)(punto.x+largo),(int)(punto.y+largo));
        this.punto3=new Point((int)(punto.x),(int)(punto.y+2*largo));
        this.punto4=new Point((int)(punto.x-largo),(int)(punto.y+largo));
        
        this.lineaSuperiorDerecha= new Line(punto.x,punto.y,punto2.x,punto2.y);
        this.lineaSuperiorIzquierda= new Line(punto.x,punto.y,punto4.x,punto4.y);
        this.lineaInferiorIzquierda= new Line(punto3.x,punto3.y,punto4.x,punto4.y);
        this.lineaInferiorDerecha= new Line(punto2.x,punto2.y,punto3.x,punto3.y);
    }
    
    @Override
    public void Mover(Point punto) {
        this.Borrar();
        this.punto.y= (int)(punto.y-largo);
        this.punto.x=(int)(punto.x+largo-10);
        
        this.punto2=new Point((int)(punto.x+largo),(int)(punto.y+largo));
        this.punto3=new Point((int)(punto.x),(int)(punto.y+2*largo));
        this.punto4=new Point((int)(punto.x-largo),(int)(punto.y+largo));
        
        this.lineaSuperiorDerecha= new Line(punto.x,punto.y,punto2.x,punto2.y);
        this.lineaSuperiorIzquierda= new Line(punto.x,punto.y,punto4.x,punto4.y);
        this.lineaInferiorIzquierda= new Line(punto3.x,punto3.y,punto4.x,punto4.y);
        this.lineaInferiorDerecha= new Line(punto2.x,punto2.y,punto3.x,punto3.y);
        
        this.Dibujar();
    }
    
    @Override
    public void Dibujar(){
         
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
    }
    @Override
    public void Borrar(){
        this.pane.getChildren().remove(this.lineaInferiorDerecha);
        this.pane.getChildren().remove(this.lineaSuperiorIzquierda);
        this.pane.getChildren().remove(this.lineaSuperiorDerecha);
        this.pane.getChildren().remove(this.lineaInferiorIzquierda);
    }
    
}
