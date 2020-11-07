package domain;

import utils.MatrixUtils;

import java.util.List;
import java.util.Vector;

public final class Figure {

    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public Figure(List<Vertex> vertices, List<Edge> edges) {
        this.vertices   = vertices;
        this.edges      = edges;
    }

    public Figure() {
        this(new Vector<>(), new Vector<>());
    }

    public int addVertex(double x, double y, double z) {
        int id = vertices.size();
        vertices.add(new Vertex(id, x, y, z));
        return id;
    }

    private void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void makeEdge (int idVertexOrigin, int idVertexDestiny) {
        if (idVertexOrigin > vertices.size() || idVertexDestiny > vertices.size()) {
            return;
        }

        addEdge(new Edge(
            this.vertices.get(idVertexOrigin),
            this.vertices.get(idVertexDestiny)
        ));
    }

    public void transform(MatrixTransformation matrix) {
        vertices.forEach(vertex -> {
            double[][] matrixResult = MatrixUtils.multiply(
                matrix.getMatrix(),
                matrix.getMatrix().length == 3 ?
                    vertex.getMatrix() :
                    vertex.getMatrixExtended()
            );
            vertex.setMatrix(matrixResult);
        });
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

}
