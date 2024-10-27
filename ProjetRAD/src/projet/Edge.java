package projet;

public class Edge <T > implements Comparable<Edge<T>> {
private Node<T>villeSource;
private Node<T>villeDestination;
private int weight;
public Edge(Node<T> villeSource, Node<T> villeDestination,int weight) {
	this.villeSource=villeSource;
	this.villeDestination=villeDestination;
	this.weight=weight;
}



public Node<T> getVilleSource() {
	return villeSource;
}

public void setVilleSource(Node<T> villeSource) {
	this.villeSource = villeSource;
}

public Node<T>getVilleDestination() {
	return villeDestination;
}

public void setVilleDestination(Node<T>villeDestination) {
	this.villeDestination = villeDestination;
}

public int getWeight() {
	return weight;
}

public void setWeight(int weight) {
	this.weight = weight;
}

public Node<T> getEnd() {
	// TODO Auto-generated method stub
	return villeDestination;
}



@Override
public int compareTo(Edge<T> o) {
    return Integer.compare(this.weight, o.weight);
}
@Override
public String toString() {
	return "Edge [villeSource=" + villeSource + ", villeDestination=" + villeDestination + ", weight=" + weight + "]";
}











}

