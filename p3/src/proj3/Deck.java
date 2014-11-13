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
 * Class invariants: deck is always 52.
 */
import java.util.Random;
public class Deck {

		private Card[] deck;
		
		/**
		 * Deck constructor
		 * creating a type of card with 52 cards
		 * 
		 */
		public Deck()
		{
			deck = new Card [52];
			startGame();
		}
		
		/**
		 * starts game by adding cards to deck
		 */
		public void startGame()
		{
			//adding all values of ranks and suit to an array.
			Rank [] ranks = Rank.values();
			Suit [] suits = Suit.values();
		
			
			//using a for loop to add the cards to the deck.
			int counter = 0;
			for(int j = 0; j < suits.length; j++)
			{
				for(int i = 0; i < ranks.length; i++ )
				{
					Card cards = new Card(ranks[i], suits[j]);
					deck[counter] = cards ;
					counter++;
				}
			}
		}
		
		/**
		 * shuffles card according to algorithm
		 * @param gameNumber
		 */
		public void Shuffle(long gameNumber)
		{
			//creates random number and shuffles
			Random randomNumber = new Random(gameNumber );
			for(int i = 51; i > 0; i--)
			{
				int randomIndex = randomNumber.nextInt(i+1);
				Card swap = deck[randomIndex];
				deck[randomIndex] = deck[i];
				deck[i] = swap;
			}
		}
		
		/**
		 * returns card with index i
		 * @param i
		 * @return deck[i]
		 * @Pre i is an int <= 52
		 */
		public Card getCard(int i)
		{
			
			return deck[i];
		}
		
		/**
		 * prints out the cards
		 * @return output 
		 */
		public String toString()
		{
			String output = "";
			for (int i = 0; i < 52; i++)
			{
				output += deck[i];
				output += "";
			}
			return output;
		}
		
		//unit testing for Deck Class. 
		
		/*
		public static void main(String[] args) 
		{
			System.out.println("Unit Testing for Deck Class");
			Deck d = new Deck();
			d.startGame();
			d.getCard(2);
			d.Shuffle(586023742);
			System.out.println(d.toString());
		}
		*/
}
