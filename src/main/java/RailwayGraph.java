
import java.util.*;

public class RailwayGraph {
    // Using a map instead of set - shouldn't use objects as sets because those objects are mutable
    private Map<String,Town> towns = new HashMap<>();

    public RailwayGraph(String input){
        setTowns(input);
    }

    public Map<String, Town> getTowns() {
        return towns;
    }

    public void setTowns(String input) {
        String[] inputRoutes = input.split(", ");
        String[][] routes = new String[inputRoutes.length][3];
        for (int i=0; i < inputRoutes.length; i++) {
            routes[i] = inputRoutes[i].split("");
            // creating town objects and adding them to our graph
            Town t = new Town(routes[i][0]);
            //System.out.println(t.getTownName());
            towns.put(routes[i][0], t);
        }
        populateRoutes(routes);
    }

    // looping through and creating the destination connections w/ related distance
    protected void populateRoutes(String[][] routes) {
        for (String[] route : routes) {
            Town startLocation = towns.get(route[0]);
            Town destination = towns.get(route[1]);
            startLocation.addDestination(destination, Integer.parseInt(route[2]));
        }
    }

    public int calculateDistance(String[] route) {
        Town currentTown = towns.get(route[0]);
        int tripDistance = 0;
        for (int i=1; i < route.length; i++) {
            if (currentTown != null) {
                Town currentDestination = towns.get(route[i]);
                if (currentTown.routeMap.containsKey(currentDestination)) {
                    tripDistance += currentTown.routeMap.get(currentDestination);
                    currentTown = currentDestination;
                } else {
                    throw new NoSuchElementException("NO SUCH ROUTE");
                }
            }
        }
        return tripDistance;
    }

    public int getTripOptions(String tripStart, String tripEnd, int minStops, int maxStops) {
        Town startTown = towns.get(tripStart);
        Town endTown = towns.get(tripEnd);
        int validTrips = 0;
        if (startTown != null && endTown != null) {
            validTrips = getTripOptions(startTown, endTown, minStops, maxStops, 0, validTrips);
        }
        return validTrips;
    }

    private int getTripOptions(Town currentTown, Town tripEnd, int minStops, int maxStops, int stopsSoFar, int validTrips) {
        if (stopsSoFar > 0 && currentTown == tripEnd && stopsSoFar <= maxStops && stopsSoFar >= minStops) {
            return 1;
        } else if (stopsSoFar >= maxStops) {
            return 0;
        }

        for (Town t : currentTown.routeMap.keySet()) {
            validTrips += getTripOptions(t, tripEnd, minStops, maxStops, stopsSoFar+1, 0);
        }
        return validTrips;
    }


    public int countAllPaths(String tripStart, String tripEnd, int maxTravelDistance) {
        return countAllPaths(towns.get(tripStart), towns.get(tripEnd), maxTravelDistance, 0,0);
    }

    private int countAllPaths(Town current, Town end, int max, int currDist, int pathCount) {
        if (currDist > 0 && current.equals(end)) { pathCount += 1; }

        for (Town t : current.routeMap.keySet()) {
            int distanceToNextTown = current.routeMap.get(t);
            if (currDist + distanceToNextTown < max) {
                pathCount = countAllPaths(t, end, max, currDist + distanceToNextTown, pathCount);
            }

        }
        return pathCount;
    }

    public void calculateShortestRoutesFromSource(String source) {
        Town startTown = towns.get(source);
        startTown.setDistance(0);

        Set<Town> settledTowns = new HashSet<>();
        Set<Town> unsettledTowns = new HashSet<>();

        unsettledTowns.add(startTown);

        while (unsettledTowns.size() != 0) {
            Town currentTown = getLowestDistanceTown(unsettledTowns);
            unsettledTowns.remove(currentTown);
            for (Town t : currentTown.routeMap.keySet()) {
                int distance = currentTown.routeMap.get(t);
                if (!settledTowns.contains(t)) {
                    calculateMinimumDistance(t, distance, currentTown);
                    unsettledTowns.add(t);
                }
            }
            settledTowns.add(currentTown);
        }
    }

    private static Town getLowestDistanceTown(Set < Town > unsettledTowns) {
        Town lowestDistanceTown = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Town Town: unsettledTowns) {
            int TownDistance = Town.getDistance();
            if (TownDistance < lowestDistance) {
                lowestDistance = TownDistance;
                lowestDistanceTown = Town;
            }
        }
        return lowestDistanceTown;
    }

    private static void calculateMinimumDistance(Town evaluationTown,
                                                 Integer edgeWeigh, Town sourceTown) {
        Integer sourceDistance = sourceTown.getDistance();
        if (sourceDistance + edgeWeigh < evaluationTown.getDistance()) {
            evaluationTown.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Town> shortestRoute = new LinkedList<>(sourceTown.getShortestRoute());
            shortestRoute.add(sourceTown);
            evaluationTown.setShortestRoute(shortestRoute);
        }
    }


}

