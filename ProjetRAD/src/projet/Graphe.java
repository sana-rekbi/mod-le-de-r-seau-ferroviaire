package projet;
/*
 * les operations internes et observateurs:
 * add_node:ajoute un noeud donné au graphe, tel que ce nouveau noeud n'est par relié à aucun autre
 * @param node1:represente la  ville source
 * @param node2: represente la ville destinaire
 * @param weight: represente la cout d'exploitation de la ligné
 * add_edge:ajoute un arc  entre deux noeuds du graphe, en précisant le poids
 * si cet arc déjà present, on mettra à jour son poids
 * @param node: ville
 * arc_connecte:indique s’il existe un chemin permettant de relier deux noeuds en suivant les arcs du graphe(observateur)
 * @param node1: represente ville source
 * @param node2: represente la ville destinataire
 * del_edge:supprime un arc du graphe
 * @param node1  represente ville source
 * @param node2 represente ville destinaire
 * del_node: supprime un noeuds donné du graphe. Ceci entraine la suppression de tous les arcs y  ́etant connectés
 * @param node: represente une ville 
 * get_degree:retourne le nombre d'arcs connectés à un noeud donné
 *@param node: represente ville
 * 
 * 
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* add_node:ajoute un noeud donné au graphe, tel que ce nouveau noeud n'est par relié à aucun autre
* @param villeSource
* @param villeDestination
* @param weight: represente la cout d'exploitation de la ligné
* add_edge:ajoute un arc  entre deux noeuds du graphe, en précisant le poids
* si cet arc déjà present, on mettra à jour son poids
* @param ville
* arc_connecte:indique s’il existe un chemin permettant de relier deux noeuds en suivant les arcs du graphe(observateur)
* @param villeSource
* @param villeDestination
* del_node: supprime un noeuds donné du graphe. Ceci entraine la suppression de tous les arcs y  ́etant connectés
* @param vile
* get_degree:retourne le nombre d'arcs connectés à un noeud donné
*@param ville
* 
* 
* */
public abstract class Graphe <T >{

//Operations internee
private List<Edge<T>>Edges=new ArrayList<>();
private List<Node<T>> nodes = new ArrayList<>();
private Map<Node<T>, Integer> neighbours;
public abstract void add_edge(Graphe<T>graphe,Node<T> villeSource, Node<T> nodeDestination, int weight);
public abstract void add_node(Graphe<T>graphe,Node<T> ville);
public abstract void del_edge(Graphe<T>graphe,Node<T> villeSource, Node<T> villeDestination);
public abstract void del_node(Graphe<T>graphe,Node<T>  ville);
public abstract List<Node<T>> getNode();
public abstract Edge<T>[]getEdge();
public abstract void  createGraph();
	
	//observateurs:
public abstract boolean arc_connected(Graphe<T>graphe,Node<T> villeSource, Node<T> villeDestination);
public abstract int get_degree(Node<T> ville);
public abstract Edge<T> get_lightest();
public abstract int get_weight(Node<T> villeSource,Node<T> villeDestination);
public abstract int num_edge();
@Override
public String toString() {
	return "Graphe [Edges=" + Edges + ", nodes=" + nodes + "]";
}




}


