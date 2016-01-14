/**
 * ICS4U
 *  
 * 
 * Display Class
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Display extends JPanel implements ActionListener {
  //variables
  private Deck deck;
  private String[] options = {"     Show     ", "    Shuffle    ", "    Deal    ", " Quick Sort ", "Selection Sort", "  Reset  ", "  Search  ", "    Add    "}; 
  private JComboBox<String> searchSuit, searchValue, addSuit, addValue;
  private SpinnerListModel model;
  private JSpinner dealPos;
  private JToolBar tool;
  public static final int TOOL_HEIGHT = 40;
  public static final Dimension btnmin = new Dimension(60, 40);
  public static final Dimension btnmax = new Dimension(100, 40);
  public static final Dimension separator = new Dimension(5, TOOL_HEIGHT);
  public static final String[] suitStrings = {'\u2660' + "", '\u2665' + "", '\u2663' + "", '\u2666' + ""};
  public static final String[] valueStrings = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
  private boolean up = false;
  //constructor
  public Display(Deck d)
  {
    super(new BorderLayout());
    this.deck = d;
    tool = new JToolBar();
    tool.setPreferredSize(new Dimension(Window.WINDOW_WIDTH, TOOL_HEIGHT));
    add(tool, BorderLayout.PAGE_START);
    
    searchSuit = new JComboBox<String>(suitStrings);
    searchValue = new JComboBox<String>(valueStrings);
    addSuit = new JComboBox<String>(suitStrings);
    addValue = new JComboBox<String>(valueStrings);
    String[] temp = new String[52];
    for(int i = 0; i < temp.length; i++)
    {
      temp[i] = i + 1 + "";
    }
    model = new SpinnerListModel(temp);
    dealPos = new JSpinner(model);
    
    addButtons(tool);
  }
  
  //method to add buttons to the toolbar
  public void addButtons(JToolBar tool)
  {
    JButton button = null;
    
    //Show button
    tool.add(create(0, "Show all cards in the deck"));
    //Shuffle button
    tool.add(create(1, "Shuffle the deck"));
    
    tool.addSeparator(separator);
    
    //add a card button
    tool.add(create(7, "Add a card to the deck"));
    tool.add(addValue);
    tool.add(addSuit);
        
    tool.addSeparator(separator);
    
    //Deal Button
    tool.add(create(2, "Deal a card"));
    tool.add(dealPos);
    
    tool.addSeparator(separator);
    
    //search button
    tool.add(create(6, "Search for a card"));
    tool.add(searchValue);
    tool.add(searchSuit);
    
    tool.addSeparator(separator);
    
    //Quicksort Button
    tool.add(create(3, "Quick Sort the deck without suit order"));
    //Selection sort button
    tool.add(create(4, "Selection Sort the deck with suit order"));
    
    tool.addSeparator(separator);
    
    //reset button
    tool.add(create(5, "Reset the deck"));
  }
  
  //create a button
  public JButton create(int index, String text)
  {
    JButton button = new JButton(options[index]);
    button.setToolTipText(text);
    button.addActionListener(this);
    button.setMinimumSize(btnmin);
    button.setMaximumSize(btnmax);
    return button;
  }
  
  //interpret ranks
  public int interpretRank(String rank)
  {
    try{
      return Integer.parseInt(rank) - 2;
    }catch(Exception e)
    {
      if(rank.equals("J"))
        return 9;
      if(rank.equals("Q"))
        return 10;
      if(rank.equals("K"))
        return 11;
      if(rank.equals("A"))
        return 12;
    }
    return -1;
  }
  
    //interpret suit
  public int interpretSuit(String suit)
  {
    for(int i = 0; i < suitStrings.length; i++)
    {
      if(suit.equals(suitStrings[i]))
        return i;
    }
    return -1;
  }

  
  //overridden actionperformed method from ActionListener
  @Override
  public void actionPerformed(ActionEvent e)
  {
    JButton b = new JButton();
    String text = "";
    try{
      b = (JButton)(e.getSource());
      text = b.getText();
    }catch(Exception e2)
    {
    }
    
    //get the text
    if(text.equals(options[0]))
    {
      up = true;
    }else if(text.equals(options[1]))
    {
      deck.shuffle();
    }else if(text.equals(options[7]))
    {
      try
      {
        Card c = new Card(interpretRank((String)addValue.getSelectedItem()), interpretSuit((String)addSuit.getSelectedItem()));
        if(c.exists())
        {
          deck.add(c);
        }
      }catch(Exception e2)
      {
      }
    }else if(text.equals(options[2]))
    {
      try
      {
        deck.deal(Integer.parseInt((String)dealPos.getValue()) - 1);
      }catch(Exception e2)
      {
        deck.deal();
      }
      String[] temp = new String[deck.getSize()];
      for(int i = 0; i < temp.length; i++)
      {
        temp[i] = i + 1 + "";
      }
      model = new SpinnerListModel(temp);
      dealPos.setModel(model);
    }else if(text.equals(options[6]))
    {
      try
      {
        deck.flip(deck.search(new Card(interpretRank((String)searchValue.getSelectedItem()), interpretSuit((String)searchSuit.getSelectedItem()))));
      }catch(Exception e2)
      {
      }
    }else if(text.equals(options[3]))
    {
      deck.quicksort();
    }else if(text.equals(options[4]))
    {
      deck.selectionsort();
    }else if(text.equals(options[5]))
    {
      deck = new Deck();
    }
    repaint();
  }
    
  //overriden paintComponent method from JComponent
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    if(up)
      this.deck.showUp(g);
    else
      this.deck.show(g);
    up = false;
  }
  
}
