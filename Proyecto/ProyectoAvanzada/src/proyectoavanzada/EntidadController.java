/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoavanzada;

import java.awt.FontMetrics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import proyectoavanzada.Poligonos.Hexagono;
import proyectoavanzada.Poligonos.Pentagono;
import proyectoavanzada.Poligonos.Rectangulo;
import proyectoavanzada.Poligonos.Rombo;
import proyectoavanzada.Poligonos.Triangulo;

/**
 * FXML Controller class
 *
 * @author Matias
 */
public class EntidadController implements Initializable {
    
    public double posX;
    public double posY;
    public boolean sePuedeCrear=false;
    public Rectangulo rectangulo;
    
    public double largoTexto=0;
    @FXML
    public Text texto= null;
    @FXML
    public Pane pane;
    @FXML
    public TextField insertarTexto1;
    @FXML
    public Button entidad;
    @FXML
    public Button botonCrear;
    @FXML
    public Button borrar;
    @FXML
    public void dibujarEntidad(){
        if (sePuedeCrear){
            Point punto = MouseInfo.getPointerInfo().getLocation();
            posX=punto.getX()-300;
            posY=punto.getY()-10;
            texto.setText(insertarTexto1.getText());
            texto.setLayoutX(posX);
            texto.setLayoutY(posY);
            texto.setVisible(true);
            //dibujar el rectangulo
            largoTexto=insertarTexto1.getText().length()*10;
            Point puntoPrincipal;
            Rectangulo poligono=new Rectangulo (pane, largoTexto, punto);
            poligono.Dibujar();
            sePuedeCrear=false;
            //this.rectangulo= new Rectangulo(pane, largoTexto, puntoPrincipal);
           // this.rectangulo.dibujar();
            
        }
    }
    @FXML
    public void crearEntidad(){
        insertarTexto1.setVisible(true);
    }
    @FXML
    public void crear(){
        sePuedeCrear=true;
        //donde
    }
    @FXML
    public void borrar(){
        this.rectangulo.Borrar();
        texto.setVisible(false);
    }
    @FXML
    public void mover(){
        Point punto = MouseInfo.getPointerInfo().getLocation();
        posX=punto.getX()-300;
        posY=punto.getY()-10;
        this.rectangulo.Mover(punto);
        texto.setVisible(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        texto.setVisible(false);
        insertarTexto1.setVisible(false);
    }    
    
}
