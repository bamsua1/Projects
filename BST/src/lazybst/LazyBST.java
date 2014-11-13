/**                                                                                                                                                                                                                                                                     
 * TODO Class description
 *Class Invariants:
 *
 * @author Bamidele Suarau
 * @version October 22, 2013
 * @project CMSC 341 - Fall 2013 - Project #2
 * @section #
 * I USED THE TEXTBOOK IMPLEMENTATION
 */
package lazybst;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Bamidele
 *
 */
public class LazyBST < T extends Comparable<? super T> > {

	//Instance variables
	private Node<T> root;

	/**
	 * constructor: sets root to null
	 */
	public  LazyBST()
	{
		root = null;
	}

	/**
	 * checks if tree is empty
	 * @return null
	 */
	public boolean isEmpty()
	{
		return root == null;
	}

	/**
	 * calls it's helper method. 
	 * @param x
	 */
	public void insert (T x) 
	{
		//calls the method that checks if
		//tree is balanced
		root = checkForBalance(root);
		//calls insert's helper method.
		root = insert(x, root);
	}

	/**
	 * insert helper method, does the main inserting
	 * @param x
	 * @param node
	 * @return node
	 */
	public Node<T> insert(T x, Node<T> node)
	{
		//checks if node is null and creates new one
		if(node == null) 
		{
			return new Node<T>(x, null, null);
		}

		//compares the value to the node and uses recursion
		//to recall it to the left node
		if(x.compareTo(node.value) < 0)
		{
			node.left = insert(x, node.left);
		}

		//compares the value to the node value
		//and calls recursion to the left
		else if(x.compareTo(node.value) > 0)
		{

			node.right = insert(x, node.right);

		}

		else
		{

		}
		//updates size and height after insertion & returns node.
		sizeUpdate(node);
		heightUpdate(node);
		return node;
	}

	/**
	 * updates the height of the passed in node.
	 * @param node
	 */
	public void heightUpdate(Node<T> node)
	{
		if(node == null)
		{
			return;
		}
		//check if it has children and changes the node's height
		if(node.left != null && node.right != null)
		{
			node.height = 1 + Math.max(node.left.height, node.right.height);
		}
		//checks if it has only one child and updates height
		else if(node.left != null)
		{
			node.height = 1 + node.left.height;
		}
		else if(node.right != null)
		{
			node.height = 1 + node.right.height;
		}
		else
		{
			node.height = 0;
		}
	}
	/**
	 * updates the size of a node
	 * @param node
	 */
	public void sizeUpdate(Node<T> node)
	{

		//checks if it has children and sums up their sizes
		if(node.left != null && node.right != null)
		{
			node.size = node.left.size + node.right.size + 1;
		}
		//checks if it has only one child and updates size.
		else if(node.left != null)
		{
			node.size = node.left.size +1;
		}
		else if(node.right != null)
		{
			node.size = node.right.size +1;
		}

		else 
		{
			node.size = 1;
		}

	}
	/**
	 * calls remove helper method
	 * @param x
	 * @return x value removed
	 */
	public T remove (T x) 
	{
		//calls a function to check the if tree is balanced
		root = checkForBalance(root);
		//calls remove helper method
		Node<T> actualNode = new Node<T>(); 
		Node<T> node = remove(x, root, actualNode);

		//checks if the value was removed and returns it
		if(node != null)
		{

			return actualNode.value;
		}
		else
		{
			return null;
		}
	}

	/**
	 * looks for a value and removes it if it exists
	 * @param x
	 * @param node
	 * @param actualNode
	 * @return node removed
	 */
	public Node<T> remove(T x, Node<T> node, Node<T> actualNode)
	{

		//checks node is null and returns it.
		if(node == null)
		{
			return node;
		}
		sizeUpdate(node);
		heightUpdate(node);
		//compares the value to the node's value and recurses 
		//respectively
		actualNode.value = node.value;
		if(x.compareTo(node.value) < 0 )
		{

			node.left = remove(x, node.left, actualNode);
		}

		else if(x.compareTo(node.value) > 0)
		{

			node.right = remove(x, node.right, actualNode);
		}

		//checks if it has children and looks for the minimum child's parent
		//then switches node's value for the minimum and removes the minimum using 
		//recursion on the parent.
		else if(node.right != null && node.left != null)
		{
			if(node.right.left == null)
			{
				Node<T> temp = node;
				node = node.right;
				node.left = temp.left;
			}
			else
			{
				actualNode = findMinDelete(node.right);
				node.value = actualNode.value;
				sizeUpdate(node);
			}


		}
		else
		{
			//checks if the value 
			actualNode.value = node.value;

			if(root.value == x)
			{
				if(root.right != null)
				{
					root = root.right;
				}
				else if(root.left != null)
				{
					root = root.left;
				}
			}
			if (node.left != null)
			{
				node = node.left;
			}
			else 
			{
				node = node.right;
			}
			return node;
		}

		//updates the node's size & height
		//sizeUpdate(node);

		heightUpdate(node);
		return node;
	}


	/**
	 * checks the tree for if needed to  be re-balanced
	 * @param node
	 * @return
	 */
	public Node<T> checkForBalance(Node<T> node)
	{
		//checks if the list empty
		if(node == null)
		{
			return null;
		}

		//checks if height is less than 3, quits 
		if(node.height <= 3)
		{
			return node;
		}

		//checks if the node and children should be rebalanced.
		if((node.left != null && node.right != null)  && (node.left.size >= 2*node.right.size || node.right.size >= 2* node.left.size))
		{  

			Node<T> newNode = rebalance(node);
			sizeUpdate(newNode);
			heightUpdate(newNode);
			return newNode;
		}


		else if(node.left == null && node.right.size >= 2)
		{

			Node<T> newNode = rebalance(node);
			sizeUpdate(newNode);
			heightUpdate(newNode);
			return newNode;
		}
		else if(node.right == null && node.left.size >= 2)
		{

			Node<T> newNode = rebalance(node);
			sizeUpdate(newNode);
			heightUpdate(newNode);
			return newNode;
		}

		else 
		{		

			node.left = checkForBalance(node.left);
			node.right = checkForBalance(node.right);

		}
		//updates size

		sizeUpdate(node);
		heightUpdate(node);
		return node;
	}

	/**
	 * calls rebalance helper method.
	 * @param node
	 * @return
	 */
	public Node<T> rebalance(Node<T> node)
	{

		Node<T> newNode = null;
		if(isEmpty())
		{

		}
		else
		{
			List<Node<T>> array = new ArrayList<Node<T>>() ;
			returnArray(array, node);
			newNode = rebalance(array);
		}
		return newNode;

	}
	/**
	 *rebalances the array and converts to
	 *a tree
	 */
	public Node<T> rebalance(List<Node<T>> array)
	{
		Node<T> node;
		//checks if the size of the array is 
		//1 and acts accordingly
		if(array.size() == 1)
		{
			node = array.get(0);
			node.left = null;
			node.right = null;
			sizeUpdate(node);
			heightUpdate(node);
			return node;
		}
		//checks if array is 0 and quits
		if(array.size() == 0)
		{
			return null;
		}

		//recursively calls rebalance
		int listsize = array.size();
		if(listsize % 2 == 0)
		{
			node = array.get((listsize/2)-1);
			//sizeUpdate(node);
			//heightUpdate(node);
			node.left = rebalance(array.subList(0, (listsize/2)-1));
			node.right = rebalance(array.subList(listsize/2, listsize));
		}
		else
		{
			node = array.get(listsize/2);
			node.left = rebalance(array.subList(0, listsize/2));
			node.right = rebalance(array.subList((listsize/2)+1, listsize));
		}

		sizeUpdate(node);
		heightUpdate(node);
		return node;
	}

	/**
	 * finds the minimum value by calling helper function
	 * @return
	 */
	public Node<T> findMin()
	{
		//checks if the tree is empty and throws an error
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		//calls helper function.
		return  findMinDelete(root);

	}

	/**
	 * finds the minimum
	 * @param node
	 * @return parent: the parent of the minimum node
	 */
	public Node<T> findMinDelete(Node<T> node)
	{
		if(node.left.left == null)
		{

			Node<T> temp = node.left;
			node.left = temp.right;
			sizeUpdate(node);
			return temp;

		}

		Node<T> temp = findMinDelete(node.left);
		sizeUpdate(node);
		return temp;
	}


	/**
	 * finds a particular value by calling
	 * a helper method
	 * @param x
	 * @return value found
	 */
	public T find (T x) 
	{
		if(isEmpty())
		{
			return null;
		}
		return find(x, root);
	}

	/**
	 * helper method for find
	 * @param x
	 * @param node
	 * @return value of node
	 */
	public T find(T x, Node<T> node)
	{
		if(node == null)
		{
			return null;
		}


		if(x.compareTo(node.value) < 0)
		{
			return find(x, node.left);
		}

		else if(x.compareTo(node.value) > 0)
		{
			return find(x, node.right);
		}
		else
		{
			return node.value;
		}

	}

	/**
	 * fin
	 * @param x
	 * @param y
	 * @return count: difference in the values
	 */
	public int span (T x, T y) 
	{
		ArrayList<Integer> list = new ArrayList<Integer>();

		if(root != null)
		{
			span(x, y, root, list);
		}


		return list.size();
	}

	/**
	 * helper method of span
	 * @param x
	 * @param y
	 * @param node
	 * @param list
	 */
	public void span(T x, T y, Node<T> node, ArrayList<Integer> list)
	{
		if(node.left != null && node.value.compareTo(x) > 0)
		{
			span( x, y, node.left, list);
		}

		if(node.value.compareTo(x) >= 0 && node.value.compareTo(y) <= 0)
		{
			list.add(1);
		}

		if(node.right != null && node.value.compareTo(y) < 0)
		{

			span(x, y, node.right, list);
		}


	}

	/**
	 * converts the nodes in BST to a sorted array
	 * @param array
	 * @param node
	 */
	public void returnArray(List<Node<T>> array, Node<T> node)
	{
		if(node != null)
		{
			returnArray(array, node.left);
			array.add(node);
			returnArray(array, node.right);
		}
	}
	/**
	 * prints out the tree values in order
	 */
	public void dump () 
	{
		if(isEmpty())
		{
			System.out.println("Empty tree");
		}
		else
		{
			dump(root);
		}
	}

	public void dump(Node<T> node)
	{
		if(node != null)
		{
			dump(node.left);
			System.out.println(node.value + "    Size: " + node.size + "    Height: " + node.height);
			dump(node.right);

		}
	}



	/**
	 * Node Class
	 * @author Bamidele
	 *
	 * @param <T>
	 */
	class Node<T> {

		//variables
		T value;
		Node<T> left;
		Node<T> right;
		int size;
		int height;

		public Node()
		{

		}
		//constructors
		public Node(T value)
		{
			this(value, null, null);
		}


		public Node(T value, Node<T> left, Node<T> right)
		{
			this.value = value;
			this.left = left;
			this.right = right;
			size = 1;
			height = 0;
		}

		public int size()
		{
			return size;
		}

		public String toString()
		{
			String str = "";
			str += "This node contains: " + value ;
			return str;
		}
	}
}
