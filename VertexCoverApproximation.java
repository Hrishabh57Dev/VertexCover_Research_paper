import java.util.*;

public class VertexCoverApproximation {

    public static Set<String> findApproximateVertexCover(Map<String, List<String>> graph) {
        Set<String> vertexCover = new HashSet<>();
        Set<String> edges = new HashSet<>();

        // Initialize the set of edges
        for (String vertex : graph.keySet()) {
            edges.addAll(graph.get(vertex));
        }

        while (!edges.isEmpty()) {
            // Choose an edge (u, v) from the remaining edges
            String u = null;
            String v = null;
            for (String edge : edges) {
                u = edge.substring(0, 1);
                v = edge.substring(1);
                break;
            }

            // Add its endpoints to the vertex cover
            vertexCover.add(u);
            vertexCover.add(v);

            // Remove all edges incident to u or v from the remaining edges
            edges.removeAll(graph.get(u));
            edges.removeAll(graph.get(v));
        }

        return vertexCover;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C", "E"));
        graph.put("B", Arrays.asList("A", "E"));
        graph.put("C", Arrays.asList("A", "E"));
        graph.put("D", Arrays.asList("E"));
        graph.put("E", Arrays.asList("A", "B", "C", "D"));

        Set<String> approxVertexCover = findApproximateVertexCover(graph);
        System.out.println("Approximate Vertex Cover: " + approxVertexCover);
    }
}
