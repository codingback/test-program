import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Tree<T> {

	private T head;

	private ArrayList<Tree<T>> leafs = new ArrayList<Tree<T>>();

	private Tree<T> parent = null;

	private int size = 0;

	private HashMap<T, Tree<T>> locate = new HashMap<T, Tree<T>>();

	public Tree(T head) {
		this.head = head;
		locate.put(head, this);
		size++;
	}

	public int size() {
		return size;
	}

	public void addLeaf(T root, T leaf) {
		if (locate.containsKey(root)) {
			locate.get(root).addLeaf(leaf);
		} else {
			addLeaf(root).addLeaf(leaf);
		}

	}

	public void oneMoreChild() {
		size++;
	}

	public Tree<T> addLeaf(T leaf) {
		Tree<T> t = new Tree<T>(leaf);
		leafs.add(t);
		t.parent = this;
		t.locate = this.locate;
		locate.put(leaf, t);

		Tree<T> parentNode = this;
		while (parentNode != null) {
			parentNode.oneMoreChild();
			parentNode = parentNode.getParent();
		}
		return t;
	}

	public Tree<T> setAsParent(T parentRoot) {
		Tree<T> t = new Tree<T>(parentRoot);
		t.leafs.add(this);
		this.parent = t;
		t.locate = this.locate;
		// t.locate.put(head, this);
		t.locate.put(parentRoot, t);
		return t;
	}

	public void setAsParent(Tree<T> parrentTreeNode) {
		parrentTreeNode.leafs.add(this);
		this.parent = parrentTreeNode;
		parrentTreeNode.locate = this.locate;
		// parrentTreeNode.locate.put(head, this);
		parrentTreeNode.locate.put(parrentTreeNode.head, parrentTreeNode);

		Tree<T> tranParentNode = parrentTreeNode.getParent();
		Tree<T> currentNode = parrentTreeNode;
		while (tranParentNode != null) {

			tranParentNode.locate = currentNode.locate;
			tranParentNode.locate.put(tranParentNode.head, tranParentNode);
			currentNode = tranParentNode;
			tranParentNode = tranParentNode.getParent();
		}

	 }

	public T getHead() {
		return head;
	}

	public Tree<T> getTree(T element) {
		return locate.get(element);
	}

	public Tree<T> getParent() {
		return parent;
	}

	public Collection<T> getSuccessors(T root) {
		Collection<T> successors = new ArrayList<T>();
		Tree<T> tree = getTree(root);
		if (null != tree) {
			for (Tree<T> leaf : tree.leafs) {
				successors.add(leaf.head);
			}
		}
		return successors;
	}

	public Collection<Tree<T>> getSubTrees() {
		return leafs;
	}

	public static <T> Collection<T> getSuccessors(T of, Collection<Tree<T>> in) {
		for (Tree<T> tree : in) {
			if (tree.locate.containsKey(of)) {
				return tree.getSuccessors(of);
			}
		}
		return new ArrayList<T>();
	}

	@Override
	public String toString() {
		return "[" + printTree() + "]";
	}

	// private static final int indent = 2;

	public String printTree() {
		String s = "{\"head\": " + head.toString();

		if (leafs.size() > 0) {
			s += ", \"leafs\":[";
		}
		for (int i = 0; i < leafs.size(); i++) {
			s += leafs.get(i).toString();

			if (leafs.size() - i > 1)
				s += ",";

		}
		if (leafs.size() > 0) {
			s += "]";
		}
		return s += "}";
	}

	public static void main(String[] args) {
		Tree<String> testTree = new Tree<String>("root");
		Tree<String> newTree = new Tree<String>("newRoot");

		testTree.addLeaf("root", "node1");
		testTree.addLeaf("root", "node2");

		newTree.addLeaf("node3");
		newTree.addLeaf("node3", "node4");



		// System.out.println(testTree.printTree());

		// System.out.println(newTree.printTree());


		testTree.setAsParent(newTree.getTree("node3"));

		// System.out.println(newTree.printTree());

		newTree.addLeaf("test");

		System.out.println(newTree.getTree("node1").toString());

	}
}