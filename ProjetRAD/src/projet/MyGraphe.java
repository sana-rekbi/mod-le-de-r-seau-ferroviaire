 package projet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyGraphe<T> extends Graphe<T>{
	

	private HashMap<Node<T>, ArrayList<Edge<T>>> nodes;
	private Edge<T>[][] edges;
	private int[][]adjMatrix;
	private boolean isDirected;
	private HashMap<Node<T>, List<Edge<T>>> Sommetsadjacences;
	private int nbrdenode;
	private int  numEdges=0;

	public MyGraphe() {
	     nodes = new HashMap<>();
	     
	        adjMatrix = new int[nodes.size()][nodes.size()];
	        edges = new Edge[0][0];
	        isDirected = false;
	        Sommetsadjacences = new HashMap<Node<T>, List<Edge<T>>>();
	    }
    
	
	
	


	@Override
	public void del_edge(Graphe<T>graphe,Node<T> villeSource, Node<T> villeDestination) {
		if (graphe.getNode().contains(villeSource) && graphe.getNode().contains(villeDestination)) {
	        ArrayList<Edge<T>> edges1 = nodes.get(villeSource);
	        ArrayList<Edge<T>> edges2 = nodes.get(villeDestination);
	        ArrayList<Edge<T>> edgesToRemove1 = new ArrayList<>();
	        ArrayList<Edge<T>> edgesToRemove2 = new ArrayList<>();
	        for (Edge<T> edge : edges1) {
	            if (edge.getVilleDestination().equals(villeDestination)) {
	                edgesToRemove1.add(edge);
	            }
	        }
	        if (!edgesToRemove1.isEmpty()) {
	            edges1.removeAll(edgesToRemove1);
	            if (!isDirected) {
	                for (Edge<T> edge : edges2) {
	                    if (edge.getVilleDestination().equals(villeSource)) {
	                        edgesToRemove2.add(edge);
	                    }
	                }
	                if (!edgesToRemove2.isEmpty()) {
	                    edges2.removeAll(edgesToRemove2);
	                }
	            }
	        }
	    }
	}


	@Override
	public void del_node(Graphe<T> graphe, Node<T> ville) {
	    // Vérifier si la ville existe dans le graphe
	    if (!graphe.getNode().contains(ville)) {
	        System.out.println("La ville n'existe pas dans le graphe.");
	        return;
	    }

	    // Récupérer l'index de la ville à supprimer
	    int index = graphe.getNode().indexOf(ville);

	    // Supprimer la ville de la liste de noeuds du graphe
	    graphe.getNode().remove(ville);

	    // Supprimer les arcs connectés à cette ville dans la liste d'adjacences
	    List<Edge<T>> nodeNeighbors = Sommetsadjacences.get(ville);
	    for (Edge<T> edge : nodeNeighbors) {
	        Node<T> neighbor = (Node<T>) edge.getEnd();
	        List<Edge<T>> neighborNeighbors = Sommetsadjacences.get(neighbor);
	        Edge<T> edgeToRemove = null;
	        for (Edge<T> e : neighborNeighbors) {
	            if (e.getEnd().equals(ville)) {
	                edgeToRemove = e;
	                break;
	            }
	        }
	        neighborNeighbors.remove(edgeToRemove);
	    }
	    Sommetsadjacences.remove(ville);

	    // Mettre à jour la matrice d'adjacence
	    int[][] newAdjMatrix = new int[graphe.getNode().size()][graphe.getNode().size()];
	    int newRow = 0;
	    for (int i = 0; i < adjMatrix.length; i++) {
	        if (i == index) {
	            continue;
	        }
	        int newCol = 0;
	        for (int j = 0; j < adjMatrix[i].length; j++) {
	            if (j == index) {
	                continue;
	            }
	            newAdjMatrix[newRow][newCol] = adjMatrix[i][j];
	            newCol++;
	        }
	        newRow++;
	    }
	    adjMatrix = newAdjMatrix;
	}


	@Override
	public int get_degree(Node<T> ville) {
	    int degree = 0;
	    // Trouver les voisins du noeud dans la liste d'adjacence
	    if (Sommetsadjacences.containsKey(ville)) {
	        List<Edge<T>> nodeNeighbors = Sommetsadjacences.get(ville);
	        degree = nodeNeighbors.size();
	        for (Edge<T> edge : nodeNeighbors) {
	            if (!isDirected) {
	                Node<T> neighbor = (Node<T>) edge.getEnd();
	                List<Edge<T>> neighborNeighbors = Sommetsadjacences.get(neighbor);
	                for (Edge<T> neighborEdge : neighborNeighbors) {
	                    if (neighborEdge.getEnd().equals(ville)) {
	                        degree++;
	                        break;
	                    	}
	                	}
	           
	            	}
	   
	        	}
	        }
		return degree;
	    }



	@Override
	public int get_weight(Node<T> villeSource, Node<T> villeDestination) {
	    // Vérifie si les deux noeuds sont présents dans la liste de noeuds
	    if (! nodes.containsKey(villeSource) || !(nodes.containsKey(villeDestination))){
	        System.out.println("Erreur : un ou plusieurs noeuds n'existent pas dans la graphe.");
	        return -1;
	    }

	    // Récupère les voisins du premier noeud
	    List<Edge<T>> node1Neighbors = Sommetsadjacences.get(villeSource);

	    // Vérifie si l'arc existe dans la liste des voisins du premier noeud
	    Edge<T> edge = null;
	    for (Edge<T> e : node1Neighbors) {
	        if (e.getEnd().equals(villeDestination)) {
	            edge = e;
	            break;
	        }
	    }

	    if (edge == null) {
	        System.out.println("Erreur : l'arc n'existe pas dans la graphe.");
	        return -1;
	    }

	    // Retourne le poids de l'arc
	    return edge.getWeight();
	}


	

	@Override
	public int num_edge() {
	    int count = 0;
	    for (int i = 0; i < edges.length; i++) {
	        for (int j = 0; j < edges[i].length; j++) {
	            if (edges[i][j] != null) {
	                count++;
	            }
	        }
	    }
	    if (!isDirected) {
	        count /= 2;
	    }
	    return count;
	}
	
	
	@Override
	public void add_edge(Graphe<T> graphe, Node<T> villeSource, Node<T> villeDestination, int weight) {
	    // Vérifie si les deux noeuds sont présents dans la liste de noeuds
	    if (!graphe.getNode().contains(villeSource) || !graphe.getNode().contains(villeDestination)) {
	        System.out.println("Erreur : un ou plusieurs noeuds n'existent pas dans la graphe.");
	        return;
	    }
	    
	    // Récupère les indices correspondant aux deux noeuds dans la matrice
	    int index1 = getIndex(villeSource);
	    int index2 = getIndex(villeDestination);

	    // Vérifie si l'arc existe déjà dans la matrice d'adjacence
	    if (edges[index1][index2] != null) {
	        System.out.println("L'arc entre " + villeSource + " et " + villeDestination + " existe déjà. Mise à jour du poids.");
	        edges[index1][index2].setWeight(weight);
	        // Si le graphe est non orienté, mettre également à jour le poids de l'arc inverse
	        if (!isDirected) {
	            edges[index2][index1].setWeight(weight);
	        }
	        return;
	    }

	    // Ajoute l'arc dans la matrice avec son poids
	    edges[index1][index2] = new Edge<T>(villeSource, villeDestination, weight);
	    edges[index1][index2].setWeight(weight);
	    numEdges++;
	    // Si le graphe est non orienté, ajouter également l'arc inverse avec le même poids
	    if (!isDirected) {
	        edges[index2][index1] = new Edge<T>(villeDestination, villeSource, weight);
	        edges[index2][index1].setWeight(weight);
	        numEdges++;
	    }
	}
	private int getIndex(Node<T> node) {
	    int index = 0;
	    for (Node<T> n : nodes.keySet()) {
	        if (n.equals(node)) {
	            return index;
	        }
	        index++;
	    }
	    return -1;
	}

	@Override
	public List<Node<T>> getNode() {
	    int minDegree = Integer.MAX_VALUE;
	    List<Node<T>> nodesWithMinDegree = new ArrayList<>();

	    for (Node<T> node : nodes.keySet()) {
	        int degree = node.getDegree();
	        if (degree < minDegree) {
	            minDegree = degree;
	            nodesWithMinDegree.clear();
	            nodesWithMinDegree.add(node);
	        } else if (degree == minDegree) {
	            nodesWithMinDegree.add(node);
	        }
	    }

	    return nodesWithMinDegree;
	}


	@Override
	public Edge<T> get_lightest() {
		
				
		// TODO Auto-generated method stub
		 Edge<T> lightestArc = null;
		    int minWeight = Integer.MAX_VALUE;
		    for (Edge<T> edge : getEdge()) {
		        int weight = edge.getWeight();
		        if (weight < minWeight) {
		            lightestArc = edge;
		            minWeight = weight;
		        }
		    }
		    return lightestArc;
	}
	/**Cette méthode vérifie si les deux villes sont présentes dans le graphe, puis parcourt la liste d'adjacences de la ville source pour trouver l'arc qui se connecte à la ville de destination. 
	 * Si un tel arc est trouvé, la méthode renvoie true, sinon elle renvoie false.
	 * */
	@Override
	public boolean arc_connected(Graphe<T> graphe, Node<T> villeSource, Node<T> villeDestination) {
	    if (graphe.getNode().contains(villeSource) && graphe.getNode().contains(villeDestination)) {
	        List<Edge<T>> edges = Sommetsadjacences.get(villeSource);
	        for (Edge<T> edge : edges) {
	            if (edge.getEnd().equals(villeDestination)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}


	@Override
	public Edge<T>[] getEdge() {
	// Convertir la liste d'arêtes en un tableau d'arêtes
    Edge<T>[] edgeArray = new Edge[nbrdenode * nbrdenode]; // Le nombre maximum d'arêtes est le nombre de noeuds au carré
    int index = 0;
    for (int i = 0; i < adjMatrix.length; i++) {
        for (int j = 0; j < adjMatrix.length; j++) {
            if (edges[i][j] != null) {
                edgeArray[index] = edges[i][j];
                index++;
            }
        }
    }
	return edgeArray;
	}
	
	
	@Override

	public void add_node(Graphe<T> graphe, Node<T> ville) {
	    // Ajouter le nouveau nœud à la liste des nœuds
	    nodes.put(ville, new ArrayList<>());
	    
	    // Augmenter le nombre de nœuds dans le graphe
	    nbrdenode++;
	    
	    // Redimensionner la matrice d'adjacence pour inclure le nouveau nœud
	    Edge<T>[][] newMatrix = new Edge[nbrdenode][nbrdenode];
	    for (int i = 0; i < nbrdenode - 1; i++) {
	        for (int j = 0; j < nbrdenode - 1; j++) {
	            newMatrix[i][j] = edges[i][j];
	        }
	    }
	    edges = newMatrix;
	    
	    // Ajouter une nouvelle ligne et une nouvelle colonne à la matrice d'adjacence
	    for (int i = 0; i < nbrdenode; i++) {
	        edges[nbrdenode-1][i] = null;
	        edges[i][nbrdenode-1] = null;
	    }
	    
	    // Mettre à jour la liste des nœuds du graphe donné
	    graphe.getNode().add(ville);
	}


	@Override
	public void createGraph() {
		// TODO Auto-generated method stub
		new HashMap<Node<T>, ArrayList<Edge<T>>>();
	
	}


	
	
}