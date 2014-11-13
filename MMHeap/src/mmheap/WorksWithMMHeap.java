/**
 * TODO Class description
 *Class Invariants:
 *
 * @author BamideleSuarau
 * @version Oct 28, 2013
 * @project CMSC 202 - Spring 2012 - Project #
 * @section #
 *
 */
package mmheap;

/**
 * @author Bamidele
 *
 */
public interface WorksWithMMHeap<T> {

	public T newObject();
	public T [] newArray(int n) ;
	public int compare(T n1, T n2)  ;
}
