package interconnexion;

import java.io.Serializable;
import java.util.List;


public class Graph implements Serializable{
    private  List<Node> nodes;
    private  List<Arc> arcs;
    public Graph(List<Node> vertexes, List<Arc> edges) {
        this.nodes = vertexes;
        this.arcs = edges;
    }

    public List<Node> getVertexes() {
        return nodes;
    }

    public List<Arc> getEdges() {
        return arcs;
    }

}
