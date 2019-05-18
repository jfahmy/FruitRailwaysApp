import junit.framework.TestCase;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RailwayGraphTest {
    @Rule public final ExpectedException exception = ExpectedException.none();
    protected RailwayGraph graph = new RailwayGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");


    @Test
    public void testInstanceOfRailwayGraph() {
        Assert.assertThat(graph, IsInstanceOf.instanceOf(RailwayGraph.class));
    }

    @Test
    public void testBuildTowns() {
        Map<String, Town> towns = graph.getTowns();

        Assert.assertThat(towns.get("A"), IsInstanceOf.instanceOf(Town.class));
        assertEquals(towns.size(), 5);
    }

    @Test
    public void testPopulateRoutes() {
        Town townA = graph.getTowns().get("A");
        Town townB = graph.getTowns().get("B");
        Town townE = graph.getTowns().get("E");

        TestCase.assertSame(townA.routeMap.get(townB), 5);
        TestCase.assertSame(townA.routeMap.get(townE), 7);
        assertEquals(townA.routeMap.size(), 3);
        TestCase.assertSame(townE.routeMap.get(townB), 3);
        assertEquals(townB.routeMap.size(), 1);
    }

    @Test
    public void testCalculateDistance() {
        int distanceBetweenTowns = graph.calculateDistance(new String[]{"A", "E", "B", "C", "D"});
        assertEquals(distanceBetweenTowns, 22);
    }

    @Test
    public void testCalculateDistanceThrowsException() {
        Exception thrown = assertThrows(
                NoSuchElementException.class,
                () -> graph.calculateDistance(new String[]{"A", "E", "D"}));

        assertEquals(thrown.getMessage(), "NO SUCH ROUTE");
    }

    @Test
    public void testGetTripOptions() {
        assertEquals(graph.getTripOptions("C", "C", 1, 3), 2);
        assertEquals(graph.getTripOptions("A", "C", 4, 4), 3);
    }

    @Test
    public void countAllPaths() {
        assertEquals(graph.countAllPaths("C", "C", 30), 7);
        assertEquals(graph.countAllPaths("C", "C", 50), 39);
        assertEquals(graph.countAllPaths("A", "B", 20), 6);
        assertEquals(graph.countAllPaths("A", "B", 5), 0);
        assertEquals(graph.countAllPaths("A", "A", 100), 0);
    }

    @Test
    public void getTowns() {
    }

    @Test
    public void setTowns() {
    }
}