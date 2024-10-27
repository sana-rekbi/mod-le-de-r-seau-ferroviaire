package projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node<T> {
    private T nomVille;
    private Map<Node<T>, Edge<T>> neighbours;
    private List<Edge<T>> edges;
    Node<T> parent;
    int rank;

    public Node(T nom) {
        this.nomVille = nom;
        this.neighbours = new HashMap<>();
        this.edges = new ArrayList<>();
        this.parent = this;
        this.rank = 0;
    }

    public T getNomVille() {
        return nomVille;
    }

    public void setNomVille(T nomVille) {
        this.nomVille = nomVille;
    }

    public Map<Node<T>, Edge<T>> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Map<Node<T>, Edge<T>> neighbours) {
        this.neighbours = neighbours;
    }

    public List<Edge<T>> getEdges() {
        return new ArrayList<>(edges);
    }

    public int getDegree() {
        return edges.size();
    }

    @Override
    public String toString() {
        return nomVille.toString(); // Utilisez la représentation en chaîne de caractères de la ville
    }
}

