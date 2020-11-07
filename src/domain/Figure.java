package domain;

import utils.MatrixUtils;

import java.util.List;
import java.util.Vector;

public final class Figure {

    private List<Vertex> vertices;
    private List<Edge> edges;

    public Figure(List<Vertex> vertices, List<Edge> edges) {
        this.vertices   = vertices;
        this.edges      = edges;
    }

    public Figure() {
        this(new Vector<>(), new Vector<>());
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void transform(MatrixTransformation matrix) {
        edges.forEach(edge -> {
            double[][] matrixOrigin = MatrixUtils.multiply(
                matrix.getMatrix(),
                matrix.getMatrix().length == 3 ?
                    edge.getVertexOrigin().getMatrix() :
                    edge.getVertexOrigin().getMatrixExtended()
            );

            double[][] matrixDestiny = MatrixUtils.multiply(
                matrix.getMatrix(),
                matrix.getMatrix().length == 3 ?
                    edge.getVertexDestiny().getMatrix() :
                    edge.getVertexDestiny().getMatrixExtended()
            );

            edge.setMatrixOrigin(matrixOrigin);
            edge.setMatrixDestiny(matrixDestiny);

        });
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

}
