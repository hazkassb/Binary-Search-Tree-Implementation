package searchTree;

public interface SearchTree<E> {
	/**	Inserts item where it belongs in the tree. Returns true if 
	 * item is inserted, false if it isn't (already in the tree)
	 */
	boolean add(E item);
	
	
	/**	Returns true if target is found in the tree
	 * 
	 */
	boolean contains(E target);
	
	
	/**	Returns a reference to the data in the node that is equal to target. 
	 * If no such node is found, returns null
	 */
	E find(E target);
	
	
	/**	Removes target (if found) from the tree and returns it; 
	 * otherwise, returns null
	 */
	E delete(E target);
	
	
	/**	Removes target (if found) from the tree and returns true;
	 * otherwise, returns false
	 */
	boolean remove(E target);
	
}
