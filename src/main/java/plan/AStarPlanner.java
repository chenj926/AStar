package plan;

import map.MapEdge;
import map.MapNode;

import java.util.*;

/**
 * A class defining planning using A* search
 */
public class AStarPlanner extends Planner {
    /**
     * heuristics used for A*
     */
    Heuristic heuristic;
    /**
     * cost function used for A*
     */
    CostFunction costFunction;

    /**
     * Initializer
     *
     * @param heuristic a heuristic object
     * @param costFunction    cost function option
     */
    public AStarPlanner(Heuristic heuristic, CostFunction costFunction) {
        super();
        this.heuristic = heuristic;
        this.costFunction = costFunction;
    }

    /**
     * Runs A* search
     *
     * @param startNode the start node
     * @param goalNode  the goal node
     * @return a list of MapNode objects
     */
    @Override
    public PlanResult plan(MapNode startNode, MapNode goalNode) {
        //TODO
        HashMap<MapNode, MapNode> parents = new HashMap<>();
        // cost for g(n) and h(n)
        // g(n) cost of reach each node
        Map<MapNode, Double> gCost = new HashMap<>();

        // curr best cost of reach each node
        // priorityQueue base on the lowest cost (g(n))
        PriorityQueue<MapNode> priorityQueue = new PriorityQueue<>(
                Comparator.comparingDouble(node -> gCost.get(node) + heuristic.getHeuristics(node, goalNode))
        );

        gCost.put(startNode, 0.0);
        Set<MapNode> expandedNodes = new HashSet<>();
        parents.put(startNode, null);
        priorityQueue.offer(startNode);

        while(!priorityQueue.isEmpty()) {
            MapNode currNode = priorityQueue.poll();
            expandedNodes.add(currNode);

            // if found goal
            if (currNode.id == goalNode.id) {
                return new PlanResult(expandedNodes.size(), getNodeList(parents, goalNode));
            }

            // for each child node
            for (MapEdge edge : currNode.edges) {
                // get next node
                MapNode nextNode = edge.destinationNode;

                // get the g(n)
                // get the cost of curr node to next node
                Double newGCost = gCost.getOrDefault(currNode, Double.MAX_VALUE) + costFunction.getCost(edge);
                // previous cost for next node, if no record default +inf
                Double oldGCost = gCost.getOrDefault(nextNode, Double.MAX_VALUE);

                if(newGCost < oldGCost) {
                    gCost.put(nextNode, newGCost);
                    parents.put(nextNode, currNode);

                    // put the nextnode in pq, and since we append the calculated h(n) on g(n) when comparing
                    priorityQueue.offer(nextNode);
                }
            }
        }

        // no path found
        return new PlanResult(expandedNodes.size(), null);
    }

    /**
     * Gets the name of the planner
     *
     * @return planner name
     */
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
