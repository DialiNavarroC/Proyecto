package proyectoavanzada;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
 
class Imagen{
  public static BufferedImage capturarPantalla() throws
           AWTException, IOException {
     BufferedImage captura = new Robot().createScreenCapture(
           new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
 
     // Guardar Como JPEG
     return captura;
  }
}