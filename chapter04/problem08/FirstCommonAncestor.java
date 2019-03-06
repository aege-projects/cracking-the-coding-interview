public class FirstCommonAncestor {
	class Node {
		int value;
		Node left = null, right = null;

		public Node(int v) { this.value = v; }
	
		public boolean isAncestorOf(Node node) {
			if (node == null) {
				return false;
			}

			if (node == this) {
				return true;
			}

			return isAncestorOf(left) || isAncestorOf(right);
		}
	}

	Node findFCA(Node root, Node a, Node b) {
		if (a.isAncestorOf(b)) {
			return a;
		}

		if (b.isAncestorOf(a)) {
			return b;
		}

		return dfsFCA(root, a, b);
	}

	Node dfsFCA(Node node, Node a, Node b) {
		if (node == null) {
			return null;
		}

		if (node == a) {
			return a;
		}

		if (node == b) {
			return b;
		}

		Node l = dfsFCA(node.left, a, b);
		Node r = dfsFCA(node.right, a, b);
		if (l == null && r == null) {
			return null;
		} else if (l != null && r == null) {
			return l;
		} else if (l == null && r != null) {
			return r;
		} else {
			return node;
		}

	}
	
}
