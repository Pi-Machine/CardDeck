/**
 * ICS4U
 *  
 * 
 * Display Class
 */
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Display extends JPanel implements ActionListener {
  //variables
  private Deck deck;
  private String[] options = {"Show", "Shuffle", "Deal", "Quick Sort", "Selection Sort", "Reset", "Search", "Add"}; 
  private JTextField dealField = new JTextField("(default 1st)");
  private JTextField searchField = new JTextField("Find card");
  private JTextField addField = new JTextField("Add card");
  public static final int TOOL_HEIGHT = 50;
  public static final Dimension btnmin = new Dimension(60, 40);
  public static final Dimension btnmax = new Dimension(100, 40);
  public static final Dimension separator = new Dimension(5, TOOL_HEIGHT);
  private boolean up = false;
  //constructor
  public Display(Deck d)
  {
    super(new BorderLayout());
    this.deck = d;
    JToolBar tool = new JToolBar();
    addButtons(tool);
    tool.setPreferredSize(new Dimension(Window.WINDOW_WIDTH, TOOL_HEIGHT));
    add(tool, BorderLayout.PAGE_START);
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
    tool.add(addField);
        
    tool.addSeparator(separator);
    
    //Deal Button
    tool.add(create(2, "Deal a card"));
    tool.add(dealField);
    
    tool.addSeparator(separator);
    
    //search button
    tool.add(create(6, "Search for a card"));
    tool.add(searchField);
    
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
        deck.add(new Card(Integer.parseInt(addField.getText()) - 1));
      }catch(Exception e2)
      {
        System.out.println("HI");
      }
      addField.setText("Add card");
    }else if(text.equals(options[2]))
    {
      try
      {
        deck.deal(Integer.parseInt(dealField.getText()) - 1);
      }catch(Exception e2)
      {
        deck.deal();
      }
      dealField.setText("(default 1st)");
    }else if(text.equals(options[6]))
    {
      try
      {
        deck.flip(deck.search(new Card(Integer.parseInt(searchField.getText()) - 1)));
      }catch(Exception e2)
      {
      }
      searchField.setText("Find card");
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
