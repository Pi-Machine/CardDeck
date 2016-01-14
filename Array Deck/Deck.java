/**
 * ICS4U
 *  
 * 
 * Deck Class
 */
import java.awt.*;
public class Deck {
  //variables
  private Card[] deck;
  public static final int CARDS_IN_SUIT = 13;
  public static final int NUM_OF_SUITS = 4;
  public static final int TOP_BUFFER = 50;
  public static final int LEFT_BUFFER = 50;
  public static final int DISPLAY_WIDTH = (Window.WINDOW_WIDTH - 2 * LEFT_BUFFER) / (CARDS_IN_SUIT + 1);
  public static final int DISPLAY_HEIGHT = (Window.WINDOW_HEIGHT - 2 * TOP_BUFFER - Display.TOOL_HEIGHT) / (NUM_OF_SUITS + 1);
 
  //default constructor
  public Deck()
  {
    this.deck = new Card[52];
    for(int i = 0; i < deck.length; i++)
    {
      deck[i] = new Card(i);
    }
  }
  
  //constructor
  public Deck(Card[] cards)
  {
    this.deck = cards;
  }
  
  //method to display the cards
  public void show(Graphics g)
  {
    for(int i = 0; i < deck.length; i++)
    {
      deck[i].show(g, LEFT_BUFFER + (i % CARDS_IN_SUIT) * DISPLAY_WIDTH, Display.TOOL_HEIGHT + TOP_BUFFER + (i / CARDS_IN_SUIT) * DISPLAY_HEIGHT);
    }
  }
  
  //method to display the cards faceup
  public void showUp(Graphics g)
  {
    for(int i = 0; i < deck.length; i++)
    {
      if(!deck[i].getFaceup())
      {
        deck[i].flip();
      }
      deck[i].show(g, LEFT_BUFFER + (i % CARDS_IN_SUIT) * DISPLAY_WIDTH, Display.TOOL_HEIGHT + TOP_BUFFER + (i / CARDS_IN_SUIT) * DISPLAY_HEIGHT);
    }
  }
  
  //method to shuffle the deck
  public void shuffle()
  {
    for(int i = 0; i < deck.length; i++)
    {
      int index = (int)(Math.random() * deck.length);
      Card temp = deck[i].copy();
      deck[i] = deck[index];
      deck[index] = temp;
    }
  }
  
  //Overloaded
  //method to deal a card from the top of the deck
  public Card deal()
  {
    return deal(deck.length - 1);
  }
  
  //Overloaded
  //method to deal a card from a position
  public Card deal(int index)
  {
    Card[] temp = new Card[deck.length - 1];
    int counter = 0;
    Card c = new Card();
    for(int i = 0; i < deck.length; i++)
    {
      if(i == index)
      {
        c = deck[i];
      }else{
        temp[counter] = deck[i];
        counter++;
      }
    }
    deck = temp;
    return c;
  }
  
  //method to add a card to the end of the deck
  public void add(Card c)
  {
    Card[] temp = new Card[deck.length + 1];
    for(int i = 0; i < deck.length; i++)
    {
      temp[i] = deck[i];
    }
    temp[temp.length - 1] = c;
    this.deck = temp;
  }
  
  //method to search for cards
  public int[] search(Card c)
  {
    int counter = 0;
    int[] indexes = new int[deck.length];
    for(int i = 0; i < deck.length; i++)
    {
      Card current = deck[i];
      if(current.getSuit() == c.getSuit() && current.getRank() == c.getRank())
      {
        indexes[counter] = i;
        counter++;
      }
    }
    int[] returnIndexes = new int [counter];
    for(int i = 0; i < counter; i++)
    {
      returnIndexes[i] = indexes[i];
    }
    return returnIndexes;
  }
  
  //quicksort driver method
  public void quicksort()
  {
    quicksortrec(0, deck.length - 1);
  }
  
  //quicksort recursive method
  public void quicksortrec(int first, int last)
  {
    //base case
    if(last - first <= 0)
    {
      return;
    }else{
      int left = first;
      int right = last;
      Card pivot = deck[last];
      while(left <= right)
      {
        while(deck[left].compareTo(pivot) > 0)
        {
          left++;
        }
        while(deck[right].compareTo(pivot) < 0)
        {
          right--;
        }
        if(left <= right)
        {
          Card temp = deck[left];
          deck[left] = deck[right];
          deck[right] = temp;
          left++;
          right--;
        }
      }
      quicksortrec(first, right);
      quicksortrec(left, last);
    }
  }
  
  //selection sort algorithm
  public void selectionsort()
  {
    for(int i = 0; i < deck.length; i++)
    {
      Card min = deck[i];
      int index = i;
      for(int j = i; j < deck.length; j++)
      {
        if(min.compareTo(deck[j]) < 0)
        {
          min = deck[j].copy();
          index = j;
        }
      }
      //swapping
      Card temp = deck[i];
      deck[i] = min;
      deck[index] = temp;
    }
  }
  
  //method to flip cards at given indexes
  public void flip(int[] indexes)
  {
    for(int i = 0; i < indexes.length; i++)
    {
      deck[indexes[i]].flip();
    }
  }
}
