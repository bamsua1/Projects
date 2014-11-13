/**
 * TODO Class description
 *Class Invariants:
 *
 * @author BamideleSuarau
 * @version Nov 1, 2013
 * @project CMSC 202 - Spring 2012 - Project #
 * @section #
 *
 */
package mmheap;

import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * @author Bamidele
 *
 */
public class QHeap<T extends WorksWithMMHeap<T>> {

	private T object;
	private int size;
	private static int pH = -1;
	MMHeap<T> mmh;
	ArrayList<MMHeap<T>> mmhList = new ArrayList<MMHeap<T>>(); 

	/**
	 * 
	 * @param exemplar
	 * @param n
	 */
	public QHeap(T exemplar, int n)
	{
		object = exemplar;
		
		for(int i = 0; i < 4; i ++)
		{
			mmh = new MMHeap<T>(exemplar, (n/4)+2);
			mmhList.add(mmh);
		}

	}

	/**
	 * inserts into the QHeap
	 * @param x
	 */
	public void insert(T x)
	{
		int actual = 0;
		if(size == 0)
		{
			mmhList.get(0).insert(x);
			size++;
			return;
		}

		if(size <  4 )
		{  
			if(object.compare(x, mmhList.get(size-1).getMax()) >= 0)
			{
				mmhList.get(size).insert(x);
				size++;
				return;
			}
			else if(object.compare(x, mmhList.get(size-1).getMin()) < 0)
			{
				mmhList.get(size-1).insert(x);
				mmhList.get(size).insert(mmhList.get(size-1).getMax());
				mmhList.get(size-1).deleteMax();
				size++;
				return;
			}
		}


		if(object.compare(x, mmhList.get(0).getMax()) > 0)
		{
			if(object.compare(x, mmhList.get(1).getMax()) > 0)
			{
				if(object.compare(x, mmhList.get(2).getMax()) > 0)
				{
					mmhList.get(3).insert(x);
					actual = 3;
					size++;

				}
				else
				{
					mmhList.get(2).insert(x);
					actual = 2;
					size++;
				}
			}
			else
			{
				mmhList.get(1).insert(x);
				actual = 1;
				size++;
			}
		}

		else 
		{
			mmhList.get(0).insert(x);
			actual = 0;
			size++;
		}

		//rebalances the methods.
		pH++;
		if(pH % 4 == 0)
		{
			pH = 0;
		}
		int move = pH - actual;
		if(move == 1 || move == -1)
		{
			if(move == 1)
			{
				mmhList.get(actual+1).insert(mmhList.get(actual).getMax());
				mmhList.get(actual).deleteMax();
			}
			else
			{
				mmhList.get(actual-1).insert(mmhList.get(actual).getMin());
				mmhList.get(actual).deleteMin();
			}

		}
		else if(move == 2 || move == -2)
		{
			if(move == 2)
			{
				mmhList.get(actual+1).insert(mmhList.get(actual).getMax());
				mmhList.get(actual).deleteMax();
				mmhList.get(actual+2).insert(mmhList.get(actual+1).getMax());
				mmhList.get(actual+1).deleteMax();
			}
			else
			{
				mmhList.get(actual-1).insert(mmhList.get(actual).getMin());
				mmhList.get(actual).deleteMin();
				mmhList.get(actual-2).insert(mmhList.get(actual-1).getMin());
				mmhList.get(actual-1).deleteMin();
			}
		}
		else if(move == 3 || move == -3)
		{
			if(move == 3)
			{
				mmhList.get(actual+1).insert(mmhList.get(actual).getMax());
				mmhList.get(actual).deleteMax();
				mmhList.get(actual+2).insert(mmhList.get(actual+1).getMax());
				mmhList.get(actual+1).deleteMax();
				mmhList.get(actual+3).insert(mmhList.get(actual+2).getMax());
				mmhList.get(actual+2).deleteMax();
			}
			else
			{
				mmhList.get(actual-1).insert(mmhList.get(actual).getMin());
				mmhList.get(actual).deleteMin();
				mmhList.get(actual-2).insert(mmhList.get(actual-1).getMin());
				mmhList.get(actual-1).deleteMin();
				mmhList.get(actual-3).insert(mmhList.get(actual-2).getMin());
				mmhList.get(actual-2).deleteMin();
			}
		}
		else
		{
			
		}
		
		return;
	}


	/**
	 * gets the Min of the heap
	 * @return
	 */
	public T getMin()
	{
		if(size == 0)
		{
			System.out.println("This statistic doesn't exist");
			return null;
		}
		return mmhList.get(0).getMin();
	}
	/**
	 * gets the Max of the heap
	 * @return
	 */
	public T getMax()
	{
		if(size > 3)
		{
			return mmhList.get(3).getMax();
		}
		if(size == 0)
		{
			System.out.println("This statistic doesn't exist");
			return null;
		}
		return mmhList.get(size-1).getMax();
	}
	/**
	 * gets the 25th percentile of the heap
	 * @return
	 */
	public T get25()
	{
		if(size < 4)
		{
			System.out.println("This statistic doesn't exist");
			return null;
		}

		return mmhList.get(0).getMax();
	}
	/**
	 * gets the 50th percentile of the heap
	 * @return
	 */
	public T get50()
	{
		if(size < 4)
		{
			System.out.println("This statistic doesn't exist");
			return null;
		}

		return mmhList.get(1).getMax();

	}
	/**
	 * gets the 75th percentile of the heap
	 * @return
	 */
	public T get75()
	{
		if(size < 4)
		{
			System.out.println("This statistic doesn't exist");
			return null;
		}

		return mmhList.get(2).getMax();
	}

	/**
	 * prints out the quartile heap
	 * @return
	 */
	public void dump()
	{
		System.out.println("**********************************");
		System.out.println("***** Quartile heap statistics ...");
		System.out.println("Minimum = " + getMin());
		System.out.println("25th Percentile = " + get25());
		System.out.println("Median = " + get50());
		System.out.println("75th Percentile = " + get75());
		System.out.println("Maximum = " + getMax());

		System.out.println("***** First Quartile heap dump ...");
		System.out.println("-----------------------------------");

		mmhList.get(0).dump();


		System.out.println("***** Second Quartile heap dump ...");
		System.out.println("-----------------------------------");
		mmhList.get(1).dump();
		System.out.println("***** Third Quartile heap dump ...");
		System.out.println("-----------------------------------");
		mmhList.get(2).dump();

		System.out.println("***** Fourth Quartile heap dump ...");
		System.out.println("-----------------------------------");
		mmhList.get(3).dump();
	}

}
