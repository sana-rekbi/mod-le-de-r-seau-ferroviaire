package projet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Entreprise<T> {
    private List<Node<T>> villes;
    private Graphe<T> reseauNational;
    private Graphe<T> reseauEntreprise;

public Entreprise() {

        villes=new ArrayList<>();
        this.reseauNational=new MyGraphe();
        this.reseauEntreprise = new MyGraphe();
    }
public List<Node<T>> getVilles() {
    return villes;
}

public void setVilles(List<Node<T>> villes) {
    this.villes = villes;
}

public Graphe<T> getReseauNational() {
    return reseauNational;
}

public void setReseauNational(Graphe<T> reseauNational) {
    this.reseauNational = reseauNational;
}

public Graphe<T> getReseauEntreprise() {
    return reseauEntreprise;
}

public void setReseauEntreprise(Graphe<T> reseauEntreprise) {
    this.reseauEntreprise = reseauEntreprise;
}







public void addCity(Node<T> ville) {
    // Ajouter la ville au réseau national
    reseauNational.add_node(reseauNational,ville);
    
    // Vérifier si la ville est déjà présente dans le réseau d'entreprise
    boolean villeExistante = false;
    for (Node<T> node : reseauEntreprise.getNode()) {
        if (node.getNomVille().equals(ville)) {
            villeExistante = true;
            break;
        }
    }
    
    // Si la ville n'est pas déjà présente dans le réseau d'entreprise, on l'ajoutera
    if (!villeExistante) {
        reseauEntreprise.add_node(reseauNational,ville);
    }

    // Ajouter la ville à la liste des villes
    villes.add(ville);
}

public void addRail(Node<T> villeSource, Node<T> villeDestination, int distance) {
    // Ajouter l'arête entre les deux villes dans les deux réseaux
   
    reseauEntreprise.add_edge(reseauNational, villeSource, villeDestination, distance);
}


public void delRail(Node<T> ville1, Node<T> ville2) {
    // Supprimer l'arête entre les deux villes des deux réseaux
    reseauEntreprise.del_edge(reseauNational,ville1, ville2);
   
}



public Graphe<T> Optimal(Graphe<T> graphe) {//utilise l'algo de kruskal pour retourner le graphe optimal(arbre coivrant minimal)
    // Créer une liste de toutes les arêtes dans le graphe
    List<Edge<T>> allEdges = new ArrayList<>();
    for (Node<T> node : graphe.getNode()) {
        for (Edge<T> edge : node.getEdges()) {
            if (!allEdges.contains(edge)) {
                allEdges.add(edge);
            }
        }
    }

    // Trier les arêtes par poids croissant
    Collections.sort(allEdges, Comparator.comparingInt(Edge::getWeight));

    // Créer un nouvel graphe contenant uniquement les nœuds du graphe d'origine
    Graphe<T> optimalGraph = new MyGraphe<>();
    for (Node<T> node : graphe.getNode()) {
        optimalGraph.add_node(graphe, node);
    }

    // Utiliser l'algorithme de Kruskal pour sélectionner les arêtes de l'arbre couvrant minimal
    UnionFind<T> unionFind = new UnionFind<>();
    for (Node<T> node : graphe.getNode()) {
        unionFind.makeSet(node.getNomVille());
    }
 

    for (Edge<T> edge : allEdges) {
        Node<T> sourceRoot = unionFind.find(edge.getVilleSource());
        Node<T> destRoot = unionFind.find(edge.getVilleDestination());

        if (!sourceRoot.equals(destRoot)) {
            optimalGraph.add_edge(graphe, edge.getVilleSource(), edge.getVilleDestination(), edge.getWeight());
            unionFind.union(sourceRoot, destRoot);
        }
    }

    return optimalGraph;
}




	  

@Override
public String toString() {
	return "Entreprise [villes=" + villes + ", reseauNational=" + reseauNational + ", reseauEntreprise="
			+ reseauEntreprise + "]";
}

}


