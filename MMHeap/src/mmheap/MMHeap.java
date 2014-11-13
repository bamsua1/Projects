/**
 * TODO Class description
 *Class Invariants:
 *
 * @author Bamidele Suarau
 * @version Nov 4, 2013
 * @project CMSC 202 - Spring 2012 - Project #3
 * @section #
 *
 */
package mmheap;
import java.util.ArrayList;


/**
 * @author Bamidele
 *
 */
public class MMHeap<T extends WorksWithMMHeap<T>> {

	//instance variables
	private T object;
	private int size = 0;
	private T[] array;

	//MMHeap constructor
	public MMHeap(T exemplar, int initialSize) 
	{
		object = exemplar;
		array = object.newArray(initialSize);
	}

	/**
	 * returns the size of the MMHeap
	 * @return size
	 */
	public int size()
	{
		return size;
	}

	/**
	 * inserts the parameter X in the right position
	 * in the Heap
	 * @param x
	 */
	public void insert(T x)
	{
		//if the input is null, returns null
		if(x == null)
		{
			return;
		}
		//if size == 0, inputs in the first index of the tree
		if(size == 0)
		{
			array[0] = x;
			size++;
			return;
		}

		//declares variable and adds the input to the last index
		int index = size;
		array[index] = x;
		int parent = (index-1)/2;
		int gParent = (index-3)/4;
		//gets the present level after input
		int level = getLevel(index+1);
		//if size < 3; does special input
		if(size <= 3)
		{
			//checks if the element at 0 is greater than the new one, if yes, swaps
			if(object.compare(array[0], array[index]) > 0)
			{
				T temp = array[0];
				array[0] = array[index];
				array[index] = temp;
				size++;
				return;
			}
			else
			{
				//else does nothing. 
				size++;
				return;
			}
		}

		//checks if level is even
		if(level % 2 == 0)
		{
			//compares it to its parent and carries out the right action. 
			if(object.compare(array[index], array[parent]) == 0)
			{
				size++;
				return;
			}
			else if(object.compare(array[index], array[parent]) > 0)
			{

				T temp = array[parent];
				array[parent] = array[index];
				array[index] = temp;


				int y = parent;
				while(object.compare(array[y], array[(y-3)/4]) > 0 && y > 2)
				{

					T memp = array[(y -3)/4];
					array[(y-3)/4] = array[y];
					array[y] = memp;

					y = (y -3)/4;
				}

				size++;
				return;

			}

			else if(object.compare(array[index], array[parent]) < 0)
			{
				if(object.compare(array[index], array[gParent]) >= 0)
				{
					size++;
					return;
				}

				else 
				{
					//switch till  x > grandparent
					int y = index;
					while(object.compare(array[y], array[(y-3)/4]) < 0 && y >2)
					{

						T memp = array[(y -3)/4];
						array[(y-3)/4] = array[y];
						array[y] = memp;

						y = (y -3)/4;
					}

					size++;
					return;

				}

			}

		}
		else
		{
			if(object.compare(array[index], array[parent]) == 0)
			{
				size++;
				return;
			}
			else if(object.compare(array[index], array[parent]) > 0)
			{
				if(object.compare(array[index], array[gParent]) <= 0)
				{
					size++;
					return;
				}
				else
				{
					//switch till x < grandparent
					int y = index;
					while(object.compare(array[y], array[(y-3)/4]) > 0 && y >2)
					{

						T memp = array[(y -3)/4];
						array[(y-3)/4] = array[y];
						array[y] = memp;

						y = (y -3)/4;
					}

					size++;
					return;

				}
			}
			else if(object.compare(array[index], array[parent]) < 0)
			{
				T temp = array[parent];
				array[parent] = array[index];
				array[index] = temp;

				int y = parent;
				while(object.compare(array[y], array[(y-3)/4]) < 0 && y >2)
				{

					T memp = array[(y -3)/4];
					array[(y-3)/4] = array[y];
					array[y] = memp;

					y = (y -3)/4;
				}

				size++;
				return;

			}
		}

	}

	/**
	 * takes in the index and calculates the level. 
	 * @param index
	 * @return level
	 */
	public int getLevel(int index)
	{
		double value = (Math.log10(index/1.0)/Math.log10(2.0));
		int level = (int)Math.floor(value);
		return level;
	}
	/**
	 * returns the smallest element in a heap
	 * @return the minimum, or null.
	 */
	public T getMin()
	{
		if(size == 0)
		{
			return null;
		}
		return array[0];
	}

	/**
	 * returns the maximum element in a heap
	 * @return
	 */
	public T getMax()
	{
		if(size == 0)
		{
			return null;
		}
		else if(size == 1)
		{
			return array[0];
		}
		else if(size == 2)
		{
			return array[1];
		}

		else
		{
			if(object.compare(array[1], array[2]) < 0)
			{
				return array[2];
			}
			else if(object.compare(array[1], array[2]) > 0)
			{
				return array[1];
			}
			else
			{
				return array[1];
			}

		}
	}

	/**
	 * compares all the possible grandchildren and
	 * children. and gets the maximum.	
	 * @param index
	 * @return the smallest element or 0
	 */
	public ArrayList<Integer> min(int index)
	{
		ArrayList<Integer> array2 = new ArrayList<Integer>();
		if(4*index + 3 >= size)
		{
			array2.add(0, 0) ;
			return array2;
		}
		int smallerIndex;
		int smallerIndex2;
		int smallest = 0;

		if(4*index + 6 < size)
		{
			if(array[4*index+6] != null)
			{

				smallerIndex = grandChildMin(4*(index)+3, 4*index + 4);
				smallerIndex2 = grandChildMin(4*(index)+5, 4*index + 6);
				smallest = grandChildMin(smallerIndex, smallerIndex2);

				array2.add(0, smallest) ;
				return array2;
			}
		}
		if(4*index+5 < size)
		{
			if(array[4*index+5] != null && array[4*index+6] == null)
			{

				smallerIndex = grandChildMin(4*(index)+3, 4*index + 4);
				smallest = grandChildMin(smallerIndex, 4*index+5);
				array2.add(0, smallest) ;
				return array2;


			}
		}
		if(4*index+4 < size)
		{
			if(array[2*index + 2] != null )
			{

				smallerIndex = grandChildMin(4*(index)+3, 4*(index)+4);
				smallest = grandChildMin(smallerIndex, 2*index+2);

				array2.add(0, smallest) ;
				array2.add(1, 0);
				return array2;

			}
			else
			{
				smallest = grandChildMin(4*(index)+3, 4*index + 4);
				array2.add(0, smallest) ;
				return array2;
			}
		}


		if(4*index+3 < size)
		{

			if(array[2*index + 2] != null)
			{

				smallest = grandChildMin(4*index+3, 2*index + 2);
				array2.add(0, smallest) ;
				array2.add(1, 0);
				return array2;
			}

			else
			{

				smallest = 4*index +3;
				array2.add(0, smallest) ;
				return array2;
			}


		}
		
		return array2;

	}
	/**
	 * compares all the possible grandchildren and children
	 * children. and gets the maximum.
	 * @param index
	 * @return
	 */
	public ArrayList<Integer> max(int index)
	{
		ArrayList<Integer> array2 = new ArrayList<Integer>();
		if(4*index + 3 >= size )
		{
			array2.add(0);
			return array2;
		}

		int maxIndex;
		int maxIndex2;
		int max = 0;

		if(4*index + 6 < size)
		{
			
			
				maxIndex = grandChildMax(4*(index)+3, 4*index + 4);
				maxIndex2 = grandChildMax(4*(index)+5, 4*index + 6);
				max = grandChildMax(maxIndex, maxIndex2);
				array2.add(max);
				return array2;
			
		}
		if(4*index+5 < size)
		{

			maxIndex = grandChildMax(4*(index)+3, 4*index + 4);
			max = grandChildMax(maxIndex, 4*index+5);
			array2.add(max);
			return array2;


		}
		if(4*index+4 < size)
		{
				maxIndex = grandChildMax(4*(index)+3, 4*(index)+4);
				max = grandChildMax(maxIndex, 2*index+2);

				array2.add(0, max) ;
				array2.add(1, 0);
				return array2;
		}



		if(4*index + 3 < size)
		{
				max = grandChildMax(4*index+3, 2*index + 2);
				array2.add(0, max) ;
				array2.add(1, 0);
				return array2;
			
		}

		return array2;

	}

	/**
	 * returns the smallest of 2 indexes.
	 * @param index
	 * @param index2
	 * @return smallest index
	 */
	public int grandChildMin(int index, int index2)
	{
		
		if(index  >= size)
		{
			return 0;
		}

		if(index <size && index2  >= size)
		{
			return index;
		}

		if(object.compare(array[index], array[index2]) <= 0)
		{
			return index;
		}
		else
		{
			return index2;
		}
	}
	/**
	 * returns the largest of 2 indexes.
	 * @param index
	 * @param index2
	 * @return biggest
	 */
	public int grandChildMax(int index, int index2)
	{

		if(index  >= size)
		{
			return 0;
		}

		if(index  < size && index2 >= size)
		{
			return index;
		}
		
		

		if(object.compare(array[index], array[index2]) >= 0)
		{
			return index;
		}
		else
		{
			return index2;
		}
	}

	/**
	 * deletes the smallest item in a heap
	 * @return min item
	 */
	public T deleteMin()
	{	
		//declating variables
		int minIndex = 0;
		boolean flag = false;
		boolean flag2 = false;
		//if size == 0; returns null
		if(size == 0)
		{
			return null;
		}
		//if checks size and deletes accordingly.
		if(size == 1)
		{
			T tempo = array[0];
			array[0] = null;
			size--;
			return tempo;

		}

		if(size == 2)
		{
			T temp = array[0];
			array[0] = array[1];
			array[1] = null;
			size--;
			return temp;

		}
		if(size == 3)
		{
			T tempo = array[0];
			int small = grandChildMin(1, 2);
			array[0] = array[small];
			if(small == 2)
			{
				array[small] = null;
			}
			else
			{
				array[1] = array[2];
				array[2] = null;
			}

			size--;
			return tempo;
		}

		//if size > 3 does sophisticated deleting
		T temp = array[0];
		array[0] = array[size-1];
		array[size-1] = null;
		size--;
		int index = 0;

		//gets the smallest of the children & grandchildren
		int smallest = min(index).get(0);
		//while it has grandchildren, keeps going
		while(smallest != 0)
		{
			//doesn't switch if im less than the smallest grandchild
			//& breaks
			if(object.compare(array[index], array[smallest]) <= 0 )
			{
				flag = true;
				break;
			}

			//special case when i could switch with child or grandchild
			if( min(index).size() > 1)
			{
				flag2 = true;
			}

			//switches the locations and updates index
			T lemp = array[index];
			array[index] = array[smallest];
			array[smallest] = lemp;
			index = smallest;
			smallest = min(smallest).get(0);

		}
		//if i dont switch, i check if i have to switch then percolate back up
		if(flag == true)
		{
			if(object.compare(array[index], array[grandChildMin(2*index+1, 2*index+2)]) > 0)
			{
				int mIndex = grandChildMin(2*index+1, 2*index+2);
				T k = array[mIndex];
				array[mIndex] = array[index];
				array[index] = k;

				int y = mIndex;
				while(object.compare(array[y], array[(y-3)/4]) > 0 && y > 2)
				{
					T f = array[(y-3)/4];
					array[(y-3)/4] = array[y];
					array[y] = f;
					y = (y-3)/4;
				}
			}
			else if(object.compare(array[index], array[(index-1)/2]) > 0)
			{
				T m = array[(index-1)/2];
				array[(index-1)/2] = array[index];
				array[index] = m;
				int y = (index-1)/2;
				while(object.compare(array[y], array[(y-3)/4]) > 0 && y > 2)
				{
					T k = array[(y-3)/4];
					array[(y-3)/4] = array[y];
					array[y] = k;
					y = (y-3)/4;
				}
			}
		}

		else if(flag2 == true)
		{
			int y = index;

			if(getLevel(y+1) % 2 == 1)
			{
				while(object.compare(array[y], array[(y-3)/4]) > 0 && y > 2)
				{
					T k = array[(y-3)/4];
					array[(y-3)/4] = array[y];
					array[y] = k;
					y = (y-3)/4;
				}

			}
			else{
				while(object.compare(array[y], array[(y-3)/4]) < 0 && y > 2)
				{
					T k = array[(y-3)/4];
					array[(y-3)/4] = array[y];
					array[y] = k;
					y = (y-3)/4;
				}
			}


		}

		else if(object.compare(array[index], array[grandChildMin(2*index +1, 2*index+2)]) > 0 && grandChildMin(2*index +1, 2*index+2) != 0)
		{
			minIndex = grandChildMin(2*index+1, 2*index+2);
			T wemp = array[index];
			array[index] = array[minIndex];
			array[minIndex] = wemp;

			int y = minIndex;
			while(object.compare(array[y], array[(y-3)/4]) > 0 && y > 2)
			{
				T demp = array[(y-3)/4];
				array[(y-3)/4] = array[y];
				array[y] = demp;
				y = (y-3)/4;
			}
		}

		else if (object.compare(array[index], array[(index-1)/2]) > 0) 
		{

			T femp = array[(index-1)/2];
			array[(index-1)/2] = array[index];
			array[index] = femp;

			int y = (index-1)/2;
			while(object.compare(array[y], array[(y-3)/4]) > 0 && y > 2)
			{
				T demp = array[(y-3)/4];
				array[(y-3)/4] = array[y];
				array[y] = demp;
				y = (y-3)/4;
			}
		}

		
		return temp;
	}


	/**
	 * delete the max element in a heap
	 * @return the largest element
	 */
	public T deleteMax()
	{
		int maxIndex = 0;
		boolean flag = false;
		boolean flag2 = false;
		if(size == 0)
		{
			return null;
		}
		if(size == 1)
		{
			T temp = array[0];
			array[0] = null;
			size--;
			return temp;
		}
		if(size == 2)
		{

			T wemp = array[1];
			array[1] = null;
			size--;
			return wemp;
		}
		if(size == 3)
		{
			T femp ;
			if(object.compare(array[1], array[2]) >0)
			{
				femp = array[1];
				array[1] = array[2];
				array[2] = null;
				size--;
				return femp;
			}
			else
			{
				femp = array[2];
				array[2] = null;
				size--;
				return femp;
			}

		}
		int index = 0;
		if(object.compare(array[1], array[2]) >= 0)
		{
			index = 1;
		}
		else
		{
			index = 2;
		}

		T temp = array[index];
		array[index] = array[size -1];
		array[size-1] = null;
		size--;

		int max = max(index).get(0);
		while(max != 0)
		{
			if(object.compare(array[index], array[max]) >= 0)
			{
				flag = true;
				break;
			}

			if( max(index).size() > 1)
			{
				flag2 = true;
				
			}

			T lemp = array[index];
			array[index] = array[max];
			array[max] = lemp;


			index = max;
			max = max(max).get(0);
		}

		if(flag == true)
		{

		}
		else if(flag2 == true)
		{
			int y = index;
			if(getLevel(index+1) % 2 == 1)
			{
				while(object.compare(array[y], array[(y-3)/4]) > 0 && y > 2)
				{
					T k = array[(y-3)/4];
					array[(y-3)/4] = array[y];
					array[y] = k;
					y = (y-3)/4;
				}
			}
			else{
				while(object.compare(array[y], array[(y-3)/4]) < 0 && y > 2)
				{
					T k = array[(y-3)/4];
					array[(y-3)/4] = array[y];
					array[y] = k;
					y = (y-3)/4;
				}
			}
		}

		else if(object.compare(array[index], array[grandChildMax(2*index+1, 2*index+2)]) < 0 && grandChildMax(2*index+1, 2*index+2) != 0)
		{
			maxIndex = grandChildMax(2*index+1, 2*index+2);
			T wemp = array[index];
			array[index] = array[maxIndex];
			array[maxIndex] = wemp;
			int y = maxIndex;
			while(object.compare(array[y], array[(y-3)/4]) < 0 && y > 2)
			{
				T demp = array[(y-3)/4];
				array[(y-3)/4] = array[y];
				array[y] = demp;
				y = (y-3)/4;
			}
		}

		else if(object.compare(array[index], array[(index-1)/2]) < 0) 
		{

			T femp = array[(index-1)/2];
			array[(index-1)/2] = array[index];
			array[index] = femp;

			int y = (index-1)/2;
			while(object.compare(array[y], array[(y-3)/4]) < 0 && y > 2)
			{
				T demp = array[(y-3)/4];
				array[(y-3)/4] = array[y];
				array[y] = demp;
				y = (y-3)/4;
			}
		}


		
		return temp;
	}

	/**
	 * prints out the heap stats
	 */
	public void dump()
	{

		System.out.println("--- min-max heap dump --- ");
		System.out.println("   Size = " + size);
		System.out.println("   Minimum = " + getMin());
		System.out.println("   Maximum = " + getMax());
		if(getLevel(size)% 2 == 0 )
		{
			System.out.println("Last Level is Even");
		}
		else
		{
			System.out.println("Last Level is Odd");
		}
		System.out.println("--- heap data items --- ");
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] == null)
			{
				break;
			}
			System.out.println("H[" + (i+1) + "] = " + array[i]);
		}

	}

}
