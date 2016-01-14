/**
 * ICS4U
 *  
 * 
 * Window Class
 */
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
public class Window extends JFrame{
  //variables
  private Deck deck = new Deck();
  private Display display = new Display(deck);
  //constants
  public static final int WINDOW_WIDTH = 1000;
  public static final int WINDOW_HEIGHT = 600;
  
  public Window()
  {
    super();
    
    //set entire frame details
    setContentPane (display);
    pack();
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo (null);
    setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    setTitle("---Deck of Cards---");
    
    setVisible (true);
  }
  
  //main method
  public static void main(String[] args)
  {
    Window w = new Window();
  }
}
