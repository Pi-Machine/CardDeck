/**
 * ICS4U
 *  
 * 
 * Deck Class
 */
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Deck {
  //variables
  private List<Card> deck;
  public static final int CARDS_IN_SUIT = 13;
  public static final int NUM_OF_SUITS = 4;
  public static final int TOP_BUFFER = 50;
  public static final int LEFT_BUFFER = 50;
  public static final int DISPLAY_WIDTH = (Window.WINDOW_WIDTH - 2 * LEFT_BUFFER) / (CARDS_IN_SUIT + 1);
  public static final int DISPLAY_HEIGHT = (Window.WINDOW_HEIGHT - 2 * TOP_BUFFER - Display.TOOL_HEIGHT) / (NUM_OF_SUITS + 1);
 
  //default constructor
  public Deck()
  {
    this.deck = new ArrayList<Card>();
    for(int i = 0; i < 52; i++)
    {
      deck.add(new Card(i));
    }
  }
  
  //constructor
  public Deck(List<Card> cards)
  {
    this.deck = cards;
  }
  
  //method to display the cards
  public void show(Graphics g)
  {
    for(int i = 0; i < deck.size(); i++)
    {
      deck.get(i).show(g, LEFT_BUFFER + (i % CARDS_IN_SUIT) * DISPLAY_WIDTH, Display.TOOL_HEIGHT + TOP_BUFFER + (i / CARDS_IN_SUIT) * DISPLAY_HEIGHT);
    }
  }
  
  //method to display the cards faceup
  public void showUp(Graphics g)
  {
    for(int i = 0; i < deck.size(); i++)
    {
      if(!deck.get(i).getFaceup())
      {
        deck.get(i).flip();
      }
      deck.get(i).show(g, LEFT_BUFFER + (i % CARDS_IN_SUIT) * DISPLAY_WIDTH, Display.TOOL_HEIGHT + TOP_BUFFER + (i / CARDS_IN_SUIT) * DISPLAY_HEIGHT);
    }
  }
  
  //method to shuffle the deck
  public void shuffle()
  {
    for(int i = 0; i < deck.size(); i++)
    {
      int index = (int)(Math.random() * deck.size());
      Card temp = deck.get(i).copy();
      deck.set(i, deck.get(index));
      deck.set(index, temp);
    }
  }
  
  //Overloaded
  //method to deal a card from the top of the deck
  public Card deal()
  {
    return deal(deck.size() - 1);
  }
  
  //Overloaded
  //method to deal a card from a position
  public Card deal(int index)
  {
    return deck.remove(index);
  }
  
  //method to add a card to the end of the deck
  public void add(Card c)
  {
    this.deck.add(c);
  }
  
  //method to search for cards
  public ArrayList<Integer> search(Card c)
  {
    ArrayList<Integer> indexes = new ArrayList<Integer>();
    for(int i = 0; i < deck.size(); i++)
    {
      Card current = deck.get(i);
      if(current.equals(c))
      {
        indexes.add(i);
      }
    }
    return indexes;
  }
  
  //quicksort driver method
  public void quicksort()
  {
    quicksortrec(0, deck.size() - 1);
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
      Card pivot = deck.get(last);
      while(left <= right)
      {
        while(deck.get(left).getRank() < pivot.getRank())
        {
          left++;
        }
        while(deck.get(right).getRank() > pivot.getRank())
        {
          right--;
        }
        if(left <= right)
        {
          Card temp = deck.get(left);
          deck.set(left, deck.get(right));
          deck.set(right, temp);
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
    for(int i = 0; i < deck.size(); i++)
    {
      Card min = deck.get(i);
      int index = i;
      for(int j = i; j < deck.size(); j++)
      {
        if(min.compareTo(deck.get(j)) < 0)
        {
          min = deck.get(j).copy();
          index = j;
        }
      }
      //swapping
      Card temp = deck.get(i);
      deck.set(i, min);
      deck.set(index, temp);
    }
  }
  
  //method to flip cards at given indexes
  public void flip(ArrayList<Integer> indexes)
  {
    for(int i = 0; i < indexes.size(); i++)
    {
      deck.get(indexes.get(i)).flip();
    }
  }
  
  //return size
  public int getSize()
  {
    return deck.size();
  }
}
