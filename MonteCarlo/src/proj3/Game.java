/**
 * TODO Class description
 *Class Invariants: DeckSize 52
 *
 *
 * @author BamideleSuarau
 * @version Mar 26, 2013
 * @project CMSC 202 - Spring 2012 - Project #
 * @section #
 *
 */
package proj3;

import java.util.Scanner;

/**
 * @author Bamidele
 *
 */
public class Game {

	//creating instance variables
	private int tableauRows; 
	private int tableauColumns;
	private Card [] board;
	private int counter;
	private Deck deck;
	private boolean remove;
	private int score = 0;
	private long gameNumber;
	private int numOFcards = 0;
	private int newCounter = 25;
	private int DeckSize = 52;
	
	
	/**
	 * Constructor for Class Game.
	 * @param tableauRows
	 * @param tableauColumns
	 * @Preconditions : tableauRows & tableauColumns >0, board 1Dimensional 
	 * & card type. 
	 * @Postconditions: 
	 */
	public Game(int tableauRows, int tableauColumns) 
	{
		//initializing variables and creating deck
		this.tableauRows = tableauRows;
		this.tableauColumns = tableauColumns;
		deck = new Deck();
		board = new Card [tableauRows * tableauColumns]; 
		DisplayDeck();
	}

	/**
	 * Adds cards to board
	 * @Preconditions tableauRows*tableauColumns > 0
	 * Counter has to be positive
	 * @Postcondition there should be shuffled cards on the board. 
	 */
	public void DisplayDeck()
	{
		//using a for loop to add cards to board
		for(int i = 0; i < tableauRows*tableauColumns; i++)
		{
			board[i] = deck.getCard(counter);
			counter++;
			
		}
	}
	
	
	/**
	 * Consolidate board, moves all the cards up left & adds new cards
	 * @Pre newCounter has to be positive
	 * @Post board should be consolidated
	 */
	public void consolidate()
	{
		//using a for loop to go through the board
		for(int i = 0; i < tableauRows*tableauColumns; i++)
		{
			//moving the cards if it's null
			        if(board[i] == null)
				{
					int a = i;
					int b = i;
					
					while(board[b] == null && b < board.length-1)
					{
						b++;
						
					}
					
					board[a] = board[b];
					board[b] = null;
					
					
				}
				
				//adding new cards to the empty spaces
				if ((board[i] == null) && (newCounter < DeckSize))
				{
					board[i] = deck.getCard(newCounter);
					newCounter++;
				}
			}
		}
	
	/**
	 * gets the next the suit using index
	 * @param coordinate
	 * @return index on board. 
	 */
	public Suit getSuit(Coordinate coordinate)
	{
		//gets the row & column of coordinate 
		//converts to index and returns the suit
		int row = coordinate.getRow();
		int column = coordinate.getColumn();
		int index = (row * tableauColumns) + column;
		
		
		if(board[index] == null)
		{
			return null;
		}
			
		return  board[index].getSuit();
		
	}
	
	
	/**
	 * 
	 * @param coordinate
	 * @return index on board
	 */
	public Rank getRank(Coordinate coordinate)
	{
		//gets the row & column of coordinate 
		//converts to index and returns the rank
		int row = coordinate.getRow();
		int column = coordinate.getColumn();
		
		int index = (row * tableauColumns) + column;
		
		if(board[index] == null)
		{
			return null;
		}
		
		return board[index].getRank();
	}
	
	/**
	 * Starts a new game.
	 * @param gameNumber
	 */
	public void newGame(long gameNumber)
	{
		//starts new game, resets everything  & displays deck.
		this.gameNumber = gameNumber;
		
		counter = 0;
		deck.startGame();
		deck.Shuffle(gameNumber);
		numOFcards = 27;
		newCounter = 25;
		score = 0;
		DisplayDeck();
	}
	
	/**
	 * 
	 * @return Help
	 */
	public String getHelpText()
	{
		return "Welcome to Monte Carlo Solitaire. \n" +
				"To win this game, You have to click on two cards that match and \n" +
		
				"are in range of one card to each other either digonally, \n" +
				"vertically or horizontally. These selected cards are then removed from \n" +
				"the tableau. \n" +
				
				
				"You can get help by clicking on the Hint option or pressing, Shift + \n" +
				"Control/Command + H. This option higlights cards that can be removed. \n" +
				
				"If they aren't any Hint tells you to consolidate. Consolidating is shifting \n" +
				"the cards on the tableau to the top left to fill up the spaces on the board \n" +
				"and add more \n cards to the free spaces if there are still more in the deck. \n" +
				
				"It's advisable to consolidate when you can't find any cards to remove or when \n" +
				"hint tells you to. You can consolidate by clicking the option or pressing \n " +
				"Command/Control + C. \n" +
				"When you get 52 points you've pratically removed all the cards on the tableau \n " +
				"and you've won the game. \n" +
				"You can restart the game by pressing Command/Control + R, new game by either\n" +
				"clicking Start New Random Game(command R) which starts a random game, or you \n" +
				"could choose New Game(command G) which gives you the opprtunity of typing \n" +
				"in a particular set of numbers which is used to pick a game \n" +
			
				"BEST OF LUCK. HAVE FUN.\n " ;
				
	}
	
	/**
	 * 
	 * @return numOFcards
	 * @Pre Number of cards has to >= 0
	 */
	public int numberOfCardsLeft()
	{
		//returns the num of cards left
		return numOFcards;
	}
	
	/**
	 * 
	 * @return Score
	 * @Pre score > 0
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * starts newGame
	 * @Pre gameNumber has to be int/long type.
	 */
	public void replay()
	{
		//replays game with the last gameNumber used
		newGame(gameNumber);
	}
	
	/**
	 * 
	 * @return true if Hint is implemented
	 */
	public boolean isHintImplemented()
	{
		return true;
	}
	
	/**
	 * Checks for Hint & returns coordinate if 
	 * it exists and null if not
	 * @return Coordinate or null
	 */
	public Coordinate[] getHint()
	{
		//sets coordArray to 2 indexes
		Coordinate  coordArray [] = new Coordinate[2];
		
		//uses 2 for loops to search for the cards alike
		// and returns the coordinates
		for(int i = 0; i < 25; i++)
		{
			for(int j = 0; j < 25; j++)
			{
				//checking if they're empty
				if(board[i] != null && board[j] != null)
				{
					//converting from i to col&rows
					int col1 = i % tableauRows;
					int row1 = (i - col1)/ (tableauRows);
				
					int col2 = (j) % tableauRows;
					int row2 = ((j) - col2)/ (tableauRows);
					//checking if they're close
					if((checkClose(row1, row2, col1, col2) == true)) 
					{
						//checking if the ranks are equal
						if(board[i].getRank() == board[j].getRank())
						{
							//creating new coordinate with the rows and col
							Coordinate coord1 = new Coordinate(row1, col1);
							Coordinate coord2 = new Coordinate(row2, col2);
							
							//adding to coordArray
							coordArray[0] = coord1;
							coordArray[1] = coord2;
							return coordArray;
						}
					
					}
					
				}
				
			}
		}
		
	//returns null if no match
	return null;
	}
	
	/**
	 * checks if cards are close to each other & returns true.
	 * @param coordinate1
	 * @param coordinate2
	 * @return true or false
	 */
	public boolean removeCards(Coordinate coordinate1, Coordinate coordinate2)
	{
		
		//derives the row and columns for both coordinates
		int row1 = coordinate1.getRow();
		int col1 = coordinate1.getColumn();
		
		int row2 = coordinate2.getRow();
		int col2 = coordinate2.getColumn();
		
		//uses an if statement to compare the coords
		if(checkClose(row1, row2, col1, col2) == true)
		{
			
				int index1 = row1 * tableauColumns + col1;
				int index2 = row2 * tableauColumns + col2;
				//checks if the ranks are equal
				if (board[index1].getRank() == board[index2].getRank())
				{
					score += 2;
					if (numOFcards >1)
					{
						numOFcards -= 2;
					}
					
					else if (numOFcards == 1)
					{
						numOFcards -= 1 ;
					}
					
					//set the board to unll
					board[index1] = null;
					board[index2] = null;
					remove = true;
					
					
				}
				
				else
				{
					remove = false;
					
				}
				
		}
		
		
		
		
		return remove;
	}
	
	/**
	 * checks if the the cards are close to each other
	 * @param row1
	 * @param row2
	 * @param col1
	 * @param col2
	 * @return true if they are and false if not. 
	 */
	public boolean checkClose(int row1, int row2, int col1, int col2)
	{
		boolean newFlag = false;
	
		//checking if in range of each other
		if(((row2-row1 == 1) && (col2-col1 ==0)) || ((col2-col1 == 1) && (row2-row1 == 0)) 
				|| ((row2-row1 == -1) && (col2-col1 ==0)) || ((col2-col1 == -1) && (row2-row1 == 0)) 
				|| ((row1+1 ==row2) && (col1+1 == col2)) || ((row1-1==row2) && (col1+1==col2)) 
				|| ((row1+1 == row2) && (col1-1 == col2)) || ((row1-1==row2) && (col1-1 ==col2)))
		{
			newFlag = true;
		}
		
		else
		{
			newFlag = false;
		}
		
		return newFlag;
	}
	
	
	
	/**
	 * checks if player wins
	 * @return true if score = 52
	 * false if not
	 */
	public boolean isWin()
	{
		
		if (score == 52)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	//unit testing for Game class
	
	/*
	public static void main(String[] args) 
	{
		int rows = 5;
		int columns = 5;
		long gamenumber = 1233456;
		Coordinate coord1 = new Coordinate(2,5);
		Coordinate coord2 = new Coordinate(3,1);
		int row1 = 2;
		int col1 = 5;
		int row2 = 3;
		int col2 = 1;
		
		
		System.out.println("Unit Testing for Game Class");
		Game game = new Game(rows, columns);
		game.newGame(gamenumber);
		game.removeCards(coord1, coord2);
		game.checkClose(row1, row2, col1, col2);
		game.consolidate();
		System.out.println(game.getRank(coord1));
		System.out.println(game.getSuit(coord2));
		game.DisplayDeck();
		game.isHintImplemented();
		game.getHint();
		System.out.println(game.numberOfCardsLeft());
		System.out.println(game.getScore());
		game.isWin();
		game.getHelpText();
		game.replay();
	}
	*/
	
}