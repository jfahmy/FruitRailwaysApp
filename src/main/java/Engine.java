import java.util.List;

public class Engine {
    public static void main(String[] args) {
        System.out.println("Hello world");

        RailwayGraph graph = new RailwayGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");


        graph.calculateShortestRoutesFromSource("B");
        System.out.println(graph.getTowns().get("B").getDistance());
        List<Town> path = graph.getTowns().get("A").getShortestRoute();
        for (Town t : path) {
            System.out.println(t.getTownName());
        }


    }
}
