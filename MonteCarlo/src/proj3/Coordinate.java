/**
 * TODO Class description
 *Class Invariants:
 *
 * @author BamideleSuarau
 * @version Mar 26, 2013
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
 * Class invariants: row and column exists for all coord.
 */
public class Coordinate {

	//creating instance variables
	private int row;
	private int column;
	
	/**
	 * constructor
	 * @param row
	 * @param column
	 * 
	 * @Pre: Row > 0
	 * @Post: Column >0
	 */
	public Coordinate(int row, int column)
	{
		this.row = row;
		this.column = column;
		
	}


	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * 
	 * @return the row
	 */
	public int getRow() 
	{
		return row;
	}
	
	//unit testing for Coordinate
	/*
	public static void main(String [] args)
	{
		System.out.println("Unit Testing for Coordinate Class");
		Coordinate coordinate = new Coordinate(5, 6);
		coordinate.getColumn();
		coordinate.getRow();
		
	}
	*/
}
