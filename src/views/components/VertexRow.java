package views.components;

import domain.Vertex;

public class VertexRow {
    private int id;
    private Vertex vertexOriginal, vertexTransformed;

    public VertexRow(int id, Vertex vertexOriginal, Vertex vertexTransformed) {
        this.id = id;
        this.vertexOriginal = vertexOriginal;
        this.vertexTransformed = vertexTransformed;
    }

    public int getId() {
        return id;
    }

    public Vertex getVertexOriginal() {
        return vertexOriginal;
    }

    public Vertex getVertexTransformed() {
        return vertexTransformed;
    }

}
