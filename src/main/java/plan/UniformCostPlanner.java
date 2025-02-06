package plan;

import map.MapEdge;
import map.MapNode;

import java.util.*;

/**
 * A class defining planning using Uniform Cost Search
 */
public class UniformCostPlanner extends Planner {
    /**
     * heuristics used for Uniform Cost Search
     */
    CostFunction costFunction;

    /**
     * Initializer
     *
     * @param costFunction a costFunction object
     */
    public UniformCostPlanner(CostFunction costFunction) {
        super();
        this.costFunction = costFunction;
    }

    /**
     * Runs Uniform Cost Search
     *
     * @param startNode the start node
     * @param goalNode  the goal node
     * @return a list of MapNode objects
     */
    @Override
    public PlanResult plan(MapNode startNode, MapNode goalNode) {
        //
        HashMap<MapNode, MapNode> parents = new HashMap<>();

        // curr best cost of reach each node
        Map<MapNode, Double> currBestCost = new HashMap<>();

        // priorityQueue base on the lowest cost
        PriorityQueue<MapNode> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(currBestCost::get));
        Set<MapNode> expandedNodes = new HashSet<>();

        // put the start node in first
        currBestCost.put(startNode, 0.0);
        parents.put(startNode, null); // no parent for start
        priorityQueue.offer(startNode);

        while(!priorityQueue.isEmpty()) {
            MapNode currNode = priorityQueue.poll();

            // if this makes it graph??
//            // skip the node already visited
//            if (expandedNodes.contains(currNode)) {
//                continue;
//            }

            expandedNodes.add(currNode);  // when explored this node, add

            // if found goal
            if (currNode.id == goalNode.id) {
                return new PlanResult(expandedNodes.size(), getNodeList(parents, goalNode));
            }
            // iterate
            for (MapEdge edge : currNode.edges) {
                // to get cost for edge x
                double cost = costFunction.getCost(edge);
                MapNode nextNode = edge.destinationNode;

                // new cost for the destination node of curr node
                // get the cost of curr node to next node
                double newCost = currBestCost.getOrDefault(currNode, Double.MAX_VALUE) + cost;
                // previous cost for next node, if no record default +inf
                double oldCost = currBestCost.getOrDefault(nextNode, Double.MAX_VALUE);

                // if we found cheaper cost
                if (newCost < oldCost) {
                    currBestCost.put(nextNode, newCost);
                    parents.put(nextNode, currNode);

                    // auto rank base on cost
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
