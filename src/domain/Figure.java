package domain;

import utils.MatrixUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
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

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
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

    @Override
    public Figure clone() {
        Figure figure = new Figure();

        getVertices().forEach(vertex -> figure.addVertex(
            vertex.getX(),
            vertex.getY(),
            vertex.getZ()
        ));

        getEdges().forEach(edge -> {
            figure.makeEdge(
                edge.getVertexOrigin().getId(),
                edge.getVertexDestiny().getId()
            );
        });

        return figure;
    }

    public void transform(MatrixTransformation matrix) {
        vertices.forEach(vertex -> {
            double[][] matrixResult = MatrixUtils.multiply(
                matrix.getMatrix(),
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
