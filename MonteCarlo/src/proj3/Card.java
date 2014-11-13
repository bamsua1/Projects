/**
 * TODO Class description
 *Class Invariants:
 *
 * @author BamideleSuarau
 * @version Mar 28, 2013
 * @project CMSC 202 - Spring 2012 - Project #
 * @section #
 *
 */
package proj3;

/**
 * @author Bamidele
 *
 */

/**
 * Class invariants: rank and suit are 52 each.
 */
public class Card {

	private Rank rank;
	private Suit suit;
	//constructor to initiate rank and suit.
	public Card(Rank rank, Suit suit)
	{
		this.rank = rank;
		this.suit = suit;
	}
	
	//getters for rank & suit
	public Rank getRank()
	{
		return rank;
	}
	
	public Suit getSuit()
	{
		return suit;
	}
	public String toString() {
		return  rank + "  " + suit + "\n";
	}
	
	//unit testing for Coordinate class
	
	/*
	public static void main ( String args[])
	{
		System.out.println("Unit Testing for Card Class");
		Rank rank = null;
		Suit suit = null;
		Card card = new Card(rank, suit);
		card.getRank();
		card.getSuit();
		System.out.print(card.toString());
	}
	*/
}
