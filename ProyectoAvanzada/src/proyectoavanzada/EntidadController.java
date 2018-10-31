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
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    public boolean muevete=false;
    
    @FXML 
    public Circle circulo;
    public ContextMenu context = new ContextMenu();
    @FXML
    public Text nombreEntidad;
    public double largoTexto=0;
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
    
    @FXML
    public void transportar(){
        for (int i = 0; i < entidades.size(); i++) {
            punto = MouseInfo.getPointerInfo().getLocation();
            posX=punto.getX()-280;
            posY=punto.getY();
            if (interseccionTransportar(entidades.get(i).rectangulo.getPuntos(),i)){
                muevete=true;
                
            }
            else {
                muevete=false;
            }
            if (muevete){
                Rectangulo rectangulito = entidades.get(i).rectangulo;
                entidades.get(i).nombre.setLayoutX(posX-40);
                entidades.get(i).nombre.setLayoutY(posY-20);
                punto.x=punto.x-20;
                punto.y=punto.y-20;
                rectangulito.Mover(punto);
                while(entidades.get(i).lineas.size()>0){
                    pane.getChildren().remove(entidades.get(i).lineas.get(0));
                    entidades.get(i).lineas.remove(0);
                }
                for (int j = 0; j <entidades.get(i).getRelaciones().size() ; j++) {
                    Line lineaa=CrearRelacion(entidades.get(i), entidades.get(i).getRelaciones().get(j).poligono, false);
                    lineaa.setStroke(Color.BLACK); 
                    lineaa.setStrokeWidth(1);
                    lineaa.setStrokeLineCap(StrokeLineCap.ROUND);
                    entidades.get(i).lineas.add(lineaa);
                    pane.getChildren().add(lineaa);
                    if(entidades.get(i).getRelaciones().get(j).unoAuno){
                        lineaa=CrearRelacion(entidades.get(i), entidades.get(i).getRelaciones().get(j).poligono, true);
                        lineaa.setStroke(Color.BLACK); 
                        lineaa.setStrokeWidth(1);
                        lineaa.setStrokeLineCap(StrokeLineCap.ROUND);
                        entidades.get(i).lineas.add(lineaa);
                        pane.getChildren().add(lineaa);
                    }
                    
                }
               
                for (int k = 0; k < circulos.size(); k++) {
                    pane.getChildren().remove(circulos.get(k));
                }
                break;
            }
        }
        /*for (int i = 0; i < relaciones.size()&& !muevete; i++) { //mueve relaciones
            punto = MouseInfo.getPointerInfo().getLocation();
            if(relaciones.get(i).poligono.seleccionar(punto)){
                relaciones.get(i).poligono.mover(punto);
                for (int j = 0; j < relaciones.get(i).getLineas().size(); j++) {
                    pane.getChildren().remove(relaciones.get(i).getLineas().get(j));
                }
                relaciones.get(i).getLineas().clear();
                for (int j = 0; j < relaciones.get(i).getEntidadesSelec().size(); j++) {
                    Line linea=CrearRelacion(relaciones.get(i).getEntidadesSelec().get(j), relaciones.get(i).poligono, false);
                    
                    relaciones.get(i).getLineas().add(linea);
                    pane.getChildren().add(linea);
                    if(relaciones.get(i).unoAuno){
                        linea=CrearRelacion(relaciones.get(i).getEntidadesSelec().get(j), relaciones.get(i).poligono, true);
                        relaciones.get(i).getLineas().add(linea);
                        pane.getChildren().add(linea);
                    }
                }
                break;
            }
        }*/
    }
    
    @FXML
    public void dibujar(){
        
        if(sePuedeDibujar){
            punto = MouseInfo.getPointerInfo().getLocation();
            posX=punto.getX()-300;
            posY=punto.getY()-10;
            Text textito = new Text();
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
                textito.setVisible(true);
                //crearRectangulo
                Rectangulo rectangulo = new Rectangulo(pane,largoTexto,punto);
                rectangulo.Dibujar();
                
                
                Entidad entidad = new Entidad(textito,rectangulo);
                entidades.add(entidad);
                sePuedeCrearEntidad=false;
            }
            else if(sePuedeCrearRelacion){
                textito.setVisible(true);
                Poligono poligono=new Poligono(pane);
                Relacion relacion = new Relacion(texto,poligono,entidadesSeleccionadas);
                if(entidadesSeleccionadas.size()>0){
                    if(entidadesSeleccionadas.size()==1||entidadesSeleccionadas.size()==2){
                        poligono.Dibujar(4, (int) largoTexto, punto);
                    }
                    else{
                    poligono.Dibujar(entidadesSeleccionadas.size(), (int) largoTexto, punto);
                    }
                
                
                textito.setLayoutX(posX-30);
                textito.setLayoutY(posY);            
                textito.setVisible(true);
                if(entidadesSeleccionadas.size()==1){
                    relacion.unoAuno=true;
                }
                relaciones.add(relacion);
                
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
                context.show(pane,posX+300,posY);
                System.out.println("Poligonazo");
                //textito.setVisible(true);
                Poligono poligono=new Poligono(pane);
                Relacion relacion = new Relacion(texto,poligono,entidadesSeleccionadas);
                if(entidadesSeleccionadas.size()==1){
                System.out.println("ENTREEEEEEEEEE");
                textito.setLayoutX(posX-30);
                textito.setLayoutY(posY);            
                textito.setVisible(true);
                
               
                
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
        
        MenuItem item1= new MenuItem("Genéricos");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("PUNTEADa");
                item1Accion();
                //poligono.DibujarElipsePunteada(1000, (int) largoTexto, punto);
            }
        });

        MenuItem item2= new MenuItem("Clave");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Clave");
            }
        });

        MenuItem item3= new MenuItem("Clave Parcial");
        item3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Clave parcial");
            }
        });

        MenuItem item4= new MenuItem("multiValuados");
        item4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("MultiValuados");
            }
        });

        MenuItem item5= new MenuItem("Compuesto");
        item5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Compuesto");
            }
        });

        MenuItem item6= new MenuItem("Derivado");
        item6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Derivado");
            }
        });
        context.getItems().addAll(item1,item2,item3,item4,item5,item6);    
        texto.setVisible(false);
        nombre.setVisible(false);
        botonCrear.setVisible(false);
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
    public void item1Accion(){
        Poligono poligono = new Poligono(pane);
        poligono.DibujarElipseNormal(1000, (int)largoTexto, punto);
        Line linea=CrearRelacion(entidadesSeleccionadas.get(0), poligono, false);
        pane.getChildren().add(linea);
        System.out.println("AKJSNFASDKLHFN<SLKFCD.S<.LMFDK");
    }
}
