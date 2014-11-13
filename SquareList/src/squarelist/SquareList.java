/**
 * TODO Class description
 *Class Invariants:
 *
 * @author Bamidele Suarau
 * @version Sep 27th, 2013
 * @project CMSC 341 - Fall 2013 - Project #1
 * 
 *
 */
package squarelist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Bamidele
 *
 */
public class SquareList {

	//SquareList instance variables
	LinkedList<SinglyLinkedList> sqrList;
	int size = 0;

	/**
	 * Constructor for SquareList. 
	 */
	public SquareList()
	{
		//creating a new Singlylinkedlist in sqrList. 
		sqrList = new LinkedList<SinglyLinkedList>();
		sqrList.add(new SinglyLinkedList());
	}

		
	/**
	 * addFirst: takes in the data and adds it to the index 0 in the List,
	 * updates the size of the List then consolidates to rearrange the list. 
	 * @param data
	 */
	public void addFirst(Integer data)
	{
		//this if-Statement checks if the List is empty.
		//if it is, it just adds the element to the beginning of the list.
		//it also catches the NoSuchElementException. 
		if(isEmpty())
		{	
			try
			{
				sqrList.getFirst().addFirst(data);
				size++;
				return;
			} 
			catch(NoSuchElementException e)
			{
				System.out.println("No such Element");
			}
		}

		//if the List is not empty, it adds the element to the list and consolidates.
		try
		{
			sqrList.getFirst().addFirst(data);
			size++;
			consolidate();
		} 
		catch(NoSuchElementException e)
		{
			System.out.println("No such Element");
		}

	}
	/**
	 * isEmpty: checks if the squareList is empty.
	 * @return true if it is
	 * false if it isn't.
	 */
	public boolean isEmpty()
	{
		if(size == 0)
		{
			return true;
		}

		else
		{
			return false;
		}
	}

	/**
	 * addLast: adds data to the end of the last element in a list.
	 * or calls addFirst if it's empty. 
	 * @param data
	 */
	public void addLast(Integer data)
	{
		//checks if isEmpty is true, calls addFirst if it is and quits the method.
		if(isEmpty())
		{
			this.addFirst(data);
			return;
		}

		//if it isn't empty, adds the element to the end of the list by calling
		//addLast of the innerList, updates size
		//consolidates and catches NoSuchElementException
		try
		{
			sqrList.getLast().addLast(data);
			size++;
			consolidate();

		}
		catch(NoSuchElementException e){
			System.out.println("No such Element");
		}
	}

	/**
	 * removeFirst: removes the first element in a list. 
	 * @return the data removed
	 */
	public Integer removeFirst() 
	{
		//checks if list is empty and ends the method
		if(isEmpty())
		{
			return null;
		}

		//if it isn't empty, it calls the first list of the
		//squareList and removes the first element by calling
		//removeFirst of the innerList, updates size, consolidates,
		//and calls returns the data. 
		Integer data = null;
		try
		{

			data = sqrList.getFirst().removeFirst();
			size--;
			consolidate();
			return data;
		}
		catch(NoSuchElementException e){
			System.out.println("No such Element");
		}

		return null;
	}

	/**
	 * add: adds data to the required position.
	 * @param pos
	 * @param data
	 */
	public void add(int pos, Integer data) 
	{

		//checks if the index inputed is the same as the index of
		//the last element in the sqrList, if it is, it calls addLast and 
		//ends this method.
		if(pos == size-1)
		{
			addLast(data);
			return;
		}

		//checks if the index inputed is the same as the index of
		//the first element in the sqrList, if it is, it calls addFirst and 
		//ends this method.
		else if(pos == 0)
		{
			addFirst(data);
			return;
		}

		//checks if the index inputed is invalid, quits, and does nothing. 
		if(pos < 0 || pos > size)
		{
			return;
		}

		//so if the above 3 conditions aren't satisfied. this method adds the 
		//value to the required position in the first list by calling insert from the inner list,
		//consolidates and ends the method. or catches the NoSuchElementException.
		
		try
		{
			if(pos < sqrList.getFirst().size)
			{

				sqrList.getFirst().insert(data, pos);
				size++;
				consolidate();
				return;
			}
		} 
		catch(NoSuchElementException e)
		{
			System.out.println("No such Element");
		}

		//this one accounts for every other lists in the sqrList ignoring the first List. 
		try
		{
			ListIterator<SinglyLinkedList> itr = sqrList.listIterator();
			SinglyLinkedList list = null;
			int totalnum = 0;
			int regular_size = 0;


			while(pos >= totalnum)
			{
				list = itr.next();
				totalnum += list.size();
				regular_size = list.size();
			}

			totalnum -= regular_size;
			list.insert(data, pos-totalnum);
			size++;
			consolidate();
		}
		catch(NoSuchElementException e){
			System.out.println("No such Element");
		}
	}

	/**
	 * remove: removes the data at the required position. 
	 * @param pos
	 * @return data
	 */
	public Integer remove(int pos)
	{
		//checks if the size is invalid, quits and does nothing if it isn't.
		if(pos < 0 || pos >= size)
		{
			return null;
		}

		//checks if the required position is in the first list, if yes, it removes it,
		//updates size, consolidates, and returns data.
		try
		{
			if(pos < sqrList.getFirst().size)
			{

				Integer data = sqrList.getFirst().remove(pos);
				size++;
				consolidate();
				return data;
			}
		} 
		catch(NoSuchElementException e)
		{
			System.out.println("No such Element");
		}
		
		//if the element is not in the first list of the SquareList,
		//it checks the remaining part of the list and remove the element
		//using remove from inner function. 
		try{	
			ListIterator<SinglyLinkedList> itr = sqrList.listIterator();
			SinglyLinkedList list = null;
			int totalnum = 0;
			int regular_size = 0;


			while(pos >= totalnum)
			{
				list = itr.next();
				totalnum += list.size();
				regular_size = list.size();
			}

			totalnum -= regular_size;

			size--;
			Integer data = list.remove(pos-totalnum);
			consolidate();
			return data;
		}
		catch(NoSuchElementException e)
		{
			System.out.println("No such element");
		}
		return null;

	}

	/**
	 * get: looks for the integer at a particular index
	 * returns the data.
	 * @param pos
	 * @return data 
	 */
	public Integer get(int pos)
	{
		//checks if index is invalid
		if(pos < 0 || pos > size-1)
		{
			return null;
		}

		//checks if the required index is in the first list
		//of the squareList. returns the data if it is, using get from inner
		//function. 
		try
		{
			if(pos < sqrList.getFirst().size)
			{

				return sqrList.getFirst().get(pos);

			}
		}
		catch (NoSuchElementException e)
		{
			System.out.println("No such Element");
		}

		//checks if the required data is the remaining part of the SquareList
		//using a while loop and returns the value at index, using get from inner
		//function. 
		ListIterator<SinglyLinkedList> itr = sqrList.listIterator();

		SinglyLinkedList list = null;
		int totalnum = 0;
		int regular_size = 0;


		while(pos >= totalnum)
		{
			list = itr.next();
			totalnum += list.size();
			regular_size = list.size();
		}

		totalnum -= regular_size;
		return  list.get(pos - totalnum);

	}

	/**
	 * set: changes the value at a particular index to the value 
	 * entered sent into the method.
	 * @param pos
	 * @param data
	 */
	public void set(int pos, Integer data)
	{
		//Checks if index is invalid and returns the value if it is. 
		if(pos < 0 || pos > size-1)
		{
			return;
		}
		
		//checks if the index is in the first list of the squareList
		//and change the value at that index to the required data. 
		try
		{

			if(pos < sqrList.getFirst().size)
			{

				sqrList.getFirst().set(pos, data);

			}
		}
		catch(NoSuchElementException e)
		{
			System.out.println("No such Element");
		}

		//if it isn't in the first list and it's not invalid
		//it;s guaranteed that the index is in the remaining 
		//part of the List, so it looks for the index and changes the value 
		//using set() from the inner function. 
		ListIterator<SinglyLinkedList> itr = sqrList.listIterator();
		SinglyLinkedList list = null;
		int totalnum = 0;
		int regular_size = 0;



		while(pos >= totalnum)
		{
			list = itr.next();
			totalnum += list.size();
			regular_size = list.size();
		}

		totalnum -= regular_size;
		list.set(pos-totalnum, data);


	}

	/**
	 * size: keeps track of the size of the squareList
	 * @return size
	 */
	public int size()
	{
		return size;
	}

	/**
	 * indexOf: returns the index of the data inputed
	 * @param data
	 * @return pos
	 */
	public int indexOf(Integer data) 
	{
		//uses an iterator to go through the sqrList and use indexOf() from 
		//inner list to check if the data exists in the first list.
		ListIterator<SinglyLinkedList> itr = sqrList.listIterator();
		SinglyLinkedList list = itr.next();
		int totalnum = 0;
		int pos = list.indexOf(data);
		totalnum += list.size;
		if(pos >= 0)
		{
			return pos ;
		}

		//if it doesn't in the first list it uses a while loop to go through the
		//sqrList and check individual lists using indexOf and returns the data. 
		else
		{
			while(itr.hasNext())
			{
				list = itr.next();
				totalnum += list.size();
				pos = list.indexOf(data);
				if(pos >= 0)
				{
					return pos + (totalnum - list.size());
				}
			}

		}
		//returns -1 if data doesnt exist in list
		return -1;

	}


	/**
	 * consolidate: takes care of merge, splitting and removal 
	 * of empty lists.  
	 * @throws NoSuchElementException
	 */
	public void consolidate() throws NoSuchElementException
	{
		//checks if the iterator has next and iterates through it.
		ListIterator<SinglyLinkedList> itr = sqrList.listIterator();
		while(itr.hasNext())
		{

			SinglyLinkedList list = itr.next();
			int listsize = list.size();

			//checks if innerList is empty and removes it. 
			if(list.isEmpty())
			{
				itr.remove();
			}

			//checks if an element's size <= sqrt(n)/2 and if the size of the next 
			//element is <= sqrt(n)/2 and merges.
			if(list.size() <= Math.sqrt(size)/2 && itr.hasNext())
			{
				
				SinglyLinkedList list2 = itr.next();
				if(list2.size() <= Math.sqrt(size)/2)
				{
					list.merge(list2);
					itr.remove();
				}
				else
				{
					itr.previous();
				}
			}

			//checks if a list is too long. and splits it into 2.
			if(listsize > 2*Math.sqrt(size))
			{
				SinglyLinkedList[] splited = list.split();
				itr.remove();
				itr.add(splited[0]);
				itr.add(splited[1]);
			}
		}
	}

	/**
	 * dump: prints out the result in a required format .
	 */
	public void dump()
	{
		ListIterator<SinglyLinkedList> current = sqrList.listIterator();
		ArrayList<SinglyLinkedList> array = new ArrayList<SinglyLinkedList>();
		int numLists = 0;
		while(current.hasNext()){
			array.add(current.next());
			numLists++;
		}


		System.out.println("*******************************************************************");
		System.out.println("SquareList Dump:");
		System.out.println("Total Size =" + size() + ", # of lists = " + numLists);
		System.out.println("===================================================================");
		for (SinglyLinkedList i:array){
			System.out.println("InnerList Dump:");
			System.out.println(  i ); 
			System.out.println("size = " + i.size());
			System.out.println("tail = " + i.tail.next.data);
			System.out.println("===================================================================");

		}

	}

	public String toString()
	{
		String str = "";
		str += sqrList.toString();
		return str;
	}
	
	

	/**
	 * Inner List: Single Linked List
	 */
	public class SinglyLinkedList {

		private Node header;
		private Node tail;
		private int size = 0;

		/**
		 * constructor.
		 * 
		 */
		public SinglyLinkedList()
		{
			//creates dummy header and sets it to null
			header = new Node();
			header.setNext(null);
			tail = new Node();
		}

		/**
		 * checks if List is empty
		 * @return null
		 */
		public boolean isEmpty()
		{
			return header.next == null;
		}

		public String toString()
		{
			Node node = header;
			String str = ""; 

			while(node.next != null)
			{
				node = node.next;
				str += node.toString() + " ";
			}
			return str;
		}

		/**
		 * addFirst: adds to the beginning of a List
		 * @param data
		 */
		public void addFirst(Integer data)
		{
			//checks if the list is empty. and adds the element
			if (header.next == null)
			{
				Node newLink = new Node(data);
				newLink.next = null;
				header.next = newLink;
				tail.next = newLink;
				size++;
				return;
			}

			//if itsn't empty. it adds to the list
			Node newLink = new Node(data);
			newLink.next = header.next;
			header.next = newLink;
			size++;
		}

		/**
		 * removeFirst: removes the 1st element in the list and returns 
		 * the value.  
		 * @return
		 */
		public Integer removeFirst()
		{
			//checks if the list is empty and doesnt bother to remove.
			if(isEmpty())
			{
				return null;
			}

			//if the list isn't empty, checks if its the only
			//element in the list then removes.
			if (header.next.next == null)
			{
				//Node firstLink = header.next;
				Node temp = header.next;
				header.next = null;
				tail.next = header;
				size--;
				return temp.data;
			}


			//if none of the earlier statements are satisfied. 
			//removes from the list.
			Node firstLink = header.next;
			Node temp = firstLink;
			header.next = temp.next;
			tail.next = temp.next;
			size--;
			return temp.next.data; 

		}


		/**
		 * addLast: adds to the end of a list. 
		 * @param data
		 */
		public void addLast(Integer data)
		{

			//checks if list is empty: calls addFirst
			if(isEmpty())
			{
				addFirst(data);
				return;
			}
			//if the list is not empty, maneuvers to the 
			//list, adds to it and resets the tail.
			Node endLink = header;
			while(endLink.next != null)
			{
				endLink = endLink.next;
			}

			endLink.next = new Node(data, null);
			tail.next = endLink.next;
			size++;
		}

		/**
		 * inserts a particular element in the required postion
		 * @param data
		 * @param location
		 */
		public void insert(Integer data, int location)
		{
			//checks if size is invalid.
			if(location > size() || location < 0)
			{
				return;
			}
			
			//checks if location == size and uses addLast. 
			if(location == size())
			{
				addLast(data);
				return;
			}

			//if it survives any of the earlier requirements,
			//inserts in location.
			Node link = header;
			for(int i = 0; i < location; i++)
			{
				link = link.next;
			}

			link.next = new Node(data, link.next);
			size++;
		}

		/**
		 * remove: removes at intended location.
		 * @param location
		 * @return
		 */
		public Integer remove(int location)
		{
			//if location == 0, uses removeFirst.
			if(location == 0)
			{
				removeFirst();
				return 0;
			}

			//checks if the index is invalid
			else if(location > size()-1 || location < 0)
			{
				return null;
			}

			//checks if location is last index
			//and removes element
			else if(location == size-1)
			{

				Node link = header;
				for(int i = 0; i < location; i++)
				{
					link = link.next;
				}
				link.next = link.next.next;
				size--;
				tail.next = link;
				return link.data;
			}

			Node link = header;
			for(int i = 0; i < location; i++)
			{
				link = link.next;
			}
			link.next = link.next.next;
			size--;
			return link.data;
		}

		/**
		 * merge: joins two small lists together
		 * @param list
		 */
		public void merge(SinglyLinkedList list)
		{
			this.tail.next.next = list.header.next;
			tail.next = list.tail.next;
			size += list.size();
		}

		/**
		 * get: gets the value at required index
		 * @param index
		 * @return data
		 */
		public Integer get(int index)
		{
			//checks if index is invalid.
			if(index >= size() || index < 0)
			{
				return null;
			}
			
			//if not, gets the value. 
			Node link = header;
			for(int i = 0; i < index; i++)
			{
				link = link.next;
			}
			return link.next.data;
		}

		/**
		 * set: changes the value at a particular index to 
		 * the new value
		 * @param index
		 * @param data
		 */
		public void set(int index, Integer data)
		{
			//checks if index is invalid. 
			if(index > size() || index < 0)
			{
				return;
			}
			//if not sets the value
			if(index == size-1)
			{
				Node link = header;
				for(int i = 0; i < index; i++)
				{
					link = link.next;
				}
				link.next.data = data;
				tail.next = link.next;
			}
			Node link = header;
			for(int i = 0; i < index; i++)
			{
				link = link.next;
			}
			link.next.data = data;
		}

		/**
		 * indexOf: gets the index of a particular data
		 * @param data
		 * @return index
		 */
		public int indexOf(Integer data)
		{
			//uses a while loop o find the index
			Node link = header;
			int i = -1;
			while(link.next != null)
			{
				i++;
				link = link.next;
				if(link.data == data)
				{
					return i;
				}
			}

			return -1;
		}
		/**
		 * 
		 * @return size
		 */
		public int size()
		{
			return size;
		}

		/**
		 * split: Splits a long list into 2 lists. 
		 * @return an array of the splited lists
		 */
		public SinglyLinkedList[] split()
		{
			//creates 2 new lists 
			SinglyLinkedList newList = new SinglyLinkedList();
			SinglyLinkedList newList2 = new SinglyLinkedList();

			//checks the conditions and adds the elements to the lists
			for(int i = 0; i < size(); i++)
			{
				if(size() % 2 == 1 && i < size()/2)
				{
					newList.addLast(get(i));
				}

				else if(size() % 2 == 1 && i >= size()/2)
				{
					newList2.addLast(get(i));
				}

				else if(size() % 2 == 0 && i < size()/2 )
				{
					newList.addLast(get(i));
				}

				else if(size() % 2 == 0 && i >= size()/2 )
				{
					newList2.addLast(get(i));
				}

			}

			SinglyLinkedList[]lists = new SinglyLinkedList[2];
			lists[0] = newList;
			lists[1] = newList2;
			return lists;

		}

		/**
		 * Node Class
		 * @author Bamidele
		 *
		 */
		public class Node {

			private Integer data;
			private Node next;


			public Node(){

			}

			public Node(Integer data)
			{
				this.data = data;
			}
			public Node(Integer data, Node next)
			{
				this.data = data;
				this.next = next;
			}
			
			public String toString()
			{
				return this.data + "";
			}

			/**
			 * @return the data
			 */
			public Integer getData() {
				return data;
			}

			/**
			 * @param data the data to set
			 */
			public void setData(Integer data) {
				this.data = data;
			}

			/**
			 * @return the next
			 */
			public Node getNext() {
				return next;
			}

			/**
			 * @param next the next to set
			 */
			public void setNext(Node next) {
				this.next = next;
			}
		}

	}

}