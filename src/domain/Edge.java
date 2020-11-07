package domain;

public final class Edge {

    private Vertex vertexOrigin;
    private Vertex vertexDestiny;

    public Edge(Vertex vertexOrigin, Vertex vertexDestiny) {
        this.vertexOrigin   = vertexOrigin;
        this.vertexDestiny  = vertexDestiny;
    }

    public Vertex getVertexOrigin() {
        return vertexOrigin;
    }

    public Vertex getVertexDestiny() {
        return vertexDestiny;
    }

    public void setVertexOrigin(Vertex vertexOrigin) {
        this.vertexOrigin = vertexOrigin;
    }

    public void setVertexDestiny(Vertex vertexDestiny) {
        this.vertexDestiny = vertexDestiny;
    }

    public void setMatrixOrigin(double[][] matrix) {
        vertexOrigin.setX(matrix[0][0]);
        vertexOrigin.setY(matrix[1][0]);
        vertexOrigin.setZ(matrix[2][0]);
    }

    public void setMatrixDestiny(double[][] matrix) {
        vertexDestiny.setX(matrix[0][0]);
        vertexDestiny.setY(matrix[1][0]);
        vertexDestiny.setZ(matrix[2][0]);
    }
}
