import java.util.*;

public class VertexCoverExact {

    private static int minVertexCoverSize = Integer.MAX_VALUE;

    public static void branchAndBound(Map<String, List<String>> graph, Set<String> currentVertexCover) {
        // Calculate the lower bound (number of edges not covered by vertices in the current cover)
        int lowerBound = calculateLowerBound(graph, currentVertexCover);

        // Prune if the lower bound exceeds the current best solution
        if (lowerBound >= minVertexCoverSize) {
            return;
        }

        // If all edges are covered, update the minimum vertex cover size
        if (graph.isEmpty()) {
            minVertexCoverSize = Math.min(minVertexCoverSize, currentVertexCover.size());
            return;
        }

        // Select an edge (u, v) to branch on
        String u = graph.keySet().iterator().next();
        String v = graph.get(u).get(0);

        // Explore subproblem including vertex u
        Map<String, List<String>> subgraphU = new HashMap<>(graph);
        subgraphU.remove(u);
        for (List<String> edges : subgraphU.values()) {
            edges.remove(u);
        }
        Set<String> newCoverU = new HashSet<>(currentVertexCover);
        newCoverU.add(u);
        branchAndBound(subgraphU, newCoverU);

        // Explore subproblem including vertex v
        Map<String, List<String>> subgraphV = new HashMap<>(graph);
        subgraphV.remove(v);
        for (List<String> edges : subgraphV.values()) {
            edges.remove(v);
        }
        Set<String> newCoverV = new HashSet<>(currentVertexCover);
        newCoverV.add(v);
        branchAndBound(subgraphV, newCoverV);
    }

    private static int calculateLowerBound(Map<String, List<String>> graph, Set<String> currentVertexCover) {
        int uncoveredEdges = 0;
        for (List<String> edges : graph.values()) {
            for (String edge : edges) {
                if (!currentVertexCover.contains(edge)) {
                    uncoveredEdges++;
                }
            }
        }
        // Divide by 2 because each edge is counted twice
        return uncoveredEdges / 2;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C", "E"));
        graph.put("B", Arrays.asList("A", "E"));
        graph.put("C", Arrays.asList("A", "E"));
        graph.put("D", Arrays.asList("E"));
        graph.put("E", Arrays.asList("A", "B", "C", "D"));

        Set<String> initialCover = new HashSet<>();
        branchAndBound(graph, initialCover);

        System.out.println("Minimum Vertex Cover Size: " + minVertexCoverSize);
    }
}
