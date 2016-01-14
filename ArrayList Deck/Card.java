/**
 * ICS4U
 *  
 * 
 * Card Class
 */
import java.io.*;
import javax.imageio.*;
import java.awt.*;
public class Card implements Comparable{
  //variables
  private int rank; //card rank
  private int suit; //card suit
  private int value; //card value
  private boolean faceUp; //faces up or down
  private Image image; //image for the card
  
  //global constants
  //global constants for suits
  public static final int CLUB = 2;
  public static final int DIAMOND = 3;
  public static final int HEART = 1;
  public static final int SPADE = 0;
  public static final int HEIGHT = 50;
  public static final String FOLDER = "cards\\";
  private final Image back;
  
  //Default Constructor
  public Card()
  {
    this.suit = 0;
    this.rank = 0;
    this.value = 0;
    this.faceUp = true;
    this.image = null;
    this.back = loadImage(FOLDER + "b.gif");
  }
  
  //Constructor
  public Card(int v)
  {
    this.suit = v / Deck.CARDS_IN_SUIT;
    this.rank = v % Deck.CARDS_IN_SUIT;
    this.value = v;
    this.faceUp = true;
    this.image = this.loadImage(FOLDER + (v + 1) + ".gif");
    this.back = loadImage(FOLDER + "b.gif");
  }
  
  //Constructor
  public Card(int r, int s)
  {
    this.suit = s;
    this.rank = r;
    this.value = 13 * s + r;
    this.faceUp = true;
    this.image = this.loadImage(FOLDER + (value + 1) + ".gif");
    this.back = loadImage(FOLDER + "b.gif");
  }
  
  //Constructor
  public Card(int v, boolean up)
  {
    this.suit = v / Deck.CARDS_IN_SUIT;
    this.rank = v % Deck.CARDS_IN_SUIT;
    this.value = v;
    this.faceUp = up;
    this.image = this.loadImage(FOLDER + (v + 1) + ".gif");
    this.back = loadImage(FOLDER + "b.gif");
  }
  
  //method to show the card
  public void show(Graphics g, int x, int y)
  {
    if (faceUp)
    {
      g.drawImage (image, x, y, null);
    }
    else
    {
      g.drawImage (back, x, y, null);
    }
  }
  
  //method to flip the card
  public void flip()
  {
    faceUp = !faceUp;
  }
  
  //Copy of a card
  public Card copy()
  {
    return new Card(this.value, this.faceUp);
  }
  
  //method to get an image
  public Image loadImage (String name)
  {
    if(name.equals(FOLDER + "0.gif"))
    {
      return null;
    }
    Image img = null;
    try
    {
      img = ImageIO.read (new File (name));
    }catch(Exception e)
    {
      System.out.println("File not found.");
    }
    return img;
  }
  
  //equals method for comparison
  public boolean equals(Card other)
  {
    return this.value == other.getValue();
  }
  
  //compareTo method
  @Override
  public int compareTo(Object other)
  {
    Card c = (Card) other;
    if(this.value < c.getValue())
      return -1;
    if(this.value > c.getValue())
      return 1;
    return 0;
  }
  
  //GETTERS
  public boolean getFaceup()
  {
    return faceUp;
  }
  public int getRank()
  {
    return rank;
  }
  public int getSuit()
  {
    return suit;
  }
  public int getValue()
  {
    return value;
  }
  public boolean exists()
  {
    return value <= 51 && value >= 0;
  }
}
