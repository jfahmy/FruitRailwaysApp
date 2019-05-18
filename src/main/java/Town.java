import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Town {
    private String townName;
    //private List<Town> shortestPath = new LinkedList<>();
    //private Integer distance = Integer.MAX_VALUE;
    Map<Town, Integer> routeMap = new HashMap<>();

    private List<Town> shortestRoute = new LinkedList<>();


    public List<Town> getShortestRoute() {
        return shortestRoute;
    }

    public void setShortestRoute(List<Town> shortestRoute) {
        this.shortestRoute = shortestRoute;
    }

    private Integer distance = Integer.MAX_VALUE;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void addDestination(Town destination, int distance) {
        routeMap.put(destination, distance);
    }

    public Town(String townName) {
        this.townName = townName;
    }

    public String getTownName() {
        return townName;
    }

}
