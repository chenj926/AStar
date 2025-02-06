package plan;

import map.MapEdge;
import map.MapNode;

import java.util.*;

/**
 * A class defining planning using Best First Search
 */
public class GreedyBestFirstPlanner extends Planner {
    /**
     * heuristics used for Best First Search
     */
    Heuristic heuristic;

    /**
     * Initializer
     *
     * @param heuristic a heuristic object
     */
    public GreedyBestFirstPlanner(Heuristic heuristic) {
        super();
        this.heuristic = heuristic;
    }

    /**
     * Runs Best First Search
     *
     * @param startNode the start node
     * @param goalNode  the goal node
     * @return a list of MapNode objects
     */
    @Override
    public PlanResult plan(MapNode startNode, MapNode goalNode) {
        // find the path (next node who with best distance to goal)
        HashMap<MapNode, MapNode> parents = new HashMap<>();
        // Priority queue ordered by heuristic
        PriorityQueue<MapNode> queue = new PriorityQueue<>(
                Comparator.comparingDouble(n -> heuristic.getHeuristics(n, goalNode))
        );

        Set<MapNode> expandedNodes = new HashSet<>();

        parents.put(startNode, null);
        queue.offer(startNode);

        while(!queue.isEmpty()) {
            MapNode currNode = queue.poll();

            expandedNodes.add(currNode);

            // if found goal
            if (currNode.id == goalNode.id) {
                return new PlanResult(expandedNodes.size(), getNodeList(parents, goalNode));
            }

            for (MapEdge edge : currNode.edges) {
                MapNode nextNode = edge.destinationNode;
                // skip the parent node
                if (parents.containsKey(nextNode)) {
                    continue;
                }

//                if (cost < currBestCost) {
                    queue.add(nextNode);
                    parents.put(nextNode, currNode);
//                }
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
