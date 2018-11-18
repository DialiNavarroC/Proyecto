/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoavanzada;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import proyectoavanzada.Poligonos.Poligono;
import proyectoavanzada.Poligonos.Rectangulo;

/**
 * FXML Controller class
 *
 * @author Matias
 */
public class EntidadController implements Initializable {
    
    public double posX;
    public double posY;
    public boolean sePuedeCrearEntidad=false;
    public boolean sePuedeCrearRelacion=false;
    public boolean sePuedeDibujar=false;
    public boolean sePuedeSeleccionar=false;
    public boolean sePuedeCrearAtributo=false;
    public boolean sePuedeSeleccionarEntidadAtributo=false;
    public Rectangulo rectangulo;
    
    public int posicionPuntoMenorXPoligono=0;
    public int posicionPuntoMenorYPoligono=0;
    public int posicionPuntoMenorXRectangulo=0;
    public int posicionPuntoMenorYRectangulo=0;
    public int p=0;
    public int contadorPuntos=0;
    public boolean sePuedeSeleccionarBorrar=false;
    
    @FXML 
    public Circle circulo;
    public ContextMenu contextMenuAtributos = new ContextMenu();
    public ContextMenu contextMenuEntidades = new ContextMenu();
    @FXML
    public Text nombreEntidad;
    public double largoTexto=0;
    public Text textito = new Text();
    @FXML
    public Text texto= null;
    @FXML
    public Text nombre;
    @FXML
    public Text textoBotonCrear;
    @FXML
    public Pane pane;
    @FXML
    public TextField insertarTexto1;
    @FXML
    public Button botonEntidad;
    @FXML
    public Button botonCrear;
    @FXML
    public Button botonMover;
    public Button botonBorrar;
    @FXML
    public Button borrar;
    public ArrayList<Entidad> entidades= new ArrayList();
    public ArrayList<Relacion> relaciones= new ArrayList();
    public ArrayList<Entidad> entidadesSeleccionadas= new ArrayList();
    public ArrayList distanciaEntrePuntos= new ArrayList();
    public ArrayList circulos= new ArrayList();
    public ArrayList<Line> lineas = new ArrayList();
    public Point punto;
    public int posPunto;
    public int posPunto2;
    public boolean recurcion=false;
    
    public Line  CrearRelacion(Entidad entidad, Poligono poligono, boolean unoAuno){
        double distanciaMinima= 7000;
        Point punto1Ant=new Point();
        Point punto2Ant=new Point();
        Point punto1=new Point();
        Point punto2=new Point();
        for (int j = 0; j <entidad.rectangulo.puntos.size(); j++) { //puntos del rectangulo
            for (int k = 1; k < poligono.getPuntos().size(); k++) { //puntos del poligono
                    double distanciaSiguiente=entidad.rectangulo.puntos.get(j).distance(poligono.getPuntos().get(k));
                    if(distanciaSiguiente<distanciaMinima  ){
                        distanciaMinima=distanciaSiguiente;
                        punto1Ant=punto1;
                        punto2Ant=punto2;
                        punto1=entidad.rectangulo.puntos.get(j);
                        punto2=poligono.getPuntos().get(k);
                        posPunto=k;
                        posPunto2=j;
                    }
            }
        }
        if(unoAuno){
            Line linea=new Line(punto1Ant.x, punto1Ant.y, punto2Ant.x, punto2Ant.y);
            return linea;
        }
        Line linea=new Line(punto1.x, punto1.y, punto2.x, punto2.y);
        return linea;
    }
     public Line  CrearRelacionDoble(Entidad entidad, Poligono poligono, boolean unoAuno){
        double distanciaMinima= 7000;
        Point punto1Ant=new Point();
        Point punto2Ant=new Point();
        Point punto1=new Point();
        Point punto2=new Point();
        for (int j = 0; j <entidad.rectangulo.puntos.size(); j++) { //puntos del rectangulo
            for (int k = 1; k < poligono.getPuntos().size(); k++) { //puntos del poligono
                    double distanciaSiguiente=entidad.rectangulo.puntos.get(j).distance(poligono.getPuntos().get(k));
                    if(distanciaSiguiente<distanciaMinima  ){
                        distanciaMinima=distanciaSiguiente;
                        punto1Ant=punto1;
                        punto2Ant=punto2;
                        punto1=entidad.rectangulo.puntos.get(j);
                        punto2=poligono.getPuntos().get(k);
                        posPunto=k;
                        posPunto2=j;
                    }
            }
        }
        if(unoAuno){
            Line linea=new Line(punto1Ant.x+3, punto1Ant.y+3, punto2Ant.x+3, punto2Ant.y+3);
            return linea;
        }
        Line linea=new Line(punto1.x+3, punto1.y+3, punto2.x+3, punto2.y+3);
        return linea;
    }
    
    @FXML
    public void transportar(){
        for (int i = 0; i < relaciones.size(); i++) { //mueve relaciones
            punto = MouseInfo.getPointerInfo().getLocation();
            punto.x=punto.x-300;
            
            if(relaciones.get(i).poligono.seleccionar(punto)){
                if(relaciones.get(i) instanceof RelaciónDebil){
                    punto.x=punto.x+300;
                    Poligono poligono2 = relaciones.get(i).poligono;
                    RelaciónDebil nv= (RelaciónDebil)relaciones.get(i);
                    nv.poligono2.mover(punto);
                    moverRelacion(punto, relaciones.get(i));
                    contadorPuntos--;
                    puntosDeControl();
                    punto.x=punto.x-300;
                }
                punto.x=punto.x+300;
                relaciones.get(i).poligono.mover(punto);
                moverRelacion(punto, relaciones.get(i));
                contadorPuntos--;
                puntosDeControl();
                return;
            }
        }
        for (int i = 0; i < entidades.size(); i++){
            punto = MouseInfo.getPointerInfo().getLocation();
           posX=punto.getX()-280;
           posY=punto.getY();
           if (interseccionTransportar(entidades.get(i).rectangulo.getPuntos(),i)){
               if (entidades.get(i) instanceof EntidadDebil){
                    EntidadDebil entidadDebil = (EntidadDebil)entidades.get(i);
                    punto.x=punto.x-20;
                    punto.y=punto.y-20;
                    entidadDebil.rectangulo2.MoverRecantulo2(punto);
                    punto.x=punto.x+20;
                    punto.y=punto.y+20;
                }
               
                Rectangulo rectangulito = entidades.get(i).rectangulo;
                entidades.get(i).nombre.setLayoutX(posX-40);
                entidades.get(i).nombre.setLayoutY(posY-20);
                punto.x=punto.x-20;
                punto.y=punto.y-20;
                rectangulito.Mover(punto);
                
                moverEntidad(punto,entidades.get(i));
                
                contadorPuntos--;
                puntosDeControl();
                return;
               
           }
        }
        
        
    }
    public void moverRelacion(Point punto, Relacion relacion){
        
        for (int j = 0; j < relacion.getLineas().size(); j++) {
            pane.getChildren().remove(relacion.getLineas().get(j));
        }
        relacion.getLineas().clear();
        for (int j = 0; j < relacion.getEntidadesSelec().size(); j++) {
            Line linea=CrearRelacion(relacion.getEntidadesSelec().get(j), relacion.poligono, false);
            relacion.getLineas().add(linea);
            pane.getChildren().add(linea);
            if(relacion.unoAuno){
                linea=CrearRelacion(relacion.getEntidadesSelec().get(j), relacion.poligono, true);
                relacion.getLineas().add(linea);
                pane.getChildren().add(linea);
            }
            Point punto2=relacion.getEntidadesSelec().get(j).rectangulo.punto;
            if(!recurcion)
            moverEntidad(punto2, relacion.getEntidadesSelec().get(j));
        }
        
    }
    
    public void moverEntidad(Point punto, Entidad entidad){
        
        while(entidad.lineas.size()>0){
            pane.getChildren().remove(entidad.lineas.get(0));
            entidad.lineas.remove(0);
        }
        for (int j = 0; j <entidad.getRelaciones().size() ; j++) {
            Line lineaa=CrearRelacion(entidad, entidad.getRelaciones().get(j).poligono, false);
            lineaa.setStroke(Color.BLACK); 
            lineaa.setStrokeWidth(1);
            lineaa.setStrokeLineCap(StrokeLineCap.ROUND);
            entidad.lineas.add(lineaa);
            pane.getChildren().add(lineaa);
            if(entidad.getRelaciones().get(j).unoAuno){
                lineaa=CrearRelacion(entidad, entidad.getRelaciones().get(j).poligono, true);
                lineaa.setStroke(Color.BLACK); 
                lineaa.setStrokeWidth(1);
                lineaa.setStrokeLineCap(StrokeLineCap.ROUND);
                entidad.lineas.add(lineaa);
                pane.getChildren().add(lineaa);
            }
            recurcion=true;
            moverRelacion(punto, entidad.getRelaciones().get(j));
            recurcion=false;
            
        }

        for (int k = 0; k < circulos.size(); k++) {
            pane.getChildren().remove(circulos.get(k));
        }

        
    }
    @FXML
    public void dibujar(){
        
        if(sePuedeDibujar){
            punto = MouseInfo.getPointerInfo().getLocation();
            posX=punto.getX()-300;
            posY=punto.getY()-10;
            textito = new Text();
            textito.setFill(Color.BLACK);
            textito.setStyle(texto.getStyle());
            textito.setFont(texto.getFont());
            textito.setText(insertarTexto1.getText());
            textito.setLayoutX(posX+3);
            textito.setLayoutY(posY+10);            
            textito.setVisible(false);

            pane.getChildren().add(textito);
            
            if(insertarTexto1.getText().length()<6){
                largoTexto=6*10;
            }
            else{
                largoTexto=insertarTexto1.getText().length()*10;
            }
            

            if (sePuedeCrearEntidad){
                contextMenuEntidades.show(pane,posX+300,posY+10);
                
                sePuedeCrearEntidad=false;
            }
            else if(sePuedeCrearRelacion){
                //textito.setVisible(true);
                Poligono poligono=new Poligono(pane);
                Poligono poligono2= new Poligono(pane);
                Relacion relacion = new Relacion(texto,poligono,entidadesSeleccionadas);
                Relacion relacion2= new RelaciónDebil(poligono2,texto,poligono,entidadesSeleccionadas);
                
                if(entidadesSeleccionadas.size()>0){
                    if(entidadesSeleccionadas.size()==1||entidadesSeleccionadas.size()==2){
                        for (int i = 0; i < entidadesSeleccionadas.size(); i++) { //busco si hay una entidad debil
                            if(entidadesSeleccionadas.get(i) instanceof EntidadDebil){
                                poligono2.Dibujar(4, (int) largoTexto+5, punto);
                                relaciones.add(relacion2);
                                break;
                            }
                                
                        }
                        poligono.Dibujar(4, (int) largoTexto, punto);
                    }
                    else{
                        for (int i = 0; i < entidadesSeleccionadas.size(); i++) {
                            if(entidadesSeleccionadas.get(i) instanceof EntidadDebil){
                                poligono2.Dibujar(entidadesSeleccionadas.size(), (int) largoTexto+5, punto);
                                break;
                            }
                                
                        }
                        poligono.Dibujar(entidadesSeleccionadas.size(), (int) largoTexto, punto);
                    }
                
                
                    textito.setLayoutX(posX-30);
                    textito.setLayoutY(posY);            
                    textito.setVisible(true);
                    if(entidadesSeleccionadas.size()==1){
                        relacion.unoAuno=true;
                        relacion2.unoAuno=true;
                    }
                    relaciones.add(relacion);

                    //funcion para unir
                    for(int i=0; i<entidadesSeleccionadas.size();i++){ //entidades a relacionar
                        //dibujar linea que une
                        if(entidadesSeleccionadas.get(i) instanceof EntidadDebil){
                            entidadesSeleccionadas.get(i).relaciones.add(relacion2);
                            Line lineaa =CrearRelacionDoble(entidadesSeleccionadas.get(i), poligono, false);
                            lineaa.setStroke(Color.BLACK); //colo de la linea que une
                            lineaa.setStrokeWidth(1);
                            lineaa.setStrokeLineCap(StrokeLineCap.ROUND);
                            pane.getChildren().add(lineaa);
                            entidadesSeleccionadas.get(i).getLineas().add(lineaa);
                            relacion2.getLineas().add(lineaa);
                            
                        }
                        entidadesSeleccionadas.get(i).relaciones.add(relacion);
                        Line lineaa =CrearRelacion(entidadesSeleccionadas.get(i), poligono, false);
                        lineaa.setStroke(Color.BLACK); //colo de la linea que une
                        lineaa.setStrokeWidth(1);
                        lineaa.setStrokeLineCap(StrokeLineCap.ROUND);
                        pane.getChildren().add(lineaa);
                        entidadesSeleccionadas.get(i).getLineas().add(lineaa);
                        relacion.getLineas().add(lineaa);
                    }
                    if(entidadesSeleccionadas.size()==1){
                        relacion.unoAuno=true;
                        Line lineaa =CrearRelacion(entidadesSeleccionadas.get(0), poligono, true);
                        lineaa.setStroke(Color.BLACK); //coloco de la linea que une
                        lineaa.setStrokeWidth(1);
                        lineaa.setStrokeLineCap(StrokeLineCap.ROUND);
                        pane.getChildren().add(lineaa);
                        entidadesSeleccionadas.get(0).getLineas().add(lineaa);
                    }
                    sePuedeCrearRelacion=false;  
                    for (int i = 0; i < entidadesSeleccionadas.size(); i++) {
                        entidadesSeleccionadas.get(i).rectangulo.Borrar();
                        entidadesSeleccionadas.get(i).rectangulo.Dibujar();
                        entidadesSeleccionadas.get(i).rectangulo.seleccionado=false;
                    }
                    contadorPuntos--;
                    puntosDeControl();
                }
                else{
                    textito.setVisible(false);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Informacion");
                    alert.setHeaderText("Entidades");
                    alert.setContentText("Debe Seleccionar entidades");
                    alert.showAndWait();
                    
                }
            }
            else if(sePuedeCrearAtributo){
                contextMenuAtributos.show(pane,posX+300,posY+10);
                System.out.println("Poligonazo");
                //textito.setVisible(true);
                Poligono poligono=new Poligono(pane);
                Relacion relacion = new Relacion(texto,poligono,entidadesSeleccionadas);
                if(entidadesSeleccionadas.size()==1){
                    System.out.println("ENTREEEEEEEEEE");
                    textito.setLayoutX(posX-30);
                    textito.setLayoutY(posY);            

                    //funcion para unir
                    for(int i=0; i<entidadesSeleccionadas.size();i++){ //entidades a relacionar
                        //dibujar linea que une
                        entidadesSeleccionadas.get(i).relaciones.add(relacion);
                        Line lineaa =CrearRelacion(entidadesSeleccionadas.get(i), poligono, false);
                        lineaa.setStroke(Color.BLACK); //colo de la linea que une
                        lineaa.setStrokeWidth(1);
                        lineaa.setStrokeLineCap(StrokeLineCap.ROUND);
                        pane.getChildren().add(lineaa);
                        entidadesSeleccionadas.get(i).getLineas().add(lineaa);
                        relacion.getLineas().add(lineaa);
                    }

                    sePuedeCrearRelacion=false;  
                    for (int i = 0; i < entidadesSeleccionadas.size(); i++) {
                        entidadesSeleccionadas.get(i).rectangulo.Borrar();
                        entidadesSeleccionadas.get(i).rectangulo.Dibujar();
                        entidadesSeleccionadas.get(i).rectangulo.seleccionado=false;
                    }
                }
                else{
                    textito.setVisible(false);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Informacion");
                    alert.setHeaderText("Entidades");
                    alert.setContentText("Debe Seleccionar entidades");
                    alert.showAndWait();
                    
                }
                sePuedeCrearAtributo=false;
            }
        
        }
        else if (sePuedeSeleccionar){
            try{
                Point punto = MouseInfo.getPointerInfo().getLocation();
                posX=punto.getX()-300;
                posY=punto.getY()-25;
                for (int i = 0; i < entidades.size(); i++) {
                    //entidades.get(i).rectangulo.imprimirPuntos();
                    if (interseccionRectangulo(entidades.get(i).rectangulo.getPuntos(),i)){
                        entidadesSeleccionadas.add(entidades.get(i));
                    }
                    else{
                        //IMPLEMENTAR LAS RELACIONES (else if(interseccionRelacion(...)))
                    }
                }
                
            }catch(Exception e){
                
            }
        }
        else if (sePuedeSeleccionarBorrar){
            try{
                Point punto = MouseInfo.getPointerInfo().getLocation();
                posX=punto.getX()-300;
                posY=punto.getY()-25;
                for (int i = 0; i < entidades.size(); i++) {
                    //entidades.get(i).rectangulo.imprimirPuntos();
                    if (interseccionRectangulo(entidades.get(i).rectangulo.getPuntos(),i)){
                        pane.getChildren().remove(entidades.get(i));
                        pane.getChildren().remove(entidades.get(i).rectangulo);
                        entidades.get(i).rectangulo.Borrar();
                        entidades.get(i).nombre.setVisible(false);
                        entidades.remove(i);
                        entidades.get(i).getLineas().clear();
                        pane.getChildren().remove(entidades.get(i).getLineas());
                        
                        
                    }
                    sePuedeSeleccionarBorrar=false;
                    
                }
                
            }catch(Exception e){
                
            }
        }
        insertarTexto1.setText("");
        sePuedeDibujar=false;
    
    }
    
    @FXML
    public void crearEntidad(){
        
        nombre.setVisible(true);
        sePuedeCrearEntidad=true;
        sePuedeCrearRelacion=false;
        nombre.setText("Nombre entidad: ");
        insertarTexto1.setVisible(true);
        botonCrear.setVisible(true);
        textoBotonCrear.setVisible(true);
        sePuedeSeleccionar=false;
        nombreEntidad.setText("");
        if(entidadesSeleccionadas.size()>0){
            for (int i = 0; i < entidadesSeleccionadas.size(); i++) {
                entidadesSeleccionadas.get(i).rectangulo.Borrar();
                entidadesSeleccionadas.get(i).rectangulo.Dibujar();
                entidadesSeleccionadas.get(i).rectangulo.seleccionado=false;
            }
        }
    }
    
    @FXML
    public void crearRelacion(){
        for (int i = 0; i < entidadesSeleccionadas.size(); i++) {
                entidadesSeleccionadas.get(i).rectangulo.Borrar();
                entidadesSeleccionadas.get(i).rectangulo.Dibujar();
                entidadesSeleccionadas.get(i).rectangulo.seleccionado=false;
            }
        entidadesSeleccionadas=new ArrayList();
        
        sePuedeDibujar=false;
        sePuedeSeleccionar=true;
        nombre.setVisible(true);
        sePuedeCrearRelacion=true;
        sePuedeCrearEntidad=false;
        nombre.setText("Nombre relación: ");
        insertarTexto1.setVisible(true);
        botonCrear.setVisible(true);
        textoBotonCrear.setVisible(true);
    }
    
    @FXML
    public void crearAtributo(){
        posX= MouseInfo.getPointerInfo().getLocation().x;
        posY= MouseInfo.getPointerInfo().getLocation().y;
        entidadesSeleccionadas=new ArrayList();
        sePuedeDibujar=false;
        sePuedeSeleccionar=true;
        nombre.setVisible(true);
        sePuedeCrearRelacion=false;
        sePuedeCrearEntidad=false;
        sePuedeCrearAtributo=true;
        nombre.setText("Nombre Atributo: ");
        insertarTexto1.setVisible(true);
        botonCrear.setVisible(true);
        textoBotonCrear.setVisible(true);
    }
    
    @FXML
    public void crear(){
        if(insertarTexto1.getText().isEmpty()||insertarTexto1.getText().length()>20 ){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Informacion");
        alert.setHeaderText("Texto");
        alert.setContentText("Debe insertar un nombre en el cuadro de texto / Máximo 20 caracteres");
        alert.showAndWait();
        sePuedeDibujar=false;
        nombre.setVisible(true);
        insertarTexto1.setVisible(true);
        botonCrear.setVisible(true);
        textoBotonCrear.setVisible(true);
        }
        else{
            sePuedeDibujar=true;
            nombre.setVisible(false);
            insertarTexto1.setVisible(false);
            botonCrear.setVisible(false);
            textoBotonCrear.setVisible(false);
            sePuedeSeleccionar=false;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        crearContextMenuAtributos();
        crearContextMenuEntidades();
        texto.setVisible(false);
        nombre.setVisible(false);
        botonCrear.setVisible(false);
        botonBorrar.setVisible(false);
        textoBotonCrear.setVisible(false);
        insertarTexto1.setVisible(false);
        circulo.setVisible(false);
    }     
    
    public boolean interseccionRectangulo(ArrayList<Point> puntos,int entidadNum){
        if ((entidades.get(entidadNum).rectangulo.getPuntos().get(0).x<=posX
                &&posX<=entidades.get(entidadNum).rectangulo.getPuntos().get(1).x
                &&entidades.get(entidadNum).rectangulo.getPuntos().get(1).y<=posY
                &&posY<=entidades.get(entidadNum).rectangulo.getPuntos().get(2).y)&&!entidades.get(entidadNum).rectangulo.seleccionado ){
                entidades.get(entidadNum).rectangulo.Dibujar2();
                entidades.get(entidadNum).rectangulo.seleccionado=true;
                return true;
        }
        return false;
    }
    
    public boolean interseccionTransportar(ArrayList<Point> puntos,int entidadNum){
        if (entidades.get(entidadNum).rectangulo.getPuntos().get(0).x<=(posX-20)
                &&(posX-20)<=entidades.get(entidadNum).rectangulo.getPuntos().get(1).x
                &&entidades.get(entidadNum).rectangulo.getPuntos().get(1).y<=(posY-20)
                &&(posY-20)<=entidades.get(entidadNum).rectangulo.getPuntos().get(2).y){
                Rectangulo rectangulo = entidades.get(entidadNum).rectangulo;
                rectangulo.Dibujar2();
                return true;
        }
        return false;
    }
    
    @FXML
    public void puntosDeControl(){
        contadorPuntos++;
        if(contadorPuntos%2!=0){
            for (int i = 0; i < entidades.size(); i++) {
            for (int j = 0; j < entidades.get(i).rectangulo.puntos.size(); j++) {
                Circle circulo = new Circle();
                circulo.setStroke(Color.TOMATO);
                circulo.setStrokeWidth(6);
                circulo.setStrokeLineCap(StrokeLineCap.ROUND);
                circulo.setCenterX(entidades.get(i).rectangulo.puntos.get(j).x);
                circulo.setCenterY(entidades.get(i).rectangulo.puntos.get(j).y);            
                circulos.add(circulo);
                pane.getChildren().add(circulo);
                
            }
        }
        if(relaciones.size()>0){
            for (int i = 0; i < relaciones.size(); i++) {
                for (int j = 0; j < relaciones.get(i).poligono.puntos.size(); j++) {
                     Circle circulo = new Circle();
                circulo.setStroke(Color.MAGENTA);
                circulo.setStrokeWidth(6);
                circulo.setStrokeLineCap(StrokeLineCap.ROUND);
                circulo.setCenterX(relaciones.get(i).poligono.puntos.get(j).x);
                circulo.setCenterY(relaciones.get(i).poligono.puntos.get(j).y);            
                circulos.add(circulo);
                pane.getChildren().add(circulo);
                }
               
            }
        }
        }
        else{
            for (int i = 0; i < circulos.size(); i++) {
                pane.getChildren().remove(circulos.get(i));
        }
        
        }
    }
    
    @FXML
    public void limpiar(){
        pane.getChildren().clear();
        relaciones.clear();
        entidades.clear();
        entidadesSeleccionadas.clear();
        Poligono poligono=new Poligono(pane);
        poligono.puntosBolean.clear();
        
    }
    
    @FXML
    public void guardar() throws AWTException, IOException{
        BufferedImage imagen=Imagen.capturarPantalla();
        String extension="JPG";
        imagen=imagen.getSubimage(300,20,1065, 700);
         FileChooser fileChooser = new FileChooser();
                 
        //Ingreso de filtro de extensión
        FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("png", ".png");
        fileChooser.getExtensionFilters().add(extFilter);

        //Mostrar diálogo de guardar archivo
        final Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
          
        if(file != null){
            try {
                ImageIO.write(imagen, "png", file);
            } catch (IOException ex) {
            }
        }
    
          
    }
    
    public void crearContextMenuAtributos(){
        MenuItem item1= new MenuItem("Genéricos");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("PUNTEADa");
                item1AccionAtributos();
            }
        });

        MenuItem item2= new MenuItem("Clave");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Clave");
                item2AccionAtributos();
            }
        });

        MenuItem item3= new MenuItem("Clave Parcial");
        item3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Clave parcial");
                item3AccionAtributos();
            }
        });

        MenuItem item4= new MenuItem("multiValuados");
        item4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("MultiValuados");
                item4AccionAtributos();
            }
        });

        MenuItem item5= new MenuItem("Compuesto");
        item5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Compuesto");
                item5AccionAtributos();
            }
        });

        MenuItem item6= new MenuItem("Derivado");
        item6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Derivado");
                item6AccionAtributos();
            }
        });
        
        contextMenuAtributos.getItems().addAll(item1,item2,item3,item4,item5,item6);
    }
    
    public void crearContextMenuEntidades(){
        MenuItem item1= new MenuItem("Fuerte");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Debil");
                item1AccionEntidades();
            }
        });

        MenuItem item2= new MenuItem("Débil");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Debil");
                item2AccionEntidades();
            }
        });
        contextMenuEntidades.getItems().addAll(item1,item2);
    }
    
    //generico
    public void item1AccionAtributos(){
        //donde está el texto
        textito.setVisible(true);
        //donde está la entidad para relacionarla
        Atributo atributo=new Atributo(pane, punto, texto, TipoAtributo.generico, 20);
        atributo.dibujar();
        Line linea=CrearRelacion(entidadesSeleccionadas.get(0), atributo.poligono, false);
        pane.getChildren().add(linea);
    } 
    
    //clave
    public void item2AccionAtributos(){
        textito.setVisible(true);
        //donde está la entidad para relacionarla
        Atributo atributo=new Atributo(pane, punto, texto, TipoAtributo.clave, 20);
        atributo.dibujar();
        Line linea=CrearRelacion(entidadesSeleccionadas.get(0), atributo.poligono, false);
        pane.getChildren().add(linea);
    } 
    
    //clave parcial
    public void item3AccionAtributos(){
        textito.setVisible(true);
        Atributo atributo=new Atributo(pane, punto, texto, TipoAtributo.claveParcial, 20);
        atributo.dibujar();
        Line linea=CrearRelacion(entidadesSeleccionadas.get(0), atributo.poligono, false);
        pane.getChildren().add(linea);
    } 
    
    //multivaluados
    public void item4AccionAtributos(){
        textito.setVisible(true);
        Atributo atributo=new Atributo(pane, punto, texto, TipoAtributo.multivaluados, 20);
        atributo.dibujar();
        Line linea=CrearRelacion(entidadesSeleccionadas.get(0), atributo.poligono, false);
        pane.getChildren().add(linea);
    } 
    
    //compuestos
    public void item5AccionAtributos(){
        //textito.setVisible(true);
    } 
    
    //derivado
    public void item6AccionAtributos(){
        textito.setVisible(true);
        //donde está la entidad para relacionarla
        Atributo atributo=new Atributo(pane, punto, texto, TipoAtributo.Derivados, 20);
        
        atributo.dibujar();
        Line linea=CrearRelacion(entidadesSeleccionadas.get(0), atributo.poligono, false);
        pane.getChildren().add(linea);
    } 
    
    //fuerte (normal)
    public void item1AccionEntidades(){
        textito.setVisible(true);
        //crearRectangulo
        Rectangulo rectangulo = new Rectangulo(pane,largoTexto,punto);
        rectangulo.Dibujar();


        Entidad entidad = new Entidad(textito,rectangulo);
        entidades.add(entidad);
    } 
    
    //debil (doble linea)
    public void item2AccionEntidades(){
        textito.setVisible(true);
        //crearRectangulo
        Rectangulo rectangulo = new Rectangulo(pane,largoTexto,punto);
        rectangulo.Dibujar();
        punto.x=punto.x-3;
        punto.y=punto.y-3;
        largoTexto+=6;
        Rectangulo rectangulo2 = new Rectangulo(pane,largoTexto,punto);
        rectangulo2.lineaInferior= new Line(punto.x-300,punto.y+21,punto.x-300+largoTexto,punto.y+21);
        rectangulo2.lineaIzquierda= new Line(punto.x-300,punto.y-25,punto.x-300,punto.y+21);
        rectangulo2.lineaDerecha= new Line(punto.x+largoTexto-300,punto.y-25,punto.x+largoTexto-300,punto.y+21);
        //correr las lineas
        rectangulo2.Dibujar();

        Entidad entidadDebil = new EntidadDebil(textito,rectangulo,rectangulo2);
	entidades.add(entidadDebil);
    } 
    
    @FXML 
    public void seleccionar(){
        sePuedeSeleccionarBorrar=true;
        sePuedeDibujar=false;
        botonBorrar.setVisible(true);
        
    }
    @FXML
    public void botonBorrarFuncion(){
        
    }
}

