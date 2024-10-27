package projet;


	import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

	public class Graphe_Matrix<T > extends Graphe<T> {
	    
	    private List<Node<T>> nodes;
	    private int[][] matrix;
	    private Edge<T>[][]edges;
	    protected int numNode;
	    
	    public Graphe_Matrix() {
	        nodes = new ArrayList<Node<T>>();
	        matrix = new int[numNode][numNode];
	        this.edges = new Edge[numNode][numNode];
	        this.numNode=numNode;
	        
	    }
	    
	    @Override
	    
	    public void createGraph() {
	    	// TODO Auto-generated method stub
	    	
	        
	        // Initialisation de la matrice avec des zéros
	        for (int i = 0; i < numNode; i++) {
	            for (int j = 0; j <numNode; j++) {
	                matrix[i][j] = 0;
	                edges[i][j]=null;
	            }
	        }
	    }
		    
		

	    


		@Override
		public void add_edge(Graphe<T> graphe,Node<T> villeSource, Node<T> villeDestination, int weight) {
			// TODO Auto-generated method stub
			int index1 = nodes.indexOf(villeSource);
		    int index2 = nodes.indexOf(villeDestination);
		    if (index1 >= 0 && index2 >= 0) {
		        matrix[index1][index2] = weight;
		        matrix[index2][index1] = weight; 
		    }
		}

		
		@Override
		public void add_node(Graphe<T> graphe, Node<T> ville) {
			// TODO Auto-generated method stub
		    // Vérifier si le noeud n'existe pas déjà
		    if (!nodes.contains(ville)) {
		        // Ajouter le noeud à la liste des noeuds
		        nodes.add(ville);

		        // Ajouter une nouvelle ligne et une nouvelle colonne à la matrice de graphe
		        int[][] newMatrix = new int[matrix.length + 1][matrix.length + 1];
		        for (int i = 0; i < matrix.length; i++) {
		            for (int j = 0; j < matrix.length; j++) {
		                newMatrix[i][j] = matrix[i][j];
		            }
		        }
		        matrix = newMatrix;

		        // Mettre à jour les indices du noeud ajouté et des autres noeuds dans la matrice
		        int index = nodes.indexOf(ville);
		        for (int i = 0; i < nodes.size() - 1; i++) {
		            Node<T> otherNode = nodes.get(i);
		            int otherIndex = getIndex(otherNode);
		            Edge<T>[] edges = graphe.getEdge();
		            int distance = Integer.MAX_VALUE;
		            for (int j = 0; j < edges.length; j++) {
		                Edge<T> edge = edges[j];
		                if ((edge.getVilleSource().equals(ville) && edge.getVilleDestination().equals(otherNode)) ||
		                        (edge.getVilleSource().equals(otherNode) && edge.getVilleDestination().equals(ville))) {
		                    distance = edge.getWeight();
		                }
		            }
		            matrix[otherIndex][index] = distance;
		            matrix[index][otherIndex] = distance;
		        }

		        // Ajouter le noeud au graphe
		        add_node(graphe,ville);
		    }
		}


		@Override
		public boolean arc_connected(Graphe<T>graphe,Node<T> villeSource, Node<T> villeDestination) {
			// TODO Auto-generated method stub
			int index1 = getIndex(villeSource);
		    int index2 = getIndex(villeDestination);
		    return (matrix[index1][index2] != 0);
		}
		

		private int getIndex(Node<T> ville) {
		    for (int i = 0; i < nodes.size(); i++) {
		        if (nodes.get(i).equals(ville)) {
		            return i;
		        }
		    }
		    return -1; // Retourne -1 si la ville n'est pas trouvée
		}


		
		
		
		 @Override
		public void del_edge(Graphe<T>graphe,Node<T> villeSource, Node<T> villeDestination) {
			// TODO Auto-generated method stub
			int index1 = getIndex(villeSource);
			    int index2 = getIndex(villeDestination);
			    if (index1 >= 0 && index2 >= 0) {
			        if (matrix[index1][index2] != 0) {
			            matrix[index1][index2] = 0;
			        }
			    }
		}

		 @Override
		public void del_node(Graphe<T>graphe,Node<T> ville) {
			// TODO Auto-generated method stub
			int num_nodes = nodes.size();

		    int index = nodes.indexOf(ville);
		    if (index >= 0) {
		        // Suppression de la ligne correspondante
		        for (int i = index; i < num_nodes - 1; i++) {
		            for (int j = 0; j < num_nodes; j++) {
		                matrix[i][j] = matrix[i + 1][j];
		            }
		        }
		        for (int j = 0; j < num_nodes; j++) {
		            matrix[num_nodes - 1][j] = 0;
		        }

		        // Suppression de la colonne correspondante
		        for (int i = 0; i < num_nodes; i++) {
		            for (int j = index; j < num_nodes - 1; j++) {
		                matrix[i][j] = matrix[i][j + 1];
		            }
		        }
		        for (int i = 0; i < num_nodes; i++) {
		            matrix[i][num_nodes - 1] = 0;
		        }

		        nodes.remove(ville);
		    }

		}

		@Override
		
		/* * 
		 * La méthode prend en entrée un nœud(ville) de type Node et retourne le nombre d'arcs connectés à ce nœud(ville).
    	 *Elle commence par initialiser un compteur de degré à 0.
    	 *Ensuite, elle cherche l'index du nœud dans la liste nodes. Si le nœud est trouvé, elle parcourt la ligne correspondante dans la matrice de graphe et incrémente le compteur de degré pour chaque arc non nul.
    	 *Enfin, elle retourne le compteur de degré. Si le nœud(ville) n'est pas trouvé dans la liste nodes, la méthode retourne 0.
		 */
		public int get_degree(Node<T> ville) {
			int degree = 0;
		    int nodeIndex = nodes.indexOf(ville);

		    if (nodeIndex != -1) {
		        for (int i = 0; i < nodes.size(); i++) {
		            if (matrix[nodeIndex][i] != 0) {
		                degree++;
		            }
		        }
		    }

		    return degree;
		
			
		}
		/**
		 * Cette méthode get_lightest permet de récupérer l'arête de poids minimum dans le graphe représenté par la matrice d'adjacence matrix.
		 * */
		@Override
		public Edge<T> get_lightest() {
			// TODO Auto-generated method stub
			 Edge<T> lightestEdge = null;
			    int lightestWeight = Integer.MAX_VALUE;
			    for (int i = 0; i < nodes.size(); i++) {
			        for (int j = 0; j < nodes.size(); j++) {
			            if (matrix[i][j] >0 && matrix[i][j] < lightestWeight) {
			                lightestWeight = matrix[i][j];
			                lightestEdge = new Edge<T>(nodes.get(i),nodes.get(j),lightestWeight);
			            }
			        }
			    }
			    return lightestEdge;
		
		}
		
		@Override
		public int get_weight(Node<T> villeSource, Node<T> villeDestination) {
			// TODO Auto-generated method stub
			int index1=nodes.indexOf(villeSource);
			int index2=nodes.indexOf(villeDestination);
			
			return matrix[index1][index2];
		}
		
		/**Cette méthode parcourt la matrice d'adjacence de Graph_matrix et compte le nombre d'arêtes en ajoutant 1 à chaque fois qu'elle trouve un poids supérieur à zéro.
		 *  Puisque chaque arête apparaît deux fois dans la matrice (une fois pour chaque nœud), on divise le total par 2 pour obtenir le nombre réel d'arêtes.
		 * */
		@Override
		public int num_edge() {
			// TODO Auto-generated method stub
			    int numEdges = 0;
			    for (int i = 0; i < nodes.size(); i++) {
			        for (int j = 0; j < nodes.size(); j++) {
			            if (matrix[i][j] > 0) {
			                numEdges++;
			            }
			        }
			    }
			    // Pour éviter de compter deux fois chaque arête, on divise le total par 2
			    return numEdges / 2;
		}
		/**La méthode renvoie une liste de nœuds.
		 *  Elle parcourt simplement la liste de nœuds actuelle et crée une nouvelle liste des Nodes en les ajoutant un par un à l'aide de la méthode add() de l'objet ArrayList.
		 *  Cette nouvelle liste est ensuite renvoyée.
		 * */
		@Override
		public List<Node<T>> getNode() {
			// TODO Auto-generated method stub
			 List<Node<T>> nodeList = new ArrayList<Node<T>>();
			    for (Node<T> node : nodes) {
			        nodeList.add(node);
			    }
			    return nodeList;
		}
		
		
		@Override
		public Edge<T>[] getEdge() {
			// TODO Auto-generated method stub
			 List<Edge<T>> edgeList = new ArrayList<>();
			    for (int i = 0; i < nodes.size(); i++) {
			        for (int j = 0; j < nodes.size(); j++) {
			            if (matrix[i][j] > 0) {
			                edgeList.add(new Edge<>(nodes.get(i), nodes.get(j), matrix[i][j]));
			            }
			        }
			    }
			    @SuppressWarnings("unchecked")
			    Edge<T>[] edges = edgeList.toArray(new Edge[edgeList.size()]);
			    return edges;		
			    
		}

		@Override
		public String toString() {
			return "Graphe_Matrix [nodes=" + nodes + ", matrix=" + Arrays.toString(matrix) + ", edges="
					+ Arrays.toString(edges) + ", numNode=" + numNode + "]";
		}
		
	
		

		

	}
