package projet;

import java.util.List;

public class TestGraphe {

    public static void main(String[] args) {
        Entreprise<String> entreprise = new Entreprise<>();

        // Test addCity()
        Node<String> node1 = new Node<>("Paris");
        entreprise.addCity(node1);

        List<Node<String>> villes = entreprise.getVilles();
        if (villes.contains(node1)) {
            System.out.println("addCity() test passed.");
        } else {
            System.out.println("addCity() test failed.");
        }

        // Test addRail()
        Node<String> node2 = new Node<>("Lyon");
        entreprise.addCity(node2);
        entreprise.addRail(node1, node2, 100);

        Graphe<String> reseauNational = entreprise.getReseauNational();
        Graphe<String> reseauEntreprise = entreprise.getReseauEntreprise();
        if (reseauNational.getNode().contains(node1) &&
                reseauNational.getNode().contains(node2) &&
                reseauNational.getEdge().length > 0 &&
                reseauEntreprise.getNode().contains(node1) &&
                reseauEntreprise.getNode().contains(node2) &&
                reseauEntreprise.getEdge().length > 0) {
            System.out.println("addRail() test passed.");
        } else {
            System.out.println("addRail() test failed.");
        }

        // Test delRail()
        entreprise.delRail(node1, node2);

        Edge<String>[] edgesNational = reseauNational.getEdge();
        Edge<String>[] edgesEntreprise = reseauEntreprise.getEdge();
        if (reseauNational.getNode().contains(node1) &&
                reseauNational.getNode().contains(node2) &&
                edgesNational != null && edgesNational.length == 0 &&
                reseauEntreprise.getNode().contains(node1) &&
                reseauEntreprise.getNode().contains(node2) &&
                edgesEntreprise != null && edgesEntreprise.length == 0) {
            System.out.println("delRail() test passed.");
        } else {
            System.out.println("delRail() test failed.");
        }
    

    

     // Test obtenirReseauOptimal()
       Node<String> node3 = new Node<>("Marseille");
        Node<String> node4 = new Node<>("Bordeaux");
        entreprise.addCity(node3);
        entreprise.addCity(node4);
        entreprise.addRail(node1, node3, 2);
        entreprise.addRail(node1, node4, 3);
        entreprise.addRail(node2, node3, 4);
        entreprise.addRail(node2, node4, 5);
        entreprise.addRail(node3, node4, 6);

     
       

        // Calcul du réseau optimal
        Graphe<String> reseauOptimal = entreprise.Optimal(reseauNational);

        // Vérification du réseau optimal
        if (reseauOptimal.getNode().size() == 4 &&
                reseauOptimal.getNode().contains(node1) &&
                reseauOptimal.getNode().contains(node2) &&
                reseauOptimal.getNode().contains(node3) &&
                reseauOptimal.getNode().contains(node4)) {
            System.out.println("Optimal() test passed.");
        } else {
            System.out.println("Optimal() test failed.");
        }

     
        // Afficher l'arbre couvrant minimal
        System.out.println("Arbre couvrant minimal :");
        System.out.println(reseauOptimal);

    }
    
}

