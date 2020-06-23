package searchTree;

import binaryTree.BinaryTree;

public class BinarySearchTree<E extends Comparable<E>> 
				extends BinaryTree<E> implements SearchTree<E> {
//	Data Fields
	/**	Stores a second return value from the recursive add 
	 * method that indicates whether the item has been inserted
	 * @return
	 */
	protected boolean addReturn;
	
	
	/**	Stores a second return value from the recursive delete method 
	 * that references the item that was stored in the tree
	 */
	protected E deleteReturn;


	public boolean add(E item) {
		root = add(root, item);
		return addReturn;
	}
	/** Recursive add method
	 * 
	 */
	private Node<E> add(Node<E> localRoot, E item){
		if(localRoot == null) {
			// item is not in the tree; therefore, insert it
			addReturn = true;
			return new Node<>(item);
		} else if(item.compareTo(localRoot.getData()) == 0) {
			// item is equal to localRoot.getData()
			addReturn = false;
			return localRoot;
		} else if(item.compareTo(localRoot.getData()) < 0){
			// item is less than localRoot.getData()
			localRoot.setLeft(add(localRoot.getLeft(), item)); //= add(localRoot.getLeft(), item);
			return localRoot;
		} else {
			// item is greater than localRoot.data
			localRoot.setRight(add(localRoot.getRight(), item));  		//add(localRoot.getRight(), item);
			return localRoot;
		}
	}


	
	
	public boolean contains(E target) {
		// TODO Auto-generated method stub
		return false;
	}


	public E find(E target) {
		return (E) find(root, target);
	}
	/**	Recursive find method
	 * 
	 */
	private E find(Node<E> localRoot, E target) {
		if(localRoot == null) {
			return null;
		}
		
		// Compare the target with the data field at the root.
		int compResult = target.compareTo(localRoot.getData());
		if(compResult == 0) {
			return localRoot.getData();
		} else if(compResult < 0) {
			return find(localRoot.getLeft(), target);
		} else {
			return find(localRoot.getRight(), target);
		}
	}


	
	/**	Starter method delete
	 *	post: The object is not in the tree.
	 * @param target The object to be deleted
	 * @return The object deleted from the tree
	 * 			or null if the object is not in the tree
	 * @throws ClassCastException if the target does not implement Comparable
	 */
	public E delete(E target) {
		root = delete(root, target);
		return deleteReturn;
	}
	
	//Recursive delete method.
	/**	post: The item is not in the tree.
	 * 		deleteReturn is equal to the deleted item as it was stored in the tree
	 * 		or null if the item was not found
	 * @param localRoot The root of the current subtree
	 * @param item the item to be deleted
	 * @return The modified local root that does not contain the item
	 */
	private Node<E> delete(Node<E> localRoot, E item){
		if(localRoot == null) {
			//item is not in the tree
			deleteReturn = null;
			return localRoot;
		}
		
		//Search for item to delete
		int compResult = item.compareTo(localRoot.getData());
		if(compResult < 0) {
			//item is smaller than localRoot.data
			localRoot.setLeft(delete(localRoot.getLeft(), item));
			return localRoot;
		} else if(compResult > 0){
			//item is larger than localRoot.data
			localRoot.setRight(delete(localRoot.getRight(), item));
			return localRoot;
		} else {
			//item is at local root
			if(localRoot.getLeft() == null) {
				//If there is no left child, return right child
				//which can also be null.
				return localRoot.getRight();
			} else if(localRoot.getRight() == null) {
				//If there is no right child, return left child
				return localRoot.getLeft();
			} else {
				//Node being deleted has 2 children, replace data with 
				//inorder predecessor
				if(localRoot.getLeft().getRight() == null) {
					//The left child has no right child.
					//Replace the data with the data in the left child
					localRoot.setData(localRoot.getLeft().getData());
					//Replace the left child with its left child
					localRoot.setLeft(localRoot.getLeft().getLeft());
					return localRoot;
				} else {
					//Search for the inner predecessor (ip) and
					//replace deleted node's data with ip
					localRoot.setData(findLargestChild(localRoot.getLeft()));
					return localRoot;
				}
				
			}
		}
	}
	/**	Find the node that is the inorder predecessor and replace it with its
	 * 	left child (if any).
	 * 	@post: The inorder predecessor is removed from the tree.
	 * 	@param parent The parent of possible inorder predecessor (ip)
	 * 	@return The data in the ip
	 */
	private E findLargestChild(Node<E> parent) {
		//If the right child has no right child, it is the inorder predecessor.
		if(parent.getRight().getRight() == null) {
			E returnValue = parent.getRight().getData();
			parent.setRight(parent.getRight().getLeft());
			return returnValue;
		} else {
			return findLargestChild(parent.getRight());
		}
	}
	@Override
	public boolean remove(E target) {
		// TODO Auto-generated method stub
		return false;
	}

}
