package projet;

import java.util.HashMap;
import java.util.Map;

public class UnionFind<T> {
    private Map<T, Node<T>> nodes;

    public UnionFind() {
        nodes = new HashMap<>();
    }

    public void makeSet(T value) {
        Node<T> node = new Node<>(value);
        nodes.put(value, node);
    }

    public Node<T> find(Node<T> sourceRoot) {
        if (sourceRoot == null)
            return null;

        Node<T> node = nodes.get(sourceRoot.getNomVille());
        if (node == null)
            return null;

        if (!node.parent.equals(node))
            node.parent = find(node.parent);

        return node.parent;
    }

    public void union(Node<T> sourceRoot, Node<T> destRoot) {
        Node<T> root1 = find(sourceRoot);
        Node<T> root2 = find(destRoot);

        if (root1 == null || root2 == null || root1.equals(root2))
            return;

        Node<T> node1 = nodes.get(root1.getNomVille());
        Node<T> node2 = nodes.get(root2.getNomVille());

        if (node1.rank < node2.rank) {
            node1.parent = node2;
        } else if (node1.rank > node2.rank) {
            node2.parent = node1;
        } else {
            node2.parent = node1;
            node1.rank++;
        }
    }

    @Override
    public String toString() {
        return "UnionFind [nodes=" + nodes + "]";
    }
}
