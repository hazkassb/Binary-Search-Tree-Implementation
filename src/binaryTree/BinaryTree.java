package binaryTree;

import java.io.Serializable;
import java.util.Scanner;
import java.util.function.BiConsumer;

/**
 * 
 * @author HamzaBoubacar
 *
 *	Class to encapsulate a tree node
 * @param <E>
 */

public class BinaryTree<E> implements Serializable{
	protected static class Node<E> implements Serializable {
		//Data Fields
		/**	The information stored in this node. */
		protected E data;
		
		/**	Reference to the left child. */
		protected Node<E> left;
		
		/**	Reference to the right child. */
		protected Node<E> right;
		
		
		//Constructors
		/**	Construct a node with given data and no children */
		public Node(E data) {
			this.data = data;
			left = null;
			right = null;
		}
		
		
		//Methods
		/**	Return a string representation of the node.
		 * @return A string representation of the data fields
		 */
		public String toString() {
			return getData().toString();
		}

//		Setters and Getters
		public Node<E> getLeft() {
			return this.left;
		}
		public void setLeft(Node<E> newLeft) {
			this.left = newLeft;
		}


		public Node<E> getRight() {
			return this.right;
		}
		public void setRight(Node<E> newRight) {
			this.right = newRight;
		}


		public E getData() {
			return this.data;
		}


		/**
		 * @param data the data to set
		 */
		public void setData(E data) {
			this.data = data;
		}
		
		
	}
	
//	Data Fields
	/**	Reference to the root of the tree. */
	protected Node<E> root;
	
	
//	Constructors
	/**	Constructs an empty binary tree.	*/
	public BinaryTree() {
		root = null;
	}
	
	/**	Constructs a binary tree with the given node as the root.	*/
	protected BinaryTree(Node<E> root) {
		this.root = root;
		
	}
	
	/**	Constructs a binary tree with the given data at the root and the two given subtrees. */
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new Node<>(data);
		
		if(leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}
		
		if(rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
		
	}
	
	
//	Methods
	/**	Returns the left subtree
	 * @return The left subtree or null if either the root or the left subtree is null
	 */
	public BinaryTree<E> getLeftSubtree(){
		if(root != null && root.getLeft() != null) {
			return new BinaryTree<>(root.getLeft());
		} else {
			return null;
		}
		
	}
	
	/**	Returns the right subtree
	 * 
	 */
	public BinaryTree<E> getRightSubtree(){
		if(root != null && root.getRight() != null) {
			return new BinaryTree<>(root.getRight());
		} else {
			return null;
		}
		
	}
	
	/**	Returns the data in the root
	 * 
	 */
	public E getData() {
		return this.root.data;
		
	}
	
	/**	Returns true if this tree is a leaf, false otherwise
	 * @return tree if the root has no children
	 */
	public boolean isLeaf() {
		return (root.getLeft() == null && root.getRight() == null);
		
	}
	
	/**	Returns a String representation of the tree
	 * 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, 1, sb);
		return sb.toString();
		
	}
	
	/** Converts a subtree to a string
	 * Performs a pre-order traversal
	 * @param node The local root
	 * @param depth The depth
	 * @param sb The StringBuilder to save the output
	 */
	private void toString(Node<E> node, int depth, StringBuilder sb) {
		for(int i = 1; i < depth; i++) {
			sb.append(" ");
		}
		
		if(node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			toString(node.getLeft(), depth + 1, sb);
			toString(node.getRight(), depth + 1, sb);
		}
		
	}

	/**	Performs a pre-order traversal of the tree. Each node and its depths
	 * 	are passed to the consumer function.
	 */
	public void preOrderTraversal(BiConsumer<E, Integer> consumer) {
		preOrderTraversal(root, 1, consumer);
	}
	/**	Performs a recursive pre-order traversal of the tree, applying the action 
	 * specified in the consumer object.
	 * @param consumer object whose accept method specifies the action to be performed
	 * on each node.
	 */
	private void preOrderTraversal(Node<E> node, int depth, BiConsumer<E, Integer> consumer) {
		if(node == null) {
			consumer.accept(null, depth);
		} else {
			consumer.accept(node.getData(), depth);
			preOrderTraversal(node.getLeft(), depth + 1, consumer);
			preOrderTraversal(node.getRight(), depth + 1, consumer);
		}
	}
	
	/**	Constructs a binary tree by reading its data using Scanner scan
	 * 	pre: The input consists of a pre-order traversal of the binary tree.
	 * 	The line "null" indicates a null tree.
	 * 	@param scan The Scanner attached to the input file.
	 * 	@return A String representation of the binary tree
	 */
	public static BinaryTree<String> readBinaryTree(Scanner scan){
		//Read a line and trim leading and trailing spaces
		String data = scan.nextLine().trim();
		
		if(data.equals("null")) {
			return null;
		} else {
			BinaryTree<String> leftTree = readBinaryTree(scan);
			BinaryTree<String> rightTree = readBinaryTree(scan);
			
			return new BinaryTree<>(data, leftTree, rightTree);
			
		}
	}
	
}
